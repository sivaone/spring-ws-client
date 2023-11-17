package com.github.sivaone.springwsclient.kubernetesconfig.service;

import java.util.List;

import com.github.sivaone.springwsclient.kubernetesconfig.entity.Flight;

public interface FlightService {

    Flight getFlightById(String flightId);

    List<Flight> getAllFlights();

}
