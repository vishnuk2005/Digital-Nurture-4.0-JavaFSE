package com.rest.webservice.spring_learning.service;

import com.rest.webservice.spring_learning.model.Country;
import java.util.List;

public interface CountryService {
    Country getCountry(String code);
    List<Country> getAllCountries();
}
