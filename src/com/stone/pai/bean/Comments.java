package com.stone.pai.bean;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 评价信息
 * 
 * @author strj
 * 
 * 
 *         2014-7-15 上午10:15:00
 */
@SuppressWarnings("serial")
public class Comments implements Serializable {
	private Long id;
	private Long ref_id;
	private String content;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Comments [id=" + id + ", ref_id=" + ref_id + ", content="
				+ content + ", type=" + type + "]";
	}

}
