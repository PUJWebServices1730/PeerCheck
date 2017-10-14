/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author davlad
 */
@Entity
@Table(name = "ARTICLES")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Articles.findAll", query = "SELECT a FROM Articles a")
	, @NamedQuery(name = "Articles.findById", query = "SELECT a FROM Articles a WHERE a.id = :id")
	, @NamedQuery(name = "Articles.findByAbstract1", query = "SELECT a FROM Articles a WHERE a.abstract1 = :abstract1")
	, @NamedQuery(name = "Articles.findByCategory", query = "SELECT a FROM Articles a WHERE a.category = :category")
	, @NamedQuery(name = "Articles.findByKeywords", query = "SELECT a FROM Articles a WHERE a.keywords = :keywords")
	, @NamedQuery(name = "Articles.findByTitle", query = "SELECT a FROM Articles a WHERE a.title = :title")})
public class Articles implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
	private Integer id;
	@Basic(optional = false)
    @Column(name = "ABSTRACT")
	private String abstract1;
	@Basic(optional = false)
    @Column(name = "CATEGORY")
	private String category;
	@Basic(optional = false)
    @Column(name = "KEYWORDS")
	private String keywords;
	@Basic(optional = false)
    @Column(name = "TITLE")
	private String title;
	@ManyToMany(mappedBy = "articlesCollection")
	private Collection<Users> usersCollection;
	@ManyToMany(mappedBy = "articlesCollection")
	private Collection<Events> eventsCollection;
	@OneToMany(mappedBy = "articleId")
	private Collection<Reviews> reviewsCollection;
	@JoinColumn(name = "MAIN_AUTHOR_ID", referencedColumnName = "ID")
    @ManyToOne
	private Users mainAuthorId;
	@OneToMany(mappedBy = "articleId")
	private Collection<Files> filesCollection;

	public Articles() {
	}

	public Articles(Integer id) {
		this.id = id;
	}

	public Articles(Integer id, String abstract1, String category, String keywords, String title) {
		this.id = id;
		this.abstract1 = abstract1;
		this.category = category;
		this.keywords = keywords;
		this.title = title;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAbstract1() {
		return abstract1;
	}

	public void setAbstract1(String abstract1) {
		this.abstract1 = abstract1;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@XmlTransient
	public Collection<Users> getUsersCollection() {
		return usersCollection;
	}

	public void setUsersCollection(Collection<Users> usersCollection) {
		this.usersCollection = usersCollection;
	}

	@XmlTransient
	public Collection<Events> getEventsCollection() {
		return eventsCollection;
	}

	public void setEventsCollection(Collection<Events> eventsCollection) {
		this.eventsCollection = eventsCollection;
	}

	@XmlTransient
	public Collection<Reviews> getReviewsCollection() {
		return reviewsCollection;
	}

	public void setReviewsCollection(Collection<Reviews> reviewsCollection) {
		this.reviewsCollection = reviewsCollection;
	}

	public Users getMainAuthorId() {
		return mainAuthorId;
	}

	public void setMainAuthorId(Users mainAuthorId) {
		this.mainAuthorId = mainAuthorId;
	}

	@XmlTransient
	public Collection<Files> getFilesCollection() {
		return filesCollection;
	}

	public void setFilesCollection(Collection<Files> filesCollection) {
		this.filesCollection = filesCollection;
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
		if (!(object instanceof Articles)) {
			return false;
		}
		Articles other = (Articles) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "entities.Articles[ id=" + id + " ]";
	}
	
}