package com.beck.beck_app;

import com.beck.beck_app.GroupEvents;
import com.beck.beck_app.Info;
import com.beck.beck_app.Track;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-03-28T16:31:51")
@StaticMetamodel(Event.class)
public class Event_ { 

    public static volatile SingularAttribute<Event, String> eventDesc;
    public static volatile ListAttribute<Event, Track> trackList;
    public static volatile SingularAttribute<Event, String> eventName;
    public static volatile ListAttribute<Event, GroupEvents> groupEventsList;
    public static volatile SingularAttribute<Event, Integer> id;
    public static volatile ListAttribute<Event, Info> infoList;
    public static volatile SingularAttribute<Event, String> status;

}