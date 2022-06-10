package com.rays.beans;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * a BaseBean Class having Serializable Interface and Comparable Interface
 * executed by all classes making it as an parent Class.
 * 
 * @author Vikas Singh
 */
public abstract class BaseBean implements Comparable<BaseBean>, Serializable, DropdownListBean {

	private static final long serialVersionUID = 1L;

	long id;
	String createdBy;
	String modifiedBy;
	Timestamp createdDatetime;
	Timestamp modifiedDatetime;
	
	public BaseBean() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public Timestamp getModifiedDatetime() {
		return modifiedDatetime;
	}

	public void setModifiedDatetime(Timestamp modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}

	public int compareTo(BaseBean bean) {

		return 0;
	}

}
