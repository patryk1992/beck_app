/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beck.beck_app.facade;

import com.beck.beck_app.model.Event;
import com.beck.beck_app.model.Group1;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author rober_000
 */
@Stateless
public class EventFacade extends AbstractFacade<Event> {
    @PersistenceContext(unitName = "com.beck_beck_app_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventFacade() {
        super(Event.class);
    }
    public Event findByEventName(String eventName) {
     Query cq = getEntityManager().createNamedQuery("Event.findByEventName"); 
     cq.setParameter("eventName", eventName);     
     Event selectedGroup1 =  null;
     try {
        selectedGroup1 = (Event) cq.getSingleResult();
      }
     catch(Exception e)
     {
        e.printStackTrace();
     }
     return selectedGroup1;
    }
}
