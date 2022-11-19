package com.example.airport.component.reader;

import com.example.airport.data.model.AirportEntity;
import com.example.airport.data.model.CountryEntity;
import com.example.airport.data.repository.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class AirportInputDataReader extends InputReader<AirportEntity> {

    @Autowired
    private CountryRepository countryRepository;


    protected List<AirportEntity> buildEntities(List<String[]> airportRecords) {
        //"id","ident","type","name","latitude_deg","longitude_deg","elevation_ft","continent","iso_country","iso_region",
        // "municipality","scheduled_service","gps_code","iata_code","local_code","home_link","wikipedia_link","keywords"
        final List<AirportEntity> airportEntities = new ArrayList<>();
        if (airportRecords != null && airportRecords.size() > 2) {

           setKeyToIndexMap(airportRecords.get(0));

            airportRecords.stream().skip(1).forEach(
                    record -> {
                        setRecords(record);
                        if(getValueByKey("iso_country") != null && getValueByKey("iso_country").length() > 1){

                            AirportEntity airportEntity = AirportEntity.builder().
                                    id(getLongValueByKey("id"))
                                    .country(findCountry(getValueByKey("iso_country")))
                                    .name(getValueByKey("name"))
                                    .identity(getValueByKey("ident"))
                                    .type(getValueByKey("type"))
                                    .name(getValueByKey("name"))
                                    .latitude_deg(getDoubleValueByKey("latitude_deg"))
                                    .longitude_deg(getDoubleValueByKey("longitude_deg"))
                                    .elevation_ft(getDoubleValueByKey("elevation_ft"))
                                    .continent(getValueByKey("continent"))
                                    .iso_region(getValueByKey("iso_region"))
                                    .municipality(getValueByKey("municipality"))
                                    .scheduled_service(getValueByKey("scheduled_service"))
                                    .gps_code(getValueByKey("gps_code"))
                                    .iata_code(getValueByKey("iata_code"))
                                    .local_code(getValueByKey("local_code"))
                                    .home_link(getValueByKey("home_link"))
                                    .wikipedia_link(getValueByKey("wikipedia_link"))
                                    .keywords(getValueByKey("keywords"))
                                    .build();
                            airportEntities.add(airportEntity);
                        }
                        else {
                            log.warn("As country not provided skipping airport with id {}  -  {} ",getLongValueByKey("id"),getValueByKey("iso_country") );
                        }

                    }
            );
        }
        return airportEntities;
    }

    private CountryEntity findCountry(final String countryCode) {
        if(countryCode != null){
            final CountryEntity byCode = countryRepository.findByCode(countryCode);
            if(byCode == null){
                log.warn("Can not find country with code {}", countryCode);
            }
            return byCode;
        }
        return null;
    }


}
