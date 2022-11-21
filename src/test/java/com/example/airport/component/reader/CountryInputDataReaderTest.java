package com.example.airport.component.reader;

import com.example.airport.data.model.CountryEntity;
import org.apache.commons.io.IOUtils;
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
public class CountryInputDataReaderTest {

    @Autowired
    CountryInputDataReader countryInputDataReader;

      @Test
      public void processInputCountryTest() {
        InputStream inputStream = this.getClass().getResourceAsStream("/csv/countries.csv");
        final List<CountryEntity> inputRecords = countryInputDataReader.processInput(inputStream);
        assertNotNull(inputRecords);
        assertEquals(3, inputRecords.size());
        final CountryEntity country = inputRecords.get(0);
        assertEquals(302672, country.getId());
        assertEquals("AD", country.getCode());
        assertEquals("Andorra", country.getName());
        assertEquals("EU", country.getContinent());
        assertEquals("https://en.wikipedia.org/wiki/Andorra", country.getWikipedia_Link());
        assertEquals("", country.getKeywords());
    }
    @Test
    public void processInputNullTest()  {
        final List<CountryEntity> inputRecords = countryInputDataReader.processInput(null);
        assertNull(inputRecords);
    }

    @Test
    public void processInputNotNullTest() throws IOException {
        InputStream inputStream = this.getClass().getResourceAsStream("/csv/countries.csv");
        assertNotNull(inputStream);
    }
}
