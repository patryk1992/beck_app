package com.beck.beck_app;

import com.beck.beck_app.Track;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-03-28T16:31:51")
@StaticMetamodel(Point.class)
public class Point_ { 

    public static volatile SingularAttribute<Point, Track> trackId;
    public static volatile SingularAttribute<Point, Double> latitude;
    public static volatile SingularAttribute<Point, Integer> id;
    public static volatile SingularAttribute<Point, String> trackName;
    public static volatile SingularAttribute<Point, Integer> orderNr;
    public static volatile SingularAttribute<Point, Double> longitude;

}