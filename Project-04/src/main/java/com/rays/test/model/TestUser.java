package com.rays.test.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.rays.beans.UserBean;
import com.rays.modal.UserModel;
import com.rays.exception.DuplicateRecordException;
import com.rays.exception.ApplicationException;
import com.rays.exception.RecordNotFoundException;
/**
 * 
 * @author Vikas Singh
 *
 */
public class TestUser {
	private static UserModel model = new UserModel();

	public static void main(String[] args) throws Exception {
		//testadd();
		 //testAuthenticate();
		// testDelete();
		 //testFindByPK();
		// testFindByLogin();
		//  testForgetPassword();
		// testUpdate();
		 testSearch();
		// testList();
		//testResetPassword();
		// testRoles();
		// testRegisteredUser();
	}

	public static void testRegisteredUser() throws Exception {
		try {
			UserBean bean = new UserBean();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

			bean.setFirstName("Vinay");
			bean.setLastName("Sharma");
			bean.setLogin("vinay123@gmail.com");
			bean.setPassword("vinay156");
			bean.setConfirmPassword("vinay156");
			bean.setMobileNo("9848454844");
			bean.setRoleId(1);
			bean.setDob(sdf.parse("04/11/2003"));
			bean.setGender("Male");
			bean.setLastLogin(null);
			bean.setLock(null);
			bean.setRegisteredIP(null);
			bean.setLastLoginIP(null);
			bean.setCreatedBy("Admin");
			bean.setModifiedBy(null);
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(null);
			bean.setUnSuccessfulLogin(0);

			bean.setGender("f");
			bean.setRoleId(3);
			long pk = model.registerUser(bean);
			System.out.println("Successfully register");
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getLastName());
			System.out.println(bean.getDob());
			UserBean registerbean = model.findByPK(pk);
			if (registerbean != null) {
				System.out.println("Test registation fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	public static void testRoles() throws Exception {
		try {
			UserBean bean = new UserBean();
			List<UserBean> list = new ArrayList<UserBean>();
			bean.setRoleId(1);
			list = model.getRoles(bean);
			if (list.size() < 0) {
				System.out.println("Test Get Roles fail");
			}
			Iterator<UserBean> it = list.iterator();
			while (it.hasNext()) {
				bean = (UserBean) it.next();
				System.out.print(bean.getId() + "\t");
				System.out.print(bean.getFirstName() + "\t");
				System.out.print(bean.getLastName() + "\t");
				System.out.print(bean.getLogin() + "\t");
				System.out.print("\t" + bean.getPassword() + "\t");
				System.out.print(bean.getDob() + "\t");
				System.out.print(bean.getRoleId() + "\t");
				System.out.print(bean.getGender() + "\t");
				System.out.println("\t" + bean.getMobileNo() + "\t");

			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testResetPassword() throws Exception {
		UserBean bean = new UserBean();
		try {
			bean = model.findByLogin("vikassinghr14@gmail.com");
			if (bean != null) {
				boolean pass = model.resetPassword(bean);
				System.out.println(pass);
				if (pass = false) {
					System.out.println("Test Update fail");

				}
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testList() throws Exception {
		try {
			UserBean bean = new UserBean();
			List<UserBean> list = new ArrayList<UserBean>();
			list = model.list(1, 10);

			Iterator<UserBean> it = list.iterator();
			while (it.hasNext()) {
				bean = (UserBean) it.next();
				System.out.print(bean.getId() + "\t");
				System.out.print(bean.getFirstName() + "\t");
				System.out.print(bean.getLastName() + "\t");
				System.out.print(bean.getLogin() + "\t");
				System.out.print(bean.getPassword() + "\t");
				System.out.print(bean.getDob() + "\t");
				System.out.print(bean.getRoleId() + "\t");
				System.out.print(bean.getGender() + "\t");
				System.out.println(bean.getMobileNo() + "\t");

			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testSearch() throws Exception {
		try {
			UserBean bean = new UserBean();
			List<UserBean> list = new ArrayList<UserBean>();
			list = model.search(bean, 1, 100);
			if (list.size() < 0) {
				System.out.println("Test Search fail");
			}
			Iterator<UserBean> it = list.iterator();
			while (it.hasNext()) {
				bean = (UserBean) it.next();
				System.out.print(bean.getId() + "\t");
				System.out.print(bean.getFirstName() + "\t");
				System.out.print(bean.getLastName() + "\t");
				System.out.print(bean.getLogin() + "\t");
				System.out.print(bean.getPassword() + "\t");
				System.out.print(bean.getDob() + "\t");
				System.out.print(bean.getRoleId() + "\t");
				System.out.println(bean.getGender());

			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testUpdate() throws Exception {
		UserBean bean = new UserBean();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

		bean.setFirstName("Abhinav");
		bean.setLastName("Verma");
		bean.setLogin("abhinav456@gmail.com");
		bean.setPassword("abhi123");
		bean.setConfirmPassword("abhi123");
		bean.setMobileNo("9978569499");
		bean.setRoleId(1);
		bean.setDob(sdf.parse("10/11/2001"));
		bean.setGender("Male");
		bean.setLastLogin(null);
		bean.setLock(null);
		bean.setRegisteredIP(null);
		bean.setLastLoginIP(null);
		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(null);
		bean.setUnSuccessfulLogin(0);
		bean.setId(2);

		model.update(bean);

	}

	public static void testForgetPassword() throws Exception {
		try {
			boolean b = model.forgetPassword("vikastestrays04@gmail.com");

			System.out.println(b + "Success : Test Forget Password Success");

		} catch (RecordNotFoundException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testFindByLogin() throws Exception {
		String login = "vikastestrays04@gmail.com";
		UserBean bean = model.findByLogin(login);
		System.out.println("First Name-- " + bean.getFirstName());
		System.out.println("Password-- " + bean.getPassword());
		

	}

	public static void testFindByPK() throws Exception {
		long l = 2;
		UserBean bean = model.findByPK(l);
		System.out.println(bean.getId());
		System.out.println(bean.getFirstName());
	}

	public static void testadd() throws Exception {
		UserBean bean = new UserBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		bean.setFirstName("Vinay");
		bean.setLastName("Sharma");
		bean.setLogin("vina156@gmail.com");
		bean.setPassword("vina156");
		bean.setConfirmPassword("vina156");
		bean.setMobileNo("9848454844");
		bean.setRoleId(1);
		bean.setDob(sdf.parse("2003/05/01"));
		bean.setGender("Male");
		bean.setLastLogin(null);
		bean.setLock(null);
		bean.setRegisteredIP(null);
		bean.setLastLoginIP(null);
		bean.setCreatedBy("Admin");
		bean.setModifiedBy(null);
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(null);
		bean.setUnSuccessfulLogin(0);

		long pk = model.add(bean);
		System.out.println(pk + " Inserted");

	}

	public static void testAuthenticate() throws Exception {
		UserBean bean = new UserBean();

		bean = model.authenticate("vikas123@gmail.com", "1643");
		System.out.println("First Name of User---- " + bean.getFirstName());
		System.out.println("Success!!!");

	}

	public static void testDelete() throws Exception {
		UserBean bean = new UserBean();
		bean.setId(2);

		model.delete(bean.getId());

	}

}
