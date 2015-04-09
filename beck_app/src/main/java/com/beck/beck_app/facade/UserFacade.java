/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beck.beck_app.facade;

import com.beck.beck_app.model.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 *
 * @author Robert
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {
    final static Logger logger = Logger.getLogger(UserFacade.class);
    
    @PersistenceContext(unitName = "com.beck_beck_app_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
     public User findByNameAndPassword(String username, String password) {
     Query cq = getEntityManager().createNamedQuery("User.findByUsernameAndPassword"); 
     cq.setParameter("username", username);
     cq.setParameter("password", password);
     User selectedUsr =  null;
      try {
     selectedUsr = (User) cq.getSingleResult();
      }
     catch(Exception e)
     {
         logger.warn("blad  podczas proby logowania");
     }
     return selectedUsr;
    }
    
}
