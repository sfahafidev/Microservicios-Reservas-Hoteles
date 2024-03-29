package com.aleal.reservations.controller;

import java.util.List;

import com.aleal.reservations.config.ReservationsServiceConfiguration;
import com.aleal.reservations.model.PropertiesReservations;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aleal.reservations.model.Reservation;
import com.aleal.reservations.services.IReservationService;

@Log4j2
@RestController
public class ReservationController {
	
	@Autowired
	private IReservationService service;
	@Autowired
	private ReservationsServiceConfiguration configReservations;

	

	@GetMapping("reservations")
	public List<Reservation> search(){
		log.info("inicio de método search");
		return (List<Reservation>) this.service.search();
	}

	@GetMapping("/reservations/read")
	public String getPropertiesReservations() throws JsonProcessingException {

		ObjectWriter owj = new ObjectMapper().writer().withDefaultPrettyPrinter();

		PropertiesReservations propertiesReservations = new PropertiesReservations(configReservations.getMsg(), configReservations.getBuildVersion(), configReservations.getMailDetails());

		String jsonString = owj.writeValueAsString(propertiesReservations);
		return jsonString;
	}

}
