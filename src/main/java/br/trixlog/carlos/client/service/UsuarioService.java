package br.trixlog.carlos.client.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.trixlog.carlos.client.model.Usuario;

@Service
public class UsuarioService {
	public static final String REST_SERVICE_URI = "http://localhost:9090/api/trixlog/usuarios";
	public void cadastrarFuncionario(Usuario usuario){
	        RestTemplate restTemplate = new RestTemplate();
	        Usuario usu = restTemplate.postForObject(REST_SERVICE_URI, usuario, Usuario.class);
	        System.out.println(usu.getId());
	}
	
	public Usuario logar(Usuario usuario){
		RestTemplate restTemplate = new RestTemplate();
	    Usuario logado = restTemplate.postForObject(REST_SERVICE_URI+"/logar",usuario, Usuario.class);
	    return logado;
	}
}
