package com.aleal.rooms.controller;

import java.util.List;

import com.aleal.rooms.config.RoomsServiceConfiguration;
import com.aleal.rooms.model.PropertiesRooms;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.aleal.rooms.model.Room;
import com.aleal.rooms.services.IRoomService;

@RestController
public class RoomController {

	@Autowired
	private IRoomService service;
	@Autowired
	private RoomsServiceConfiguration configRomms;
	
	@GetMapping("rooms")
	public List<Room> search(){
		return (List<Room>) this.service.search();	
	}

	@GetMapping("rooms/{id}")
	public List<Room> searchByHotelId(@PathVariable long id){

		return (List<Room>) this.service.searchRoomByHotelId(id);
	}

	@GetMapping("/rooms/read")
	public String getPropertiesRooms() throws JsonProcessingException {

		ObjectWriter owj = new ObjectMapper().writer().withDefaultPrettyPrinter();

		PropertiesRooms propertiesRooms = new PropertiesRooms(configRomms.getMsg(), configRomms.getBuildVersion(), configRomms.getMailDetails());

		String jsonString = owj.writeValueAsString(propertiesRooms);
		return jsonString;
	}




}
