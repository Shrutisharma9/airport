package com.example.airport.service;

import java.nio.file.Path;
import java.util.List;

public interface CSVReaderService {
    List<String[]> readAllLines(Path filePath) throws Exception;
}
