/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beck.beck_app.facade;

import com.beck.beck_app.model.Notice;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Patryk
 */
@Stateless
public class NoticeFacade extends AbstractFacade<Notice> {
    @PersistenceContext(unitName = "com.beck_beck_app_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NoticeFacade() {
        super(Notice.class);
    }
    
    
     public Notice findByTitle(String title) {
     Query cq = getEntityManager().createNamedQuery("Notice.findByTitle"); 
     cq.setParameter("title", title);
     Notice s =  null;
      try {
     s = (Notice) cq.getSingleResult();
      }
     catch(Exception e)
     {
         return null;
     }
     return s;
    }
    
}
