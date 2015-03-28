/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beck.beck_app;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "notice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notice.findAll", query = "SELECT n FROM Notice n"),
    @NamedQuery(name = "Notice.findById", query = "SELECT n FROM Notice n WHERE n.id = :id"),
    @NamedQuery(name = "Notice.findByMessage", query = "SELECT n FROM Notice n WHERE n.message = :message"),
    @NamedQuery(name = "Notice.findByTitle", query = "SELECT n FROM Notice n WHERE n.title = :title")})
public class Notice implements Serializable {
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

    public Notice() {
    }

    public Notice(Integer id) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notice)) {
            return false;
        }
        Notice other = (Notice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.beck.beck_app.Notice[ id=" + id + " ]";
    }
    
}
