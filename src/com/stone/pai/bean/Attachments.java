package com.stone.pai.bean;

import java.io.Serializable;

/**
 * 附件信息
 * 
 * @author strj
 * 
 * 
 *         2014-7-15 上午11:10:20
 */
@SuppressWarnings("serial")
public class Attachments implements Serializable {
	private Long id;
	private Long ref_id;
	private String url;
	private String type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRef_id() {
		return ref_id;
	}

	public void setRef_id(Long ref_id) {
		this.ref_id = ref_id;
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

	@Override
	public String toString() {
		return "Attachments [id=" + id + ", ref_id=" + ref_id + ", url=" + url
				+ ", type=" + type + "]";
	}

}
