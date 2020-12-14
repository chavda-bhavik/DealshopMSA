package com.mycompany.businessmsa.entity;

import com.mycompany.businessmsa.entity.Businesslinkstb;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-14T08:34:35")
@StaticMetamodel(Linkstb.class)
public class Linkstb_ { 

    public static volatile SingularAttribute<Linkstb, Integer> linkID;
    public static volatile CollectionAttribute<Linkstb, Businesslinkstb> businesslinkstbCollection;
    public static volatile SingularAttribute<Linkstb, String> name;
    public static volatile SingularAttribute<Linkstb, String> logo;

}