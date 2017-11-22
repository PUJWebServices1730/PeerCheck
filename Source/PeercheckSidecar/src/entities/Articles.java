/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author juanm
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
    , @NamedQuery(name = "Articles.findByTitle", query = "SELECT a FROM Articles a WHERE a.title = :title")
    , @NamedQuery(name = "Articles.findInTitle", query = "SELECT a FROM Articles a WHERE LOWER(a.title) LIKE CONCAT('%',LOWER(:param),'%')")
    , @NamedQuery(name = "Articles.findInCategory", query = "SELECT a FROM Articles a WHERE LOWER(a.category) LIKE CONCAT('%',LOWER(:param),'%')")
    , @NamedQuery(name = "Articles.findByAuthor", query = "SELECT a FROM Articles a WHERE a.mainAuthorId = :mainAuthorId")})
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
    @ManyToMany(mappedBy = "articlesList")
    private List<Users> usersList;
    @ManyToOne
    private Events eventId;
    @OneToMany(mappedBy = "articleId", fetch = FetchType.EAGER)
    private List<Reviews> reviewsList;
    @JoinColumn(name = "MAIN_AUTHOR_ID", referencedColumnName = "ID")
    @ManyToOne
    private Users mainAuthorId;
    @OneToMany(mappedBy = "articleId", cascade = CascadeType.PERSIST)
    private List<Files> filesList;

    public Articles() {
        usersList = new ArrayList<>();
        reviewsList = new ArrayList<>();
        filesList = new ArrayList<>();
    }

    public Articles(Integer id) {
        this.id = id;
        usersList = new ArrayList<>();
        reviewsList = new ArrayList<>();
        filesList = new ArrayList<>();
    }

    public Articles(Integer id, String abstract1, String category, String keywords, String title) {
        this.id = id;
        this.abstract1 = abstract1;
        this.category = category;
        this.keywords = keywords;
        this.title = title;
        usersList = new ArrayList<>();
        reviewsList = new ArrayList<>();
        filesList = new ArrayList<>();
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
    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }
    
    public Events getEvent() {
        return eventId;
    }

    public void setEvent(Events event) {
        this.eventId = event;
    }

    @XmlTransient
    public List<Reviews> getReviewsList() {
        return reviewsList;
    }

    public void setReviewsList(List<Reviews> reviewsList) {
        this.reviewsList = reviewsList;
    }

    public Users getMainAuthorId() {
        return mainAuthorId;
    }

    public void setMainAuthorId(Users mainAuthorId) {
        this.mainAuthorId = mainAuthorId;
    }

    @XmlTransient
    public List<Files> getFilesList() {
        return filesList;
    }

    public void setFilesList(List<Files> filesList) {
        this.filesList = filesList;
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
