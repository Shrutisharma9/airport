package com.example.airport.data.repository;

import com.example.airport.data.model.AirportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<AirportEntity, Long> {

    @Query(value = "SELECT a.country_code FROM  airports as a group by a.country_code order by count(a.country_code) desc limit 10", nativeQuery = true)
    List<Long> retrieveTop10CountriesWithMaximumNumberOfAirport();
}
