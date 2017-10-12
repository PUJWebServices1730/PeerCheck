/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sebas
 */
@Entity
@Table(name = "REVIEWS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reviews.findAll", query = "SELECT r FROM Reviews r")
    , @NamedQuery(name = "Reviews.findById", query = "SELECT r FROM Reviews r WHERE r.id = :id")
    , @NamedQuery(name = "Reviews.findByDate", query = "SELECT r FROM Reviews r WHERE r.date = :date")
    , @NamedQuery(name = "Reviews.findByGrade", query = "SELECT r FROM Reviews r WHERE r.grade = :grade")
    , @NamedQuery(name = "Reviews.findByMessage", query = "SELECT r FROM Reviews r WHERE r.message = :message")
    , @NamedQuery(name = "Reviews.findByStatus", query = "SELECT r FROM Reviews r WHERE r.status = :status")})
public class Reviews implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "GRADE")
    private int grade;
    @Basic(optional = false)
    @Column(name = "MESSAGE")
    private String message;
    @Basic(optional = false)
    @Column(name = "STATUS")
    private String status;
    @JoinColumn(name = "ARTICLE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Articles articleId;
    @JoinColumn(name = "REVIEWER_ID", referencedColumnName = "ID")
    @ManyToOne
    private Users reviewerId;

    public Reviews() {
    }

    public Reviews(Integer id) {
        this.id = id;
    }

    public Reviews(Integer id, Date date, int grade, String message, String status) {
        this.id = id;
        this.date = date;
        this.grade = grade;
        this.message = message;
        this.status = status;
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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Articles getArticleId() {
        return articleId;
    }

    public void setArticleId(Articles articleId) {
        this.articleId = articleId;
    }

    public Users getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Users reviewerId) {
        this.reviewerId = reviewerId;
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
        if (!(object instanceof Reviews)) {
            return false;
        }
        Reviews other = (Reviews) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Reviews[ id=" + id + " ]";
    }
    
}
