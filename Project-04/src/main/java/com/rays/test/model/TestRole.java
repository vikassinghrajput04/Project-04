package com.rays.test.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.rays.beans.RoleBean;
import com.rays.exception.ApplicationException;
import com.rays.exception.DuplicateRecordException;
import com.rays.exception.RecordNotFoundException;
import com.rays.modal.RoleModel;
/**
 * 
 * @author Vikas Singh
 *
 */
public class TestRole {

	public static RoleModel model = new RoleModel();

	public static void main(String[] args) throws Exception {
		// testAdd();
		// testDelete();
		// testUpdate();
		// testFindByPK();
		// testFindByName();
		// testSearch();
		testList();

	}

	public static void testAdd() throws Exception {

		try {
			RoleBean bean = new RoleBean();
			bean.setName("Vinay");
			bean.setDescription("Help");
			bean.setCreatedBy("Admin");
			bean.setModifiedBy(null);
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(null);
			long pk = 0;
			try {
				pk = model.add(bean);
			} catch (RecordNotFoundException e) {

				e.printStackTrace();
			}
			RoleBean addedbean = model.findByPK(pk);
			if (addedbean == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	public static void testDelete() throws Exception {

		try {
			RoleBean bean = new RoleBean();
			long pk = 3;
			bean.setId(pk);
			model.delete(bean);
			RoleBean deletedbean = model.findByPK(pk);
			if (deletedbean != null) {
				System.out.println("Test Delete fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testUpdate() throws Exception {

		try {
			RoleBean bean = new RoleBean();
			bean.setName("Hemant");
			bean.setDescription("Help");
			bean.setCreatedBy("Admin");
			bean.setModifiedBy("Admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			bean.setId(2);

			model.update(bean);
			System.out.println("Updated!!!");
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testFindByPK() throws Exception {
		try {
			RoleBean bean = new RoleBean();
			long pk = 2;
			bean = model.findByPK(pk);
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.print(bean.getId() + "\t");
			System.out.print(bean.getName() + "\t");
			System.out.print(bean.getDescription() + "\t");
			System.out.print(bean.getCreatedBy() + "\t");
			System.out.print(bean.getModifiedBy() + "\t");
			System.out.print(bean.getCreatedDatetime() + "\t");
			System.out.println(bean.getModifiedDatetime() + "\t");
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testFindByName() throws Exception {
		RoleBean bean = new RoleBean();
		bean = model.findByName("Hemant");
		if (bean == null) {
			System.out.println("Test Find By PK fail");
		}
		System.out.print(bean.getId() + "\t");
		System.out.print(bean.getName() + "\t");
		System.out.print(bean.getDescription() + "\t");
		System.out.print(bean.getCreatedBy() + "\t");
		System.out.print(bean.getModifiedBy() + "\t");
		System.out.print(bean.getCreatedDatetime() + "\t");
		System.out.println(bean.getModifiedDatetime() + "\t");
	}

	public static void testSearch() throws Exception {

		RoleBean bean = new RoleBean();
		bean.setName("Hem");
		List<RoleBean> list = new ArrayList<RoleBean>();
		list = model.search(bean, 1, 100);
		if (list.size() < 0) {
			System.out.println("Test Search fail");
		}
		Iterator<RoleBean> it = list.iterator();
		while (it.hasNext()) {
			bean = (RoleBean) it.next();
			System.out.print(bean.getId() + "\t");
			System.out.print(bean.getName() + "\t");
			System.out.print(bean.getDescription() + "\t");
			System.out.print(bean.getCreatedBy() + "\t");
			System.out.print(bean.getModifiedBy() + "\t");
			System.out.print(bean.getCreatedDatetime() + "\t");
			System.out.println(bean.getModifiedDatetime() + "\t");
		}

	}

	public static void testList() throws Exception {

		try {
			RoleBean bean = new RoleBean();
			List<RoleBean> list = new ArrayList<RoleBean>();
			list = model.list();
			if (list.size() <=0) {
				System.out.println("Test list fail");
			}
			Iterator<RoleBean> it = list.iterator();
			while (it.hasNext()) {
				bean = (RoleBean) it.next();
				System.out.print(bean.getId() + "\t");
				System.out.print(bean.getName() + "\t");
				System.out.print(bean.getDescription() + "\t");
				System.out.print(bean.getCreatedBy() + "\t");
				System.out.print(bean.getModifiedBy() + "\t");
				System.out.print(bean.getCreatedDatetime() + "\t");
				System.out.println(bean.getModifiedDatetime() + "\t");
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
}
