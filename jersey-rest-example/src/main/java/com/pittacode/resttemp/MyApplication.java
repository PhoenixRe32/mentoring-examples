package com.pittacode.resttemp;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public final class MyApplication extends ResourceConfig {

    public MyApplication() {
        register(Hello.class);
        register(CtoFService.class);
        register(FtoCService.class);
    }
}
