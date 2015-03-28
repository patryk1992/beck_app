package com.beck.beck_app;

import com.beck.beck_app.Group1;
import com.beck.beck_app.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-03-25T15:17:36")
@StaticMetamodel(UserGroups.class)
public class UserGroups_ { 

    public static volatile SingularAttribute<UserGroups, Integer> id;
    public static volatile SingularAttribute<UserGroups, Group1> groupId;
    public static volatile SingularAttribute<UserGroups, String> status;
    public static volatile SingularAttribute<UserGroups, User> userId;

}