package com.beck.beck_app.controller;

import com.beck.beck_app.model.ImagesEvent;
import com.beck.beck_app.util.JsfUtil;
import com.beck.beck_app.util.JsfUtil.PersistAction;
import com.beck.beck_app.facade.ImagesEventFacade;
import com.beck.beck_app.model.Event;
import java.io.IOException;

import java.io.Serializable;
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
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@Named("imagesEventController")
@SessionScoped
public class ImagesEventController implements Serializable {

    @EJB
    private com.beck.beck_app.facade.ImagesEventFacade ejbFacade;
    private List<ImagesEvent> items = null;
    private ImagesEvent selected;
    private UploadedFile uploadedFile;
    int t;
    
    
    public ImagesEventController() {
    }

    
    @PostConstruct
    public void init() {
    selected = new ImagesEvent();
    }
     
       public void fileUploadListener(FileUploadEvent event){
        uploadedFile = event.getFile();
    }  
    
     public void insert() throws IOException{
        if(uploadedFile!=null){
          byte[] bytes;
          bytes = IOUtils.toByteArray( uploadedFile.getInputstream() );
          selected.setImages(bytes);
        }
        else{
            int t = 1;
            //System.out.println("The file object is null.");
        }
    }
    
    public ImagesEvent getSelected() {
        return selected;
    }

    public void setSelected(ImagesEvent selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ImagesEventFacade getFacade() {
        return ejbFacade;
    }

    public ImagesEvent prepareCreate() {
        selected = new ImagesEvent();
        initializeEmbeddableKey();
        return selected;
    }

     public void upload(FileUploadEvent event) {  
        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
 
    }  
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ImagesEventCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void getForEvent(Event s) {

       selected = getFacade().findByEvent(s);
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ImagesEventUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ImagesEventDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ImagesEvent> getItems() {
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

    public ImagesEvent getImagesEvent(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<ImagesEvent> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ImagesEvent> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ImagesEvent.class)
    public static class ImagesEventControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ImagesEventController controller = (ImagesEventController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "imagesEventController");
            return controller.getImagesEvent(getKey(value));
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
            if (object instanceof ImagesEvent) {
                ImagesEvent o = (ImagesEvent) object;
                return getStringKey(o.getIdImages());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ImagesEvent.class.getName()});
                return null;
            }
        }

    }

}
