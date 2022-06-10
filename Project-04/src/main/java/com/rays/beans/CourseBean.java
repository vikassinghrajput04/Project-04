package com.rays.beans;

/**
 * CourseBean having attributes course_name,address,state,city and phoneNo
 * having setter getter of attributes
 * 
 *@author Vikas Singh
 */
public class CourseBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String courseName;
	private String description;
	private String duration;
	
	public CourseBean() {}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getKey() {
		// TODO Auto-generated method stub
		return id + "";
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return courseName;
	}

}
