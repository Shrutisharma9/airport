package com.example.airport.reader;

import com.example.airport.data.model.CountryEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CountryInputDataReader extends InputReader<CountryEntity> {


    protected List<CountryEntity> buildEntities(List<String[]> countryRecords) {
        //"id","code","name","continent","wikipedia_link","keywords"
        final List<CountryEntity> countryEntities = new ArrayList<>();

        setKeyToIndexMap(countryRecords.get(0));
        if (countryRecords != null && countryRecords.size() > 2) {
            countryRecords.stream().skip(1).forEach(
                    record -> {
                        setRecords(record);
                        CountryEntity country = CountryEntity.builder()
                                .id(getLongValueByKey("id"))
                                .code(getValueByKey("code"))
                                .name(getValueByKey("name"))
                                .continent(getValueByKey("continent"))
                                .wikipedia_Link(getValueByKey("wikipedia_link"))
                                .keywords(getValueByKey("keywords")).build();
                        countryEntities.add(country);
                    }
            );
        }
        return countryEntities;
    }


}
