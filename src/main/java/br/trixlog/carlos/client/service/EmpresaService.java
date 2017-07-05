package br.trixlog.carlos.client.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.trixlog.carlos.client.model.ListaEmpresa;

@Service
public class EmpresaService {
	public static final String REST_SERVICE_URI = "http://localhost:9090/api/trixlog/empresas";
	public ListaEmpresa getAllEmpresa(){
		RestTemplate restTemplate = new RestTemplate();
		ListaEmpresa listaEmpresa = restTemplate.getForObject(REST_SERVICE_URI,ListaEmpresa.class);
		return listaEmpresa;
	}
}
