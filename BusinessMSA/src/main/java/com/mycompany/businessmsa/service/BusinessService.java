/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.businessmsa.service;

import com.mycompany.businessmsa.entity.Businesstb;
import com.mycompany.businessmsa.model.BusinessModel;
import java.util.Collection;
import javax.inject.Inject;
import javax.ws.rs.Path;
import org.eclipse.microprofile.jwt.JsonWebToken;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author bhavi
 */
@Path("/business")
public class BusinessService {
    
    @Inject BusinessModel bm;
    @Inject JsonWebToken token;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Businesstb> getAllBusinesses() {
        return bm.getAllBusinesses();
    }
    
    @GET
    @Path("/{businessId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Businesstb getBusinessDetails(@PathParam("businessId") int BusinessId) {
        return bm.getBusinessDetails(BusinessId);
    }
    
    @POST
    @Path("/email")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Businesstb getBusinessDetailsByEmail(String emailId) {
        System.out.println("get request for business email id " + emailId);
        return bm.getBusinessByUserEmail(emailId);
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Businesstb registerBusiness(Businesstb business) {
        return bm.RegisterBusiness(business.getBusinessName(), business.getEmailID(), business.getAddress(), business.getCustomerCarePhoneNo(), business.getReservationPhoneNo(), business.getDaysOfOperation(), business.getHoursOfOperation(), business.getLocation(), business.getNeedToKnow(), business.getAwardsRecognition(), business.getBusinessCategoryID().getCategoryID(), business.getBusinessTypeID().getBusinessTypeID(), business.getStateID().getStateID(), business.getCityID().getCityID(), business.getUserID().getUserID());
    }
    
    @PUT
    @Path("/{businessId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editBusiness(Businesstb bu, @PathParam("businessId") int businessId) {
        bm.editBusinessProfile(businessId, bu.getBusinessName(), bu.getEmailID(), bu.getAddress(), bu.getCustomerCarePhoneNo(), bu.getReservationPhoneNo(), bu.getDaysOfOperation(), bu.getHoursOfOperation(), bu.getLocation(), bu.getNeedToKnow(), bu.getAwardsRecognition(), bu.getBusinessCategoryID().getCategoryID(), bu.getBusinessTypeID().getBusinessTypeID(), bu.getStateID().getStateID(), bu.getCityID().getCityID(), bu.getBankAccountNo(), bu.getBankName(), bu.getIFSCCode());
    }
}
