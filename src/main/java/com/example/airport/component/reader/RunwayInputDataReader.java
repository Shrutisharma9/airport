package com.example.airport.component.reader;

import com.example.airport.data.model.AirportEntity;
import com.example.airport.data.model.RunwayEntity;
import com.example.airport.data.repository.AirportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RunwayInputDataReader extends InputReader<RunwayEntity> {

    @Autowired
    AirportRepository airportRepository;

    /**
     * read and save runways record based on each airport from csv to database
     * @param runwayRecords
     * @return
     */
    protected List<RunwayEntity> buildEntities(List<String[]> runwayRecords) {
       // "id","airport_ref","airport_ident","length_ft","width_ft","surface","lighted","closed",
        // "le_ident","le_latitude_deg","le_longitude_deg","le_elevation_ft",
        // "le_heading_degT","le_displaced_threshold_ft","he_ident","he_latitude_deg","he_longitude_deg",
        // "he_elevation_ft","he_heading_degT","he_displaced_threshold_ft"
        final List<RunwayEntity> runwayEntities = new ArrayList<>();
        if (runwayRecords != null && runwayRecords.size() > 2) {
            setKeyToIndexMap(runwayRecords.get(0));

            runwayRecords.stream().skip(1).forEach(
                    record -> {
                        setRecords(record);
                        if(getLongValueByKey("id") == null){
                            log.warn("no id provided");
                        }
                        RunwayEntity runwayEntity = RunwayEntity.builder()
                                        .id(getLongValueByKey("id"))
                                        .airport(findAirport(getLongValueByKey("airport_ref")).orElse(null))
                                .lengthFeet(getIntegerValueByKey("length_ft"))
                                .widthFeet(getIntegerValueByKey("width_ft"))
                                .surface(getValueByKey("surface"))
                                .lighted("1".equals(getValueByKey("lighted")))
                                .closed("1".equals(getValueByKey("closed")))
                                .identification(getValueByKey("le_ident"))
                                .longitude(getDoubleValueByKey("le_longitude_deg"))
                                .latitude(getDoubleValueByKey("le_latitude_deg"))
                                .elevationFeet(getDoubleValueByKey("le_elevation_ft"))
                                .heading(getDoubleValueByKey("le_heading_degT"))
                                .displacedThresholdFeet(getDoubleValueByKey("le_displaced_threshold_ft"))
                                .identificationHe(getValueByKey("he_ident"))
                                .latitudeHe(getDoubleValueByKey("he_latitude_deg"))
                                .longitudeHe(getDoubleValueByKey("he_longitude_deg"))
                                .elevationFeetHe(getDoubleValueByKey("he_elevation_ft"))
                                .headingHe(getDoubleValueByKey("he_heading_degT"))
                                .displacedThresholdFeetHe(getDoubleValueByKey("he_displaced_threshold_ft"))
                                 .build();
                        runwayEntities.add(runwayEntity);
                    }
            );
        }
        return runwayEntities;
    }

    /**
     * retrieves the airport reference id
     * @param referenceId
     * @return
     */
    private Optional<AirportEntity> findAirport(final Long referenceId) {
        if(referenceId != null){
            return airportRepository.findById(referenceId);
        }
        return Optional.empty();
    }


}
