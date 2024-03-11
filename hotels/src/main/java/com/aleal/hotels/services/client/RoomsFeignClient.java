package com.aleal.hotels.services.client;

import com.aleal.hotels.model.Room;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("rooms") //Nombre del microservicio a consumir
public interface RoomsFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "rooms/{id}", consumes = "application/json")
    public List<Room> searchByHotelId(@PathVariable long id);

}
