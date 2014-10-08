package com.stone.pai.bean;

public class HistoryTaskList {
	private Long id;
	private User user;
	private String user_name;
	private String resource;
	private String rid;
	private String api;
	private String created_at;
	private String data;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "HistoryTaskList [id=" + id + ", user=" + user + ", user_name="
				+ user_name + ", resource=" + resource + ", rid=" + rid
				+ ", api=" + api + ", created_at=" + created_at + ", data="
				+ data + "]";
	}

}
