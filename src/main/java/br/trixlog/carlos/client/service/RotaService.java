package br.trixlog.carlos.client.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.trixlog.carlos.client.model.Coordenada;
import br.trixlog.carlos.client.model.ListaRota;
import br.trixlog.carlos.client.model.Rota;

@Service
public class RotaService {
	public static final String REST_SERVICE_URI = "http://localhost:9090/api/trixlog/rotas";
	
	public Rota gerarRota(List<Coordenada> coordenadas,Integer vehicleId,String empresaId){
		RestTemplate restTemplate = new RestTemplate();
		Rota rota = new Rota();
		rota.setPath(coordenadas);
		rota.setVehicleId(vehicleId);
		rota.setName(empresaId);
	    Rota rotaResult = restTemplate.postForObject(REST_SERVICE_URI+"/gerarRota",rota,Rota.class);
		return rotaResult;
	}
	
	public ListaRota buscarRotasEmpresa(String empresaId){
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
	    params.put("empresaId",empresaId);
		ListaRota listaRotas = restTemplate.getForObject(REST_SERVICE_URI+"/buscarRotasByEmpresa/{empresaId}",ListaRota.class, params);
		return listaRotas;
	}
	
	public Rota getRota(String rotaId){
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
	    params.put("rotaId",rotaId);
		Rota rota = restTemplate.getForObject(REST_SERVICE_URI+"/getRota/{rotaId}",Rota.class, params);
		return rota;
	}
}
