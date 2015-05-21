/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beck.beck_app.facade;

import com.beck.beck_app.model.Communication;
import com.beck.beck_app.model.User;
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
public class CommunicationFacade extends AbstractFacade<Communication> {
    @PersistenceContext(unitName = "com.beck_beck_app_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommunicationFacade() {
        super(Communication.class);
    }
    
    public List<Communication> findToUserId(User userId) {
        Query cq = getEntityManager().createNamedQuery("Communication.findByUserTo");
        cq.setParameter("userIdTo", userId);
         cq.setParameter("visibleFrom", 1);
        return cq.getResultList();
    }
     public List<Communication> findFromUserId(User userId) {
        Query cq = getEntityManager().createNamedQuery("Communication.findByUserFrom");
        cq.setParameter("userIdFrom", userId);
         cq.setParameter("visibleTo", 1);
        return cq.getResultList();
    }
    
}
