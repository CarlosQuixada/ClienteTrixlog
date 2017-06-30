package br.trixlog.carlos.client.controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.trixlog.carlos.client.model.Coordenada;
import br.trixlog.carlos.client.model.Rota;
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

	@RequestMapping(value = "/gerarRota")
	public String gerarRota(@RequestParam MultipartFile pontos, @RequestParam Integer vehicleId) {
		Util util = new Util();
		if (!pontos.isEmpty()) {
			try {
				Files.copy(pontos.getInputStream(),
						Paths.get("src/main/resources/static/arquivos", pontos.getOriginalFilename()));

				List<Coordenada> coordenadas = util.getCoordenadas(pontos.getOriginalFilename());

				Rota rota = rotaService.gerarRota(coordenadas, vehicleId);

			} catch (Exception e) {
				List<Coordenada> coordenadas = util.getCoordenadas(pontos.getOriginalFilename());
				Rota rota = rotaService.gerarRota(coordenadas, vehicleId);
			}
		}
		return "home";
	}
}
