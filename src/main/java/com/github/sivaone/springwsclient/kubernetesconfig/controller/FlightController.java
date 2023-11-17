package com.github.sivaone.springwsclient.kubernetesconfig.controller;

import java.util.List;

import com.github.sivaone.springwsclient.kubernetesconfig.entity.Flight;
import com.github.sivaone.springwsclient.kubernetesconfig.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    private static final Logger logger = LoggerFactory.getLogger(FlightController.class);

    @GetMapping
    public @ResponseBody List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{flight-id}")
    public @ResponseBody Flight getFlightById(@PathVariable("flight-id") String flightId) {
        return flightService.getFlightById(flightId);
    }

}
