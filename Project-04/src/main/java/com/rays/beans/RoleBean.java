package com.rays.beans;
/**
 * RoleBean having final attributes likes admin,student,college_school,kiosk
 * and attributes name,description
 * 
 * @author Vikas Singh
 */

public class RoleBean extends BaseBean {

	
	private static final long serialVersionUID = 1L;
	
	public static int ADMIN;
	public static int STUDENT;
	public static int COLLEGE_SCHOOL;
	public static int KIOSK;
	private String name;
	private String description;
	
	public RoleBean() {}

	public int getADMIN() {
		return ADMIN;
	}

	public void setADMIN(int aDMIN) {
		ADMIN = aDMIN;
	}

	public int getSTUDENT() {
		return STUDENT;
	}

	public void setSTUDENT(int sTUDENT) {
		STUDENT = sTUDENT;
	}

	public int getCOLLEGE_SCHOOL() {
		return COLLEGE_SCHOOL;
	}

	public void setCOLLEGE_SCHOOL(int cOLLEGE_SCHOOL) {
		COLLEGE_SCHOOL = cOLLEGE_SCHOOL;
	}

	public int getKIOSK() {
		return KIOSK;
	}

	public void setKIOSK(int kIOSK) {
		KIOSK = kIOSK;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getKey() {
		return id+"";
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return name;
	}
	
	

}
