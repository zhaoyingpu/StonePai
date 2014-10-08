package com.stone.pai.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AllActions implements Serializable {
	private String api;// 动作
	private String description;// 动作描述

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "AllActions [api=" + api + ", description=" + description + "]";
	}

}
