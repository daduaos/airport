package com.airport.enumerator;

public enum AirplaneActionsEnum {
	WAITING("esperando"), 
	LANDING("aterrizando"), 
	TAKING_OFF("despegando");

	private String description;

	AirplaneActionsEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}


}
