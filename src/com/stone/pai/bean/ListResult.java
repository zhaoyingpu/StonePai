package com.stone.pai.bean;

import java.util.List;

public class ListResult<D> {
	private int start;
	private int count;
	private int total;
	private List<D> items;
	private int statusCode;
	private String content;

	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<D> getItems() {
		return items;
	}
	public void setItems(List<D> items) {
		this.items = items;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}