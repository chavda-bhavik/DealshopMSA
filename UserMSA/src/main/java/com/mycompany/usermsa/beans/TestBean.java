/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.usermsa.beans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author bhavi
 */
@Named(value = "testBean")
@RequestScoped
public class TestBean {

    /**
     * Creates a new instance of TestBean
     */
    public TestBean() {
    }
    
}
