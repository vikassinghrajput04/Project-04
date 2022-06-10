package com.rays.test.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.rays.beans.MarksheetBean;
import com.rays.modal.MarksheetModel;

import com.rays.exception.ApplicationException;
/**
 * 
 * @author Vikas Singh
 *
 */
public class TestMarksheet {
	private static MarksheetModel model = new MarksheetModel();
	
	public static void main(String[] args) throws Exception {
		//testNextPK();
		//testAdd();
		//testDelete();
		//testFindByPk();
		//testFindByRollNo();
		//testGetMeritList();
		//testUpdate();
		//testSearch();
		testlist();
		
	}

	public static void testlist() throws Exception {
        List<MarksheetBean> list = new ArrayList<MarksheetBean>();
        list = model.list(1, 100);
        if (list.size() == 0) {
            System.out.println("Test Search fail");
            throw new ApplicationException("Error in testSearch!!");
        }
        Iterator<MarksheetBean> it= list.iterator();
		while (it.hasNext()) {
			MarksheetBean bean = (MarksheetBean) it.next();
			System.out.print(bean.getId()+"\t");
			System.out.print(bean.getRollNo()+"\t");
			System.out.print(bean.getStudentId()+"\t");
			System.out.print(bean.getName()+"\t");
			System.out.print(bean.getPhysics()+"\t");
			System.out.print(bean.getChemistry()+"\t");
			System.out.print(bean.getMaths()+"\t");
			System.out.print(bean.getCreatedBy()+"\t");
			System.out.print(bean.getModifiedBy()+"\t");
			System.out.print(bean.getCreatedDatetime()+"\t");
			System.out.println(bean.getModifiedDatetime()+"\t");
		}
		
	}

	public static void testSearch() throws Exception {
		MarksheetBean bean = new MarksheetBean();
        List<MarksheetBean> list = new ArrayList<MarksheetBean>();
       // bean.setName("radha");
        bean.setRollNo("1205");
        list = model.search(bean, 1, 10);
        if (list.size() == 0) {
            System.out.println("Test Search fail");
            throw new ApplicationException("Error in testSearch!!");
        }
        Iterator<MarksheetBean> it= list.iterator();
		while (it.hasNext()) {
			MarksheetBean m = (MarksheetBean) it.next();
			System.out.print(m.getId()+"\t");
			System.out.print(m.getRollNo()+"\t");
			System.out.print(m.getStudentId()+"\t");
			System.out.print(m.getName()+"\t");
			System.out.print(m.getPhysics()+"\t");
			System.out.print(m.getChemistry()+"\t");
			System.out.print(m.getMaths()+"\t");
			System.out.print(m.getCreatedBy()+"\t");
			System.out.print(m.getModifiedBy()+"\t");
			System.out.print(m.getCreatedDatetime()+"\t");
			System.out.println(m.getModifiedDatetime()+"\t");
		}
		
	}

	public static void testUpdate() throws Exception {
		MarksheetBean bean = new MarksheetBean();
		bean.setRollNo("1205");
		bean.setStudentId(5);
		bean.setName("Shivam");
		bean.setPhysics(70);
		bean.setChemistry(65);
		bean.setMaths(79);
		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(5);
		model.update(bean);
		System.out.println("Updated!!!");
		
	}

	public static void testGetMeritList() throws Exception {
		List<MarksheetBean> list = new ArrayList<MarksheetBean>();
		list = model.getMeritList(1,1);
		Iterator<MarksheetBean> it = list.iterator();
		while (it.hasNext()) {
			MarksheetBean bean = (MarksheetBean) it.next();
			System.out.print(bean.getId()+"\t");
			System.out.print(bean.getRollNo()+"\t");
			System.out.print(bean.getStudentId()+"\t");
			System.out.print(bean.getName()+"\t");
			System.out.print(bean.getPhysics()+"\t");
			System.out.print(bean.getChemistry()+"\t");
			System.out.print(bean.getMaths()+"\t");
			System.out.print(bean.getCreatedBy()+"\t");
			System.out.print(bean.getModifiedBy()+"\t");
			System.out.print(bean.getCreatedDatetime()+"\t");
			System.out.println(bean.getModifiedDatetime()+"\t");
		}
		
	}

	public static void testFindByRollNo() throws Exception {
		String rollNo = "1204";
		MarksheetBean bean = model.findByRollNo(rollNo);
		System.out.print(bean.getId()+"\t");
		System.out.print(bean.getRollNo()+"\t");
		System.out.print(bean.getStudentId()+"\t");
		System.out.print(bean.getName()+"\t");
		System.out.print(bean.getPhysics()+"\t");
		System.out.print(bean.getChemistry()+"\t");
		System.out.print(bean.getMaths()+"\t");
		System.out.print(bean.getCreatedBy()+"\t");
		System.out.print(bean.getModifiedBy()+"\t");
		System.out.print(bean.getCreatedDatetime()+"\t");
		System.out.println(bean.getModifiedDatetime()+"\t");
	}

	public static void testFindByPk() throws Exception {
		int id = 1;
		MarksheetBean bean = model.findByPK(id);
		System.out.print(bean.getId()+"\t");
		System.out.print(bean.getRollNo()+"\t");
		System.out.print(bean.getStudentId()+"\t");
		System.out.print(bean.getName()+"\t");
		System.out.print(bean.getPhysics()+"\t");
		System.out.print(bean.getChemistry()+"\t");
		System.out.print(bean.getMaths()+"\t");
		System.out.print(bean.getCreatedBy()+"\t");
		System.out.print(bean.getModifiedBy()+"\t");
		System.out.print(bean.getCreatedDatetime()+"\t");
		System.out.println(bean.getModifiedDatetime()+"\t");
		
		
	}

	public static void testDelete() throws Exception {
		MarksheetBean bean =new MarksheetBean();
		bean.setId(5);
		model.delete(bean);
		System.out.println("Deleted!!!");
		
		
	}

	public static void testAdd() throws Exception {
		MarksheetBean bean = new MarksheetBean();
		bean.setRollNo("1201AA106");
		bean.setStudentId(4);
		bean.setName("Roshan");
		bean.setPhysics(90);
		bean.setChemistry(85);
		bean.setMaths(89);
		bean.setCreatedBy("Admin");
		bean.setModifiedBy(null);
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(null);
		
		int pk = model.add(bean);
		System.out.println("Record Inserted in Id --- "+pk);
	}

	public static void testNextPK() throws Exception {
		int id = model.nextPK();
		System.out.println("Id Fetched is --- "+id);
		
	}
	public List<MarksheetBean> list() throws ApplicationException {
		return null;
	}

}
