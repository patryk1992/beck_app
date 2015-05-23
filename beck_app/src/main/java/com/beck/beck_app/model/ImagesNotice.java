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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author rober_000
 */
@Entity
@Table(name = "images_notice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ImagesNotice.findAll", query = "SELECT i FROM ImagesNotice i"),
    @NamedQuery(name = "ImagesNotice.findByIdimagesNotice", query = "SELECT i FROM ImagesNotice i WHERE i.idimagesNotice = :idimagesNotice"),
    @NamedQuery(name = "ImagesNotice.findByImagesDesc", query = "SELECT i FROM ImagesNotice i WHERE i.imagesDesc = :imagesDesc")})
public class ImagesNotice implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "images")
    private byte[] images;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idimages_notice")
    private Integer idimagesNotice;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "images_desc")
    private String imagesDesc;
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Event eventId;

    @Transient
    private StreamedContent content;
    
    public ImagesNotice() {
    }

    public ImagesNotice(Integer idimagesNotice) {
        this.idimagesNotice = idimagesNotice;
    }

    public ImagesNotice(Integer idimagesNotice, String imagesDesc, String imagesPath) {
        this.idimagesNotice = idimagesNotice;
        this.imagesDesc = imagesDesc;

    }

    public Integer getIdimagesNotice() {
        return idimagesNotice;
    }

    public void setIdimagesNotice(Integer idimagesNotice) {
        this.idimagesNotice = idimagesNotice;
    }

    public String getImagesDesc() {
        return imagesDesc;
    }

    public void setImagesDesc(String imagesDesc) {
        this.imagesDesc = imagesDesc;
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
        hash += (idimagesNotice != null ? idimagesNotice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImagesNotice)) {
            return false;
        }
        ImagesNotice other = (ImagesNotice) object;
        if ((this.idimagesNotice == null && other.idimagesNotice != null) || (this.idimagesNotice != null && !this.idimagesNotice.equals(other.idimagesNotice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.beck.beck_app.controller.ImagesNotice[ idimagesNotice=" + idimagesNotice + " ]";
    }

    public byte[] getImages() {
        return images;
    }

    public void setImages(byte[] images) {
        this.images = images;
    }

    /**
     * @return the content
     */
    public StreamedContent getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(StreamedContent content) {
        this.content = content;
    }
    
}
