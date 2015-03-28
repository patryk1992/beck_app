package com.beck.beck_app;

import com.beck.beck_app.Event;
import com.beck.beck_app.Point;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-03-28T15:50:50")
@StaticMetamodel(Track.class)
public class Track_ { 

    public static volatile SingularAttribute<Track, Integer> id;
    public static volatile ListAttribute<Track, Point> pointList;
    public static volatile SingularAttribute<Track, Event> eventId;
    public static volatile SingularAttribute<Track, String> trackDesc;
    public static volatile SingularAttribute<Track, String> trackName;

}