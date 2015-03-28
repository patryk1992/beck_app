package com.beck.beck_app;

import com.beck.beck_app.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-03-25T15:17:36")
@StaticMetamodel(Role.class)
public class Role_ { 

    public static volatile SingularAttribute<Role, Integer> id;
    public static volatile SingularAttribute<Role, String> roledesc;
    public static volatile SingularAttribute<Role, String> rolename;
    public static volatile ListAttribute<Role, User> userList;

}