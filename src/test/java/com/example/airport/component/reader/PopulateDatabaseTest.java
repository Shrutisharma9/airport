package com.example.airport.component.reader;

import com.example.airport.data.model.AirportEntity;
import com.example.airport.data.model.CountryEntity;
import com.example.airport.data.model.RunwayEntity;
import com.example.airport.data.repository.AirportRepository;
import com.example.airport.data.repository.CountryRepository;
import com.example.airport.data.repository.RunwayRepository;
import com.example.airport.service.DataRetrievalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PopulateDatabaseTest {

    @Autowired
    PopulateDatabase populateDatabase;
    @Autowired
    CountryRepository countryRepository;

    @Autowired
    RunwayRepository runwayRepository;

    @Autowired
    AirportRepository airportRepository;

    @Autowired
    DataRetrievalService dataRetrievalService;

    @Test
    public void onApplicationEventTest(){
        populateDatabase.onApplicationEvent(null);
        final List<CountryEntity> countryEntities = countryRepository.findAll();
        assertNotNull(countryEntities);


        final List<RunwayEntity> runwayEntities = runwayRepository.findAll();
        assertNotNull(runwayEntities);


        final List<AirportEntity> airportEntities = airportRepository.findAll();
        assertNotNull(airportEntities);

        dataRetrievalService.retrieveRunwaysByCountryCodeOrName("AO",null);

    }
}
