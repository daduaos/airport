package com.airport.enumerator;

public enum RunwayUsedToEnum {
	LAND("aterrizaje"), 
	TAKE_OFF("despegue");

	private String description;

	private RunwayUsedToEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
