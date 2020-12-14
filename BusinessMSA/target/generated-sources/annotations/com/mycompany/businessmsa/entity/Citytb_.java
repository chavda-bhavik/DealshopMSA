package com.mycompany.businessmsa.entity;

import com.mycompany.businessmsa.entity.Businesstb;
import com.mycompany.businessmsa.entity.Statetb;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-14T08:34:35")
@StaticMetamodel(Citytb.class)
public class Citytb_ { 

    public static volatile SingularAttribute<Citytb, Statetb> stateID;
    public static volatile SingularAttribute<Citytb, String> name;
    public static volatile CollectionAttribute<Citytb, Businesstb> businesstbCollection;
    public static volatile SingularAttribute<Citytb, Integer> cityID;

}