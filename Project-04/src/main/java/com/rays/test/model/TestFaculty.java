package com.rays.test.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.rays.beans.FacultyBean;
import com.rays.exception.DatabaseException;
import com.rays.modal.FacultyModel;
/**
 * 
 * @author Vikas Singh
 *
 */
public class TestFaculty {
	private static FacultyModel model = new FacultyModel();

	public static void main(String[] args) throws Exception {
		// testPK();
		// testAdd();
		// testUpdate();
		// testFindByPk();
		// testFindByName();
		// testFindByEmail();
		// testDelete();
		// testList();
		testSearch();
	}

	public static void testSearch() throws Exception {

		List<FacultyBean> list = new ArrayList<FacultyBean>();
		FacultyBean bean = new FacultyBean();
		list = model.search(bean, 0, 0);
		Iterator<FacultyBean> it = list.iterator();

		while (it.hasNext()) {
			bean = (FacultyBean) it.next();
			System.out.print(bean.getId() + "\t");
			System.out.print(bean.getFirstName() + "\t");
			System.out.print(bean.getLastName() + "\t");
			System.out.print(bean.getGender() + "\t");
			System.out.print(bean.getdOJ() + "\t");
			System.out.print(bean.getQualification() + "\t");
			System.out.print(bean.getEmailId() + "\t");
			System.out.print(bean.getMobileNo() + "\t");
			System.out.print(bean.getCollegeId() + "\t");
			System.out.print(bean.getCollegeName() + "\t");
			System.out.print(bean.getCourseId() + "\t");
			System.out.print(bean.getCourseName());
			System.out.print(bean.getSubjectId() + "\t");
			System.out.print(bean.getSubjectName() + "\t");
			System.out.print(bean.getCreatedBy() + "\t");
			System.out.print(bean.getModifiedBy() + "\t");
			System.out.print(bean.getCreatedDatetime() + "\t");
			System.out.println(bean.getModifiedDatetime());
		}

	}

	public static void testList() throws Exception {
		List<FacultyBean> list = new ArrayList<FacultyBean>();

		list = model.list(0, 100);
		Iterator<FacultyBean> it = list.iterator();

		while (it.hasNext()) {
			FacultyBean bean = (FacultyBean) it.next();
			System.out.print(bean.getId() + "\t");
			System.out.print(bean.getFirstName() + "\t");
			System.out.print(bean.getLastName() + "\t");
			System.out.print(bean.getGender() + "\t");
			System.out.print(bean.getdOJ() + "\t");
			System.out.print(bean.getQualification() + "\t");
			System.out.print(bean.getEmailId() + "\t");
			System.out.print(bean.getMobileNo() + "\t");
			System.out.print(bean.getCollegeId() + "\t");
			System.out.print(bean.getCollegeName() + "\t");
			System.out.print(bean.getCourseId() + "\t");
			System.out.print(bean.getCourseName());
			System.out.print(bean.getSubjectId() + "\t");
			System.out.print(bean.getSubjectName() + "\t");
			System.out.print(bean.getCreatedBy() + "\t");
			System.out.print(bean.getModifiedBy() + "\t");
			System.out.print(bean.getCreatedDatetime() + "\t");
			System.out.println(bean.getModifiedDatetime());
		}

	}

	public static void testDelete() throws Exception {
		FacultyBean bean = new FacultyBean();
		bean.setId(0);
		try {
			model.delete(bean);
		} catch (Exception e) {
			throw new DatabaseException("Error in test Delete");
		}

	}

	public static void testFindByEmail() throws Exception {
		String email = "rakesh123@gmail.com";
		try {
			FacultyBean bean = model.findByEmail(email);
			System.out.print(bean.getId() + "\t");
			System.out.print(bean.getFirstName() + "\t");
			System.out.print(bean.getLastName() + "\t");
			System.out.print(bean.getGender() + "\t");
			System.out.print(bean.getdOJ() + "\t");
			System.out.print(bean.getQualification() + "\t");
			System.out.print(bean.getEmailId() + "\t");
			System.out.print(bean.getMobileNo() + "\t");
			System.out.print(bean.getCollegeId() + "\t");
			System.out.print(bean.getCollegeName() + "\t");
			System.out.print(bean.getCourseId() + "\t");
			System.out.print(bean.getCourseName());
			System.out.print(bean.getSubjectId() + "\t");
			System.out.print(bean.getSubjectName() + "\t");
			System.out.print(bean.getCreatedBy() + "\t");
			System.out.print(bean.getModifiedBy() + "\t");
			System.out.print(bean.getCreatedDatetime() + "\t");
			System.out.print(bean.getModifiedDatetime());

		} catch (Exception e) {
			throw new DatabaseException("Error in testFindByEmail");
		}
	}

