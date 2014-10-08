package com.stone.pai.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AccessInfo implements Serializable {
	private Location location;// 经纬度信息
	private String formatted_address;// 地址信息

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}

	@Override
	public String toString() {
		return "AccessInfo [location=" + location + ", formatted_address="
				+ formatted_address + "]";
	}

}
