package com.stone.pai.bean;

import java.io.Serializable;
import java.util.Collection;

/**
 * ʵ����Ϣ
 * 
 * @author strj
 * 
 * 
 *         2014-7-15 ����11:08:23
 */
@SuppressWarnings("serial")
public class Case implements Serializable {
	private int id; // id��Ϣ
	private String created_at; // ����ʱ��
	private String modified_at; // �޸�ʱ��
	private String subject; // ������Ϣ
	private String description; // ������Ϣ
	private int creator; // ������
	private int account; // ����
	private Contact contact; // ��ϵ����Ϣ
	private String status; // ״̬��Ϣ
	private String priority; // ���ȼ�
	private String type; // ������Ϣ
	private String category; // �����Ϣ
	private String case_origin; // ������Դ
	private String reason; // ԭ��ֵ
	private Coordinates coordinates;//������Ϣ
	private Collection<Comments> comments;//������Ϣ
	private Collection<Attachments> attachments;//������Ϣ

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCreator() {
		return creator;
	}

	public void setCreator(int creator) {
		this.creator = creator;
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCase_origin() {
		return case_origin;
	}

	public void setCase_origin(String case_origin) {
		this.case_origin = case_origin;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public Collection<Comments> getComments() {
		return comments;
	}

	public void setComments(Collection<Comments> comments) {
		this.comments = comments;
	}

	public Collection<Attachments> getAttachments() {
		return attachments;
	}

	public void setAttachments(Collection<Attachments> attachments) {
		this.attachments = attachments;
	}

	@Override
	public String toString() {
		return "Case [id=" + id + ", created_at=" + created_at
				+ ", modified_at=" + modified_at + ", subject=" + subject
				+ ", description=" + description + ", creator=" + creator
				+ ", account=" + account + ", contact=" + contact + ", status="
				+ status + ", priority=" + priority + ", type=" + type
				+ ", category=" + category + ", case_origin=" + case_origin
				+ ", reason=" + reason + ", coordinates=" + coordinates
				+ ", comments=" + comments + ", attachments=" + attachments
				+ "]";
	}

}
