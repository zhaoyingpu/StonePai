package com.stone.pai.bean;

import java.io.Serializable;
import java.util.Date;
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
public class Task implements Serializable {
	private Long id;
	private Date created_at;
	private String modified_at;
	private String subject;
	private List<Comments> comments;
	private RelatedTo related_to;
	public long skill1;
	public long skill2;
	private Double kpi1;
	private Double kpi2;
	private Double kpi3;
	private String status; // 状态
	private String priority; // 优先级
	private String due_date; // 截止日期
	private Place place;// 经纬度
	private List<Medias> attachments;// 媒体
	private Contact contact; // 联系人信息
	private String description;// 描述信息
	private User assigned_to;// 分配给
	private User creator;// 创建者
	private List<String> allowed_actions;// 权限集合
	private String sub_status;
	private String status_str;
	private String status_detail_str;
	private User owner;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
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

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
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

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public List<String> getAllowed_actions() {
		return allowed_actions;
	}

	public void setAllowed_actions(List<String> allowed_actions) {
		this.allowed_actions = allowed_actions;
	}

	public String getSub_status() {
		return sub_status;
	}

	public void setSub_status(String sub_status) {
		this.sub_status = sub_status;
	}

	public String getStatus_str() {
		return status_str;
	}

	public void setStatus_str(String status_str) {
		this.status_str = status_str;
	}

	public List<Medias> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Medias> attachments) {
		this.attachments = attachments;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getStatus_detail_str() {
		return status_detail_str;
	}

	public void setStatus_detail_str(String status_detail_str) {
		this.status_detail_str = status_detail_str;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", created_at=" + created_at
				+ ", modified_at=" + modified_at + ", subject=" + subject
				+ ", comments=" + comments + ", related_to=" + related_to
				+ ", skill1=" + skill1 + ", skill2=" + skill2 + ", kpi1="
				+ kpi1 + ", kpi2=" + kpi2 + ", kpi3=" + kpi3 + ", status="
				+ status + ", priority=" + priority + ", due_date=" + due_date
				+ ", place=" + place + ", attachments=" + attachments
				+ ", contact=" + contact + ", description=" + description
				+ ", assigned_to=" + assigned_to + ", creator=" + creator
				+ ", allowed_actions=" + allowed_actions + ", sub_status="
				+ sub_status + ", status_str=" + status_str
				+ ", status_detail_str=" + status_detail_str + ", owner="
				+ owner + "]";
	}
	
	public String getContent()
	{
		if (subject != null)
			if (description != null && !description.isEmpty())
				return subject + " - " + description;
		
		return subject != null ? subject : description;
	}
}
