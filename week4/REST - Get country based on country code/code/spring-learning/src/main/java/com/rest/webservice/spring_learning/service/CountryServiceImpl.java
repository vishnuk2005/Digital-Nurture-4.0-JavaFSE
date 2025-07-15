package com.rest.webservice.spring_learning.service;

import com.rest.webservice.spring_learning.model.Country;

import org.springframework.stereotype.Service;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Override
    @SuppressWarnings("unchecked")
    public Country getCountry(String code) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("country.xml")) {
        	

            List<Country> countryList = (List<Country>) context.getBean("countryList");

            return countryList.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null); // or throw custom exception if not found
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Country> getAllCountries() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("country.xml")) {
            return (List<Country>) context.getBean("countryList");
        }
    }
}
