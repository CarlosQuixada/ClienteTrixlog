package br.trixlog.carlos.client.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.trixlog.carlos.client.model.ListaEmpresa;
import br.trixlog.carlos.client.model.Papel;
import br.trixlog.carlos.client.model.Usuario;
import br.trixlog.carlos.client.service.EmpresaService;
import br.trixlog.carlos.client.service.UsuarioService;

@Controller
public class RootController {
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private EmpresaService empresaService;
	
	@RequestMapping(value="/")
	public String index(){
		return "index";
	}
	
	@RequestMapping(value="/cadastrarFuncionarioFormAdmin")
	public String cadastrarFuncionarioFormAdmin(Model model){
		ListaEmpresa listaEmpresa = empresaService.getAllEmpresa();
		model.addAttribute("RH",Papel.RH);
		model.addAttribute("LOGISTICA",Papel.LOGISTICA);
		model.addAttribute("empresas", listaEmpresa.getEmpresas());
		return "usuario/cadastrarFuncionarioFormAdmin";
	}
	
	@RequestMapping(value="/cadastrarFuncionarioAdmin")
	public String cadastrarFuncionarioAdmin(Usuario usuario,HttpSession session,Model model){
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

		usuarioService.cadastrarFuncionario(usuario);
		
		model.addAttribute("papel",usuarioLogado.getPapel());
		model.addAttribute("RH",Papel.RH);
		model.addAttribute("LOGISTICA",Papel.LOGISTICA);
		model.addAttribute("ADMIN",Papel.ADMIN);
		return "home";
	}
}
