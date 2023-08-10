package com.bnta.airline.controllers;

import com.bnta.airline.models.Flight;
import com.bnta.airline.repositories.FlightRepository;
import com.bnta.airline.services.FlightService;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("flights")
public class FlightController {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    FlightService flightService;

//    @GetMapping
//    public ResponseEntity<List<Flight>> getAllFlights(){
//        return new ResponseEntity<>(flightRepository.findAll(), HttpStatus.OK);
//    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Flight>> getFlight(@PathVariable Long id){
        return new ResponseEntity<>(flightRepository.findById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<Flight>> postFlight(@RequestBody Flight flight){
        flightRepository.save(flight);
        return new ResponseEntity<>(flightRepository.findAll(),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getFlightTo(@RequestParam(required = false,name = "destination") String destination) {
        if (destination != null) {
            if (flightService.findAllFlightsTo(destination).size() == 0){
                return new ResponseEntity<>(null, HttpStatus.INSUFFICIENT_STORAGE);
            } else {
                return new ResponseEntity<>(flightService.findAllFlightsTo(destination), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(flightService.findAllFlights(), HttpStatus.OK);
    }

}
