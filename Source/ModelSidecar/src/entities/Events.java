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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "EVENTS")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Events.findAll", query = "SELECT e FROM Events e")
	, @NamedQuery(name = "Events.findById", query = "SELECT e FROM Events e WHERE e.id = :id")
	, @NamedQuery(name = "Events.findByDate", query = "SELECT e FROM Events e WHERE e.date = :date")
	, @NamedQuery(name = "Events.findByDeadline", query = "SELECT e FROM Events e WHERE e.deadline = :deadline")
	, @NamedQuery(name = "Events.findByDescription", query = "SELECT e FROM Events e WHERE e.description = :description")
	, @NamedQuery(name = "Events.findByLocation", query = "SELECT e FROM Events e WHERE e.location = :location")
	, @NamedQuery(name = "Events.findByName", query = "SELECT e FROM Events e WHERE e.name = :name")
	, @NamedQuery(name = "Events.findBySubmissionstart", query = "SELECT e FROM Events e WHERE e.submissionstart = :submissionstart")
	, @NamedQuery(name = "Events.findByWebsite", query = "SELECT e FROM Events e WHERE e.website = :website")})
public class Events implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
	private Integer id;
	@Basic(optional = false)
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
	private Date date;
	@Basic(optional = false)
    @Column(name = "DEADLINE")
    @Temporal(TemporalType.DATE)
	private Date deadline;
	@Basic(optional = false)
    @Column(name = "DESCRIPTION")
	private String description;
	@Basic(optional = false)
    @Column(name = "LOCATION")
	private String location;
	@Basic(optional = false)
    @Column(name = "NAME")
	private String name;
	@Basic(optional = false)
    @Column(name = "SUBMISSIONSTART")
    @Temporal(TemporalType.DATE)
	private Date submissionstart;
	@Basic(optional = false)
    @Column(name = "WEBSITE")
	private String website;
	@JoinTable(name = "EVENTS_ARTICLES", joinColumns = {
    	@JoinColumn(name = "EVENT_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
    	@JoinColumn(name = "ARTICLE_ID", referencedColumnName = "ID")})
    @ManyToMany
	private Collection<Articles> articlesCollection;
	@JoinColumn(name = "EDITOR_ID", referencedColumnName = "ID")
    @ManyToOne
	private Users editorId;

	public Events() {
	}

	public Events(Integer id) {
		this.id = id;
	}

	public Events(Integer id, Date date, Date deadline, String description, String location, String name, Date submissionstart, String website) {
		this.id = id;
		this.date = date;
		this.deadline = deadline;
		this.description = description;
		this.location = location;
		this.name = name;
		this.submissionstart = submissionstart;
		this.website = website;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getSubmissionstart() {
		return submissionstart;
	}

	public void setSubmissionstart(Date submissionstart) {
		this.submissionstart = submissionstart;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@XmlTransient
	public Collection<Articles> getArticlesCollection() {
		return articlesCollection;
	}

	public void setArticlesCollection(Collection<Articles> articlesCollection) {
		this.articlesCollection = articlesCollection;
	}

	public Users getEditorId() {
		return editorId;
	}

	public void setEditorId(Users editorId) {
		this.editorId = editorId;
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
		if (!(object instanceof Events)) {
			return false;
		}
		Events other = (Events) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "entities.Events[ id=" + id + " ]";
	}
	
}
