package com.stone.pai.bean;

import java.io.Serializable;

public class EmployBean implements Serializable {

	private Integer Eid; // 员工ID
	private String Ename; // 员工名称
	private String Epassword; // 登录密码
	private Integer Cid; // 所在公司ID
	private String Etel; // 员工电话
	private Integer Pid; // 员工权限ID
	private String EaddInfo; // 员工坐标
	private Integer Sid; // 任务状态ID
	private String Sname;
	private int sign;// 为0表示未选中，为1表示选中
	private String Eavater;// 员工头像地址
	private Integer TaskCount;// 任务总数
	private String level;// 等级
	private String access;// 地区

	public Integer getTaskCount() {
		return TaskCount;
	}

	public void setTaskCount(Integer taskCount) {
		TaskCount = taskCount;
	}

	public String getEavater() {
		return Eavater;
	}

	public void setEavater(String eavater) {
		Eavater = eavater;
	}

	public int getSign() {
		return sign;
	}

	public void setSign(int sign) {
		this.sign = sign;
	}

	public String getSname() {
		return Sname;
	}

	public void setSname(String sname) {
		Sname = sname;
	}

	public Integer getEid() {
		return Eid;
	}

	public void setEid(Integer eid) {
		Eid = eid;
	}

	public String getEname() {
		return Ename;
	}

	public void setEname(String ename) {
		Ename = ename;
	}

	public String getEpassword() {
		return Epassword;
	}

	public void setEpassword(String epassword) {
		Epassword = epassword;
	}

	public Integer getCid() {
		return Cid;
	}

	public void setCid(Integer cid) {
		Cid = cid;
	}

	public String getEtel() {
		return Etel;
	}

	public void setEtel(String etel) {
		Etel = etel;
	}

	public Integer getPid() {
		return Pid;
	}

	public void setPid(Integer pid) {
		Pid = pid;
	}

	public String getEaddInfo() {
		return EaddInfo;
	}

	public void setEaddInfo(String eaddInfo) {
		EaddInfo = eaddInfo;
	}

	public Integer getSid() {
		return Sid;
	}

	public void setSid(Integer sid) {
		Sid = sid;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	@Override
	public String toString() {
		return "EmployBean [Eid=" + Eid + ", Ename=" + Ename + ", Epassword="
				+ Epassword + ", Cid=" + Cid + ", Etel=" + Etel + ", Pid="
				+ Pid + ", EaddInfo=" + EaddInfo + ", Sid=" + Sid + ", Sname="
				+ Sname + ", sign=" + sign + ", Eavater=" + Eavater
				+ ", TaskCount=" + TaskCount + ", level=" + level + ", access="
				+ access + "]";
	}

}
