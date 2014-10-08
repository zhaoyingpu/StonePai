package com.stone.pai.bean;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class User implements Serializable {
	private int id;
	private String created_at;
	private String modified_at;
	private String title;
	private Contact contact;
	private String name;
	private String profile_image_url;
	private boolean is_active;
	private String password;
	private List<Statistics> statistics;
	private List<Attachments> attachments;

	public List<Statistics> getStatistics() {
		return statistics;
	}

	public void setStatistics(List<Statistics> statistics) {
		this.statistics = statistics;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfile_image_url() {
		return profile_image_url;
	}

	public void setProfile_image_url(String profile_image_url) {
		this.profile_image_url = profile_image_url;
	}

	public List<Attachments> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachments> attachments) {
		this.attachments = attachments;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", created_at=" + created_at
				+ ", modified_at=" + modified_at + ", title=" + title
				+ ", contact=" + contact + ", name=" + name
				+ ", profile_image_url=" + profile_image_url + ", is_active="
				+ is_active + ", password=" + password + ", statistics="
				+ statistics + ", attachments=" + attachments + "]";
	}

}
