package com.stone.pai.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LogInCode implements Serializable {
	private int id;
	private String challenge_code;
	private String token_type;
	private int refresh_in;
	private int expires_in;
	private String challenge_message;
	private String access_token;

	public String getChallenge_code() {
		return challenge_code;
	}

	public void setChallenge_code(String challenge_code) {
		this.challenge_code = challenge_code;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public int getRefresh_in() {
		return refresh_in;
	}

	public void setRefresh_in(int refresh_in) {
		this.refresh_in = refresh_in;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public String getChallenge_message() {
		return challenge_message;
	}

	public void setChallenge_message(String challenge_message) {
		this.challenge_message = challenge_message;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "LogInCode [id=" + id + ", challenge_code=" + challenge_code
				+ ", token_type=" + token_type + ", refresh_in=" + refresh_in
				+ ", expires_in=" + expires_in + ", challenge_message="
				+ challenge_message + ", access_token=" + access_token + "]";
	}

	
}
