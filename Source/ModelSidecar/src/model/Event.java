
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author davlad
 */
public class Event {
	private Date date;
	private Date deadline;
	private String description;
	private String location;
	private String name;
	private Date submissionStart;
	private List<String> topics;
	private String webstie;
	private List<Event> published;
	
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

	public Date getSubmissionStart() {
		return submissionStart;
	}

	public void setSubmissionStart(Date submissionStart) {
		this.submissionStart = submissionStart;
	}

	public List<String> getTopics() {
		return topics;
	}

	public void setTopics(ArrayList<String> topics) {
		this.topics = topics;
	}

	public String getWebstie() {
		return webstie;
	}

	public void setWebstie(String webstie) {
		this.webstie = webstie;
	}

	public List<Event> getPublished() {
		return published;
	}

	public void setPublished(List<Event> published) {
		this.published = published;
	}
}
