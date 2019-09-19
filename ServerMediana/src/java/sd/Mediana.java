/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author junior
 */
@Path("mediana")
public class Mediana {
    @Context
    private UriInfo context;
    
    /**
     * Creates a new instance of Mediana
     */
    public Mediana() {
    }

    /**
     * Retrieves representation of an instance of sd.Mediana
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
    @Path("/pegar/{uuid}")
    public String getMatrix(@PathParam("uuid") PathSegment pathSegment) {
        StringBuilder builder = new StringBuilder();
        float result = 0;
        ArrayList<Float> vetor = new ArrayList<Float>();
        MultivaluedMap matrix = pathSegment.getMatrixParameters();
        for(Object key : matrix.entrySet()){
//            result += Float.valueOf(key.toString().split("=")[0]);
            vetor.add(Float.valueOf(key.toString().split("=")[0]));
            
        }
        vetor.sort(null);
        
        int tipo = vetor.size() % 2;
        int meio = vetor.size() / 2;

		if (tipo == 1) {
                        try {
                            TimeUnit.SECONDS.sleep(10);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Mediana.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println(String.valueOf(vetor.get(meio)));
                        return String.valueOf(vetor.get(meio));

		} else {
                        try {
                            TimeUnit.SECONDS.sleep(10);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Mediana.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println(String.valueOf((vetor.get(meio-1) + vetor.get(meio)) / 2));
                        return String.valueOf((vetor.get(meio-1) + vetor.get(meio)) / 2);

		}
    }

    /**
     * PUT method for updating or creating an instance of Mediana
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
