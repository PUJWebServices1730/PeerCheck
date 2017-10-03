/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author davlad
 */
public class Article {
	private String myAbstract;
	private String category;
	private String copyright;
	private List<String> keywords;
	private Author principalAuthor;
	private List<Author> authors;
	private File article;
	private List<File> attachments;

	public String getMyAbstract() {
		return myAbstract;
	}

	public void setMyAbstract(String myAbstract) {
		this.myAbstract = myAbstract;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public Author getPrincipalAuthor() {
		return principalAuthor;
	}

	public void setPrincipalAuthor(Author principalAuthor) {
		this.principalAuthor = principalAuthor;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public File getArticle() {
		return article;
	}

	public void setArticle(File article) {
		this.article = article;
	}

	public List<File> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<File> attachments) {
		this.attachments = attachments;
	}	
}
