/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.commonmsa.service;

import com.mycompany.commonmsa.entity.Statetb;
import java.util.Collection;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.StateModel;

/**
 *
 * @author BHAVIK
 */
@Path("/states")
public class StateService {
    
    @Inject StateModel sm;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Statetb> getAllStates() {
        return sm.getAllStates();
    }
    
    @POST
    @RolesAllowed("Admin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Statetb addState(Statetb state) {
        return sm.addState(state.getName());
    }
    
    @PUT
    @Path("/{stateId}")
    @RolesAllowed("Admin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Statetb editState(@PathParam("stateId") int id, Statetb state) {
        return sm.editState(id, state.getName());
    }
    
    @DELETE
    @Path("/{stateId}")
    @RolesAllowed("Admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Statetb deleteState(@PathParam("stateId") int id) {
        return sm.removeState(id);
    }
}
