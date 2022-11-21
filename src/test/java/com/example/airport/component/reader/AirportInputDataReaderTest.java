package com.example.airport.component.reader;

import com.example.airport.data.model.AirportEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AirportInputDataReaderTest {

    @Autowired
    AirportInputDataReader airportInputDataReader;

    @Test
    public void processInputAirportTest() {
        InputStream inputStream = this.getClass().getResourceAsStream("/csv/airports.csv");
        final List<AirportEntity> inputRecords = airportInputDataReader.processInput(inputStream);
        assertNotNull(inputRecords);
        assertEquals(19,inputRecords.size());
        final AirportEntity airport = inputRecords.get(0);
        assertEquals(6523, airport.getId());
        assertEquals("00A", airport.getIdentity());
        assertEquals("heliport", airport.getType());
        assertEquals("Total Rf Heliport", airport.getName());
        assertEquals(40.07080078125, airport.getLatitude_deg());
        assertEquals(-74.93360137939453, airport.getLongitude_deg());
        assertEquals(11, airport.getElevation_ft());
        assertEquals("NA", airport.getContinent());
        assertEquals("US-PA", airport.getIso_region());
        assertEquals("Bensalem", airport.getMunicipality());
        assertEquals("no", airport.getScheduled_service());
        assertEquals("00A", airport.getGps_code());
        assertEquals("", airport.getIata_code());
        assertEquals("00A", airport.getLocal_code());
        assertEquals("", airport.getHome_link());
        assertEquals("", airport.getWikipedia_link());
        assertEquals("", airport.getKeywords());
    }

    @Test
    public void processInputNotNullTest() throws IOException {
        InputStream inputStream = this.getClass().getResourceAsStream("/csv/airports.csv");
        assertNotNull(inputStream);
    }

    @Test
    public void processInputNullTest()  {
        final List<AirportEntity> inputRecords = airportInputDataReader.processInput(null);
        assertNull(inputRecords);
    }

}
