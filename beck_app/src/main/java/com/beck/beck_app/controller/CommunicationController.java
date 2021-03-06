package com.beck.beck_app.controller;

import com.beck.beck_app.facade.CommunicationFacade;
import com.beck.beck_app.model.Communication;
import com.beck.beck_app.model.Group1;
import com.beck.beck_app.model.User;
import com.beck.beck_app.model.UserGroups;
import com.beck.beck_app.util.JsfUtil;
import com.beck.beck_app.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("communicationController")
@SessionScoped
public class CommunicationController implements Serializable {

    @EJB
    private com.beck.beck_app.facade.CommunicationFacade ejbFacade;
    private List<Communication> items = null;
    private List<Communication> itemsDo = null;
    private List<Communication> itemsOd = null;
    private Communication selected;

    public CommunicationController() {
    }
    
    @PostConstruct
    public void init() {
    selected = new Communication();
     
    }

    public Communication getSelected() {
        return selected;
    }

    public void setSelected(Communication selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CommunicationFacade getFacade() {
        return ejbFacade;
    }

    public Communication prepareCreate() {
        selected = new Communication();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CommunicationCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    public void send(User user) {
        selected.setUserIdFrom(user);    
        selected.setVisibleFrom(1);
        selected.setVisibleTo(1);
        create();
        getItemsFrom(user);
        getItemsDo(user);
    }
     public void setVisibleSelectedFromFalseAfterDelete() {
        selected.setVisibleFrom(0);
        update();       
    }
     public void setVisibleSelectedToFalseAfterDelete() {     
        selected.setVisibleTo(0);
        update();
    }
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CommunicationUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CommunicationDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Communication> getItemsDo(User user) {
        
        itemsDo = getFacade().findToUserId(user);
       
        return itemsDo;
    }
    public List<Communication> getItemsFrom(User user) {
        
        itemsOd = getFacade().findFromUserId(user);
       
        return itemsOd;
    }
    public List<Communication> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Communication getCommunication(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Communication> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Communication> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Communication.class)
    public static class CommunicationControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CommunicationController controller = (CommunicationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "communicationController");
            return controller.getCommunication(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Communication) {
                Communication o = (Communication) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Communication.class.getName()});
                return null;
            }
        }

    }

}
