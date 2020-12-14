/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.businessmsa.model;

import com.mycompany.businessmsa.entity.Businesscategorytb;
import com.mycompany.businessmsa.entity.Businesstb;
import com.mycompany.businessmsa.entity.Businesstypetb;
import com.mycompany.businessmsa.entity.Citytb;
import com.mycompany.businessmsa.entity.Statetb;
import com.mycompany.businessmsa.entity.Usertb;
import java.util.Collection;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author bhavi
 */
@Named
@ApplicationScoped
public class BusinessModel {
    
    EntityManager em;
    EntityTransaction et;
    public BusinessModel() {
        em = Persistence.createEntityManagerFactory("pu").createEntityManager();
    }
    
    public Collection<Businesstb> getAllBusinesses() {
        return em.createNamedQuery("Businesstb.findAll").getResultList();
    }
    
    public Businesstb RegisterBusiness(String Name, String EmailID, String Address, String CustomerCareNumber, String ReservationNumber, String DaysOfOperation, String HoursOfOperation, String Location, String NeedToKnow, String AwardsRecognition, int CategoryID, int TypeID, int StateID, int CityID, int UserID) {
        Businesstb business = new Businesstb();
        try {
        System.out.println(Name+" "+EmailID+" "+Address+" "+CustomerCareNumber+" "+ReservationNumber+" "+DaysOfOperation+" "+HoursOfOperation+" "+Location+" "+NeedToKnow+" "+AwardsRecognition+" "+CategoryID+" "+TypeID+" "+StateID+" "+CityID+" "+UserID);
        
        em.getTransaction().begin();
        
        Businesscategorytb bcategory = em.find(Businesscategorytb.class, CategoryID);
        Collection<Businesstb> categories = bcategory.getBusinesstbCollection();
        
        Businesstypetb btype = em.find(Businesstypetb.class, TypeID);
        Collection<Businesstb> types = btype.getBusinesstbCollection();
        
        Statetb state = em.find(Statetb.class, StateID);
        Collection<Businesstb> states = state.getBusinesstbCollection();
        
        Citytb city = em.find(Citytb.class, CityID);
        Collection<Businesstb> cities = city.getBusinesstbCollection();
        
        Usertb user = em.find(Usertb.class, UserID);
        Collection<Businesstb> users = user.getBusinesstbCollection();
        
        business.setBusinessName(Name);
        business.setEmailID(EmailID); 
        business.setAddress(Address);
        business.setCustomerCarePhoneNo("1");
        business.setReservationPhoneNo("1");
        business.setDaysOfOperation("1");
        business.setHoursOfOperation("1");
        business.setLocation("1");
        business.setNeedToKnow("1");
        business.setAwardsRecognition("1");
        business.setBusinessCategoryID(bcategory);
        business.setBusinessTypeID(btype);
        business.setCityID(city);
        business.setStateID(state);
        business.setUserID(user);
        business.setIsVerified(0);
        
        categories.add(business);
        types.add(business);
        states.add(business);
        cities.add(business);
        users.add(business);
        
        em.persist(business);
        em.merge(bcategory);
        em.merge(btype);
        em.merge(city);
        em.merge(state);
        em.getTransaction().commit();
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return business;
    }
    
    public void editBusinessProfile(int BusinessID, String Name, String EmailID, String Address, String CustomerCareNumber, String ReservationNumber, String DaysOfOperation, String HoursOfOperation, String Location, String NeedToKnow, String AwardsRecognition, int CategoryID, int TypeID, int StateID, int CityID, Long AccountNo, String BankName, String IFSCCode) {
        System.out.println("edit from bean");
        System.out.println(Name+" "+EmailID+" "+Address+" "+CustomerCareNumber+" "+ReservationNumber+" "+DaysOfOperation+" "+HoursOfOperation+" "+Location+" "+NeedToKnow+" "+AwardsRecognition+" "+CategoryID+" "+TypeID+" "+StateID+" "+CityID+" "+AccountNo+" "+BankName+" "+IFSCCode);
        
        em.getTransaction().begin();
        
        Businesscategorytb bcategory = em.find(Businesscategorytb.class, CategoryID);
        Collection<Businesstb> categories = bcategory.getBusinesstbCollection();
        
        Businesstypetb btype = em.find(Businesstypetb.class, TypeID);
        Collection<Businesstb> types = btype.getBusinesstbCollection();
        
        Statetb state = em.find(Statetb.class, StateID);
        Collection<Businesstb> states = state.getBusinesstbCollection();
        
        Citytb city = em.find(Citytb.class, CityID);
        Collection<Businesstb> cities = city.getBusinesstbCollection();
        
        Businesstb business = em.find(Businesstb.class, BusinessID);
        business.setBusinessName(Name);
        business.setEmailID(EmailID);
        business.setAddress(Address);
        business.setCustomerCarePhoneNo(CustomerCareNumber);
        business.setReservationPhoneNo(ReservationNumber);
        business.setDaysOfOperation(DaysOfOperation);
        business.setHoursOfOperation(HoursOfOperation);
        business.setLocation(Location);
        business.setNeedToKnow(NeedToKnow);
        business.setAwardsRecognition(AwardsRecognition);
        business.setBusinessCategoryID(bcategory);
        business.setBusinessTypeID(btype);
        business.setCityID(city);
        business.setStateID(state);
        business.setBankAccountNo(AccountNo);
        business.setBankName(BankName);
        business.setIFSCCode(IFSCCode);

        if(business.getStateID() != state) {
            Statetb oldState = business.getStateID();
            Collection<Businesstb> oldStateBusinesses = oldState.getBusinesstbCollection();
            oldStateBusinesses.remove(business);
            oldState.setBusinesstbCollection(oldStateBusinesses);
            em.merge(oldState);
        }
        if(business.getCityID() != city) {
            Citytb oldCity = business.getCityID();
            Collection<Businesstb> oldCityBusinesses = oldCity.getBusinesstbCollection();
            oldCityBusinesses.remove(business);
            oldCity.setBusinesstbCollection(oldCityBusinesses);
            em.merge(oldCity);
        }
        if(business.getBusinessCategoryID() != bcategory) {
            Businesscategorytb oldBCategory = business.getBusinessCategoryID();
            Collection<Businesstb> oldBCategBusinesses = oldBCategory.getBusinesstbCollection();
            oldBCategBusinesses.remove(business);
            oldBCategory.setBusinesstbCollection(oldBCategBusinesses);
            em.merge(oldBCategory);
        }
        if(business.getBusinessTypeID() != btype) {
            Businesstypetb oldBType = business.getBusinessTypeID();
            Collection<Businesstb> oldBTypeBusinesses = oldBType.getBusinesstbCollection();
            oldBTypeBusinesses.remove(business);
            oldBType.setBusinesstbCollection(oldBTypeBusinesses);
            em.merge(oldBType);
        }
        
        em.merge(business);
        categories.add(business);
        em.merge(bcategory);
        types.add(business);
        em.merge(btype);
        cities.add(business);
        em.merge(city);
        states.add(business);
        em.merge(state);
        em.getTransaction().commit();
    }
    
    public Businesstb getBusinessDetails(int BusinessID) {
        Businesstb business = em.find(Businesstb.class, BusinessID);
        return business;
    }
    
    public Businesstb getBusinessByUserEmail(String UserEmailId) {
        Usertb user = (Usertb) em.createNamedQuery("Usertb.findByEmail").setParameter("email", UserEmailId).getSingleResult();
        Collection<Businesstb> userBussinesses = user.getBusinesstbCollection();
        Businesstb business = new Businesstb();
        for (Businesstb userBussinesse : userBussinesses) {
            business = userBussinesse;
        }
        return business;
    }
}
