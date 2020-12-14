/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clients;

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
@RegisterRestClient(configKey = "stateclient")
@ApplicationScoped
public interface StateClient {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Statetb> getAllStates();
    
    @POST
    @ClientHeaderParam(name="authorization", value="{generateJWTToken}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Statetb addState(Statetb state);
    
    @PUT
    @Path("/{stateId}")
    @ClientHeaderParam(name="authorization", value="{generateJWTToken}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Statetb editState(@PathParam("stateId") int StateId, Statetb state);
    
    @DELETE
    @Path("/{stateId}")
    @ClientHeaderParam(name="authorization", value="{generateJWTToken}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Statetb deleteState(@PathParam("stateId") int StateId);
    
    default String generateJWTToken() {
        Config config = ConfigProvider.getConfig();
        String token ="Bearer "+ GenerateToken.generateJWT();
        System.out.println("Product Token = "+token);
        return token;
    }
}
