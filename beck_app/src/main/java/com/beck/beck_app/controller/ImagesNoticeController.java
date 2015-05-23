package com.beck.beck_app.controller;

import com.beck.beck_app.model.ImagesNotice;
import com.beck.beck_app.util.JsfUtil;
import com.beck.beck_app.util.JsfUtil.PersistAction;
import com.beck.beck_app.facade.ImagesNoticeFacade;
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
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;


@Named("imagesNoticeController")
@SessionScoped
public class ImagesNoticeController implements Serializable {


    @EJB private com.beck.beck_app.facade.ImagesNoticeFacade ejbFacade;
    private List<ImagesNotice> items = null;
    private ImagesNotice selected;
    private UploadedFile uploadedFile;
    int t;

    public void fileUploadListener(FileUploadEvent event){
        uploadedFile = event.getFile();
    }
    
    @PostConstruct
    public void init() {
    selected = new ImagesNotice();
    }
     
     
     public void insert() throws IOException{
        if(uploadedFile!=null){
          byte[] bytes;
          bytes = IOUtils.toByteArray( uploadedFile.getInputstream() );
          selected.setImages(bytes);
        }
        else{
            t=1;
            //System.out.println("The file object is null.");
        }
    }
    
    public ImagesNoticeController() {
    }

    public ImagesNotice getSelected() {
        return selected;
    }

    public void setSelected(ImagesNotice selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ImagesNoticeFacade getFacade() {
        return ejbFacade;
    }

    public ImagesNotice prepareCreate() {
        selected = new ImagesNotice();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ImagesNoticeCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ImagesNoticeUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ImagesNoticeDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ImagesNotice> getItems() throws IOException {
        if (items == null) {
            items = getFacade().findAll();
        }
       
        for (ImagesNotice item : items) {
             
          //  item.setContent(getImage(item));
        
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


    public ImagesNotice getImagesNotice(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<ImagesNotice> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ImagesNotice> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass=ImagesNotice.class)
    public static class ImagesNoticeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ImagesNoticeController controller = (ImagesNoticeController)facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "imagesNoticeController");
            return controller.getImagesNotice(getKey(value));
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
            if (object instanceof ImagesNotice) {
                ImagesNotice o = (ImagesNotice) object;
                return getStringKey(o.getIdimagesNotice());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ImagesNotice.class.getName()});
                return null;
            }
        }

    }

}
