/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beck.beck_app.facade;

import com.beck.beck_app.model.Group1;
import com.beck.beck_app.model.User;
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
public class UserGroupsFacade extends AbstractFacade<UserGroups> {
    @PersistenceContext(unitName = "com.beck_beck_app_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserGroupsFacade() {
        super(UserGroups.class);
    }
    public List<UserGroups> findByIdGroup(Group1 iDGroup) {
     Query cq = getEntityManager().createNamedQuery("UserGroups.findByGroupId"); 
     cq.setParameter("groupId", iDGroup);
  
     return cq.getResultList();
    }
    public List<UserGroups> findByIdUser(User idUser) {
     Query cq = getEntityManager().createNamedQuery("UserGroups.findByUserId"); 
     cq.setParameter("userId", idUser);
  
     return cq.getResultList();
    }
    
    
     public List<UserGroups> findByIdUserForFriends(User idUser) {
     Query cq = getEntityManager().createNamedQuery("UserGroups.findByUserIdAndDiffStatus"); 
     cq.setParameter("userId", idUser);
  
     return cq.getResultList();
     
    }
}
