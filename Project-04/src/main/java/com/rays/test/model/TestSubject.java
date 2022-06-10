package com.rays.test.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.rays.beans.SubjectBean;
import com.rays.exception.ApplicationException;
import com.rays.modal.SubjectModel;
/**
 * 
 * @author Vikas Singh
 *
 */
public class TestSubject {

	public static SubjectModel model = new SubjectModel();

	public static void main(String[] args) throws Exception {
		//testAdd();
		//testDelete();
		//testUpdate();
		// testFindByPK();
		// testfindByName() ;
		 //testsearch();
		testlist();

	}

	public static void testAdd() throws Exception {

		try {
			SubjectBean bean = new SubjectBean();

			bean.setSubjectName("Computer");
			bean.setCourseName("Bsc CS");
			bean.setCourseId(3);
			bean.setDescription("Bachelor in Science in (C.S.)");
			bean.setCreatedBy("Admin");
			bean.setModifiedBy("Admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

			long pk = model.add(bean);
			
			SubjectBean addedBean = model.findByPk(pk);
			if (addedBean == null) {
				System.out.println("Test add fail");
			}
			System.out.println("Test add success!!!");
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testDelete() throws Exception {

		try {
			SubjectBean bean = new SubjectBean();
			long pk = 2;
			bean.setId(pk);
			model.delete(bean);
			
			SubjectBean deletedBean = model.findByPk(pk);
			if (deletedBean.getCourseName() != null) {
				System.out.println("Test Delete fail");
			}
			System.out.println("Test Delete success");
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testUpdate() throws Exception {

		try {
			SubjectBean bean = new SubjectBean();
			bean.setSubjectName("Foundation");
			bean.setCourseName("Java");
			bean.setCourseId(1);
			bean.setDescription("Corporate Java");
			bean.setCreatedBy("Admin");
			bean.setModifiedBy("Admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			bean.setId(4);

			model.update(bean);
			System.out.println("Test Update Success!!!");

		} catch (ApplicationException e) {
			e.printStackTrace();
		} 
	}

	public static void testfindByPk() throws Exception {
		try {
			SubjectBean bean = new SubjectBean();
			long pk = 2;
			bean = model.findByPk(pk);
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.print(bean.getId()+"\t");
			System.out.print(bean.getSubjectName()+"\t");
			System.out.print(bean.getCourseName()+"\t");
			System.out.print(bean.getCourseId()+"\t");
			System.out.print(bean.getDescription()+"\t");
			System.out.print(bean.getCreatedBy()+"\t");
			System.out.print(bean.getModifiedBy()+"\t");
			System.out.print(bean.getCreatedDatetime()+"\t");
			System.out.println(bean.getModifiedDatetime()+"\t");

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testfindByName() throws Exception {
		try {
			SubjectBean bean = new SubjectBean();
			bean = model.findByName("Foundation");
			if (bean == null) {
				System.out.println("Test Find By findByName fail");
			}
			System.out.print(bean.getId()+"\t");
			System.out.print(bean.getSubjectName()+"\t");
			System.out.print(bean.getCourseName()+"\t");
			System.out.print(bean.getCourseId()+"\t");
			System.out.print(bean.getDescription()+"\t");
			System.out.print(bean.getCreatedBy()+"\t");
			System.out.print(bean.getModifiedBy()+"\t");
			System.out.print(bean.getCreatedDatetime()+"\t");
			System.out.println(bean.getModifiedDatetime()+"\t");

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testFindByPK() throws Exception {
		try {
			SubjectBean bean = new SubjectBean();
			long pk = 2;
			bean = model.findByPk(pk);
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.print(bean.getId()+"\t");
			System.out.print(bean.getSubjectName()+"\t");
			System.out.print(bean.getCourseName()+"\t");
			System.out.print(bean.getCourseId()+"\t");
			System.out.print(bean.getDescription()+"\t");
			System.out.print(bean.getCreatedBy()+"\t");
			System.out.print(bean.getModifiedBy()+"\t");
			System.out.print(bean.getCreatedDatetime()+"\t");
			System.out.println(bean.getModifiedDatetime());

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testsearch() throws Exception {

		try {
			SubjectBean bean = new SubjectBean();

			bean.setCourseName("Jav");
			List<SubjectBean> list = new ArrayList<SubjectBean>();
			list = model.search(bean, 1, 100);
			if (list.size() < 0) {
				System.out.println("Test Search fail");
			}
			Iterator<SubjectBean> it = list.iterator();
			while (it.hasNext()) {
				bean = (SubjectBean) it.next();
				System.out.print(bean.getId()+"\t");
				System.out.print(bean.getSubjectName()+"\t");
				System.out.print(bean.getCourseName()+"\t");
				System.out.print(bean.getCourseId()+"\t");
				System.out.print(bean.getDescription()+"\t");
				System.out.print(bean.getCreatedBy()+"\t");
				System.out.print(bean.getModifiedBy()+"\t");
				System.out.print(bean.getCreatedDatetime()+"\t");
				System.out.println(bean.getModifiedDatetime());

			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testlist() throws Exception {

		try {
			SubjectBean bean = new SubjectBean();
			List<SubjectBean> list = new ArrayList<SubjectBean>();
			list = model.list(1, 10);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator<SubjectBean> it = list.iterator();
			while (it.hasNext()) {
				bean = (SubjectBean) it.next();

				System.out.print(bean.getId()+"\t");
				System.out.print(bean.getSubjectName()+"\t");
				System.out.print(bean.getCourseName()+"\t");
				System.out.print(bean.getCourseId()+"\t");
				System.out.print(bean.getDescription()+"\t");
				System.out.print(bean.getCreatedBy()+"\t");
				System.out.print(bean.getModifiedBy()+"\t");
				System.out.print(bean.getCreatedDatetime()+"\t");
				System.out.println(bean.getModifiedDatetime());

			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
}
