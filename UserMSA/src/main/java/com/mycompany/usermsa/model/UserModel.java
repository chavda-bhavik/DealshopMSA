/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.usermsa.model;

import com.mycompany.usermsa.entity.Usercategorytb;
import com.mycompany.usermsa.entity.Usertb;
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
public class UserModel {
    
    EntityManager em;

    public UserModel() {
        em = Persistence.createEntityManagerFactory("pu").createEntityManager();
    }
    
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    public Collection<Usertb> getAllUsers() {
        return em.createNamedQuery("Usertb.findAll").getResultList();
    }
    
    public Usertb registerUser(String name, String email, String password, int category) {
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();
        Usercategorytb ucategory = em.find(Usercategorytb.class, category);
        Collection<Usertb> users = ucategory.getUsertbCollection();
        
        Usertb user = new Usertb();
        user.setUserCategoryID(ucategory);
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        users.add(user);
        
        em.persist(user);
        em.merge(ucategory);
        em.getTransaction().commit();
        System.out.println("User Registered "+user.getUserID());
        return user;
    }
}
