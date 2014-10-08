package com.stone.pai.bean;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class RelatedTo implements Serializable {
	private Long id;
	private String created_at;
	private String modified_at;
	private String subject;
	private String priority;
	private String status;
	private String case_origin;
	private List<Comments> comments;
	private List<Attachments> attachments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getModified_at() {
		return modified_at;
	}

	public void setModified_at(String modified_at) {
		this.modified_at = modified_at;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCase_origin() {
		return case_origin;
	}

	public void setCase_origin(String case_origin) {
		this.case_origin = case_origin;
	}

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	public List<Attachments> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachments> attachments) {
		this.attachments = attachments;
	}

	@Override
	public String toString() {
		return "RelatedTo [id=" + id + ", created_at=" + created_at
				+ ", modified_at=" + modified_at + ", subject=" + subject
				+ ", priority=" + priority + ", status=" + status
				+ ", case_origin=" + case_origin + ", comments=" + comments
				+ ", attachments=" + attachments + "]";
	}

}
