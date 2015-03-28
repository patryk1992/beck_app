package com.beck.beck_app;

import com.beck.beck_app.GroupEvents;
import com.beck.beck_app.UserGroups;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-03-28T15:50:50")
@StaticMetamodel(Group1.class)
public class Group1_ { 

    public static volatile SingularAttribute<Group1, String> groupName;
    public static volatile SingularAttribute<Group1, String> groupDesc;
    public static volatile ListAttribute<Group1, UserGroups> userGroupsList;
    public static volatile SingularAttribute<Group1, Integer> idgroup;
    public static volatile ListAttribute<Group1, GroupEvents> groupEventsList;

}