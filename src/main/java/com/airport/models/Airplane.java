package com.airport.models;

import com.airport.enumerator.AirplaneActionsEnum;
import com.airport.service.IAirportService;

public class Airplane {

	private int id;
	private String name;
	private AirplaneActionsEnum action;
	private boolean status;
	private IAirportService airportInstance;

	public Airplane(int id, String name, AirplaneActionsEnum action, boolean status, IAirportService airportInstance) {
		this.id = id;
		this.name = name;
		this.action = action;
		this.status = status;
		this.airportInstance = airportInstance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AirplaneActionsEnum getAction() {
		return action;
	}

	public void setAction(AirplaneActionsEnum action) {
		this.action = action;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public IAirportService getAirportInstance() {
		return airportInstance;
	}

	public void setAirportInstance(IAirportService airportInstance) {
		this.airportInstance = airportInstance;
	}

	@Override
	public String toString() {
		return "Airplane [id=" + id + ", name=" + name + ", action=" + action + ", status=" + status + "]";
	}

}
