package com.beck.beck_app.controller;

import com.beck.beck_app.facade.UserFacade;
import com.beck.beck_app.model.Role;
import com.beck.beck_app.model.User;
import com.beck.beck_app.model.UserGroups;
import com.beck.beck_app.util.JsfUtil;
import com.beck.beck_app.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.security.Principal;
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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

@Named("userController")
@SessionScoped
public class UserController implements Serializable {

    @EJB
    private com.beck.beck_app.facade.UserFacade ejbFacade;
    
    @EJB
    private com.beck.beck_app.facade.RoleFacade ejbFacadeRole;
    
    
    private List<User> items = null;
    private User selected;

    public UserController() {
    }
    
        @PostConstruct
    public void init() {
    selected = new User();
    }

    public User getSelected() {
        return selected;
    }

    public void setSelected(User selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UserFacade getFacade() {
        return ejbFacade;
    }

    
    public String login()
    { 
    //    String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex("asd");
     
    User usr =  getFacade().findByNameAndPassword(selected.getUsername(), selected.getPassword());
    if(usr==null)
    {
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Błąd logowania", "Niepoprawny login lub hasło");
       RequestContext.getCurrentInstance().showMessageInDialog(message); 
       FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
       return "/login/error";
    }
    else
    { HttpServletRequest request = (HttpServletRequest)  FacesContext.getCurrentInstance().getExternalContext().getRequest();
          try {
              request.login(usr.getUsername(), usr.getPassword());
             
          } catch (ServletException ex) {
              Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
          } 
        if(request.isUserInRole("admin")) return "/admin_views/info/List";
        else if (request.isUserInRole("user")) return "/user_views/main";
        else { 
            
        try {
            request.logout();
           
        } catch (ServletException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
         return "/login/error2";
        }
    }
    
    
    public void register()
    {
        try {
        List<Role> usrPermissions  = new ArrayList<>();
        Role basicPermission = new Role();
        basicPermission = getEjbFacadeRole().find(1);
        usrPermissions.add(basicPermission);
        
        if(selected!=null) {
        User usr =  getFacade().findByNameAndPassword(selected.getUsername(), selected.getPassword());    
        usr.setRoleList(usrPermissions);
        getFacade().edit(usr);
        }
        }
        catch (Exception e)
        {
             Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    
    public void setProps(String name, String pass)
    {
        User u =null;
        try {
            u = getFacade().findByNameAndPassword(name, pass);
        }
        catch(Exception e)
        { 
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, null, e);
        }
         if(u!=null) setSelected(u);
        
    }
 
          
    public void logout() {
 
    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
    try {
      request.logout();
      FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
      selected=null;
      
    } catch (ServletException e) {
                   Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, "nie udalo sie wylogowac");  
    }
     
  
  }
    
    
    
    public User prepareCreate() {
        selected = new User();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UserCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UserUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UserDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<User> getItems() {
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

    public User getUser(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<User> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<User> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * @return the ejbFacadeRole
     */
    public com.beck.beck_app.facade.RoleFacade getEjbFacadeRole() {
        return ejbFacadeRole;
    }

    /**
     * @param ejbFacadeRole the ejbFacadeRole to set
     */
    public void setEjbFacadeRole(com.beck.beck_app.facade.RoleFacade ejbFacadeRole) {
        this.ejbFacadeRole = ejbFacadeRole;
    }



    @FacesConverter(forClass = User.class,value="categoryLevelConverter")
    public static class UserControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserController controller = (UserController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userController");
            return controller.getUser(getKey(value));
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
            if (object instanceof User) {
                User o = (User) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), User.class.getName()});
                return null;
            }
        }

    }

}
