package com.beck.beck_app;

import com.beck.beck_app.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-03-25T15:17:36")
@StaticMetamodel(Address.class)
public class Address_ { 

    public static volatile SingularAttribute<Address, Integer> id;
    public static volatile SingularAttribute<Address, String> suburb;
    public static volatile SingularAttribute<Address, String> street;
    public static volatile ListAttribute<Address, User> userList;
    public static volatile SingularAttribute<Address, String> city;
    public static volatile SingularAttribute<Address, String> country;

}