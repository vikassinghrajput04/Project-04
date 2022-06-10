package com.rays.beans;

/**
 * TimetableBean having attributes courseName,courseId,subjectName,subjectId,email,collegeId,collegeName
 * 
 * @author Vikas Singh
 **/
import java.util.Date;

public class TimetableBean extends BaseBean {

	private static final long serialVersionUID = 1L;
	private String courseName;
	private int courseId;
	private String subjectName;
	private int subjectId;
	private Date examDate;
	private String examTime;
	private String semester;

	public TimetableBean() {
		// TODO Auto-generated constructor stub
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
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