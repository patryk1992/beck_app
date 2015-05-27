/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beck.beck_app.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Patryk
 */
@Entity
@Table(name = "event")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Event.findAll", query = "SELECT e FROM Event e"),
    @NamedQuery(name = "Event.findById", query = "SELECT e FROM Event e WHERE e.id = :id"),
    @NamedQuery(name = "Event.findByEventDesc", query = "SELECT e FROM Event e WHERE e.eventDesc = :eventDesc"),
    @NamedQuery(name = "Event.findByEventName", query = "SELECT e FROM Event e WHERE e.eventName = :eventName"),
    @NamedQuery(name = "Event.findByStatus", query = "SELECT e FROM Event e WHERE e.status = :status")})
public class Event implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventId")
    private Collection<ImagesEvent> imagesEventCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "event_desc")
    private String eventDesc;
    @Size(max = 255)
    @Column(name = "event_name")
    private String eventName;
    @Size(max = 45)
    @Column(name = "status")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventId")
    private List<GroupEvents> groupEventsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventId")
    private List<Track> trackList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventId")
    private List<Info> infoList;

    public Event() {
    }

    public Event(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public List<GroupEvents> getGroupEventsList() {
        return groupEventsList;
    }

    public void setGroupEventsList(List<GroupEvents> groupEventsList) {
        this.groupEventsList = groupEventsList;
    }

    @XmlTransient
    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }

    @XmlTransient
    public List<Info> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<Info> infoList) {
        this.infoList = infoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.beck.beck_app.Event[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<ImagesEvent> getImagesEventCollection() {
        return imagesEventCollection;
    }

    public void setImagesEventCollection(Collection<ImagesEvent> imagesEventCollection) {
        this.imagesEventCollection = imagesEventCollection;
    }

    
}
