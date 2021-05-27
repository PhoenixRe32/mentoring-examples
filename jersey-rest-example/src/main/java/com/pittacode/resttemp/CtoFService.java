package com.pittacode.resttemp;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Singleton
@Path("/ctofservice")
public class CtoFService {

    @GET
    @Produces("application/json")
    public String reference() {

        double fahrenheit;
        double celsius = 36.8;
        fahrenheit = ((celsius * 9) / 5) + 32;

        return "{\"Celcius\" : \"" + celsius + "\", \"Fahrenheit\" : \"" + fahrenheit + "\"}";
    }

    @Path("{c}")
    @GET
    @Produces("application/json")
    public String convertCtoFfromInput(@PathParam("c") double c) {
        double fahrenheit;
        double celsius;
        celsius = c;
        fahrenheit = ((celsius * 9) / 5) + 32;

        return "{\"Celcius\" : \"" + celsius + "\", \"Fahrenheit\" : \"" + fahrenheit + "\"}";
    }
}