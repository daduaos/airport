package com.airport.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.airport.enumerator.RunwayUsedToEnum;
import com.airport.exception.RunwayLockedException;
import com.airport.models.Airplane;
import com.airport.models.Runways;

@Component
public class AirplaneTakeOff extends AirportService {

	private static final Logger log = LoggerFactory.getLogger(AirplaneTakeOff.class);


	@Override
	public void requestRunway(Airplane airplane) {
		log.info("avion {} solicitando despegue", airplane.getName());
	}

	@Override
	public boolean towerControlValidating() {
		if(runway.isStatus()) {
			log.info("{} despejada y lista para {}", runway.getName(), runway.getUsedTo().getDescription());
			runway.setStatus(false);
			return true;
		} else {
			log.info("{} ocupada para {}", runway.getName(), runway.getUsedTo().getDescription());
			runway.setStatus(true);
			return false;
		}
	}

	@Override
	public void reportStatus(Airplane airplan) throws RunwayLockedException {	
		log.info("vuelo {} despego, {}", airplan.getName(), airplan.isStatus() ? "correcto" : "falso");
		if(!airplan.isStatus()) {
			throw new RunwayLockedException(String.format("%s ocupada, esperando de nuevo autorización", runway.getName()));
		}
	}

	@Override
	protected void setRunway() {
		runway = new Runways("pista de despegue", RunwayUsedToEnum.TAKE_OFF, true);
	}

}
