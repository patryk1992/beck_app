/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beck.beck_app;

import com.beck.beck_app.GroupEvents;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
}
