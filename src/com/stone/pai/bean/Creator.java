package com.stone.pai.bean;

import java.io.Serializable;
/**
 * 创建者
 * @author strj
 *
 *
 * 2014-9-4 上午9:09:22
 */
@SuppressWarnings("serial")
public class Creator implements Serializable {
	private long id;
	private String created_at;
	private String modified_at;
	private String title;
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Creator [id=" + id + ", created_at=" + created_at
				+ ", modified_at=" + modified_at + ", title=" + title
				+ ", name=" + name + "]";
	}

}
