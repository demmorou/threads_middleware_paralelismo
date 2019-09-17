/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;

/**
 * REST Web Service
 *
 * @author deusimar
 */
@Path("mediana")
public class ServerMediana {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServerMediana
     */
    public ServerMediana() {
    }

    /**
     * Retrieves representation of an instance of sd.ServerMediana
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    
    @GET
    @Produces("application/json")
    @Path("/pegar/{valores}")
    public String getMatrix(@PathParam("valores") PathSegment pathSegment) {
        StringBuilder builder = new StringBuilder();
        float result = 0;
        MultivaluedMap matrix = pathSegment.getMatrixParameters();
        for(Object key : matrix.entrySet()){
            result += Float.valueOf(key.toString().split("=")[0]);
        }
        return String.valueOf(result/matrix.size());
    }

    /**
     * PUT method for updating or creating an instance of ServerMediana
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
