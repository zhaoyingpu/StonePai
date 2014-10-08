package com.stone.pai.bean;

import java.io.Serializable;

public class Place implements Serializable {
	private String country;
	private String province;
	private String city;
	private String district;
	private String street;
	private String address;
	private Coordinates coordinates;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	@Override
	public String toString() {
		return "Place [country=" + country + ", province=" + province
				+ ", city=" + city + ", district=" + district + ", street="
				+ street + ", address=" + address + ", coordinates="
				+ coordinates + "]";
	}

}
