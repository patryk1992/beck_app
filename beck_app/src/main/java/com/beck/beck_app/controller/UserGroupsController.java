package com.beck.beck_app.controller;

import com.beck.beck_app.facade.UserFacade;
import com.beck.beck_app.facade.UserGroupsFacade;
import com.beck.beck_app.model.Group1;
import com.beck.beck_app.model.User;
import com.beck.beck_app.model.UserGroups;
import com.beck.beck_app.util.JsfUtil;
import com.beck.beck_app.util.JsfUtil.PersistAction;
import com.beck.beck_app.util.UserConverter;

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
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

@Named("userGroupsController")
@SessionScoped
public class UserGroupsController implements Serializable {

    @EJB
    private com.beck.beck_app.facade.UserFacade ejbFacade2;
    @EJB
    private com.beck.beck_app.facade.UserGroupsFacade ejbFacade;
    
    private List<UserGroups> items = null;
    private UserGroups selected;
    private DualListModel<String> cities;
    private DualListModel<User> users;
    List<User> usersSource;
    List<User> usersTarget;
    
    public UserGroupsController()
    {
    }

    @PostConstruct
    public void init() {
        selected = new UserGroups();
        usersSource =getFacade2().findAll();
        usersTarget = new ArrayList<User>();
        setUsers(new DualListModel<User>(usersSource, usersTarget));
        
        //dopisz facade do grupy tak zeby stworzyc tam liste filtrowana
      
    }
  
    
    public UserGroups getSelected() {
        return selected;
    }

    public void setSelected(UserGroups selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UserGroupsFacade getFacade() {
        return ejbFacade;
    }

    public UserGroups prepareCreate() {
        selected = new UserGroups();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UserGroupsCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    public void create2(User user,Group1 group1) {
       
        //picklist
        boolean isUserAdded=false;
        List<User> tmplit=users.getTarget();
        List <UserGroups> targetUserGroups=ejbFacade.findByIdGroup(group1);      
        for(UserGroups usrGr:targetUserGroups){
             User tmpUser=usrGr.getUserId();
             tmplit.remove(tmpUser);
             int   tmpu=tmpUser.getId();
             int   tmpu2=user.getId();
             if(tmpu2==tmpu2){
                 isUserAdded=true;
             }
        }
        if(!isUserAdded){
            selected.setUserId(user);
            selected.setGroupId(group1);
            selected.setStatus("owner");
            getFacade().create(selected);
            
        }
        for(User u : tmplit ){
            int tmpname=u.getId();
            int loggedname=user.getId();
            if(tmpname!=loggedname){
                UserGroups uG=new UserGroups();
                uG.setUserId(u);
                uG.setGroupId(group1);
                getFacade().create(uG);          
            }
        }
      
    }
    

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UserGroupsUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UserGroupsDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<UserGroups> getItems() {
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

    public UserGroups getUserGroups(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<UserGroups> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }
    

    public List<UserGroups> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * @return the cities
     */
    public DualListModel<String> getCities() {
        return cities;
    }

    /**
     * @param cities the cities to set
     */
    public void setCities(DualListModel<String> cities) {
        this.cities = cities;
    }

    /**
     * @return the ejbFacade2
     */
      private UserFacade getFacade2() {
        return ejbFacade2;
    }

    /**
     * @return the users
     */
    public DualListModel<User> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(DualListModel<User> users) {
        this.users = users;
    }
     public void setParametrsPickList(User user,Group1 group1) {
         List <UserGroups> targetUserGroups=ejbFacade.findByIdGroup(group1);
         List <User> targetUserListByGroupId= new ArrayList<User>();
       
         boolean isUserAdded=false;
         for(UserGroups usrGr:targetUserGroups){
             User tmpUser=usrGr.getUserId();
            
             int   tmpu=tmpUser.getId();
             int   tmpu2=user.getId();
             if(tmpu==tmpu2){
                isUserAdded=true;
             }             
             targetUserListByGroupId.add(tmpUser);
             
        }
        if(!isUserAdded){
             targetUserListByGroupId.add(user);
        }
         
        users.setTarget(targetUserListByGroupId);       
        List <User> sourceUserListByGroupId=ejbFacade2.findAll();
        sourceUserListByGroupId.removeAll(targetUserListByGroupId);        
        users.setSource(sourceUserListByGroupId);
       
    }
    public List<Group1> getGroupsCratedByUser(User user) {
        List <UserGroups> targetUserGroups=ejbFacade.findByIdUser(user);
         List <Group1> targetUserListByGroupId= new ArrayList<Group1>();
         for(UserGroups usrGr:targetUserGroups){
             Group1 tmpGroup=usrGr.getGroupId();
             if(usrGr.getStatus()!= null && usrGr.getStatus().contains("owner") && targetUserListByGroupId.indexOf(tmpGroup)==-1){
                 
                targetUserListByGroupId.add(tmpGroup);
             }
         }
        
        return targetUserListByGroupId;
    }

    @FacesConverter(forClass = UserGroups.class)
    public static class UserGroupsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserGroupsController controller = (UserGroupsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userGroupsController");
            return controller.getUserGroups(getKey(value));
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
            if (object instanceof UserGroups) {
                UserGroups o = (UserGroups) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), UserGroups.class.getName()});
                return null;
            }
        }

    }

}
