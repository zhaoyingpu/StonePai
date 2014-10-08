package com.stone.pai.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Level implements Serializable {
	private long id;
	private String name;
	private double value1;
	private double value2;
	private double value3;
	private int order;

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

	public double getValue1() {
		return value1;
	}

	public void setValue1(double value1) {
		this.value1 = value1;
	}

	public double getValue2() {
		return value2;
	}

	public void setValue2(double value2) {
		this.value2 = value2;
	}

	public double getValue3() {
		return value3;
	}

	public void setValue3(double value3) {
		this.value3 = value3;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	@Override
	public String toString() {
		return "Level [id=" + id + ", name=" + name + ", value1=" + value1
				+ ", value2=" + value2 + ", value3=" + value3 + ", order="
				+ order + "]";
	}

}
