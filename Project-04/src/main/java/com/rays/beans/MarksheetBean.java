package com.rays.beans;

/**
 * MarksheetBean having attributes likes rollNo,
 * studentId,name,physics,chemistry,maths * having setter getter of attributes
 * 
 *@author Vikas Singh
 */

public class MarksheetBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private String rollNo;
	private int studentId;
	private String name;
	private int physics;
	private int chemistry;
	private int maths;

	public MarksheetBean() {
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhysics() {
		return physics;
	}

	public void setPhysics(int physics) {
		this.physics = physics;
	}

	public int getChemistry() {
		return chemistry;
	}

	public void setChemistry(int chemistry) {
		this.chemistry = chemistry;
	}

	public int getMaths() {
		return maths;
	}

	public void setMaths(int maths) {
		this.maths = maths;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getKey() {
		return id + "";
	}

	public String getValue() {
		return name;
	}

}
