package com.example.airport.service.impl;


import com.example.airport.service.CSVReaderService;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
@Service
public class CsvReaderServiceImpl implements CSVReaderService {

    /**
     * this is to provide csv file path and read all the data
     * @param filePath
     * @return
     * @throws Exception
     */
    @Override
    public List<String[]> readAllLines(Path filePath) throws Exception {
        if(filePath != null){
            try (Reader reader = Files.newBufferedReader(filePath)) {
                try (CSVReader csvReader = new CSVReader(reader)) {
                    return csvReader.readAll();
                }
            }
        }
        return null;

    }
}
