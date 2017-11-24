/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Articles;
import entities.TrannyFile;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author davlad
 */
@XmlRootElement
public class ArticleWithFile {
	protected Articles article;
	protected TrannyFile file;

	public ArticleWithFile() {
	}

	public ArticleWithFile(Articles article, TrannyFile file) {
		this.article = article;
		this.file = file;
	}

	@Override
	public String toString() {
		return "CreateArticleMessage{" + "article=" + article + ", file=" + file + '}';
	}

	public Articles getArticle() {
		return article;
	}

	public void setArticle(Articles article) {
		this.article = article;
	}

	public TrannyFile getFile() {
		return file;
	}

	public void setFile(TrannyFile file) {
		this.file = file;
	}
	
	
}
