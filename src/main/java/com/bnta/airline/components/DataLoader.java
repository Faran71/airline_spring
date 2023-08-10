package com.bnta.airline.components;

import com.bnta.airline.models.Flight;
import com.bnta.airline.models.Passenger;
import com.bnta.airline.repositories.FlightRepository;
import com.bnta.airline.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    FlightRepository flightRepository;

    public DataLoader(){}

    @Override
    public void run(ApplicationArguments args) throws Exception{

        Flight londonFlight = new Flight("London",3, LocalDate.of(2023,10,10), LocalTime.of(10,10));
        flightRepository.save(londonFlight);

        Passenger passenger1 = new Passenger("Faran","@gmail");
        passenger1.addFlight(londonFlight);
        passengerRepository.save(passenger1);

        Passenger passenger2 = new Passenger("Far","@g");
        passenger2.addFlight(londonFlight);
        passengerRepository.save(passenger2);

        Flight parisFlight = new Flight("Paris",3,LocalDate.of(2023,10,9),LocalTime.of(22,20));
        flightRepository.save(parisFlight);


    }
}
