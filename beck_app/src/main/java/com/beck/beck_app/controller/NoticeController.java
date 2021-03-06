package com.beck.beck_app.controller;

import com.beck.beck_app.facade.NoticeFacade;
import com.beck.beck_app.model.Notice;
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

@Named("noticeController")
@SessionScoped
public class NoticeController implements Serializable {

    @EJB
    private com.beck.beck_app.facade.NoticeFacade ejbFacade;
    private List<Notice> items = null;
    private Notice selected;
    private Notice selectedSave;
    private List<Notice> allNotice;
    private List<String> filteredNotice;
    private List<Notice> filteredItems;
    
    public NoticeController() {
    }

    public Notice getSelected() {
        return selected;
    }

    public void setSelected(Notice selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private NoticeFacade getFacade() {
        return ejbFacade;
    }

    @PostConstruct
     public void init() {
    selected = new Notice();
     selectedSave = new Notice();
    }
    
     public void getByTitle()
     {
     selectedSave =  getFacade().findByTitle(selected.getTitle());
     
    
     }
     
    public Notice prepareCreate() {
        selected = new Notice();
        initializeEmbeddableKey();
        return selected;
    }
    
    public void setSelectedFromSelectedSave() {
    selected=selectedSave;
    
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("NoticeCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("NoticeUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("NoticeDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Notice> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
 public void setItems( List<Notice> items) {
        this.items=items;
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

    public Notice getNotice(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Notice> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Notice> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    public List<String> completeText(String query) {
        allNotice =  getFacade().findAll();
        filteredNotice = new ArrayList<String>();
        filteredItems = new ArrayList<Notice>();
         
        for (int i = 0; i < allNotice.size(); i++) {
            Notice notice = allNotice.get(i);
            if(notice.getTitle().toLowerCase().contains(query.toLowerCase())) {
                filteredNotice.add(notice.getTitle());
                filteredItems.add(notice);
            }
        }
        items=filteredItems;
        return filteredNotice;
    }
    public String processListNotice(){
        
		return "/guest_views/notice/ListNotice";
    }

    /**
     * @return the selToSave
     */
    public Notice getSelectedSave() {
        return selectedSave;
    }

    /**
     * @param selectedSave the selToSave to set
     */
    public void setSelectedSave(Notice selectedSave) {
        this.selectedSave = selectedSave;
    }
    @FacesConverter(forClass = Notice.class)
    public static class NoticeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NoticeController controller = (NoticeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "noticeController");
            return controller.getNotice(getKey(value));
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
            if (object instanceof Notice) {
                Notice o = (Notice) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Notice.class.getName()});
                return null;
            }
        }

    }

}
