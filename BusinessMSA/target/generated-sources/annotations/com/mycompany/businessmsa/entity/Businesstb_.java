package com.mycompany.businessmsa.entity;

import com.mycompany.businessmsa.entity.Businesscategorytb;
import com.mycompany.businessmsa.entity.Businessinfotb;
import com.mycompany.businessmsa.entity.Businesslinkstb;
import com.mycompany.businessmsa.entity.Businessphotostb;
import com.mycompany.businessmsa.entity.Businesstypetb;
import com.mycompany.businessmsa.entity.Citytb;
import com.mycompany.businessmsa.entity.Statetb;
import com.mycompany.businessmsa.entity.Usertb;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-14T08:34:35")
@StaticMetamodel(Businesstb.class)
public class Businesstb_ { 

    public static volatile SingularAttribute<Businesstb, String> address;
    public static volatile SingularAttribute<Businesstb, String> daysOfOperation;
    public static volatile SingularAttribute<Businesstb, Integer> isVerified;
    public static volatile CollectionAttribute<Businesstb, Businesslinkstb> businesslinkstbCollection;
    public static volatile SingularAttribute<Businesstb, Statetb> stateID;
    public static volatile SingularAttribute<Businesstb, Integer> businessID;
    public static volatile SingularAttribute<Businesstb, String> businessName;
    public static volatile SingularAttribute<Businesstb, Long> bankAccountNo;
    public static volatile SingularAttribute<Businesstb, String> emailID;
    public static volatile SingularAttribute<Businesstb, String> hoursOfOperation;
    public static volatile SingularAttribute<Businesstb, String> bankName;
    public static volatile SingularAttribute<Businesstb, Citytb> cityID;
    public static volatile SingularAttribute<Businesstb, Usertb> userID;
    public static volatile SingularAttribute<Businesstb, String> customerCarePhoneNo;
    public static volatile SingularAttribute<Businesstb, Businesstypetb> businessTypeID;
    public static volatile SingularAttribute<Businesstb, String> reservationPhoneNo;
    public static volatile CollectionAttribute<Businesstb, Businessinfotb> businessinfotbCollection;
    public static volatile SingularAttribute<Businesstb, String> awardsRecognition;
    public static volatile SingularAttribute<Businesstb, String> needToKnow;
    public static volatile SingularAttribute<Businesstb, String> iFSCCode;
    public static volatile SingularAttribute<Businesstb, Businesscategorytb> businessCategoryID;
    public static volatile SingularAttribute<Businesstb, String> location;
    public static volatile CollectionAttribute<Businesstb, Businessphotostb> businessphotostbCollection;

}