/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
public class StateModel {
    EntityManager em;

    public StateModel() {
        em = Persistence.createEntityManagerFactory("pu").createEntityManager();
    }

    public Collection<Statetb> getAllStates() {
        return em.createNamedQuery("Statetb.findAll").getResultList();
    }
    
    public Statetb addState(String Name) {
        em.getTransaction().begin();
        Statetb state = new Statetb();
        state.setName(Name);
        em.persist(state);
        em.getTransaction().commit();
        return state;
    }

    public Statetb editState(int StateID, String Name) {
        em.getTransaction().begin();
        Statetb state = em.find(Statetb.class, StateID);
        state.setName(Name);
        em.merge(state);
        em.getTransaction().commit();
        return state;
    }

    public Statetb removeState(int StateID) {
        em.getTransaction().begin();
        Statetb state = em.find(Statetb.class, StateID);
        if(state!=null) {
            em.remove(state);
        }
        em.getTransaction().commit();
        return state;
    }
}
