package com.airport.models;

import com.airport.enumerator.RunwayUsedToEnum;

public class Runways {

	private String name;
	private RunwayUsedToEnum usedTo;
	private boolean status;

	
	
	public Runways(String name, RunwayUsedToEnum usedTo, boolean status) {
		this.name = name;
		this.usedTo = usedTo;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RunwayUsedToEnum getUsedTo() {
		return usedTo;
	}

	public void setUsedTo(RunwayUsedToEnum usedTo) {
		this.usedTo = usedTo;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Runways [name=" + name + ", usedTo=" + usedTo + ", status=" + status + "]";
	}

}
