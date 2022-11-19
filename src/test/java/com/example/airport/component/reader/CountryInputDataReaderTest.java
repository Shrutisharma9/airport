package com.example.airport.component.reader;

import com.example.airport.data.model.CountryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        final Path path = Paths.get("src/test/resources/csv/countries.csv");
        final List<CountryEntity> inputRecords = countryInputDataReader.processInput(path);
        assertNotNull(inputRecords);
        assertEquals(3,inputRecords.size());
        final CountryEntity country = inputRecords.get(0);
        assertEquals(302672,country.getId());
        assertEquals("AD",country.getCode());
        assertEquals("Andorra",country.getName());
        assertEquals("EU",country.getContinent());
        assertEquals("https://en.wikipedia.org/wiki/Andorra",country.getWikipedia_Link());
        assertEquals("",country.getKeywords());

        final CountryEntity country1 = inputRecords.get(1);
        assertEquals(302618,country1.getId());
        //assertNull(country1.getCode());
        assertEquals("AE",country1.getCode());
        assertEquals("United Arab Emirates",country1.getName());
        assertEquals("AS",country1.getContinent());
        assertEquals("https://en.wikipedia.org/wiki/United_Arab_Emirates",country1.getWikipedia_Link());
        assertEquals("UAE,مطارات في الإمارات العربية المتحدة",country1.getKeywords());

        final CountryEntity country2 = inputRecords.get(2);
        assertEquals(302619,country2.getId());
        assertEquals("AF",country2.getCode());
        assertEquals("Afghanistan",country2.getName());
        assertEquals("AS",country2.getContinent());
        assertEquals("https://en.wikipedia.org/wiki/Afghanistan",country2.getWikipedia_Link());
        assertEquals("",country2.getKeywords());
    }

    @Test
    public void processInputNullTest()  {
        final List<CountryEntity> inputRecords = countryInputDataReader.processInput(null);
        assertNull(inputRecords);
    }
}
