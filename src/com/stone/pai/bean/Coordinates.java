package com.stone.pai.bean;

import java.io.Serializable;
import java.util.List;

/**
 * λ����Ϣ
 * 
 * @author strj
 * 
 * 
 *         2014-7-15 ����11:06:43
 */
@SuppressWarnings("serial")
public class Coordinates implements Serializable {
	private List<Double> coordinates;// ��γ����Ϣ
	private String type; // ������Ϣ

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
