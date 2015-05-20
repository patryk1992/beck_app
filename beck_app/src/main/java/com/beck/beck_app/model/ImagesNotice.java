/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beck.beck_app.model;

import com.beck.beck_app.model.Event;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "images_notice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ImagesNotice.findAll", query = "SELECT i FROM ImagesNotice i"),
    @NamedQuery(name = "ImagesNotice.findByIdimagesNotice", query = "SELECT i FROM ImagesNotice i WHERE i.idimagesNotice = :idimagesNotice"),
    @NamedQuery(name = "ImagesNotice.findByImagesDesc", query = "SELECT i FROM ImagesNotice i WHERE i.imagesDesc = :imagesDesc"),
    @NamedQuery(name = "ImagesNotice.findByImagesPath", query = "SELECT i FROM ImagesNotice i WHERE i.imagesPath = :imagesPath")})
public class ImagesNotice implements Serializable {
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "images_path")
    private String imagesPath;
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Event eventId;

    public ImagesNotice() {
    }

    public ImagesNotice(Integer idimagesNotice) {
        this.idimagesNotice = idimagesNotice;
    }

    public ImagesNotice(Integer idimagesNotice, String imagesDesc, String imagesPath) {
        this.idimagesNotice = idimagesNotice;
        this.imagesDesc = imagesDesc;
        this.imagesPath = imagesPath;
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

    public String getImagesPath() {
        return imagesPath;
    }

    public void setImagesPath(String imagesPath) {
        this.imagesPath = imagesPath;
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
    
}
