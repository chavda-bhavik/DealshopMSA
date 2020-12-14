package com.mycompany.commonmsa.entity;

import com.mycompany.commonmsa.entity.Businesscategorytb;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-14T10:13:31")
@StaticMetamodel(Businesstypetb.class)
public class Businesstypetb_ { 

    public static volatile SingularAttribute<Businesstypetb, String> image;
    public static volatile SingularAttribute<Businesstypetb, String> name;
    public static volatile SingularAttribute<Businesstypetb, Businesscategorytb> businessCategoryID;
    public static volatile SingularAttribute<Businesstypetb, Integer> businessTypeID;

}