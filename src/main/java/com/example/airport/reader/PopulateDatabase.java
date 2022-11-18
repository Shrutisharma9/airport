package com.example.airport.reader;

import com.example.airport.data.model.AirportEntity;
import com.example.airport.data.model.CountryEntity;
import com.example.airport.data.model.RunwayEntity;
import com.example.airport.data.repository.AirportRepository;
import com.example.airport.data.repository.CountryRepository;
import com.example.airport.data.repository.RunwayRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Component
public class PopulateDatabase implements ApplicationListener<ApplicationReadyEvent>, Ordered {


    @Value(value = "classpath:input/csv/countries.csv")
    private Resource countryCsv;

    @Value(value = "classpath:input/csv/airports.csv")
    private Resource airportCsv;

    @Value(value = "classpath:input/csv/runways.csv")
    private  Resource runwayCsv;

    @Autowired
    CountryInputDataReader countryReader;

    @Autowired
    AirportInputDataReader airportInputDataReader;

    @Autowired
    RunwayInputDataReader runwayInputDataReader;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    RunwayRepository runwayRepository;

    @Autowired
    AirportRepository airportRepository;


    @SneakyThrows
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        // In case of big CSV file the insert should be done in pagination, avoiding that complexity due to small size of data
        log.info("Loading country data it will take a while....");
        final List<CountryEntity> countryEntities = countryReader.processInput(Paths.get(countryCsv.getURI()));
        countryRepository.saveAll(countryEntities);
        log.info("Loading country completed");

        log.info("Loading airport data it will take a while....");
        final List<AirportEntity> airportEntities = airportInputDataReader.processInput(Paths.get(airportCsv.getURI()));
        airportRepository.saveAll(airportEntities);
        log.info("Loading airport completed");


        log.info("Loading runways data it will take a while....");
        final List<RunwayEntity> runwayEntities = runwayInputDataReader.processInput(Paths.get(runwayCsv.getURI()));
        runwayRepository.saveAll(runwayEntities);
        log.info("Loading runways completed");

        log.info("Application is ready to access");
    }

    /**
     * Populate data at end of application deployment
     *
     * @return
     */
    @Override
    public int getOrder() {
        return -1;
    }
}
