package com.airport.service;

import com.airport.exception.RunwayLockedException;
import com.airport.models.Airplane;
import com.airport.models.Runways;

public interface IAirportService {

	void openAirport();

	void requestRunway(Airplane airplane);

	boolean towerControlValidating();

	void reportStatus(Airplane airplane)  throws RunwayLockedException;

	Runways getRunway();
}
