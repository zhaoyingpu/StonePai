package com.stone.pai.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Medias implements Serializable {
	private long id; // 文件id
	private String url; // 文件路径
	private String type; // 文件类型
	private int file_size;// 文件大小
	private String created_at;// 创建时间
	private String file_name;
	private String media_size;
	private Object data;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getFile_size() {
		return file_size;
	}

	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return "Medias [id=" + id + ", url=" + url + ", type=" + type
				+ ", file_size=" + file_size + ", created_at=" + created_at
				+ "]";
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getMedia_size() {
		return media_size;
	}

	public void setMedia_size(String media_size) {
		this.media_size = media_size;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
