package com.rays.test.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.rays.beans.CourseBean;
import com.rays.modal.CourseModel;

import com.rays.exception.ApplicationException;
/**
 * 
 * @author Vikas Singh
 *
 */
public class TestCourse {
	public static CourseModel model = new CourseModel();

	public static void main(String[] args) throws Exception {
		// testPk();
		//testAdd();
		// testUpdate();
		// testDelete();
		// findByPk();
		// findByName();
		testSearch();
		//testList();
		
	}

	private static void testList() throws Exception {
		 try {
		        CourseBean bean = new CourseBean();
		        List<CourseBean> list = new ArrayList<CourseBean>();
		        list =  model.list(1, 100);
		        if (list.size() < 0) {
		            System.out.println("Test list fail");
		        }
	        Iterator<CourseBean> it = list.iterator();
	        while (it.hasNext()) {
	            bean = (CourseBean) it.next();
	            System.out.print(bean.getId() + "\t");
				System.out.print(bean.getCourseName() + "\t"+ "\t");
				System.out.print(bean.getDescription() + "\t"+ "\t");
				System.out.print(bean.getDuration() + "\t"+ "\t");
				System.out.print(bean.getCreatedBy() + "\t");
				System.out.print(bean.getModifiedBy() + "\t");
				System.out.print(bean.getCreatedDatetime() + "\t");
				System.out.println(bean.getModifiedDatetime());
	        }

	    } catch (ApplicationException e) {
	        e.printStackTrace();
	    }
	
	}

	private static void testSearch() throws Exception {
		CourseBean bean = new CourseBean();
	//	bean.setCourse_Name("py");
		List<CourseBean> list =model.search(bean, 0, 0);

		if (list.size() <= 0) {
			System.out.println("Test Search fail");
		}

		System.out.println(list);
		Iterator<CourseBean> it = list.iterator();
		while (it.hasNext()) {
			bean = (CourseBean) it.next();
			System.out.print(bean.getId() + "\t");
			System.out.print(bean.getCourseName() + "\t"+ "\t");
			System.out.print(bean.getDescription() + "\t"+ "\t");
			System.out.print(bean.getDuration() + "\t"+ "\t");
			System.out.print(bean.getCreatedBy() + "\t");
			System.out.print(bean.getModifiedBy() + "\t");
			System.out.print(bean.getCreatedDatetime() + "\t");
			System.out.println(bean.getModifiedDatetime());

		}
	}

	private static void findByName() throws Exception {
		String name = "Python";
		CourseBean bean = model.findByName(name);
		System.out.print(bean.getId() + "\t");
		System.out.print(bean.getCourseName() + "\t");
		System.out.print(bean.getDescription() + "\t" + "\t");
		System.out.print(bean.getDuration() + "\t" + "\t");
		System.out.print(bean.getCreatedBy() + "\t");
		System.out.print(bean.getModifiedBy() + "\t");
		System.out.print(bean.getCreatedDatetime() + "\t");
		System.out.println(bean.getModifiedDatetime());

	}

	public static void findByPk() throws Exception {
		int id= 1;
		CourseBean bean = model.findByPk(id);
		System.out.print(bean.getId() + "\t");
		System.out.print(bean.getCourseName() + "\t");
		System.out.print(bean.getDescription() + "\t");
		System.out.print(bean.getDuration() + "\t");
		System.out.print(bean.getCreatedBy() + "\t");
		System.out.print(bean.getModifiedBy() + "\t");
		System.out.print(bean.getCreatedDatetime() + "\t");
		System.out.println(bean.getModifiedDatetime());

	}

	private static void testDelete() throws Exception {
		CourseBean bean = new CourseBean();
		// bean.setId(3);

		model.delete(bean);

	}

	private static void testUpdate() throws Exception {
		CourseBean bean = new CourseBean();
		bean.setCourseName("Python");
		bean.setDescription("Programming Language");
		bean.setDuration("6 Months");
		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(2);

		model.update(bean);

	}

	private static void testAdd() throws Exception {
		CourseBean bean = new CourseBean();
		bean.setCourseName("BSc CS");
		bean.setDescription("Bachelor of Science(C.S.)");
		bean.setDuration("3 year");
		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedBy(null);

		Long pk = model.add(bean);
		System.out.println("Data Inserted in Id = " + pk);
	}

	private static void testPk() throws Exception {
		Long pk =model.nextPK();
		System.out.println(pk);
	}
}
