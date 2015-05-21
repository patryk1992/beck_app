/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beck.beck_app.model;


import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rober_000
 */
@Entity
@Table(name = "images_event")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ImagesEvent.findAll", query = "SELECT i FROM ImagesEvent i"),
    @NamedQuery(name = "ImagesEvent.findByIdImages", query = "SELECT i FROM ImagesEvent i WHERE i.idImages = :idImages"),
    @NamedQuery(name = "ImagesEvent.findByImageDesc", query = "SELECT i FROM ImagesEvent i WHERE i.imageDesc = :imageDesc")})
public class ImagesEvent implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "images")
    private byte[] images;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_images")
    private Integer idImages;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "image_desc")
    private String imageDesc;
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Event eventId;

    public ImagesEvent() {
    }

    public ImagesEvent(Integer idImages) {
        this.idImages = idImages;
    }

    public ImagesEvent(Integer idImages, String imageDesc, String imagePath) {
        this.idImages = idImages;
        this.imageDesc = imageDesc;
    }

    public Integer getIdImages() {
        return idImages;
    }

    public void setIdImages(Integer idImages) {
        this.idImages = idImages;
    }

    public String getImageDesc() {
        return imageDesc;
    }

    public void setImageDesc(String imageDesc) {
        this.imageDesc = imageDesc;
    }

    public Event getEventId() {
        return eventId;
    }

    public void setEventId(Event eventId) {
        this.eventId = eventId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idImages != null ? idImages.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImagesEvent)) {
            return false;
        }
        ImagesEvent other = (ImagesEvent) object;
        if ((this.idImages == null && other.idImages != null) || (this.idImages != null && !this.idImages.equals(other.idImages))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.beck.beck_app.controller.ImagesEvent[ idImages=" + idImages + " ]";
    }

    public byte[] getImages() {
        return images;
    }

    public void setImages(byte[] images) {
        this.images = images;
    }
    
}
