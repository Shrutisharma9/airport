package com.example.airport.site.controller;
import com.example.airport.data.model.CountryEntity;
import com.example.airport.service.DataRetrievalService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiController {

    @Autowired
    DataRetrievalService dataRetrievalService;

    @GetMapping("/searchAsync")
    public List<String> tmp(@RequestParam(name = "keyword", required = true) final String keyword) {

        final List<String> searchResult = new ArrayList<>();
        if (!StringUtils.isEmpty(keyword) && keyword.length() >= 2) {
            List<CountryEntity> countryEntities = dataRetrievalService.fuzzySearchCountryByName(keyword);
            if (!CollectionUtils.isEmpty(countryEntities)) {
                countryEntities.stream().forEach(c -> searchResult.add(c.getName()));
            }
        }
        return searchResult;
    }
}
