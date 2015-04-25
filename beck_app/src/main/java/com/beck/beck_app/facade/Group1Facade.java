/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beck.beck_app.facade;


import com.beck.beck_app.model.Group1;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Patryk
 */
@Stateless
public class Group1Facade extends AbstractFacade<Group1> {
    @PersistenceContext(unitName = "com.beck_beck_app_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Group1Facade() {
        super(Group1.class);
    }
     public Group1 findByGroupName(String groupName) {
     Query cq = getEntityManager().createNamedQuery("Group1.findByGroupName"); 
     cq.setParameter("groupName", groupName);     
     Group1 selectedGroup1 =  null;
     try {
        selectedGroup1 = (Group1) cq.getSingleResult();
      }
     catch(Exception e)
     {
        e.printStackTrace();
     }
     return selectedGroup1;
    }
    
}
