package com.beck.beck_app;

import com.beck.beck_app.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-03-25T15:17:36")
@StaticMetamodel(Communication.class)
public class Communication_ { 

    public static volatile SingularAttribute<Communication, Integer> id;
    public static volatile SingularAttribute<Communication, String> message;
    public static volatile SingularAttribute<Communication, String> title;
    public static volatile SingularAttribute<Communication, User> userIdTo;
    public static volatile SingularAttribute<Communication, User> userIdFrom;

}