package br.trixlog.carlos.client.controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.trixlog.carlos.client.model.Coordenada;
import br.trixlog.carlos.client.model.ListaRota;
import br.trixlog.carlos.client.model.Rota;
import br.trixlog.carlos.client.model.Usuario;
import br.trixlog.carlos.client.service.RotaService;
import br.trixlog.carlos.client.util.Util;

@Controller
@RequestMapping(value = "/rota")
public class RotaController {
	@Autowired
	private RotaService rotaService;

	@RequestMapping(value = "/gerarRotaForm")
	public String gerarRotaForm() {
		return "rota/gerarRotaForm";
	}
	@RequestMapping(value="/listarRota")
	public String listarRota(HttpSession session,Model model){
		Usuario usu = (Usuario)session.getAttribute("usuarioLogado");
		ListaRota listaRotas = rotaService.buscarRotasEmpresa(usu.getEmpresaId());
		model.addAttribute("rotas",listaRotas.getRotas());
		return "rota/listarRota";
	}
	
	@RequestMapping(value = "/gerarRota")
	public String gerarRota(@RequestParam MultipartFile pontos, @RequestParam Integer vehicleId,HttpSession session) {
		Util util = new Util();
		Usuario usuarioLogado = (Usuario)session.getAttribute("usuarioLogado");
		if (!pontos.isEmpty()) {
			try {
				Files.copy(pontos.getInputStream(),
						Paths.get("src/main/resources/static/arquivos", pontos.getOriginalFilename()));

				List<Coordenada> coordenadas = util.getCoordenadas(pontos.getOriginalFilename());

				Rota rota = rotaService.gerarRota(coordenadas, vehicleId,usuarioLogado.getEmpresaId());

			} catch (Exception e) {
				List<Coordenada> coordenadas = util.getCoordenadas(pontos.getOriginalFilename());
				Rota rota = rotaService.gerarRota(coordenadas, vehicleId,usuarioLogado.getEmpresaId());
			}
		}
		return "home";
	}
}
