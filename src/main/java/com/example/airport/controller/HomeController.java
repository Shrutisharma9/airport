package com.example.airport.controller;

import com.example.airport.data.model.CountryEntity;
import com.example.airport.data.model.RunwayEntity;
import com.example.airport.service.DataRetrievalService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

/**
 * Spring controller to handle home page call.
 */
@Controller
@Slf4j
public class HomeController {

    @Autowired
    DataRetrievalService dataRetrievalService;

    private static final String HOME_PAGE = "home";

    @GetMapping("/")
    public String home(Model model,
                       @RequestParam(name = "owningEntityStatisticFilter", required = false) final String appliedOwningEntityFilter) {

        model.addAttribute("hello", "welcome my friend");
        return HOME_PAGE;
    }

    @GetMapping("/airport")
    public String runwaysByCountrySubmit(Model model, @RequestParam(name = "code", required = false) final String countryCode,
                                         @RequestParam(name = "name", required = false) final String countryName) {
        if (StringUtils.isEmpty(countryCode) && StringUtils.isEmpty(countryName)) {
            log.warn("Both of the input fields are empty");

        } else {
            final Set<RunwayEntity> runwayEntities = dataRetrievalService.retrieveRunwaysByCountryCodeOrName(countryCode, countryName);
            model.addAttribute("runways", runwayEntities);

        }
        return HOME_PAGE;
    }

    @GetMapping("/countries")
    public String topTenCountriesByNumberOfAirports(Model model) {
        final List<CountryEntity> countryEntities = dataRetrievalService.retrieveTop10CountriesWithMaximumNumberOfAirport();
        model.addAttribute("countries", countryEntities);
        return HOME_PAGE;
    }

    @GetMapping("/search")
    public String topTenCountriesByNumberOfAirports(Model model, @RequestParam(name = "keyword", required = true) final String keyword) {
        final List<CountryEntity> countryEntities = dataRetrievalService.fuzzySearchCountryByName(keyword);
        model.addAttribute("countriesFuzzy", countryEntities);
        return HOME_PAGE;
    }

    @GetMapping("/temp")
    public String tmp(Model model) {

        return HOME_PAGE;
    }
}
