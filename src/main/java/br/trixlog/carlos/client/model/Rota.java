package br.trixlog.carlos.client.model;

import java.util.Date;
import java.util.List;

public class Rota {
	private String id;
	private String name;
	private Date routeDate;
	private Integer vehicleId;
	private List<Parada> stops;
	private List<Coordenada> path;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getRouteDate() {
		return routeDate;
	}

	public void setRouteDate(Date routeDate) {
		this.routeDate = routeDate;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public List<Parada> getStops() {
		return stops;
	}

	public void setStops(List<Parada> stops) {
		this.stops = stops;
	}

	public List<Coordenada> getPath() {
		return path;
	}

	public void setPath(List<Coordenada> path) {
		this.path = path;
	}

}
