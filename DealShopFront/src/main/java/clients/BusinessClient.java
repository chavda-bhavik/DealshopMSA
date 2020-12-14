/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clients;

import entity.Businesstb;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 *
 * @author BHAVIK
 */
@RegisterRestClient(configKey = "businessclient")
@ApplicationScoped
public interface BusinessClient {
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Businesstb registerBusiness(Businesstb business);
    
    @POST
    @Path("/email")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Businesstb getBusinessByEmailId(String emailid);
    
    @PUT
    @Path("/{businessId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editBusinessProfile(Businesstb bu, @PathParam("businessId") int businessId);
    
    @GET
    @Path("/{businessId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Businesstb getBusinessById(@PathParam("businessId") int id);
}
