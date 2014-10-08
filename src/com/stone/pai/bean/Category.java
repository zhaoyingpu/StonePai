package com.stone.pai.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Category implements Serializable {
	private long id;
	private String name;
	private boolean limit_area;
	private boolean custom_skill;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isLimit_area() {
		return limit_area;
	}

	public void setLimit_area(boolean limit_area) {
		this.limit_area = limit_area;
	}

	public boolean isCustom_skill() {
		return custom_skill;
	}

	public void setCustom_skill(boolean custom_skill) {
		this.custom_skill = custom_skill;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", limit_area="
				+ limit_area + ", custom_skill=" + custom_skill + "]";
	}

}
