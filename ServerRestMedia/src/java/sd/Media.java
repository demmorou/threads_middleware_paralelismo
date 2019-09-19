/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author deusimar
 */
@Path("media")
public class Media {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Media
     */
    public Media() {
    }

    /**
     * Retrieves representation of an instance of sd.Media
     * @return an instance of java.lang.String
     */
    
    @GET
    @Produces("application/json")
    @Path("/pegar/{uuid}")
    public String getMatrix(@PathParam("uuid") PathSegment pathSegment) {
        StringBuilder builder = new StringBuilder();
        float result = 0;
        MultivaluedMap matrix = pathSegment.getMatrixParameters();
        for(Object key : matrix.entrySet()){
            result += Float.valueOf(key.toString().split("=")[0]);
        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ex) {
            Logger.getLogger(Media.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(String.valueOf(result/matrix.size()));
        return String.valueOf(result/matrix.size());
    }
    
    /**
     * PUT method for updating or creating an instance of Media
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
    
}
