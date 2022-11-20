package com.example.airport.component.reader;

import com.example.airport.data.model.RunwayEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RunwayInputDataReaderTest {

    @Autowired
    RunwayInputDataReader runwayInputDataReader;

    @Test
    public void processInputNullTest()  {
        final List<RunwayEntity> inputRecords = runwayInputDataReader.processInput(null);
        assertNull(inputRecords);
    }
}
