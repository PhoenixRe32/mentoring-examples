package com.pittacode.resttemp;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Singleton
@Path("/hello")
public class Hello {

    @GET
    @Produces("text/plain")
    public String getClichedMessage() {
        return "Hello, World!";
    }
}