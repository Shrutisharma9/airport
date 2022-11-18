package com.example.airport.reader;

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
    public void processInputAirportTest() {
        final Path path = Paths.get("src/test/resources/csv/runways.csv");
        final List<RunwayEntity> inputRecords = runwayInputDataReader.processInput(path);
        assertNotNull(inputRecords);
        assertEquals(20,inputRecords.size());
        final RunwayEntity runway = inputRecords.get(0);
        assertEquals(256815, runway.getId());
       /* assertEquals(6581, airport.getIdentity());
        assertEquals("01C", airport.getType());*/
        assertEquals(2517, runway.getLengthFeet());
        assertEquals(120, runway.getWidthFeet());
        assertEquals("TURF-G", runway.getSurface());
        assertEquals(1, runway.getLighted());
        assertEquals(0, runway.getClosed());
        assertEquals("09", runway.getIdentification());
        assertEquals(43.3417, runway.getLatitude());
        assertEquals(-85.7799, runway.getLongitude());
        assertEquals(815, runway.getElevationFeet());
        assertEquals(34, runway.getHeading());
        assertEquals(387, runway.getDisplacedThresholdFeet());
        assertEquals("27", runway.getIdentificationHe());
        assertEquals(43.3417, runway.getLatitudeHe());
        assertEquals(-85.7703, runway.getLongitudeHe());
        assertEquals(815, runway.getElevationFeetHe());
        assertEquals(234, runway.getHeadingHe());
        assertEquals(580, runway.getDisplacedThresholdFeetHe());
    }

    @Test
    public void processInputNullTest()  {
        final List<RunwayEntity> inputRecords = runwayInputDataReader.processInput(null);
        assertNull(inputRecords);
    }
}