	public static void testFindByName() throws Exception {
		String name = "Vikas";
		try {
			FacultyBean bean = model.findByName(name);
			System.out.print(bean.getId() + "\t");
			System.out.print(bean.getFirstName() + "\t");
			System.out.print(bean.getLastName() + "\t");
			System.out.print(bean.getGender() + "\t");
			System.out.print(bean.getdOJ() + "\t");
			System.out.print(bean.getQualification() + "\t");
			System.out.print(bean.getEmailId() + "\t");
			System.out.print(bean.getMobileNo() + "\t");
			System.out.print(bean.getCollegeId() + "\t");
			System.out.print(bean.getCollegeName() + "\t");
			System.out.print(bean.getCourseId() + "\t");
			System.out.print(bean.getCourseName());
			System.out.print(bean.getSubjectId() + "\t");
			System.out.print(bean.getSubjectName() + "\t");
			System.out.print(bean.getCreatedBy() + "\t");
			System.out.print(bean.getModifiedBy() + "\t");
			System.out.print(bean.getCreatedDatetime() + "\t");
			System.out.print(bean.getModifiedDatetime());

		} catch (Exception e) {
			throw new DatabaseException("Error in testFindByName");
		}
	}

	public static void testFindByPk() throws Exception {
		long id = 0;
		try {
			FacultyBean bean = model.findByPk(id);
			System.out.print(bean.getId() + "\t");
			System.out.print(bean.getFirstName() + "\t");
			System.out.print(bean.getLastName() + "\t");
			System.out.print(bean.getGender() + "\t");
			System.out.print(bean.getdOJ() + "\t");
			System.out.print(bean.getQualification() + "\t");
			System.out.print(bean.getEmailId() + "\t");
			System.out.print(bean.getMobileNo() + "\t");
			System.out.print(bean.getCollegeId() + "\t");
			System.out.print(bean.getCollegeName() + "\t");
			System.out.print(bean.getCourseId() + "\t");
			System.out.print(bean.getCourseName());
			System.out.print(bean.getSubjectId() + "\t");
			System.out.print(bean.getSubjectName() + "\t");
			System.out.print(bean.getCreatedBy() + "\t");
			System.out.print(bean.getModifiedBy() + "\t");
			System.out.print(bean.getCreatedDatetime() + "\t");
			System.out.print(bean.getModifiedDatetime());

		} catch (Exception e) {
			throw new DatabaseException("Error in testFindByPk");
		}
	}

	public static void testUpdate() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			FacultyBean bean = new FacultyBean();
			bean.setFirstName("Prakash");
			bean.setLastName("Jain");
			bean.setGender("male");
			bean.setdOJ(sdf.parse("11/05/2001"));
			bean.setQualification("Graduated");
			bean.setEmailId("prakash123@gmail.com");
			bean.setMobileNo("9165487265");
			bean.setCollegeId(1);
			bean.setCollegeName("MB Khalsa College");
			bean.setCourseId(1);
			bean.setCourseName("Java");
			bean.setSubjectId(1);
			bean.setSubjectName("Core Java");
			bean.setCreatedBy("Admin");
			bean.setModifiedBy("Admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			bean.setId(1);

			model.update(bean);
		} catch (Exception e) {
			throw new DatabaseException("Exception in test Update");
		}

	}

	public static void testAdd() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			FacultyBean bean = new FacultyBean();
			bean.setFirstName("Ritik");
			bean.setLastName("Verma");
			bean.setGender("male");
			bean.setdOJ(sdf.parse("04/12/1998"));
			bean.setQualification("PostGraduated");
			bean.setEmailId("ritik123@gmail.com");
			bean.setMobileNo("9167845265");
			bean.setCollegeId(2);
			bean.setCollegeName("SVIM");
			bean.setCourseId(2);
			bean.setCourseName("Python");
			bean.setSubjectId(1);
			bean.setSubjectName("Core Python");
			bean.setCreatedBy("Admin");
			bean.setModifiedBy(null);
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(null);

			Long pk = model.add(bean);
			System.out.println("Data Inserted in Id = " + pk);
		} catch (Exception e) {
			throw new DatabaseException("Error in test add");
		}
	}

	public static void testPK() throws Exception {
		Long id = model.nextPK();
		System.out.println("Next id = " + id);
	}
}
