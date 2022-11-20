package com.example.airport.component.reader;

import com.example.airport.exception.InputDataReadException;
import com.example.airport.service.CSVReaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * this class is responsible to read and write records from csv to database for countries, airports and runways
 * @param <T>
 */
@Slf4j
public abstract class InputReader<T> {

    @Autowired
    CSVReaderService csvReaderService;
    private Map<String, Integer> keyToIndexMap;
    private String[] records;

    public List<T> processInput(InputStream inputStream) {
        try {
            if (inputStream != null) {
                final List<String[]> countryRecords = csvReaderService.readAllLines(inputStream);
                return buildEntities(countryRecords);
            } else {
                log.warn("Provided path of input file is null");
                return null;
            }

        } catch (Exception e) {
            log.error("An error occurred while reading data",  e);
            throw new InputDataReadException("An error occurred while reading data", e);

        }
    }

    protected void setKeyToIndexMap(String[] header) {
        if (header != null) {
            keyToIndexMap = IntStream.range(0, header.length)
                    .boxed()
                    .collect(Collectors.toMap(i -> header[i], i -> i, (a, b) -> b));
        }
    }

    protected void setRecords(final String[] records) {
        this.records = records;
    }

    protected String getValueByKey(String key) {
        if (key != null && records != null && keyToIndexMap != null && keyToIndexMap.get(key) != null) {
            return records[keyToIndexMap.get(key)];
        }
        return null;
    }

    protected Long getLongValueByKey(String key) {
        final String valueByKey = getValueByKey(key);
        if (valueByKey != null && !valueByKey.isEmpty()) {
            return Long.valueOf(valueByKey);
        }
        return null;
    }

    protected Integer getIntegerValueByKey(String key) {
        final String valueByKey = getValueByKey(key);
        if (valueByKey != null && !valueByKey.isEmpty()) {
            return Integer.valueOf(valueByKey);
        }
        return null;
    }

    protected Double getDoubleValueByKey(String key) {
        final String valueByKey = getValueByKey(key);
        if (valueByKey != null && !valueByKey.isEmpty()) {
            return Double.valueOf(valueByKey);
        }
        return null;
    }

    abstract List<T> buildEntities(List<String[]> countryRecords);
}