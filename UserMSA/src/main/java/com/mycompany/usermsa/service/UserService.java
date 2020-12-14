/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.usermsa.service;

import com.mycompany.usermsa.entity.Usertb;
import com.mycompany.usermsa.model.UserModel;
import java.util.Collection;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author bhavi
 */
@Path("/users")
public class UserService {
    
    @Inject UserModel um;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Usertb> getAllUsers() {
        return um.getAllUsers();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Usertb registerUser(Usertb user) {
        return um.registerUser(user.getName(), user.getEmail(), user.getPassword(), 2);
    }
    
    @POST
    @Path("/business")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Usertb registerBusinessUser(Usertb user) {
        return um.registerUser(user.getName(), user.getEmail(), user.getPassword(), 1);
    }
}
