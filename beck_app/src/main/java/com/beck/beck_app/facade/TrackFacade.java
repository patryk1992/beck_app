/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beck.beck_app.facade;

import com.beck.beck_app.model.Event;
import com.beck.beck_app.model.Group1;
import com.beck.beck_app.model.GroupEvents;
import com.beck.beck_app.model.Point;
import com.beck.beck_app.model.Track;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Patryk
 */
@Stateless
public class TrackFacade extends AbstractFacade<Track> {
    @PersistenceContext(unitName = "com.beck_beck_app_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrackFacade() {
        super(Track.class);
    }
   public List<Track> findByIdEvent(Event eventId) {
     Query cq = getEntityManager().createNamedQuery("Track.findByEventId"); 
     cq.setParameter("eventId", eventId);
  
     return cq.getResultList();
    }
}
