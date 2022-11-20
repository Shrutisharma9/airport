package com.example.airport.service;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

public interface CSVReaderService {

    public List<String[]> readAllLines(InputStream inputStream) throws Exception;

}
