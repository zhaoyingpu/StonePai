package com.stone.pai;

import java.util.List;

public class TaskList {
	private String name;
	private String filter;
	private String orderby;
	private int icon;
	
	public TaskList(String n, String f, String o, int i)
	{
		name = n;
		filter = f;
		orderby = o;
		icon = i;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public String getOrderby() {
		return orderby;
	}
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	} 
	@Override
	public String toString() {
		return name;
	}
}
