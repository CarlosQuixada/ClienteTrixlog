package br.trixlog.carlos.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.trixlog.carlos.client.model.Usuario;
import br.trixlog.carlos.client.service.UsuarioService;

@Controller
@RequestMapping(value="/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value="/cadastrarFuncionarioForm")
	public String cadastrarFuncionarioForm(){
		return "usuario/cadastrarFuncionarioForm";
	}
	
	@RequestMapping(value="/cadastrarFuncionario")
	public String cadastrarFuncionario(Usuario usuario){
		usuarioService.cadastrarFuncionario(usuario);
		return "home";
	}
	
	@RequestMapping(value="/logar")
	public String logar(@RequestParam String user, @RequestParam String senha){
		Usuario usuario = new Usuario();
		usuario.setUser(user);
		usuario.setSenha(senha);
		Usuario logado = usuarioService.logar(usuario);
		
		return "home"; 
	}
}
