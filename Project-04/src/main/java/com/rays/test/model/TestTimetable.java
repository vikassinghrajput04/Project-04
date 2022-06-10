package com.rays.test.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.rays.beans.TimetableBean;
import com.rays.modal.TimetableModel;
/**
 * 
 * @author Vikas Singh
 *
 */
public class TestTimetable {
	private static TimetableModel model = new TimetableModel();

	public static void main(String[] args) throws Exception {
		// testAdd();
		//testcheckbysem();
		//testlist();
		// testupdate();
	testFindbypk();

	}

	public static void testFindbypk() throws Exception {

		TimetableBean bean = new TimetableBean();

		bean = model.findByPK(2);

		System.out.print(bean.getId()+"\t");
		System.out.print(bean.getCourseName()+"\t");
		System.out.print(bean.getCourseId()+"\t");
		System.out.print(bean.getSubjectName()+"\t");
		System.out.print(bean.getSubjectId()+"\t");
		System.out.print(bean.getExamDate()+"\t");
		System.out.print(bean.getExamTime()+"\t");
		System.out.print(bean.getSemester()+"\t");
		System.out.print(bean.getCreatedBy()+"\t");
		System.out.print(bean.getModifiedBy()+"\t");
		System.out.print(bean.getCreatedDatetime()+"\t");
		System.out.println(bean.getModifiedDatetime());

	}

	public static void testupdate() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		TimetableBean bean = new TimetableBean();

		Date dt = new Date();
		dt = sdf.parse("15/04/2022");

		bean.setCourseName("Bsc Cs");
		bean.setCourseId(3);
		bean.setSubjectName("Physics");
		bean.setSubjectId(2);
		bean.setExamDate(dt);
		bean.setExamTime("1:00 p.m.");
		bean.setSemester("3rd");
		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(2);
		model.update(bean);
		System.out.println("Update Success!!!");

	}

	public static void testlist() throws Exception {

		TimetableBean bean = new TimetableBean();

		List<TimetableBean> list = new ArrayList<TimetableBean>();
		list = model.list();
		Iterator<TimetableBean> it = list.iterator();
		while (it.hasNext()) {
			bean = (TimetableBean) it.next();
			System.out.print(bean.getId()+"\t");
			System.out.print(bean.getCourseName()+"\t");
			System.out.print(bean.getCourseId()+"\t");
			System.out.print(bean.getSubjectName()+"\t");
			System.out.print(bean.getSubjectId()+"\t");
			System.out.print(bean.getExamDate()+"\t");
			System.out.print(bean.getExamTime()+"\t");
			System.out.print(bean.getSemester()+"\t");
			System.out.print(bean.getCreatedBy()+"\t");
			System.out.print(bean.getModifiedBy()+"\t");
			System.out.print(bean.getCreatedDatetime()+"\t");
			System.out.println(bean.getModifiedDatetime());
		}
	}

	public static void testcheckbysem() throws Exception {
		TimetableBean bean = new TimetableBean();
		bean = model.checkBycss(1, 1, "2nd");
		System.out.println(bean.getCourseName());
		System.out.println("Success!!!!");
	}

	public static void testAdd() throws Exception {

		TimetableBean bean = new TimetableBean();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dt = new Date();
		dt = sdf.parse("03/04/2022");

		bean.setCourseName("Bsc Cs");
		bean.setCourseId(3);
		bean.setSubjectName("Maths");
		bean.setSubjectId(1);
		bean.setExamDate(dt);
		bean.setExamTime("10:00 a.m.");
		bean.setSemester("3rd");
		bean.setCreatedBy("Admin");
		bean.setModifiedBy(null);
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(null);
		int pk = model.add(bean);
		System.out.println("Record Inserted in Id --- " + pk);

	}

}