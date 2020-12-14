package com.mycompany.businessmsa.entity;

import com.mycompany.businessmsa.entity.Businesstb;
import com.mycompany.businessmsa.entity.Citytb;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-14T08:34:35")
@StaticMetamodel(Statetb.class)
public class Statetb_ { 

    public static volatile CollectionAttribute<Statetb, Citytb> citytbCollection;
    public static volatile SingularAttribute<Statetb, Integer> stateID;
    public static volatile SingularAttribute<Statetb, String> name;
    public static volatile CollectionAttribute<Statetb, Businesstb> businesstbCollection;

}