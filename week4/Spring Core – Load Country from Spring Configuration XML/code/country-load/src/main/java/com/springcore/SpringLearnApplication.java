package com.springcore;

import com.springcore.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringLearnApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);
   

    public static void main(String[] args) {
        LOGGER.debug("START");
        displayCountry();
        LOGGER.debug("END");
        System.out.println("Test Start");
        displayCountry();
        System.out.println("Test End");

    }
    

    public static void displayCountry() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("country.xml")) {
            Country country = (Country) context.getBean("country", Country.class);
            LOGGER.debug("Country : {}", country.toString());
        }
    }
}
