package com.example.airport.service.impl;

import com.example.airport.data.model.AirportEntity;
import com.example.airport.data.model.CountryEntity;
import com.example.airport.data.model.RunwayEntity;
import com.example.airport.data.repository.AirportRepository;
import com.example.airport.data.repository.CountryRepository;
import com.example.airport.hibernatesearch.CountryDaoSearch;
import com.example.airport.service.DataRetrievalService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Data rerievel layer interact with data repository in transactional manner
 */
@Service
@Transactional
public class DataRetrievalServiceImpl implements DataRetrievalService {

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    AirportRepository airportRepository;

    @Autowired
    CountryDaoSearch countryDaoSearch;

    @Override
    public Set<RunwayEntity> retrieveRunwaysByCountryCodeOrName(final String countryCode, final String countryName) {
        final CountryEntity country = countryRepository.findByCodeOrName(countryCode, countryName);
        final Set<RunwayEntity> runwayEntities = new HashSet<>();
        if (country != null) {
            final Set<AirportEntity> airports = country.getAirports();

            if (!CollectionUtils.isEmpty(airports)) {
                for (AirportEntity airport : airports) {
                    runwayEntities.addAll(airport.getRunways());
                }
            }

        }
        return runwayEntities;
    }

    @Override
    public List<CountryEntity> retrieveTop10CountriesWithMaximumNumberOfAirport(){
        final List<Long> countryIds = airportRepository.retrieveTop10CountriesWithMaximumNumberOfAirport();
        List<CountryEntity> countryEntities = new ArrayList<>();
        if(countryIds !=null && !countryIds.isEmpty()){
            countryEntities = countryRepository.findAllById(countryIds);
        }
        return countryEntities;
    }

    @Override
    public List<CountryEntity> fuzzySearchCountryByName(String text){
        List<CountryEntity> countryEntities = new ArrayList<>();
        if(StringUtils.isNotEmpty(text)){
            countryEntities = countryDaoSearch.searchCountryNameByFuzzyQuery(text);
        }
        return countryEntities;
    }



}
