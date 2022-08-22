package com.airport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.airport.enumerator.AirplaneActionsEnum;
import com.airport.exception.RunwayLockedException;
import com.airport.models.Airplane;
import com.airport.service.IAirportService;
import com.airport.service.impl.AirplaneLanding;
import com.airport.service.impl.AirplaneTakeOff;

@Component
public class StrategyFactory implements ApplicationRunner {

	private static final Logger log = LoggerFactory.getLogger(StrategyFactory.class);

	private static final int LANDING_AIRPLANES = 3;
	private static final int TAKE_OFF_AIRPLANES = 3;

	@Autowired
	private AirplaneTakeOff airplaneTakeOff;

	@Autowired
	private AirplaneLanding airplaneLanding;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		try {
			List<Airplane> airplaneLandingList = new ArrayList<>(LANDING_AIRPLANES);
			List<Airplane> airplaneTakeoffList = new ArrayList<>(TAKE_OFF_AIRPLANES);

			List<Airplane> airplaneList = new ArrayList<>();
			IAirportService takeoff = getStrategy("takeoff");
			IAirportService landing = getStrategy("landing");

			for (int i = 0; i <= LANDING_AIRPLANES; i++) {
				airplaneLandingList.add(new Airplane(i, "ATEL" + i, AirplaneActionsEnum.LANDING, false, landing));
			}

			for (int i = 0; i <= TAKE_OFF_AIRPLANES; i++) {
				airplaneTakeoffList.add(new Airplane(i, "DEST" + i, AirplaneActionsEnum.TAKING_OFF, false, takeoff));
			}

			airplaneList.addAll(airplaneTakeoffList);
			airplaneList.addAll(airplaneLandingList);

			Collections.shuffle(airplaneList);

			takeoff.openAirport();
			landing.openAirport();

			startedAirport(airplaneList);
		} catch (RunwayLockedException e) {
			e.printStackTrace();
		}
	}

	private void startedAirport(List<Airplane> airplaneList) {
		List<Airplane> requeue = new ArrayList<>();

		for (Airplane airplane : airplaneList) {
			airplane.getAirportInstance().requestRunway(airplane);
			boolean statusOfAction = airplane.getAirportInstance().towerControlValidating();
			airplane.setStatus(statusOfAction);
			try {
				airplane.getAirportInstance().reportStatus(airplane);
			} catch (RunwayLockedException e) {
				log.warn("{} para el vuelo {}", e.getMessage(), airplane.getName());
				requeue.add(airplane);
			}
		}
		if (!requeue.isEmpty()) {
			startedAirport(requeue);
		}
	}

	public IAirportService getStrategy(String strategyName) {
		switch (strategyName) {
		case "takeoff":
			return airplaneTakeOff;
		case "landing":
			return airplaneLanding;
		default:
			return null;
		}
	}

}
