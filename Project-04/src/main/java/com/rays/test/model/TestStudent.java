package com.rays.test.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.rays.beans.StudentBean;
import com.rays.exception.ApplicationException;
import com.rays.exception.DuplicateRecordException;
import com.rays.modal.StudentModel;
/**
 * 
 * @author Vikas Singh
 *
 */
public class TestStudent {

	public static StudentModel model = new StudentModel();

	public static void main(String[] args) throws Exception {
		//testAdd();
		 //testDelete();
		//testUpdate();
		 //testFindByPK();
		//testFindByEmailId();
		 //testSearch();
		//testList();
		testName();

	}

	
	public static void testName() throws Exception {
		try {
			StudentBean bean = new StudentBean();
			bean = model.findByName("Piyush");
			if (bean != null) {
				System.out.println("Test Find By Name fail");
			}
			System.out.print(bean.getId()+"\t");
			System.out.print(bean.getCollegeId()+"\t");
			System.out.print(bean.getCollegeName()+"\t");
			System.out.print(bean.getFirstName()+"\t");
			System.out.print(bean.getLastName()+"\t");
			System.out.print(bean.getDob()+"\t");
			System.out.print(bean.getMobileNo()+"\t");
			System.out.print(bean.getEmail()+"\t");
			System.out.print(bean.getCreatedBy() + "\t");
			System.out.print(bean.getModifiedBy() + "\t");
			System.out.print(bean.getCreatedDatetime() + "\t");
			System.out.println(bean.getModifiedDatetime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		
	}


	public static void testAdd() throws Exception {

		try {
			StudentBean bean = new StudentBean();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			bean.setCollegeId(4);
			//bean.setCollegeName("MB khalsa College");
			bean.setFirstName("Rajveer");
			bean.setLastName("Shah");
			bean.setDob(sdf.parse("04/08/2001"));
			bean.setMobileNo("985618142");
			bean.setEmail("Rajveer123@gmail.com");
			bean.setCreatedBy("Admin");
			bean.setModifiedBy(null);
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(null);
			long pk = model.add(bean);
			StudentBean addedbean = model.findByPK(pk);
			if (addedbean == null) {
				System.out.println("Test add fail");
			}
			System.out.println("Add Success!!, Record added in Id-- "+pk);
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	
	public static void testDelete() throws Exception {

		try {
			StudentBean bean = new StudentBean();
			long pk = 4;
			bean.setId(pk);
			model.delete(bean);
			StudentBean deletedbean = model.findByPK(pk);
			if (deletedbean != null) {
				System.out.println("Test Delete fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	
	public static void testUpdate() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			StudentBean bean = model.findByPK(1);

			bean.setFirstName("Ram");
			bean.setLastName("Shah");
			bean.setDob(sdf.parse("04/08/2001"));
			bean.setMobileNo("985618142");
			bean.setEmail("Rajveer123@gmail.com");
			bean.setCreatedBy("Admin");
			bean.setModifiedBy("Admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			bean.setId(5);
			model.update(bean);

			StudentBean updatedbean = model.findByPK(bean.getId());

			System.out.println("Test Update Success of First_Name-- "+updatedbean.getFirstName());

		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
	}

	
	public static void testFindByPK() throws Exception {
		try {
			StudentBean bean = new StudentBean();
			long pk = 1;
			bean = model.findByPK(pk);
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.print(bean.getId()+"\t");
			System.out.print(bean.getCollegeId()+"\t");
			System.out.print(bean.getCollegeName()+"\t");
			System.out.print(bean.getFirstName()+"\t");
			System.out.print(bean.getLastName()+"\t");
			System.out.print(bean.getDob()+"\t");
			System.out.print(bean.getMobileNo()+"\t");
			System.out.print(bean.getEmail()+"\t");
			System.out.print(bean.getCreatedBy() + "\t");
			System.out.print(bean.getModifiedBy() + "\t");
			System.out.print(bean.getCreatedDatetime() + "\t");
			System.out.println(bean.getModifiedDatetime());
			
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	
	public static void testFindByEmailId() throws Exception {
		try {
			StudentBean bean = new StudentBean();
			bean = model.findByEmailId("vinay123@gmail.com");
			if (bean != null) {
				System.out.println("Test Find By EmailId fail");
			}
			System.out.print(bean.getId()+"\t");
			System.out.print(bean.getCollegeId()+"\t");
			System.out.print(bean.getCollegeName()+"\t");
			System.out.print(bean.getFirstName()+"\t");
			System.out.print(bean.getLastName()+"\t");
			System.out.print(bean.getDob()+"\t");
			System.out.print(bean.getMobileNo()+"\t");
			System.out.print(bean.getEmail()+"\t");
			System.out.print(bean.getCreatedBy() + "\t");
			System.out.print(bean.getModifiedBy() + "\t");
			System.out.print(bean.getCreatedDatetime() + "\t");
			System.out.println(bean.getModifiedDatetime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	
	public static void testSearch() throws Exception {

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			StudentBean bean = new StudentBean();
			List<StudentBean> list = new ArrayList<StudentBean>();
			bean.setCollegeName("SVIMS");
			list = model.search(bean, 1, 100);
			if (list.size() < 0) {
				System.out.println("Test Search fail");
			}
			Iterator<StudentBean> it = list.iterator();
			while (it.hasNext()) {
				bean = (StudentBean) it.next();
				System.out.print(bean.getId()+"\t");
				System.out.print(bean.getCollegeId()+"\t");
				System.out.print(bean.getCollegeName()+"\t");
				System.out.print(bean.getFirstName()+"\t");
				System.out.print(bean.getLastName()+"\t");
				System.out.print(bean.getDob()+"\t");
				System.out.print(bean.getMobileNo()+"\t");
				System.out.print(bean.getEmail()+"\t");
				System.out.print(bean.getCreatedBy() + "\t");
				System.out.print(bean.getModifiedBy() + "\t");
				System.out.print(bean.getCreatedDatetime() + "\t");
				System.out.println(bean.getModifiedDatetime());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	
	public static void testList() throws Exception {

		try {
			StudentBean bean = new StudentBean();
			List<StudentBean> list = new ArrayList<StudentBean>();
			list = model.list(1, 10);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator<StudentBean> it = list.iterator();
			while (it.hasNext()) {
				bean = (StudentBean) it.next();
				System.out.print(bean.getId()+"\t");
				System.out.print(bean.getCollegeId()+"\t");
				System.out.print(bean.getCollegeName()+"\t");
				System.out.print(bean.getFirstName()+"\t");
				System.out.print(bean.getLastName()+"\t");
				System.out.print(bean.getDob()+"\t");
				System.out.print(bean.getMobileNo()+"\t");
				System.out.print(bean.getEmail()+"\t");
				System.out.print(bean.getCreatedBy() + "\t");
				System.out.print(bean.getModifiedBy() + "\t");
				System.out.print(bean.getCreatedDatetime() + "\t");
				System.out.println(bean.getModifiedDatetime());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

}
