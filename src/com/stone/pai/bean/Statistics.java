package com.stone.pai.bean;

import java.io.Serializable;

/**
 * 任务列表信息
 * @author strj
 *
 *
 * 2014-9-19 下午3:29:23
 */
@SuppressWarnings("serial")
public class Statistics implements Serializable {
	private String status;
	private String substatus;
	private int count;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubstatus() {
		return substatus;
	}

	public void setSubstatus(String substatus) {
		this.substatus = substatus;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Statistics [status=" + status + ", substatus=" + substatus
				+ ", count=" + count + "]";
	}

}
