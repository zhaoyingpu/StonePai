package com.stone.pai.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AssignUser implements Serializable {
	private long assigned_to;
	private long kpi1;
	private long kpi2;
	private String priority;

	public long getAssigned_to() {
		return assigned_to;
	}

	public void setAssigned_to(long assigned_to) {
		this.assigned_to = assigned_to;
	}

	public long getKpi1() {
		return kpi1;
	}

	public void setKpi1(long kpi1) {
		this.kpi1 = kpi1;
	}

	public long getKpi2() {
		return kpi2;
	}

	public void setKpi2(long kpi2) {
		this.kpi2 = kpi2;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "AssignUser [assigned_to=" + assigned_to + ", kpi1=" + kpi1
				+ ", kpi2=" + kpi2 + ", priority=" + priority + "]";
	}

}
