package com.rays.beans;

/**
 * StudentBean having attributes firstname,lastname,dob,mobileNo,email,collegeId,collegeName
 * 
 * @author Vikas Singh
 **/
import java.util.Date;

public class StudentBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private String firstName;
	private String lastName;
	private Date dob;
	private String mobileNo;
	private String email;
	private int collegeId;
	private String collegeName;

	public StudentBean() {}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(int collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getKey() {
		return id+"";
	}
	public String getValue() {
		return firstName;
	}

}
