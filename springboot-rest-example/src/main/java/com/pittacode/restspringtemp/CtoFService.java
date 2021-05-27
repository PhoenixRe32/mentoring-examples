package com.pittacode.restspringtemp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ctofservice")
public class CtoFService {

    @GetMapping(produces = "application/json")
    public String reference() {
        double fahrenheit;
        double celsius = 36.8;
        fahrenheit = ((celsius * 9) / 5) + 32;

        return "{\"Celcius\" : \"" + celsius + "\", \"Fahrenheit\" : \"" + fahrenheit + "\"}";
    }

    @GetMapping(path = "/{c}", produces = "application/json")
    public String convertCtoFfromInput(@PathVariable("c") double c) {
        double fahrenheit;
        double celsius;
        celsius = c;
        fahrenheit = ((celsius * 9) / 5) + 32;

        return "{\"Celcius\" : \"" + celsius + "\", \"Fahrenheit\" : \"" + fahrenheit + "\"}";
    }
}
