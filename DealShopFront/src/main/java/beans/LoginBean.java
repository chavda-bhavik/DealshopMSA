/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import beans.business.BusinessBean;
import clients.BusinessClient;
import entity.Businesstb;
import entity.Usertb;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;
import token.MyCredentials;

/**
 *
 * @author bhavik
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean {

    @Inject private SecurityContext securityContext;
    @Inject private IdentityStoreHandler identityStoreHandler;
    @Inject private MyCredentials mycredentials;
    @Inject @RestClient BusinessClient bc;
    //FacesContext facesContext = FacesContext.getCurrentInstance();
    //HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
    //HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
    //HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
    
    Pbkdf2PasswordHashImpl pbkd;
    
    BusinessBean businessBean;
    private String email;
    private String username;
    private String password;
    private String confirmpassword;
    private String message;
    private AuthenticationStatus status;
    private Set<String> roles;

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public SecurityContext getSecurityContext() {
        return securityContext;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    public AuthenticationStatus getStatus() {
        return status;
    }

    public void setStatus(AuthenticationStatus status) {
        this.status = status;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public LoginBean() {
        pbkd = new Pbkdf2PasswordHashImpl();
        businessBean = new BusinessBean();
    }
    
    public void checkLoginAndRedirect() throws IOException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Object h = request.getSession().getAttribute("token");
        if(h==null) {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/user2/Home.jsf");
        }
    }
    
    public Boolean isUserLoggedIn() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        System.out.println(request.getSession().getAttribute("token"));
        return request.getSession().getAttribute("token") != null;
    }
    
    public String goToLogin() {
        return "/user2/Login.jsf?faces-redirect=true";
    }
    
    public String goToUserRegister() {
        return "/user/Register.jsf?faces-redirect=true";
    }
    
    public String goToBusinessRegister() {
        return "/business/Register.jsf?faces-redirect=true";
    }
    
    private  <T> List<T> convertSetToList(Set<T> set) {
        List<T> list = new ArrayList<>(); 
        for (T t : set) 
            list.add(t);
        return list; 
    }    
    
    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        Credential credential = new UsernamePasswordCredential(email,new Password(password));
        CredentialValidationResult result = identityStoreHandler.validate(credential);
        System.out.println(result.getStatus());
        if (result.getStatus() == CredentialValidationResult.Status.VALID) {
            System.out.println("JWTAuthenticationMechanism - CreatingCredential credential1 = new UserNamePasswordCredential token");
//            System.out.println(result.getCallerGroups().toString());
            HttpSession h = request.getSession();
            
            mycredentials.setSubject(email);
            mycredentials.setGroups(convertSetToList(result.getCallerGroups()));
            mycredentials.setLoginStatus("Login_Success");
            mycredentials.setStausMessage("Hello " + result.getCallerGroups().toString()+" Login Success !!");
               
            h.setAttribute("useremail", email);
            h.setAttribute("groups", convertSetToList(result.getCallerGroups()));
            h.setAttribute("statusmessage", "Hello " + result.getCallerGroups().toString()+" Login Success !!");
            h.setAttribute("status",  "Login_Success");
            
            context.responseComplete();
            if(result.getCallerGroups().contains("Admin"))
            {
                System.out.println("In admin");
                h.setAttribute("logged-group", "Admin");
                return "/admin/statelist.jsf?faces-redirect=true";
            }
            else if(result.getCallerGroups().contains("User"))
            {
                System.out.println("In User");
                h.setAttribute("logged-group", "User");
                return "/user2/Home.jsf";
            }
            else if(result.getCallerGroups().contains("Business"))
            {
                System.out.println("In Business");
                Businesstb business = new Businesstb();
                business = bc.getBusinessByEmailId(email);
                h.setAttribute("businessid", business.getBusinessID());
                h.setAttribute("businessstatus", business.getIsVerified());
                h.setAttribute("logged-group", "Business");
                return "/business/Home.jsf?faces-redirect=true";
            }
        } else {
            message = "Email/password is wrong !!!";
        }
        return "/user2/Login.jsf";
    }
   
    public String registerUser() {
        Usertb ur = new Usertb();
        ur.setEmail(email);
        ur.setName(username);
        ur.setPassword(pbkd.generate(password.toCharArray()));
//        commonClient.registerCommonUser(ur);
        return "/user2/Login.jsf";
    }
    
    public String getTimeDate(Date date) throws ParseException {
        String[] monthNames = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
        String oldDate = date.toString();
        DateFormat format = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
        Date testDate = (Date) format.parse(oldDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(testDate);
        String temp = monthNames[cal.get(Calendar.MONTH)]+" "+cal.get(Calendar.DATE)+", "+cal.get(Calendar.YEAR);
        return temp;
    }
    
    private static void addError(FacesContext context, String message) {
        context = FacesContext.getCurrentInstance();
        context.addMessage(null,new FacesMessage(SEVERITY_ERROR, message, null));
    }
    
    public String logout() throws ServletException {
        System.out.println("In Log out");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession().removeAttribute("logged-group");
        request.getSession().removeAttribute("businessid");
        
        request.logout();
        request.getSession().invalidate();  
        //return "/user/Home.jsf";
        return "/user2/Login.jsf";
    }
    public String logoutUser() throws ServletException {
        System.out.println("In Log out");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        session.removeAttribute("logged-group");
        session.removeAttribute("useremail");
        session.removeAttribute("role");
        session.removeAttribute("userid");
        session.removeAttribute("username");
        
        request.logout();
        request.getSession().invalidate();
        return "/user2/Login.jsf";
    }
}
