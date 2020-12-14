package com.mycompany.businessmsa.entity;

import com.mycompany.businessmsa.entity.Businesscategorytb;
import com.mycompany.businessmsa.entity.Businesstb;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-14T08:34:35")
@StaticMetamodel(Businesstypetb.class)
public class Businesstypetb_ { 

    public static volatile SingularAttribute<Businesstypetb, String> image;
    public static volatile SingularAttribute<Businesstypetb, String> name;
    public static volatile SingularAttribute<Businesstypetb, Businesscategorytb> businessCategoryID;
    public static volatile CollectionAttribute<Businesstypetb, Businesstb> businesstbCollection;
    public static volatile SingularAttribute<Businesstypetb, Integer> businessTypeID;

}