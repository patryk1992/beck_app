/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beck.beck_app.controller;


import com.beck.beck_app.model.ImagesNotice;
import java.awt.Image;
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
@Named("imageControllerN")
@ApplicationScoped
public class ImageStreamerNotice {

     @EJB private com.beck.beck_app.facade.ImagesNoticeFacade ejbNotice;

    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String imageId = context.getExternalContext().getRequestParameterMap().get("id_img");
            ImagesNotice image = ejbNotice.findInt(imageId); 
            return new DefaultStreamedContent(new ByteArrayInputStream(image.getImages()));
        }
    }

}
