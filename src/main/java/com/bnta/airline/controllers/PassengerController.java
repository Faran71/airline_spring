package com.bnta.airline.controllers;

import com.bnta.airline.models.Passenger;
import com.bnta.airline.models.PassengerDTO;
import com.bnta.airline.repositories.FlightRepository;
import com.bnta.airline.repositories.PassengerRepository;
import com.bnta.airline.services.FlightService;
import com.bnta.airline.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("passengers")
public class PassengerController {

    @Autowired
    PassengerService passengerService;

    @Autowired
    FlightService flightService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Passenger>> getPassenger(@PathVariable Long id){
        return new ResponseEntity(passengerService.displayPassenger(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Passenger>> getAllPassengers(){
        return new ResponseEntity<>(passengerService.displayAllPassengers(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Passenger> postPassenger(@RequestBody PassengerDTO passengerDTO) {
        if (passengerDTO.getFlightIds().size() > 0) {
            for (Long id : passengerDTO.getFlightIds()) {
                if (flightService.findFlight(passengerDTO.getFlightIds().get(id.intValue()-1)).getPassengers().size() <
                        flightService.findFlight(passengerDTO.getFlightIds().get(id.intValue()-1)).getCapacity()) {
                    Passenger savePassenger = passengerService.addPassenger(passengerDTO);
                    return new ResponseEntity<>(savePassenger, HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
                }
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

}
