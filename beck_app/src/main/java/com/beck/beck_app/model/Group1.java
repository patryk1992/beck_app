/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beck.beck_app.model;

import java.io.Serializable;
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
@Table(name = "groups")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Group1.findAll", query = "SELECT g FROM Group1 g"),
    @NamedQuery(name = "Group1.findByIdgroup", query = "SELECT g FROM Group1 g WHERE g.idgroup = :idgroup"),
    @NamedQuery(name = "Group1.findByGroupDesc", query = "SELECT g FROM Group1 g WHERE g.groupDesc = :groupDesc"),
    @NamedQuery(name = "Group1.findByGroupName", query = "SELECT g FROM Group1 g WHERE g.groupName = :groupName")})
public class Group1 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idgroup")
    private Integer idgroup;
    @Size(max = 255)
    @Column(name = "group_desc")
    private String groupDesc;
    @Size(max = 45)
    @Column(name = "group_name",unique=true, nullable=false)
    private String groupName;
    @Size(max = 45)
    @Column(name = "visibility")
    private String visibility;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupId")
    private List<GroupEvents> groupEventsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupId")
    private List<UserGroups> userGroupsList;

    public Group1() {
    }

    public Group1(Integer idgroup) {
        this.idgroup = idgroup;
    }

    public Integer getIdgroup() {
        return idgroup;
    }

    public void setIdgroup(Integer idgroup) {
        this.idgroup = idgroup;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @XmlTransient
    public List<GroupEvents> getGroupEventsList() {
        return groupEventsList;
    }

    public void setGroupEventsList(List<GroupEvents> groupEventsList) {
        this.groupEventsList = groupEventsList;
    }

    @XmlTransient
    public List<UserGroups> getUserGroupsList() {
        return userGroupsList;
    }

    public void setUserGroupsList(List<UserGroups> userGroupsList) {
        this.userGroupsList = userGroupsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgroup != null ? idgroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Group1)) {
            return false;
        }
        Group1 other = (Group1) object;
        if ((this.idgroup == null && other.idgroup != null) || (this.idgroup != null && !this.idgroup.equals(other.idgroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.beck.beck_app.Group1[ idgroup=" + idgroup + " ]";
    }

    /**
     * @return the visibility
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     * @param visibility the visibility to set
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
    
}
