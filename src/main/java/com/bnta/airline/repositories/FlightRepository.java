package com.bnta.airline.repositories;

import com.bnta.airline.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Long> {
    List<Flight> findByDestination(String destination);
}
