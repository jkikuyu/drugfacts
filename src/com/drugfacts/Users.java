/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drugfacts;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Spring park Hotel
 */
@Entity
@Table(name = "USERS", catalog = "PUBLIC", schema = "PUBLIC")
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUserid", query = "SELECT u FROM Users u WHERE u.userid = :userid"),
    @NamedQuery(name = "Users.findByFname", query = "SELECT u FROM Users u WHERE u.fname = :fname"),
    @NamedQuery(name = "Users.findByLname", query = "SELECT u FROM Users u WHERE u.lname = :lname"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByIntime", query = "SELECT u FROM Users u WHERE u.intime = :intime"),
    @NamedQuery(name = "Users.findByOuttime", query = "SELECT u FROM Users u WHERE u.outtime = :outtime"),
    @NamedQuery(name = "Users.findByActive", query = "SELECT u FROM Users u WHERE u.active = :active")})
public class Users implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "USERID")
    private Integer userid;
    @Basic(optional = false)
    @Column(name = "FNAME")
    private String fname;
    @Column(name = "LNAME")
    private String lname;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "INTIME")
    @Temporal(TemporalType.TIME)
    private Date intime;
    @Column(name = "OUTTIME")
    @Temporal(TemporalType.TIME)
    private Date outtime;
    @Column(name = "ACTIVE")
    private Boolean active;

    public Users() {
    }

    public Users(Integer userid) {
        this.userid = userid;
    }

    public Users(Integer userid, String fname) {
        this.userid = userid;
        this.fname = fname;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        Integer oldUserid = this.userid;
        this.userid = userid;
        changeSupport.firePropertyChange("userid", oldUserid, userid);
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        String oldFname = this.fname;
        this.fname = fname;
        changeSupport.firePropertyChange("fname", oldFname, fname);
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        String oldLname = this.lname;
        this.lname = lname;
        changeSupport.firePropertyChange("lname", oldLname, lname);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        String oldUsername = this.username;
        this.username = username;
        changeSupport.firePropertyChange("username", oldUsername, username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        String oldPassword = this.password;
        this.password = password;
        changeSupport.firePropertyChange("password", oldPassword, password);
    }

    public Date getIntime() {
        return intime;
    }

    public void setIntime(Date intime) {
        Date oldIntime = this.intime;
        this.intime = intime;
        changeSupport.firePropertyChange("intime", oldIntime, intime);
    }

    public Date getOuttime() {
        return outtime;
    }

    public void setOuttime(Date outtime) {
        Date oldOuttime = this.outtime;
        this.outtime = outtime;
        changeSupport.firePropertyChange("outtime", oldOuttime, outtime);
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        Boolean oldActive = this.active;
        this.active = active;
        changeSupport.firePropertyChange("active", oldActive, active);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.drugfacts.Users[userid=" + userid + "]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
