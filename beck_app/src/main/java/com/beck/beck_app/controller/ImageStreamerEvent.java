/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beck.beck_app.controller;

import com.beck.beck_app.model.ImagesEvent;
import com.beck.beck_app.model.ImagesNotice;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author rober_000
 */
@Named("imageControllerE")
@ApplicationScoped
public class ImageStreamerEvent {

     @EJB private com.beck.beck_app.facade.ImagesEventFacade ejbNotice;

    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String imageId = context.getExternalContext().getRequestParameterMap().get("id_img");
            ImagesEvent image = ejbNotice.findInt(imageId); 
            return new DefaultStreamedContent(new ByteArrayInputStream(image.getImages()));
        }
    }

}