package com.airport.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.airport.enumerator.RunwayUsedToEnum;
import com.airport.exception.RunwayLockedException;
import com.airport.models.Airplane;
import com.airport.models.Runways;

@Component
public class AirplaneLanding extends AirportService {

	private static final Logger log = LoggerFactory.getLogger(AirplaneLanding.class);

	@Override
	public void requestRunway(Airplane airplane) {
		log.info("avion {} solicitando aterrizaje", airplane.getName());
	}

	@Override
	public boolean towerControlValidating() {
		if (this.runway.isStatus()) {
			log.info("{} despejada y lista para {}", this.runway.getName(), this.runway.getUsedTo().getDescription());
			this.runway.setStatus(false);
			return true;
		} else {
			log.info("{} ocupada para {}", this.runway.getName(), this.runway.getUsedTo().getDescription());
			this.runway.setStatus(true);
			return false;
		}
	}

	@Override
	public void reportStatus(Airplane airplan) throws RunwayLockedException {
		log.info("vuelo {} aterrizo, {}", airplan.getName(), airplan.isStatus() ? "correcto" : "falso");
		if(!airplan.isStatus()) {
			throw new RunwayLockedException(String.format("%s ocupada, esperando de nuevo autorización", runway.getName()));
		}
	}

	@Override
	protected void setRunway() {
		this.runway = new Runways("pista de aterrizaje", RunwayUsedToEnum.LAND, false);
	}

}
