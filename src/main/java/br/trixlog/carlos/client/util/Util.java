package br.trixlog.carlos.client.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.opencsv.CSVReader;

import br.trixlog.carlos.client.model.Coordenada;
import br.trixlog.carlos.client.model.Parada;
@Service
public class Util {
	public List<Coordenada> getCoordenadas(String dataBase){
		List<Coordenada> dataSet = new ArrayList<>();
		try {
			CSVReader reader = new CSVReader(new FileReader("src/main/resources/static/arquivos/" + dataBase));
			String[] nextLine;
			
			try {
				while ((nextLine = reader.readNext()) != null) {
					Coordenada coord = new Coordenada();
					coord.setLat(Double.parseDouble(nextLine[0]));
					coord.setLng(Double.parseDouble(nextLine[1]));
					dataSet.add(coord);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return dataSet;
	}
	
	public String gerarJsonParadas(List<Parada> paradas){
		Gson json = new Gson();
		String stops = json.toJson(paradas);
		return stops;
	}
}
