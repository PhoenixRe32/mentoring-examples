package com.pittacode.resttemp;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Singleton
@Path("/ftocservice")
public class FtoCService {

    @GET
    @Produces("application/json")
    public Response reference() throws JSONException {

        JSONObject jsonObject = new JSONObject();
        double fahrenheit = 98.24;
        double celsius = (fahrenheit - 32) * 5 / 9;
        jsonObject.put("F Value", fahrenheit);
        jsonObject.put("C Value", celsius);

        return Response.status(200)
                       .entity(jsonObject.toString(2))
                       .build();
    }

    @Path("{f}")
    @GET
    @Produces("application/json")
    public Response convertFtoCfromInput(@PathParam("f") double f) throws JSONException {

        JSONObject jsonObject = new JSONObject();
        double celsius = (f - 32) * 5 / 9;
        jsonObject.put("F Value", f);
        jsonObject.put("C Value", celsius);

        return Response.status(200)
                       .entity(jsonObject.toString())
                       .build();
    }
}