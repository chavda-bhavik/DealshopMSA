/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.mycompany.commonmsa.entity.Citytb;
import com.mycompany.commonmsa.entity.Statetb;
import java.util.Collection;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author BHAVIK
 */
@Named
@ApplicationScoped
public class CityModel {
    EntityManager em;

    public CityModel() {
        em = Persistence.createEntityManagerFactory("pu").createEntityManager();
    }
    
    public Collection<Citytb> getAllCities() {
        return em.createNamedQuery("Citytb.findAll").getResultList();
    }
    
    public Citytb addCity(String Name, int StateID) {
        em.getTransaction().begin();
        Statetb state = em.find(Statetb.class, StateID);
//        int total = Integer.parseInt(em.createNamedQuery("Citytb.countByName").setParameter("name", Name).getParameter("total").toString());
//        System.out.println(total);
//        if(total == 0) {
            Collection<Citytb> stateCities = state.getCitytbCollection();
            Citytb city = new Citytb();
            city.setName(Name);
            city.setStateID(state);
            stateCities.add(city);
            em.merge(state);
            em.persist(city);
//        }
        em.getTransaction().commit();
        return city;
    }

    public Citytb editCity(int CityID, String Name, int StateID) {
        em.getTransaction().begin();
        Citytb city = em.find(Citytb.class, CityID);
        city.setName(Name);
        if(city.getStateID().getStateID() != StateID) {
            Statetb state = em.find(Statetb.class, StateID);
            city.setStateID(state);
            Collection<Citytb> stateCities = state.getCitytbCollection();
            stateCities.add(city);
            state.setCitytbCollection(stateCities);
            em.merge(state);
        }
        em.merge(city);
        em.getTransaction().commit();
        return city;
    }

    public Citytb removeCity(int CityID) {
        em.getTransaction().begin();
        Citytb city = em.find(Citytb.class, CityID);
        em.remove(city);
        em.getTransaction().commit();
        return city;
    }
}
