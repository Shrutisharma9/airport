package com.example.airport.site.controller;

import com.example.airport.data.model.CountryEntity;
import com.example.airport.data.model.RunwayEntity;
import com.example.airport.service.DataRetrievalService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

/**
 * Spring controller to handle home page call.
 */

@Controller
@Slf4j
public class ServletController {

    private static final String HOME_PAGE = "home";
    @Autowired
    DataRetrievalService dataRetrievalService;

    @GetMapping("/")
    public String home(Model model,
                       @RequestParam(name = "owningEntityStatisticFilter", required = false) final String appliedOwningEntityFilter) {

        return HOME_PAGE;
    }

    @GetMapping("/airport")
    public String runwaysByCountrySubmit(Model model, @RequestParam(name = "code", required = false) final String countryCode,
                                         @RequestParam(name = "name", required = false) final String countryName) {
        if (StringUtils.isEmpty(countryCode) && StringUtils.isEmpty(countryName)) {
            log.warn("Both of the input fields are empty");
            model.addAttribute("error", "Country code or name, one of the input is required");

        } else {

            final Set<RunwayEntity> runwayEntities = dataRetrievalService.retrieveRunwaysByCountryCodeOrName(countryCode, countryName);
            if (CollectionUtils.isEmpty(runwayEntities)) {
                model.addAttribute("info", "No Record found");
            }
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

        if(StringUtils.isNotEmpty(keyword) && keyword.length() > 1){
            final List<CountryEntity> countryEntities = dataRetrievalService.fuzzySearchCountryByName(keyword);
            if(!CollectionUtils.isEmpty(countryEntities)){
                model.addAttribute("countriesFuzzy", countryEntities);
            }
            else {
                model.addAttribute("info_s", "No Record found");
            }
        }
        else {
            model.addAttribute("error_s", "keyword is empty or less than 2 alphabets");
        }
        return HOME_PAGE;
    }


}
