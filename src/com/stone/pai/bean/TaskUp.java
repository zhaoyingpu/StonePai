package com.stone.pai.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 任务信息
 * 
 * @author strj
 * 
 * 
 *         2014-7-15 下午12:51:48
 */
@SuppressWarnings("serial")
public class TaskUp implements Serializable {
	private Long id;
	private String created_at;
	private String modified_at;
	private String subject;
	private List<Comments> comments;
	private RelatedTo related_to;
	public long skill1;
	public long skill2;
	private Double kpi1;
	private Double kpi2;
	private Double kpi3;
	private String status;
	private String priority;
	private String due_date;
	private Place place;
	private List<Long> attachments;
	private long contact; // 联系人信息
	private String description;// 描述信息
	private User assigned_to;// 分配给

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

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	public RelatedTo getRelated_to() {
		return related_to;
	}

	public void setRelated_to(RelatedTo related_to) {
		this.related_to = related_to;
	}

	public long getSkill1() {
		return skill1;
	}

	public void setSkill1(long skill1) {
		this.skill1 = skill1;
	}

	public long getSkill2() {
		return skill2;
	}

	public void setSkill2(long skill2) {
		this.skill2 = skill2;
	}

	public Double getKpi1() {
		return kpi1;
	}

	public void setKpi1(Double kpi1) {
		this.kpi1 = kpi1;
	}

	public Double getKpi2() {
		return kpi2;
	}

	public void setKpi2(Double kpi2) {
		this.kpi2 = kpi2;
	}

	public Double getKpi3() {
		return kpi3;
	}

	public void setKpi3(Double kpi3) {
		this.kpi3 = kpi3;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getAssigned_to() {
		return assigned_to;
	}

	public void setAssigned_to(User assigned_to) {
		this.assigned_to = assigned_to;
	}

	public List<Long> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Long> attachments) {
		this.attachments = attachments;
	}

	@Override
	public String toString() {
		return "TaskUp [id=" + id + ", created_at=" + created_at
				+ ", modified_at=" + modified_at + ", subject=" + subject
				+ ", comments=" + comments + ", related_to=" + related_to
				+ ", skill1=" + skill1 + ", skill2=" + skill2 + ", kpi1="
				+ kpi1 + ", kpi2=" + kpi2 + ", kpi3=" + kpi3 + ", status="
				+ status + ", priority=" + priority + ", due_date=" + due_date
				+ ", place=" + place + ", attachments=" + attachments
				+ ", contact=" + contact + ", description=" + description
				+ ", assigned_to=" + assigned_to + "]";
	}

}
