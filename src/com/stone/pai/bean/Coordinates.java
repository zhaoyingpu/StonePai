package com.stone.pai.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 位置信息
 * 
 * @author strj
 * 
 * 
 *         2014-7-15 上午11:06:43
 */
@SuppressWarnings("serial")
public class Coordinates implements Serializable {
	private List<Double> coordinates;// 经纬度信息
	private String type; // 类型信息

	public List<Double> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<Double> coordinates) {
		this.coordinates = coordinates;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Coordinates [coordinates=" + coordinates + ", type=" + type
				+ "]";
	}

}
