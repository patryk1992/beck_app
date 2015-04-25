/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beck.beck_app.facade;

import com.beck.beck_app.model.Address;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 *
 * @author Patryk
 */
@Stateless
public class AddressFacade extends AbstractFacade<Address> {
    @PersistenceContext(unitName = "com.beck_beck_app_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AddressFacade() {
        super(Address.class);
    }
    
    
    
     public Address findIdByVaules(String country, String city, String street, String suburb) {
     Query cq = getEntityManager().createNamedQuery("Address.findIdByValues"); 
     cq.setParameter("country", country);
     cq.setParameter("city", city);
     cq.setParameter("street", street);
     cq.setParameter("suburb", suburb);
     Address add =  null;
      try {
     add = (Address) cq.getSingleResult();
      }
     catch(Exception e)
     {
       return null;
     }
     return add;
    }
}
