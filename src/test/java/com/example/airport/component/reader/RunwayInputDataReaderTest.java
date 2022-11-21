package com.example.airport.component.reader;

import com.example.airport.data.model.RunwayEntity;
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
public class RunwayInputDataReaderTest {

    @Autowired
    RunwayInputDataReader runwayInputDataReader;

    @Test
    public void processInputAirportTest() {
        InputStream inputStream = this.getClass().getResourceAsStream("/csv/runways.csv");
        final List<RunwayEntity> inputRecords = runwayInputDataReader.processInput(inputStream);
        assertNotNull(inputRecords);
        assertEquals(10, inputRecords.size());
        final RunwayEntity runway = inputRecords.get(0);
        assertEquals(256815, runway.getId());
        assertEquals(2517, runway.getLengthFeet());
        assertEquals(120, runway.getWidthFeet());
        assertEquals("TURF-G", runway.getSurface());
        assertTrue(runway.getLighted());
        assertFalse(runway.getClosed());
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
    public void processInputNotNullTest() throws IOException {
        InputStream inputStream = this.getClass().getResourceAsStream("/csv/runways.csv");
        assertNotNull(inputStream);
    }

    @Test
    public void processInputNullTest()  {
        final List<RunwayEntity> inputRecords = runwayInputDataReader.processInput(null);
        assertNull(inputRecords);
    }
}
