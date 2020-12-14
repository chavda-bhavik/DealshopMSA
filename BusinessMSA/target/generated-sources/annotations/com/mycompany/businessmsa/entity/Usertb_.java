package com.mycompany.businessmsa.entity;

import com.mycompany.businessmsa.entity.Businesstb;
import com.mycompany.businessmsa.entity.Usercategorytb;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-14T08:34:35")
@StaticMetamodel(Usertb.class)
public class Usertb_ { 

    public static volatile SingularAttribute<Usertb, String> password;
    public static volatile SingularAttribute<Usertb, Usercategorytb> userCategoryID;
    public static volatile SingularAttribute<Usertb, String> name;
    public static volatile CollectionAttribute<Usertb, Businesstb> businesstbCollection;
    public static volatile SingularAttribute<Usertb, Integer> userID;
    public static volatile SingularAttribute<Usertb, String> email;

}