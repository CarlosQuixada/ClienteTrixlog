package br.trixlog.carlos.client.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.trixlog.carlos.client.model.Coordenada;
import br.trixlog.carlos.client.model.Rota;

@Service
public class RotaService {
	public static final String REST_SERVICE_URI = "http://localhost:9090/api/trixlog/rotas";
	
	public Rota gerarRota(List<Coordenada> coordenadas,Integer vehicleId){
		RestTemplate restTemplate = new RestTemplate();
		Rota rota = new Rota();
		rota.setPath(coordenadas);
		rota.setVehicleId(vehicleId);
	    Rota rotaResult = restTemplate.postForObject(REST_SERVICE_URI+"/gerarRota",rota,Rota.class);
		return rotaResult;
	}
}
