package com.stone.pai.bean;

import java.io.Serializable;

/**
 * 联系人信息
 * 
 * @author strj
 * 
 * 
 *         2014-7-15 上午11:21:21
 */
@SuppressWarnings("serial")
public class Contact implements Serializable {
	private int id;
	private String email;
	private String work_phone;
	private String mobile_phone;
	private String fax;
	private String city;
	private String street;
	private String province;
	private String postal_code;
	private String country;
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWork_phone() {
		return work_phone;
	}

	public void setWork_phone(String work_phone) {
		this.work_phone = work_phone;
	}

	public String getMobile_phone() {
		return mobile_phone;
	}

	public void setMobile_phone(String mobile_phone) {
		this.mobile_phone = mobile_phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", email=" + email + ", work_phone="
				+ work_phone + ", mobile_phone=" + mobile_phone + ", fax="
				+ fax + ", city=" + city + ", street=" + street + ", province="
				+ province + ", postal_code=" + postal_code + ", country="
				+ country + ", name=" + name + "]";
	}

}
