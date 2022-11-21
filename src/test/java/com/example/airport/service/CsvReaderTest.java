package com.example.airport.service;

import com.example.airport.service.impl.CsvReaderServiceImpl;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CsvReaderTest {

    @Test
    public void readAllLinesTest() throws Exception {
        InputStream path = this.getClass().getResourceAsStream("/csv/countries.csv");
        CSVReaderService csvReader = new CsvReaderServiceImpl();
        final List<String[]> inputRecords = csvReader.readAllLines(path);
        assertNotNull(inputRecords);
        assertEquals(4,inputRecords.size());
    }

    @Test
    public void readAllLinesTestNull() throws Exception {
        CSVReaderService csvReader = new CsvReaderServiceImpl();
        final List<String[]> inputRecords = csvReader.readAllLines(null);
        assertNull(inputRecords);
    }
}
