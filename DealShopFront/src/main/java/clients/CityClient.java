/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clients;

import entity.Citytb;
import entity.Statetb;
import java.util.Collection;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import token.GenerateToken;

/**
 *
 * @author BHAVIK
 */
@RegisterRestClient(configKey = "cityclient")
@ApplicationScoped
public interface CityClient {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Citytb> getAllCities();
    
    @POST
    @ClientHeaderParam(name="authorization", value="{generateJWTToken}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Citytb addCity(Citytb city);
    
    @PUT
    @Path("/{cityId}")
    @ClientHeaderParam(name="authorization", value="{generateJWTToken}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Citytb editCity(@PathParam("cityId") int CityId, Citytb city);
    
    @DELETE
    @Path("/{cityId}")
    @ClientHeaderParam(name="authorization", value="{generateJWTToken}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Citytb deleteCity(@PathParam("cityId") int CityId);
    
    default String generateJWTToken() {
        Config config = ConfigProvider.getConfig();
        String token ="Bearer "+ GenerateToken.generateJWT();
        System.out.println("Product Token = "+token);
        return token;
    }
}
