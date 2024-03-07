package com.aleal.rooms.dao;

import org.springframework.data.repository.CrudRepository;

import com.aleal.rooms.model.Room;

import java.util.List;

public interface IRoomDao extends CrudRepository<Room, Long>{

    List<Room> findByHotelId(long hotelId);

}
