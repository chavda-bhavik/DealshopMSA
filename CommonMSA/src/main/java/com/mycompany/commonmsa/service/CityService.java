/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.commonmsa.service;

import com.mycompany.commonmsa.entity.Citytb;
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
import model.CityModel;

/**
 *
 * @author BHAVIK
 */
@Path("/cities")
public class CityService {
    @Inject CityModel cm;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Citytb> getAllCities() {
        return cm.getAllCities();
    }
    
    @POST
    @RolesAllowed("Admin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Citytb addState(Citytb city) {
        return cm.addCity(city.getName(), city.getStateID().getStateID());
    }
    
    @PUT
    @Path("/{cityId}")
    @RolesAllowed("Admin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Citytb editCity(@PathParam("cityId") int id, Citytb city) {
        return cm.editCity(id, city.getName(), city.getStateID().getStateID());
    }
    
    @DELETE
    @Path("/{cityId}")
    @RolesAllowed("Admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Citytb deleteCity(@PathParam("cityId") int id) {
        return cm.removeCity(id);
    }
}
