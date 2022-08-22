package com.airport.service.impl;

import org.springframework.stereotype.Component;

import com.airport.models.Runways;
import com.airport.service.IAirportService;

@Component
public abstract class AirportService implements IAirportService {

	protected Runways runway;

	@Override
	public void openAirport() {
		setRunway();
	}

	protected abstract void setRunway();

	public Runways getRunway() {
		return runway;
	}

}
