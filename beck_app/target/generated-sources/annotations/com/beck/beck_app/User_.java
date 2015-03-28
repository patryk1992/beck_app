package com.beck.beck_app;

import com.beck.beck_app.Address;
import com.beck.beck_app.Communication;
import com.beck.beck_app.Role;
import com.beck.beck_app.UserGroups;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-03-28T15:50:50")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Integer> id;
    public static volatile ListAttribute<User, Role> roleList;
    public static volatile SingularAttribute<User, String> username;
    public static volatile SingularAttribute<User, String> email;
    public static volatile ListAttribute<User, UserGroups> userGroupsList;
    public static volatile SingularAttribute<User, String> lastname;
    public static volatile SingularAttribute<User, String> firstname;
    public static volatile ListAttribute<User, Communication> communicationList;
    public static volatile SingularAttribute<User, String> password;
    public static volatile ListAttribute<User, Communication> communicationList1;
    public static volatile SingularAttribute<User, Address> addressId;

}