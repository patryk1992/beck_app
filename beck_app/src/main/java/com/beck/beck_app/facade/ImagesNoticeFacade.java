/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beck.beck_app.facade;

import com.beck.beck_app.model.ImagesNotice;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rober_000
 */
@Stateless
public class ImagesNoticeFacade extends AbstractFacade<ImagesNotice> {
    @PersistenceContext(unitName = "com.beck_beck_app_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ImagesNoticeFacade() {
        super(ImagesNotice.class);
    }
    
}
