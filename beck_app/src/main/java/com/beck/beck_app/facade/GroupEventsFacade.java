/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beck.beck_app.facade;

import com.beck.beck_app.model.Event;
import com.beck.beck_app.model.Group1;
import com.beck.beck_app.model.GroupEvents;
import com.beck.beck_app.model.UserGroups;
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
public class GroupEventsFacade extends AbstractFacade<GroupEvents> {
    @PersistenceContext(unitName = "com.beck_beck_app_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GroupEventsFacade() {
        super(GroupEvents.class);
    }
     public List<GroupEvents> findByIdEvent(Event idEvent) {
     Query cq = getEntityManager().createNamedQuery("GroupEvents.findByEventId"); 
     cq.setParameter("eventId", idEvent);
  
     return cq.getResultList();
    }
}
