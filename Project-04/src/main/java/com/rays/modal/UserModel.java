package com.rays.modal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.rays.beans.UserBean;
import com.rays.exception.DuplicateRecordException;
import com.rays.utility.EmailMessage;
import com.rays.utility.EmailUtility;
import com.rays.utility.JDBCDataSource;
import com.rays.exception.ApplicationException;
import com.rays.exception.DatabaseException;
import com.rays.exception.RecordNotFoundException;
import com.rays.utility.EmailBuilder;

/**
 * 
 * @author Vikas Singh
 *
 */
public class UserModel {

	private static Logger log = Logger.getLogger(UserModel.class);
	public static UserModel model = new UserModel();

	private int roleId;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int nextPK() throws Exception {
		log.debug("UserModel.nextPk Started!!!");
		StringBuffer sql = new StringBuffer("SELECT MAX(ID) FROM ST_USER");
		int pk = 0;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			ps.close();
			rs.close();
			log.debug("UserModel.nextPk Success!!");
		} catch (Exception e) {
			log.error("UserModel.nextPk Exception");
			e.printStackTrace();
		} finally {
			log.debug("UserModel.nextPk Closed!!!");
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;
	}

	public Long add(UserBean bean) throws Exception {
		log.debug("UserModel.add Started!!");
		Connection conn = null;
		StringBuffer sql = new StringBuffer("INSERT INTO ST_USER VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		long pk = nextPK();
		UserBean existbean = findByLogin(bean.getLogin());

		if (bean.getRoleId() == 0) {
			bean.setRoleId(3);
		}

		if (existbean.getFirstName() != null) {
			throw new DuplicateRecordException("Login Id already exists");
		} else {
			try {
				conn = JDBCDataSource.getConnection();
				conn.setAutoCommit(false);
				PreparedStatement ps = conn.prepareStatement(sql.toString());
				ps.setLong(1, pk);
				ps.setString(2, bean.getFirstName());
				ps.setString(3, bean.getLastName());
				ps.setString(4, bean.getLogin());
				ps.setString(5, bean.getPassword());
				ps.setString(6, bean.getConfirmPassword());
				ps.setString(7, bean.getMobileNo());
				ps.setLong(8, bean.getRoleId());
				ps.setDate(9, new Date(bean.getDob().getTime()));
				ps.setString(10, bean.getGender());
				ps.setTimestamp(11, bean.getLastLogin());
				ps.setString(12, bean.getLock());
				ps.setString(13, bean.getRegisteredIP());
				ps.setString(14, bean.getLastLoginIP());
				ps.setString(15, bean.getCreatedBy());
				ps.setString(16, bean.getModifiedBy());
				ps.setTimestamp(17, bean.getCreatedDatetime());
				ps.setTimestamp(18, bean.getModifiedDatetime());
				ps.setInt(19, bean.getUnSuccessfulLogin());

				ps.executeUpdate();
				conn.commit();
				ps.close();
				log.debug("UserModel.add Success!!!");
			} catch (Exception e) {
				log.error("UserModel.add Exception");
				e.printStackTrace();
			}
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());

			} finally {
				log.debug("UserModel.add Closed!!");
				JDBCDataSource.closeConnection(conn);
			}
		}
		return pk;
	}

	public UserBean authenticate(String login, String pwd) throws Exception {
		log.debug("UserModel.authenticate Started!!!");
		Connection conn = null;
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE LOGIN = ? AND PASSWORD = ?");
		UserBean bean = new UserBean();

		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setString(1, login);
			ps.setString(2, pwd);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setConfirmPassword(rs.getString(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setDob(rs.getDate(9));
				bean.setGender(rs.getString(10));
				bean.setLastLogin(rs.getTimestamp(11));
				bean.setLock(rs.getString(12));
				bean.setRegisteredIP(rs.getString(13));
				bean.setLastLoginIP(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));
				bean.setUnSuccessfulLogin(rs.getInt(19));
			}
			log.debug("UserModel.authenticate Success!!");
			if (bean.getFirstName() == null) {
				System.out.println("Authenticate Failed!!!!");
			}
		} catch (Exception e) {
			log.error("UserModel.authenticate Exception");
			throw new ApplicationException("Error Login Data  " + e.getMessage());
		} finally {
			log.debug("UserModel.authenticate Closed!!!");
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	public void delete(Long l) throws Exception {
		log.debug("UserModel.delete Started!!!");
		StringBuffer sql = new StringBuffer("DELETE FROM ST_USER WHERE ID = ?");
		Connection conn = null;
		UserBean bean = findByPK(l);
		if (bean.getId() != 0) {
			try {
				conn = JDBCDataSource.getConnection();
				conn.setAutoCommit(false);

				PreparedStatement ps = conn.prepareStatement(sql.toString());
				ps.setLong(1, bean.getId());

				ps.executeUpdate();
				conn.commit();
				ps.close();
				log.debug("UserModel.delete Success!!");
			} catch (Exception e) {
				log.error("UserModel.delete Exception!!!!");
				throw new DatabaseException("Error in deleting from database");
			} finally {
				log.debug("UserModel.delete Closed!!!!");
				JDBCDataSource.closeConnection(conn);
			}
		} else {
			throw new RecordNotFoundException("Record Not Found");
		}

	}

	public UserBean findByPK(Long l) throws Exception {
		log.debug("UserModel.findByPk Started!!!");

		StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE ID = ?");
		Connection conn = null;
		UserBean bean = new UserBean();
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, l);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setConfirmPassword(rs.getString(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setDob(rs.getDate(9));
				bean.setGender(rs.getString(10));
				bean.setLastLogin(rs.getTimestamp(11));
				bean.setLock(rs.getString(12));
				bean.setRegisteredIP(rs.getString(13));
				bean.setLastLoginIP(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));
				bean.setUnSuccessfulLogin(rs.getInt(19));
			}
			ps.close();
			rs.close();

			if (bean.getId() != 0) {
				log.debug("UserModel.findByPk Success!!!");
			}

		} catch (Exception e) {
			log.error("UserModel.findByPk Exception!!!");
			throw new RecordNotFoundException("Record Not Found");
		} finally {
			log.debug("UserModel.findByPk Closed!!!");
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	public UserBean findByLogin(String login) throws Exception {
		log.debug("UserModel.findByLogin Started!!!");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE LOGIN=?");
		UserBean bean = new UserBean();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setConfirmPassword(rs.getString(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setDob(rs.getDate(9));
				bean.setGender(rs.getString(10));
				bean.setLastLogin(rs.getTimestamp(11));
				bean.setLock(rs.getString(12));
				bean.setRegisteredIP(rs.getString(13));
				bean.setLastLoginIP(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));
				bean.setUnSuccessfulLogin(rs.getInt(19));

			}
			ps.close();
			rs.close();
			log.debug("UserModel.findByLogin Success!!!");
		} catch (Exception e) {
			log.error("UserModel.findByLogin Exception!!!");
			e.printStackTrace();
		} finally {
			log.debug("UserModel.findByLogin Closed!!");
			JDBCDataSource.closeConnection(conn);

		}
		return bean;
	}

	public boolean forgetPassword(String login) throws Exception {
		UserBean userData = findByLogin(login);
		boolean flag = false;

		if (userData == null) {
			throw new RecordNotFoundException("Email ID does not exists !");

		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", userData.getLogin());
		map.put("password", userData.getPassword());
		map.put("firstName", userData.getFirstName());
		map.put("lastName", userData.getLastName());
		String message = EmailBuilder.getForgetPasswordMessage(map);
		EmailMessage msg = new EmailMessage();
		msg.setTo(login);
		msg.setSubject("SUNRAYS ORS Password reset");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		boolean check = EmailUtility.sendMail(msg);
		flag = check;

		return flag;

	}

	public boolean changePassword(long id, String oldPassword, String newPassword) throws Exception {
		log.debug("UserModel.changePassword Started!!!");
		boolean flag = false;
		UserBean beanExist = null;

		beanExist = findByPK(id);
		if (beanExist != null && beanExist.getPassword().equals(oldPassword)) {
			beanExist.setPassword(newPassword);
			update(beanExist);
			flag = true;
		} else {
			throw new RecordNotFoundException("Login not exist");
		}

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("login", beanExist.getLogin());
		map.put("password", beanExist.getPassword());
		map.put("firstName", beanExist.getFirstName());
		map.put("lastName", beanExist.getLastName());

		String message = EmailBuilder.getChangePasswordMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(beanExist.getLogin());
		msg.setSubject("SUNARYS ORS Password has been changed Successfully.");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);
		log.debug("UserModel.changePassword Success!!!");
		log.debug("UserModel.changePassword Closed!!!");
		return flag;

	}

	public void update(UserBean bean) throws Exception {
		log.debug("UserModel.update Started!!!");
		StringBuffer sql = new StringBuffer(
				"UPDATE ST_USER SET FIRST_NAME=?,LAST_NAME=?,LOGIN=?,PASSWORD=?,CONFIRM_PASSWORD=?,MOBILE_NO=?,ROLE_ID=?,DOB=?,GENDER=?,LAST_LOGIN=?,USER_LOCK=?,REGISTERED_IP=?,LAST_LOGIN_IP=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=?,UNSUCCESSFUL_LOGIN=? WHERE ID=?");
		Connection conn = null;

		UserBean beanExist = findByLogin(bean.getLogin());

		if (beanExist != null && !(beanExist.getId() == bean.getId())) {
			throw new DuplicateRecordException("LoginId is already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql.toString());

			ps.setString(1, bean.getFirstName());
			ps.setString(2, bean.getLastName());
			ps.setString(3, bean.getLogin());
			ps.setString(4, bean.getPassword());
			ps.setString(5, bean.getConfirmPassword());
			ps.setString(6, bean.getMobileNo());
			ps.setLong(7, bean.getRoleId());
			ps.setDate(8, new Date(bean.getDob().getTime()));
			ps.setString(9, bean.getGender());
			ps.setTimestamp(10, bean.getLastLogin());
			ps.setString(11, bean.getLock());
			ps.setString(12, bean.getRegisteredIP());
			ps.setString(13, bean.getLastLoginIP());
			ps.setString(14, bean.getCreatedBy());
			ps.setString(15, bean.getModifiedBy());
			ps.setTimestamp(16, bean.getCreatedDatetime());
			ps.setTimestamp(17, bean.getModifiedDatetime());
			ps.setInt(18, bean.getUnSuccessfulLogin());
			ps.setLong(19, bean.getId());
			ps.executeUpdate();
			conn.commit();
			ps.close();
			log.debug("UserModel.update Success!!!");
		} catch (Exception e) {
			log.error("UserModel.update Exception!!!");
			e.printStackTrace();

			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating User ");
		} finally {
			log.debug("UserModel.update Closed!!!");
			JDBCDataSource.closeConnection(conn);
		}

	}

	public UserBean updateAccess(UserBean bean) throws ApplicationException {
		log.debug("UserModel.updateAccess Started");
		log.debug("UserModel.updateAccess Closed");
		return null;
	}

	public List<UserBean> search(UserBean bean) throws Exception {
		log.debug("UserModel.search(only bean argument) Started");
		log.debug("UserModel.search(only bean argument) Closed");
		return search(bean, 1, 100);
	}

	public List<UserBean> search(UserBean bean, int pageNo, int pageSize) throws Exception {
		log.debug("UserModel.search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE 1=1");

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}

			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" AND FIRST_NAME like '" + bean.getFirstName() + "%'");
			}

			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" AND LAST_NAME like '" + bean.getLastName() + "%'");
			}

			if (bean.getLogin() != null && bean.getLogin().length() > 0) {
				sql.append(" AND LOGIN like '" + bean.getLogin() + "%'");
			}

			if (bean.getPassword() != null && bean.getPassword().length() > 0) {
				sql.append(" AND PASSWORD like '" + bean.getPassword() + "%'");
			}
			if (bean.getConfirmPassword() != null && bean.getConfirmPassword().length() > 0) {
				sql.append(" AND  CONFIRMPASSWORD like '" + bean.getConfirmPassword() + "%'");
			}
			if (bean.getDob() != null && bean.getDob().getDate() > 0) {
				sql.append(" AND DOB = " + bean.getDob());
			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
				sql.append(" AND MOBILE_NO = " + bean.getMobileNo());
			}
			if (bean.getRoleId() > 0) {
				sql.append(" AND ROLE_ID = " + bean.getRoleId());
			}

			if (bean.getGender() != null && bean.getGender().length() > 0) {
				sql.append(" AND GENDER like '" + bean.getGender() + "%'");
			}

		}
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" LIMIT " + pageNo + "," + pageSize);
		}
		log.debug("UserModel.search Query Success ");
		log.debug("QUERY-- " + sql);
		ArrayList<UserBean> list = new ArrayList<UserBean>();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setConfirmPassword(rs.getString(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setDob(rs.getDate(9));
				bean.setGender(rs.getString(10));
				bean.setLastLogin(rs.getTimestamp(11));
				bean.setLock(rs.getString(12));
				bean.setRegisteredIP(rs.getString(13));
				bean.setLastLoginIP(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));
				bean.setUnSuccessfulLogin(rs.getInt(19));

				list.add(bean);
			}
			rs.close();
			log.debug("UserModel.search Success!!");
		} catch (Exception e) {
			log.error("UserModel.search Exception");
			throw new ApplicationException("Exception : Exception in search user");
		} finally {
			log.debug("UserModel.search Closed!!");
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

	public List<UserBean> list() throws Exception {
		return list(0, 0);
	}

	public List<UserBean> list(int pageNo, int pageSize) throws Exception {
		log.debug("UserModel.list Started");
		ArrayList<UserBean> list = new ArrayList<UserBean>();
		StringBuffer sql = new StringBuffer("select * from ST_USER");

		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" LIMIT " + pageNo + "," + pageSize);
		}
		log.debug("UserModel.list Query Success!!");
		log.debug("QUERY-- " + sql);
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				UserBean bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setConfirmPassword(rs.getString(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setDob(rs.getDate(9));
				bean.setGender(rs.getString(10));
				bean.setLastLogin(rs.getTimestamp(11));
				bean.setLock(rs.getString(12));
				bean.setRegisteredIP(rs.getString(13));
				bean.setLastLoginIP(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));
				bean.setUnSuccessfulLogin(rs.getInt(19));
				list.add(bean);
			}
			rs.close();
			log.debug("UserModel.list Success!!!");
		} catch (Exception e) {
			log.error("UserModel.list Exception ");
			throw new ApplicationException("Exception : Exception in getting list of user");
		} finally {
			log.debug("UserModel.list Closed!!!");
			JDBCDataSource.closeConnection(conn);
		}

		return list;

	}

	public boolean resetPassword(UserBean bean) throws Exception {
		log.debug("UserModel.resetPassword Started!!!");
		String newPassword = String.valueOf(new java.util.Date().getTime()).substring(0, 4);
		UserBean userData = findByPK(bean.getId());
		userData.setPassword(newPassword);

		try {
			update(userData);
		} catch (DuplicateRecordException e) {
			return false;
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", bean.getLogin());
		map.put("password", bean.getPassword());
		map.put("firstName", bean.getFirstName());
		map.put("lastName", bean.getLastName());

		String message = EmailBuilder.getForgetPasswordMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(bean.getLogin());
		msg.setSubject("Password has been reset");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);
		log.debug("UserModel.resetPassword Success!!!");
		log.debug("UserModel.resetPassword Closed!!!");
		return true;
	}

	public Long registerUser(UserBean bean) throws Exception {
		log.debug("UserModel.register Started!!!");
		Long pk = model.add(bean);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", bean.getLogin());
		map.put("password", bean.getPassword());

		String message = EmailBuilder.getUserRegistrationMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(bean.getLogin());
		msg.setSubject("Registration is successful for ORS Project SunilOS");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);
		log.debug("UserModel.register Success!!!");
		log.debug("UserModel.register Closed!!!");
		return pk;
	}

	public List<UserBean> getRoles(UserBean bean) throws Exception {
		log.debug("UserModel.getRoles Started!!!");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE ROLE_ID=?");
		Connection conn = null;
		List<UserBean> list = new ArrayList<UserBean>();
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, bean.getRoleId());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setConfirmPassword(rs.getString(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setDob(rs.getDate(9));
				bean.setGender(rs.getString(10));
				bean.setLastLogin(rs.getTimestamp(11));
				bean.setLock(rs.getString(12));
				bean.setRegisteredIP(rs.getString(13));
				bean.setLastLoginIP(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));
				bean.setUnSuccessfulLogin(rs.getInt(19));
				list.add(bean);
			}
			rs.close();
			log.debug("UserModel.getRoles Success!!!");
		} catch (Exception e) {
			log.error("UserModel.getRoles Exception!!!");
			throw new ApplicationException("Exception : Exception in get roles");

		} finally {
			log.debug("UserModel.getRoles Closed!!!");
			JDBCDataSource.closeConnection(conn);
		}

		return list;
	}

}
