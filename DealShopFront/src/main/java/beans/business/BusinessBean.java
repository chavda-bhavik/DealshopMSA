/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.business;

import clients.BusinessClient;
import clients.UserClient;
import entity.Businesscategorytb;
import entity.Businesstb;
import entity.Businesstypetb;
import entity.Citytb;
import entity.Statetb;
import entity.Usertb;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

/**
 *
 * @author BHAVIK
 */
@Named(value = "businessBean")
@SessionScoped
public class BusinessBean implements Serializable {

    @Inject @RestClient UserClient userClient;
    @Inject @RestClient BusinessClient businessClient;
    
    Pbkdf2PasswordHashImpl pbkd;
    Businesstb business;
    private String UserName;
    private String UserEmail;
    private String Userpassword;
    private String ConfirmUserPassword;
    private String passwordError;
    
    private String BusinessName;
    private String BusinessEmailID;
    private String Address;

    public Businesstb getBusiness() {
        return business;
    }

    public void setBusiness(Businesstb business) {
        this.business = business;
    }
    
    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String UserEmail) {
        this.UserEmail = UserEmail;
    }

    public String getUserpassword() {
        return Userpassword;
    }

    public void setUserpassword(String Userpassword) {
        this.Userpassword = Userpassword;
    }

    public String getConfirmUserPassword() {
        return ConfirmUserPassword;
    }

    public void setConfirmUserPassword(String ConfirmUserPassword) {
        this.ConfirmUserPassword = ConfirmUserPassword;
    }

    public String getBusinessName() {
        return BusinessName;
    }

    public void setBusinessName(String BusinessName) {
        this.BusinessName = BusinessName;
    }

    public String getBusinessEmailID() {
        return BusinessEmailID;
    }

    public void setBusinessEmailID(String BusinessEmailID) {
        this.BusinessEmailID = BusinessEmailID;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }
    
    // Register
    public String goToRegisterBusiness() {
        return "/business/Register.jsf";
    }
    public String RBusiness() {
        System.out.println("Register Business Called");
        
        Statetb state = new Statetb();
        state.setStateID(11);
        Citytb city = new Citytb();
        city.setCityID(3);

        Businesscategorytb bcateg = new Businesscategorytb();
        bcateg.setCategoryID(3);
        Businesstypetb btyp = new Businesstypetb();
        btyp.setBusinessTypeID(2);
        Usertb user = this.registerAndGetUser();

        Businesstb bins = new Businesstb();
        bins.setAddress(Address);
        bins.setBusinessName(BusinessName);
        bins.setEmailID(BusinessEmailID);
        bins.setUserID(user);
        bins.setStateID(state);
        bins.setCityID(city);
        bins.setBusinessCategoryID(bcateg);
        bins.setBusinessTypeID(btyp);
        bins.setCustomerCarePhoneNo("");
        bins.setReservationPhoneNo("");
        bins.setNeedToKnow("");
        bins.setHoursOfOperation("");
        bins.setLocation("");
        bins.setAwardsRecognition("");
        bins.setDaysOfOperation("");

        businessClient.registerBusiness(bins);
        return "/business/Register.jsf?faces-redirect=true";
    }
    public Usertb registerAndGetUser() {
        System.out.println("regsiter called "+UserEmail+" "+UserName+" "+Userpassword);
        Usertb user = new Usertb();
        user.setEmail(UserEmail);
        user.setName(UserName);
        user.setPassword(pbkd.generate(Userpassword.toCharArray()));
        user = userClient.registerBusinessUser(user);
        return user;
    }
    
    public void getAndSetBusinessDetails() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession h = request.getSession();
        if(h.getAttribute("businessid") == null) {
            String email = h.getAttribute("useremail").toString();
            System.out.println("setting bussiness session logged user email "+email);
            //Businesstb business = new Businesstb();
            try {
                business = businessClient.getBusinessByEmailId(email);
                //h.setAttribute("businessid", business.getBusinessID());
                //h.setAttribute("businessstatus", business.getIsVerified());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                System.out.println(e.getCause());
            }
            //business = businessClient.getBusinessByEmailId(email);
        }
    }
    public String submitBusiness() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String token = request.getSession().getAttribute("token").toString();
        String businessId = request.getSession().getAttribute("businessid").toString();
//        businessClient = new BusinessClient(token);
//        businessClient.submitBusiness(businessId);
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("businessBean");
        return "/business/details.jsf?faces-redirect=true";
    }
    // End of Home
    
    // Details
    public void getBusinessDetails() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String businessid = request.getSession().getAttribute("businessid").toString();
        business = businessClient.getBusinessById(Integer.valueOf(businessid));
    }
    public String editBusiness() {
        businessClient.editBusinessProfile(business, business.getBusinessID());
        return "/business/details.jsf?faces-redirect=true";
    }
    // End of Details
    
    public BusinessBean() {
        pbkd = new Pbkdf2PasswordHashImpl();
    }
    
}
