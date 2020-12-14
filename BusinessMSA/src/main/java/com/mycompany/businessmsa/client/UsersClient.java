/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.businessmsa.client;

import com.mycompany.businessmsa.entity.Usertb;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 *
 * @author bhavi
 */
// baseUri = http://localhost:8080/UserMSA/rest/users
@RegisterRestClient(configKey = "userclient")
@ApplicationScoped
public interface UsersClient {
    
    @POST
    //@ClientHeaderParam(name="authorization", value="{generateJWTToken}")
    //@RolesAllowed("Business")
    @Path("/business")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Usertb registerBusinessUser(Usertb user);
    
    default String generateJWTToken() {
        RawToken raw = new RawToken();
        Config config = ConfigProvider.getConfig();
        String token ="Bearer "+ RawToken.getRtoken();
        System.out.println("User Token = "+token);
        return token;
    }
}
