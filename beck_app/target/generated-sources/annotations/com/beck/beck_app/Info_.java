package com.beck.beck_app;

import com.beck.beck_app.Event;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-03-28T15:50:50")
@StaticMetamodel(Info.class)
public class Info_ { 

    public static volatile SingularAttribute<Info, Integer> id;
    public static volatile SingularAttribute<Info, String> addInfo;
    public static volatile SingularAttribute<Info, String> startDate;
    public static volatile SingularAttribute<Info, String> infoDesc;
    public static volatile SingularAttribute<Info, Event> eventId;
    public static volatile SingularAttribute<Info, String> infoName;
    public static volatile SingularAttribute<Info, String> endDate;
    public static volatile SingularAttribute<Info, String> type;

}