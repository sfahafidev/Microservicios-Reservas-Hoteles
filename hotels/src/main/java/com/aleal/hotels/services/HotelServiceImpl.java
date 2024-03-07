package com.aleal.hotels.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.aleal.hotels.model.HotelRooms;
import com.aleal.hotels.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.aleal.hotels.dao.IHotelDao;
import com.aleal.hotels.model.Hotel;
import org.springframework.web.client.RestClient;

@Service
public class HotelServiceImpl implements IHotelService {
	
	@Autowired
	private IHotelDao hotelDao;
	@Autowired
	private RestClient restClient;

	@Override
	public List<Hotel> search() {
		return (List<Hotel>) hotelDao.findAll();
	}

	@Override
	public HotelRooms searchHotelById(long hotelId) {
		HotelRooms response = new HotelRooms();
		Optional<Hotel> hotel = hotelDao.findById(hotelId);

		List<Room> rooms = Objects.requireNonNull(restClient.get()
                        .uri(uriBuilder -> uriBuilder.path("http://localhost:8081/rooms/{id}").build(hotelId))
                        .retrieve()
                        .body(new ParameterizedTypeReference<List<Room>>() {
                        }))
				.stream().toList();

		response.setHotelId(hotel.get().getHotelId());
		response.setHotelName(hotel.get().getHotelName());
		response.setHotelAddress(hotel.get().getHotelAddress());
		response.setRooms(rooms);

		return response;

	}
}