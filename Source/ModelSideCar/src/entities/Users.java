/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author davlad
 */
@Entity
@Table(name = "USERS")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
	, @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id")
	, @NamedQuery(name = "Users.findByBirthdate", query = "SELECT u FROM Users u WHERE u.birthdate = :birthdate")
	, @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email")
	, @NamedQuery(name = "Users.findByName", query = "SELECT u FROM Users u WHERE u.name = :name")
	, @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password")
	, @NamedQuery(name = "Users.findByRole", query = "SELECT u FROM Users u WHERE u.role = :role")})
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
	private Integer id;
	@Basic(optional = false)
    @Column(name = "BIRTHDATE")
    @Temporal(TemporalType.DATE)
	private Date birthdate;
	@Basic(optional = false)
    @Column(name = "EMAIL")
	private String email;
	@Basic(optional = false)
    @Column(name = "NAME")
	private String name;
	@Basic(optional = false)
    @Column(name = "PASSWORD")
	private String password;
	@Basic(optional = false)
    @Column(name = "ROLE")
	private String role;
	@JoinTable(name = "USERS_ARTICLES", joinColumns = {
    	@JoinColumn(name = "USER_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
    	@JoinColumn(name = "ARTICLE_ID", referencedColumnName = "ID")})
    @ManyToMany
	private Collection<Articles> articlesCollection;
	@OneToMany(mappedBy = "reviewerId")
	private Collection<Reviews> reviewsCollection;
	@OneToMany(mappedBy = "editorId")
	private Collection<Events> eventsCollection;
	@OneToMany(mappedBy = "mainAuthorId")
	private Collection<Articles> articlesCollection1;

	public Users() {
	}

	public Users(Integer id) {
		this.id = id;
	}

	public Users(Integer id, Date birthdate, String email, String name, String password, String role) {
		this.id = id;
		this.birthdate = birthdate;
		this.email = email;
		this.name = name;
		this.password = password;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@XmlTransient
	public Collection<Articles> getArticlesCollection() {
		return articlesCollection;
	}

	public void setArticlesCollection(Collection<Articles> articlesCollection) {
		this.articlesCollection = articlesCollection;
	}

	@XmlTransient
	public Collection<Reviews> getReviewsCollection() {
		return reviewsCollection;
	}

	public void setReviewsCollection(Collection<Reviews> reviewsCollection) {
		this.reviewsCollection = reviewsCollection;
	}

	@XmlTransient
	public Collection<Events> getEventsCollection() {
		return eventsCollection;
	}

	public void setEventsCollection(Collection<Events> eventsCollection) {
		this.eventsCollection = eventsCollection;
	}

	@XmlTransient
	public Collection<Articles> getArticlesCollection1() {
		return articlesCollection1;
	}

	public void setArticlesCollection1(Collection<Articles> articlesCollection1) {
		this.articlesCollection1 = articlesCollection1;
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
		if (!(object instanceof Users)) {
			return false;
		}
		Users other = (Users) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "entities.Users[ id=" + id + " ]";
	}
	
}
