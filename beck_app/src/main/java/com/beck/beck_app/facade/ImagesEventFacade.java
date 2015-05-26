/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beck.beck_app.facade;

import com.beck.beck_app.model.ImagesEvent;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author rober_000
 */
@Stateless
public class ImagesEventFacade extends AbstractFacade<ImagesEvent> {
    @PersistenceContext(unitName = "com.beck_beck_app_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ImagesEventFacade() {
        super(ImagesEvent.class);
    }
    
    
       public ImagesEvent findInt(java.lang.String id) {
        Integer idP = Integer.valueOf(id);
        Query cq = getEntityManager().createNamedQuery("ImagesEvent.findByIdImages");
        cq.setParameter("idImages", idP);
        ImagesEvent res = (ImagesEvent) cq.getSingleResult(); 
        return res;
    }
    
}
