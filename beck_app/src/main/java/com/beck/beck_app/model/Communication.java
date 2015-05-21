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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Patryk
 */
@Entity
@Table(name = "communication")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Communication.findAll", query = "SELECT c FROM Communication c"),
    @NamedQuery(name = "Communication.findById", query = "SELECT c FROM Communication c WHERE c.id = :id"),
    @NamedQuery(name = "Communication.findByMessage", query = "SELECT c FROM Communication c WHERE c.message = :message"),
    @NamedQuery(name = "Communication.findByUserTo", query ="SELECT c FROM Communication c where c.userIdTo = :userIdTo"),
    @NamedQuery(name = "Communication.findByUserFrom", query ="SELECT c FROM Communication c where c.userIdFrom = :userIdFrom"),
    @NamedQuery(name = "Communication.findByTitle", query = "SELECT c FROM Communication c WHERE c.title = :title")})
public class Communication implements Serializable {
    @Column(name = "visible_to")
    private Integer visibleTo;
    @Column(name = "visible_from")
    private Integer visibleFrom;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "message")
    private String message;
    @Size(max = 45)
    @Column(name = "title")
    private String title;
    @JoinColumn(name = "user_id_from", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private User userIdFrom;
    @JoinColumn(name = "user_id_to", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private User userIdTo;

    public Communication() {
    }

    public Communication(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUserIdFrom() {
        return userIdFrom;
    }

    public void setUserIdFrom(User userIdFrom) {
        this.userIdFrom = userIdFrom;
    }

    public User getUserIdTo() {
        return userIdTo;
    }

    public void setUserIdTo(User userIdTo) {
        this.userIdTo = userIdTo;
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
        if (!(object instanceof Communication)) {
            return false;
        }
        Communication other = (Communication) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.beck.beck_app.Communication[ id=" + id + " ]";
    }

    public Integer getVisibleTo() {
        return visibleTo;
    }

    public void setVisibleTo(Integer visibleTo) {
        this.visibleTo = visibleTo;
    }

    public Integer getVisibleFrom() {
        return visibleFrom;
    }

    public void setVisibleFrom(Integer visibleFrom) {
        this.visibleFrom = visibleFrom;
    }
    
}
