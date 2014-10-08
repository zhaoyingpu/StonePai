package com.stone.pai.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AllActions implements Serializable {
	private String api;// ����
	private String description;// ��������

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
