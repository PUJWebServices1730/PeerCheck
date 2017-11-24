/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entities.Articles;
import entities.Events;
import entities.Files;
import entities.Reviews;
import entities.TrannyFile;
import entities.Users;
import enums.ArticleCriteria;
import enums.UserRole;
import facades.ArticlesFacade;
import facades.ArticlesFacadeRemote;
import facades.EventsFacade;
import facades.EventsFacadeRemote;
import facades.FilesFacade;
import facades.FilesFacadeRemote;
import facades.ReviewsFacade;
import facades.ReviewsFacadeRemote;
import facades.UsersFacade;
import facades.UsersFacadeRemote;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

/**
 *
 * @author juanm
 */
@Singleton
public class SessionManager implements SessionManagerRemote {

    @PersistenceContext(unitName = "PeercheckServerPU")
    private EntityManager em;

    @EJB
    private ReviewsFacadeRemote reviewsFacade;

    @EJB
    private ArticlesFacadeRemote articlesFacade;
    
    @EJB
    private UsersFacadeRemote usersFacade;

    @EJB
    private EventsFacadeRemote eventsFacade;

    @EJB
    private FilesFacadeRemote filesFacade;

    public SessionManager() {
        reviewsFacade = new ReviewsFacade();
        articlesFacade = new ArticlesFacade();
        usersFacade = new UsersFacade();
        eventsFacade = new EventsFacade();
        filesFacade = new FilesFacade();
    }

    @Override
    public Users login(String email, String password) {
        Users user = usersFacade.findByEmail(email);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public Users signup(Users user) {
        Users other = usersFacade.findByEmail(user.getEmail());
        if (other == null) {
            usersFacade.create(user);
            return usersFacade.findByEmail(user.getEmail());
        }
        return null;
    }

    @Override
    public List<Users> getAllUsers() {
        return usersFacade.getAllUsers();
    }

    @Override
    public boolean changeRol(Users user, UserRole role) {
        user.setRole(role.toString());
        usersFacade.edit(user);
        return true;
    }

    @Override
    public List<Articles> findArticleBy(ArticleCriteria criteria, String value) {
        List<Articles> articles = null;
        if (criteria.equals(ArticleCriteria.NOMBRE)) {
            articles = articlesFacade.findByName(value);
        } else if (criteria.equals(ArticleCriteria.CATEGORIA)) {
            articles = articlesFacade.findByCategory(value);
        }
        return articles;
    }

    @Override
    public List<Articles> getAllArticles() {
        return articlesFacade.getAllArticles();
    }

    @Override
    public boolean addArticle(Articles article, TrannyFile file) {
        article.getMainAuthorId().getArticlesList1().add(article);
        try {
			String home = System.getProperty("user.home");
			File dir = new File(home + "/UploadsPeercheck/");
			if (!dir.exists()){
				dir.mkdir();
			}
			File f = new File(dir.getAbsolutePath() + "/" + file.getName());
            FileOutputStream outstream = new FileOutputStream(f);
            outstream.write(file.getContent());
            outstream.close();
            Files newFile = new Files();
            newFile.setArticleId(article);
            newFile.setUrl(f.getAbsolutePath());
            newFile.setDescription("");
            newFile.setTitle(file.getName());
            filesFacade.create(newFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SessionManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SessionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public boolean addReview(Reviews review) {
        reviewsFacade.create(review);

        Articles article = review.getArticleId();
        article.getReviewsList().add(review);

        Users reviewer = review.getReviewerId();
        reviewer.getReviewsList().add(review);

        return false;
    }

    @Override
    public List<Reviews> getReviewsByReviewer(Users reviewer) {
        return reviewsFacade.findByReviewerId(reviewer);
    }

    @Override
    public double calculateFinalGradeToArticle(Articles article) {
        article = articlesFacade.find(article.getId());
        List<Reviews> reviewsList = article.getReviewsList();
        if (reviewsList.isEmpty()) {
            return 0.0;
        }
        double grade = 0.0;
		int numCompleted = 0;
        for (Reviews review : reviewsList) {
            if(review.getStatus().equals("COMPLETADA")) {
                grade += review.getGrade();
				numCompleted++;
            }
        }
        grade /= (double) numCompleted;
        return grade;
    }

    @Override
    public List<Users> findUsersByEmail(List<String> emails) {
        List<Users> users = new ArrayList<>();
        for (String email : emails) {
            users.add(usersFacade.findByEmail(email));
        }
        return users;
    }

    @Override
    public List<Users> findUsersByRole(String role) {
        return usersFacade.findByRole(role);
    }

    @Override
    public TrannyFile getArticleFile(Articles article) {
        return filesFacade.findByArticleId(article);
    }

    @Override
    public boolean updateReview(Reviews review) {
        reviewsFacade.edit(review);
        return false;
    }

    @Override
    public List<Articles> getArticlesByAuthor(Users author) {
        return articlesFacade.findByAuthor(author);
    }

    @Override
    public List<Reviews> getReviewsByArticle(Articles article) {
        return reviewsFacade.findByArticleId(article);
    }

    @Override
    public boolean addEvent(Events event) {
        eventsFacade.create(event);
        return false;
    }

    @Override
    public List<Events> getAllEvents() {
        return eventsFacade.findAll();
    }
	
	@Override
	public void addReviewerToArticle(long reviewerId, long articleId) {
        Articles article = articlesFacade.find(articleId);
		if (article == null) {
			return;
		}
        
		Users user = usersFacade.find(reviewerId);
		if (user == null || !(user.getRole().equals("REVISOR") || user.getRole().equals("EDITOR"))) {
			return;
		}
		
		Reviews review = new Reviews();
		review.setArticleId(article);
		review.setReviewerId(user);
		review.setDate(new Date());
		review.setStatus("ASIGNADA");
		review.setMessage("");
		review.setGrade(-1);
		
		reviewsFacade.create(review);
	}

	@Override
	public List<Articles> getAllArticlesInEvent(int eventId) {
		Events event = eventsFacade.find(eventId);
		if (event == null) {
			return null;
		}
		
		if (event.getArticlesList() == null) {
			return new ArrayList<>();
		}
		
		return event.getArticlesList();
	}
	
	@Override
	public List<Articles> findArticlesInEventByTitle(int eventId, String title) {
		Events event = eventsFacade.find(eventId);
		if (event == null) {
			return null;
		}
		
		if (event.getArticlesList() == null) {
			return new ArrayList<>();
		}
		
		List<Articles> articles = new ArrayList<>();
		for (Articles a : event.getArticlesList()) {
			String t = a.getTitle();
			if (t != null && t.equals(title)) {
				articles.add(a);
			}
		}
		
		return articles;
	}

	@Override
	public void changeRol(int userId, String role) {
		Users user = usersFacade.find(userId);
		if (user == null) {
			return;
		}
		UserRole userRole = null;
		try {
			userRole = UserRole.valueOf(role);
		} catch (IllegalArgumentException | NullPointerException ex) {
			return;
		}
		changeRol(user, userRole);
	}

	@Override
	public Articles getArticle(int id) {
		return articlesFacade.find(id);
	}

	@Override
	public List<Reviews> findReviewsByReviewer(int id) {
		Users user = usersFacade.find(id);
		if (user == null || user.getRole() == null || user.getRole().equals(UserRole.AUTOR.toString())) {
			return null;
		}
		return getReviewsByReviewer(user);
	}

	@Override
	public Users getUser(int id) {
		return usersFacade.find(id);
	}
}
