package com.rest.webservice.spring_learning.controller;
import com.rest.webservice.spring_learning.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class CountryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);
    @RequestMapping("/country")
    public Country getCountryIndia() {
        LOGGER.info("Start getCountryIndia");
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("country.xml")) {
            Country country = (Country) context.getBean("in");
            LOGGER.info("End getCountryIndia");
            return country;}} 
    @SuppressWarnings("unchecked")
    @GetMapping("/countries/{code}")
    public Country getCountry(@PathVariable String code) {
        LOGGER.info("Start getCountry with code: {}", code);

        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("country.xml")) {
            List<Country> countryList = (List<Country>) context.getBean("countryList");

            Country country = countryList.stream()
                    .filter((Country c) -> c.getCode().equalsIgnoreCase(code))
                    .findFirst()
                    .orElse(null);
            LOGGER.info("End getCountry: {}", country);
            return country;
        }}}
