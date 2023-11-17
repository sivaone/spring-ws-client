package com.github.sivaone.springwsclient.kubernetesconfig.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.sivaone.springwsclient.kubernetesconfig.entity.Flight;
import org.springframework.stereotype.Service;

@Service
public class FlightServiceImpl implements FlightService {

    @Override
    public Flight getFlightById(String flightId) {
        return getFlights().get(flightId);
    }

    @Override
    public List<Flight> getAllFlights() {
        return new ArrayList<>(getFlights().values());
    }

    public Map<String, Flight> getFlights(){

        Map<String, Flight> flights = new HashMap<>();
        flights.put("101", new Flight("101","Indore","Lodon", LocalDateTime.now(),LocalDateTime.now(),"This Flight does not have business class","flight_first_class","flight_capacity"));
        flights.put("102", new Flight("102","Indore","Italy", LocalDateTime.now(),LocalDateTime.now(),"This Flight have business class","flight_business_class","flight_capacity"));
        flights.put("103", new Flight("103","Indore","Switzerland", LocalDateTime.now(),LocalDateTime.now(),"This Flight have business class","flight_business_class","flight_capacity"));
        flights.put("104", new Flight("104","Indore","France", LocalDateTime.now(),LocalDateTime.now(),"This Flight have business class","flight_business_class","flight_capacity"));
        flights.put("105", new Flight("105","Indore","Spain", LocalDateTime.now(),LocalDateTime.now(),"This Flight have business class","flight_business_class","flight_capacity"));
        flights.put("106", new Flight("105","Indore","Peris", LocalDateTime.now(),LocalDateTime.now(),"This Flight have business class","flight_business_class","flight_capacity"));

        return flights;

    }

}
