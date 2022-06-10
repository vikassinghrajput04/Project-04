package com.rays.test.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.rays.beans.CollegeBean;
import com.rays.modal.CollegeModel;
import com.rays.exception.ApplicationException;
/**
 * 
 * @author Vikas Singh
 *
 */
public class TestCollege {
	public static CollegeModel model =new CollegeModel(); 
	public static void main(String[] args) throws Exception {
		//testAdd();
		//testDelete();
		//testFindByName();
		//testFindByPk();
		//testUpdate();
		//testFindByCity();
		testSearch();
		//testList();
		
	}
	public static void testList() throws Exception {
		 try {
	            CollegeBean bean = new CollegeBean();
	            List<CollegeBean> list = new ArrayList<CollegeBean>();
	            list = model.list(1, 100);
	            if (list.size() < 0) {
	                System.out.println("Test list fail");
	            }
	            Iterator<CollegeBean> it = list.iterator();
	            while (it.hasNext()) {
	                bean = (CollegeBean) it.next();
	                System.out.print(bean.getId()+"\t");
	    			System.out.print(bean.getName()+"\t");
	    			System.out.print(bean.getAddress()+"\t");
	    			System.out.print(bean.getState()+"\t");
	    			System.out.print(bean.getCity()+"\t");
	    			System.out.print(bean.getPhoneNo()+"\t");
	    			System.out.print(bean.getCreatedBy()+"\t");
	    			System.out.print(bean.getModifiedBy()+"\t");
	    			System.out.print(bean.getCreatedDatetime()+"\t");
	    			System.out.println(bean.getModifiedDatetime());
	            }

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    

		
	}
	public static void testSearch() throws Exception {
		 CollegeBean bean = new CollegeBean();
         List<CollegeBean> list = new ArrayList<CollegeBean>();
         bean.setName("MB");
          
         list =model.search(bean, 1, 100);
         if (list.size() < 0) {
             System.out.println("Test Search fail");
         }
         Iterator<CollegeBean> it = list.iterator();
         while (it.hasNext()) {
             bean = (CollegeBean) it.next();
             System.out.print(bean.getId()+"\t");
             System.out.print(bean.getName()+"\t");
             System.out.print(bean.getAddress()+"\t");
             System.out.print(bean.getState()+"\t");
             System.out.print(bean.getCity()+"\t");
             System.out.print(bean.getPhoneNo()+"\t");
             System.out.print(bean.getCreatedBy()+"\t");
             System.out.print(bean.getCreatedDatetime()+"\t");
             System.out.print(bean.getModifiedBy()+"\t");
             System.out.println(bean.getModifiedDatetime()+"\t");
         }
		
	}
	public static void testAdd() throws Exception {
			CollegeBean bean = new CollegeBean();
			bean.setName("Sage University");
			bean.setAddress("A.B.Road");
			bean.setState("Madhya Pradesh");
			bean.setCity("Rau Road");
			bean.setPhoneNo("8899457856");
			bean.setCreatedBy("Admin");
			bean.setModifiedBy("Admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			
			model.add(bean);
			
			
	}
	public static void testDelete() throws Exception {
		CollegeBean bean = new CollegeBean();
		bean.setId(1);
		
		model.delete(bean);

	}
	public static void testFindByName() throws Exception {
		
		CollegeBean bean = model.findByName("Vikas Singh Rajput");
			System.out.print(bean.getId()+"\t");
			System.out.print(bean.getName()+"\t");
			System.out.print(bean.getAddress()+"\t");
			System.out.print(bean.getState()+"\t");
			System.out.print(bean.getCity()+"\t");
			System.out.print(bean.getPhoneNo()+"\t");
			System.out.print(bean.getCreatedBy()+"\t");
			System.out.print(bean.getModifiedBy()+"\t");
			System.out.print(bean.getCreatedDatetime()+"\t");
			System.out.println(bean.getModifiedDatetime());
	}
	public static void testFindByPk() throws Exception {
		CollegeBean bean = model.findByPk(1);
			System.out.print(bean.getId()+"\t");
			System.out.print(bean.getName()+"\t");
			System.out.print(bean.getAddress()+"\t");
			System.out.print(bean.getState()+"\t");
			System.out.print(bean.getCity()+"\t");
			System.out.print(bean.getPhoneNo()+"\t");
			System.out.print(bean.getCreatedBy()+"\t");
			System.out.print(bean.getModifiedBy()+"\t");
			System.out.print(bean.getCreatedDatetime()+"\t");
			System.out.println(bean.getModifiedDatetime());

	}
	public static void testUpdate() throws Exception {
		CollegeBean bean = new CollegeBean();
		bean.setName("Sage University");
		bean.setAddress("A.B.Road");
		bean.setState("Madhya Pradesh");
		bean.setCity("Mahu");
		bean.setPhoneNo("8899457856");
		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(4);
		
		model.update(bean);
	
	}
	public static void testFindByCity() throws Exception {

        CollegeBean bean = model.findByCity("indore");
        if (bean == null) {
            System.out.println("Test Find By City fail");
        }
        System.out.print(bean.getId()+"\t");
		System.out.print(bean.getName()+"\t");
		System.out.print(bean.getAddress()+"\t");
		System.out.print(bean.getState()+"\t");
		System.out.print(bean.getCity()+"\t");
		System.out.print(bean.getPhoneNo()+"\t");
		System.out.print(bean.getCreatedBy()+"\t");
		System.out.print(bean.getModifiedBy()+"\t");
		System.out.print(bean.getCreatedDatetime()+"\t");
		System.out.println(bean.getModifiedDatetime());

	}
}
