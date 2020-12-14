package com.mycompany.businessmsa.entity;

import com.mycompany.businessmsa.entity.Businesstb;
import com.mycompany.businessmsa.entity.Businesstypetb;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-14T08:34:35")
@StaticMetamodel(Businesscategorytb.class)
public class Businesscategorytb_ { 

    public static volatile SingularAttribute<Businesscategorytb, String> image;
    public static volatile CollectionAttribute<Businesscategorytb, Businesstypetb> businesstypetbCollection;
    public static volatile SingularAttribute<Businesscategorytb, String> name;
    public static volatile CollectionAttribute<Businesscategorytb, Businesstb> businesstbCollection;
    public static volatile SingularAttribute<Businesscategorytb, Integer> categoryID;

}