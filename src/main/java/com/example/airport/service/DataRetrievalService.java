package com.example.airport.service;

import com.example.airport.data.model.CountryEntity;
import com.example.airport.data.model.RunwayEntity;

import java.util.List;
import java.util.Set;

public interface DataRetrievalService {
    Set<RunwayEntity> retrieveRunwaysByCountryCodeOrName(String countryCode, String countryName);

    List<CountryEntity> retrieveTop10CountriesWithMaximumNumberOfAirport();

    List<CountryEntity> fuzzySearchCountryByName(String text);
}
