package br.trixlog.carlos.client.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.trixlog.carlos.client.model.Papel;
import br.trixlog.carlos.client.model.Usuario;
import br.trixlog.carlos.client.service.UsuarioService;

@Controller
@RequestMapping(value="/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value="/cadastrarFuncionarioForm")
	public String cadastrarFuncionarioForm(Model model){
		model.addAttribute("RH",Papel.RH);
		model.addAttribute("LOGISTICA",Papel.LOGISTICA);
		return "usuario/cadastrarFuncionarioForm";
	}
	
	@RequestMapping(value="/cadastrarFuncionario")
	public String cadastrarFuncionario(Usuario usuario,HttpSession session,Model model){
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		usuario.setEmpresaId(usuarioLogado.getEmpresaId());
		usuarioService.cadastrarFuncionario(usuario);
		
		model.addAttribute("papel",usuarioLogado.getPapel());
		model.addAttribute("RH",Papel.RH);
		model.addAttribute("LOGISTICA",Papel.LOGISTICA);
		model.addAttribute("ADMIN",Papel.ADMIN);
		return "home";
	}
	
	@RequestMapping(value="/logar")
	public String logar(@RequestParam String login, @RequestParam String senha,HttpSession session,Model model){
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		Usuario logado = usuarioService.logar(usuario);
		if(logado == null){
			return "index";
		}
		session.setAttribute("usuarioLogado",logado);
		model.addAttribute("papel",logado.getPapel());
		model.addAttribute("RH",Papel.RH);
		model.addAttribute("LOGISTICA",Papel.LOGISTICA);
		model.addAttribute("ADMIN",Papel.ADMIN);
		return "home"; 
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "index";
	}
}
