package com.beck.beck_app.controller;

import com.beck.beck_app.model.Event;
import com.beck.beck_app.util.JsfUtil;
import com.beck.beck_app.util.JsfUtil.PersistAction;
import com.beck.beck_app.facade.EventFacade;
import com.beck.beck_app.facade.Group1Facade;
import com.beck.beck_app.facade.GroupEventsFacade;
import com.beck.beck_app.model.Group1;
import com.beck.beck_app.model.GroupEvents;
import com.beck.beck_app.model.User;
import com.beck.beck_app.model.UserGroups;

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
import org.primefaces.model.DualListModel;

@Named("eventController")
@SessionScoped
public class EventController implements Serializable {
    
    @EJB
    private com.beck.beck_app.facade.GroupEventsFacade ejbFacadeGroupEvents;


    @EJB
    private com.beck.beck_app.facade.Group1Facade ejbFacadeGroup1;

    @EJB
    private com.beck.beck_app.facade.EventFacade ejbFacadeEvent;
    private List<Event> items = null;
    private List<Event> friendsEvents = null;
    private Event selected;
   
    private DualListModel<Group1> groups;
    List<Group1> groupsSource;
    List<Group1> groupsTarget;
    @PostConstruct
    public void init() {
        
        groupsSource =getEjbFacade2().findAll();
        groupsTarget = new ArrayList<Group1>();
        setGroups(new DualListModel<Group1>(groupsSource, groupsTarget));        
       
      
    }
     public void saveGroups(User user,Group1 group1) {
       
        
        //picklist
        List<Group1> tmplit=groups.getTarget();        
        List <GroupEvents> targetGroupEvents=ejbFacadeGroupEvents.findByIdEvent(selected);       
        for(GroupEvents grEvent:targetGroupEvents){
             Group1 tmpGr=grEvent.getGroupId();
             tmplit.remove(tmpGr);
        }
        for(Group1 g : tmplit ){
          GroupEvents gE=new GroupEvents();
          gE.setEventId(selected);
          gE.setGroupId(g);
          getEjbFacadeGroupEvents().create(gE);          
        }
      
    }
     public void setProps(String name)
    {
        Event u =null;
        try {
            u = getFacade().findByEventName(name);
        }
        catch(Exception e)
        { 
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, null, e);
        }
         if(u!=null) setSelected(u);
        
    }
    public void setParametrsPickList() {
         List <GroupEvents> targetGroupEvents=ejbFacadeGroupEvents.findByIdEvent(selected);
         List <Group1> targetGroupListByEventId= new ArrayList<Group1>();
         for(GroupEvents grEvent:targetGroupEvents){
             Group1 tmpGr=grEvent.getGroupId();
             targetGroupListByEventId.add(tmpGr);
         }
        groups.setTarget(targetGroupListByEventId);       
        List <Group1> sourceGroupList=ejbFacadeGroup1.findAll();
        sourceGroupList.removeAll(targetGroupListByEventId);
        groups.setSource(sourceGroupList);
       
    }
    public void deleteEventWithConnections() {
         List <GroupEvents> targetGroupEvents=ejbFacadeGroupEvents.findByIdEvent(selected);        
         for(GroupEvents grEvent:targetGroupEvents){
            ejbFacadeGroupEvents.remove(grEvent);
         }
        destroy();
    }
    public EventController() {
    }

    public Event getSelected() {
        return selected;
    }

    public void setSelected(Event selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }
    public Group1Facade getEjbFacade2() {
        return ejbFacadeGroup1;
    }

    public void setEjbFacade2(Group1Facade ejbFacade2) {
        this.ejbFacadeGroup1 = ejbFacade2;
    }
        public GroupEventsFacade getEjbFacadeGroupEvents() {
        return ejbFacadeGroupEvents;
    }

    public void setEjbFacadeGroupEvents(GroupEventsFacade ejbFacadeGroupEvents) {
        this.ejbFacadeGroupEvents = ejbFacadeGroupEvents;
    }
    
    private EventFacade getFacade() {
        return ejbFacadeEvent;
    }
      
    public DualListModel<Group1> getGroups() {
        return groups;
    }

    public void setGroups(DualListModel<Group1> groups) {
        this.groups = groups;
    }

    public List<Group1> getGroupsSource() {
        return groupsSource;
    }

    public void setGroupsSource(List<Group1> groupsSource) {
        this.groupsSource = groupsSource;
    }

    public List<Group1> getGroupsTarget() {
        return groupsTarget;
    }

    public void setGroupsTarget(List<Group1> groupsTarget) {
        this.groupsTarget = groupsTarget;
    }
    public Event prepareCreate() {
        selected = new Event();
        initializeEmbeddableKey();
        return selected;
    }
    
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EventCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("EventUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("EventDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Event> getItems() {
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

    public Event getEvent(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Event> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Event> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * @return the friendsEvents
     */
    public List<Event> getFriendsEvents(List<GroupEvents> ls) {
       List<Event> list = new ArrayList<Event>();
       
        for (int i = 0; i < ls.size(); i++) {
           list.add(ls.get(i).getEventId());
        }
        
        
        return list;
    }

    /**
     * @param friendsEvents the friendsEvents to set
     */
    public void setFriendsEvents(List<Event> friendsEvents) {
        this.friendsEvents = friendsEvents;
    }

    @FacesConverter(forClass = Event.class)
    public static class EventControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EventController controller = (EventController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "eventController");
            return controller.getEvent(getKey(value));
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
            if (object instanceof Event) {
                Event o = (Event) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Event.class.getName()});
                return null;
            }
        }

    }

}
