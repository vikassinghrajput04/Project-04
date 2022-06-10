package com.rays.modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.rays.beans.CollegeBean;
import com.rays.beans.StudentBean;
import com.rays.exception.ApplicationException;
import com.rays.exception.DatabaseException;
import com.rays.exception.DuplicateRecordException;
import com.rays.utility.JDBCDataSource;;

/**
 * 
 * @author Vikas Singh
 *
 */
public class StudentModel {

	private static Logger log = Logger.getLogger(StudentModel.class);

	public Integer nextPK() throws Exception {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM ST_STUDENT");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
			log.debug("StudentModel.nextPK Success!!!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("StudentModel.nextPK Exception..", e);
			throw new DatabaseException("Exception : Exception StudentModel.nextPK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("StudentModel.nextPK End");
		return pk + 1;
	}

	public int add(StudentBean bean) throws Exception {
		log.debug("StudentModel.add Started!!!");
		Connection conn = null;

		// get College Name
		CollegeModel cModel = new CollegeModel();
		CollegeBean collegeBean = cModel.findByPk(bean.getCollegeId());
		bean.setCollegeName(collegeBean.getName());
		int pk =0;
		/*
		 * StudentBean duplicateName = findByEmailId(bean.getEmail()); int pk = 0;
		 * 
		 * if (duplicateName != null) { throw new
		 * DuplicateRecordException("Email already exists"); }
		 */

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ST_STUDENT VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getCollegeId());
			pstmt.setString(3, bean.getCollegeName());
			pstmt.setString(4, bean.getFirstName());
			pstmt.setString(5, bean.getLastName());
			pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(7, bean.getMobileNo());
			pstmt.setString(8, bean.getEmail());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
			log.debug("StudentModel.add Success!!!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("StudentModel.add Exception.." + e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("StudentModel.add Closed!!!");
		return pk;
	}

	public void delete(StudentBean bean) throws Exception {
		log.debug("StudentModel.delete Started!!!");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ST_STUDENT WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
			log.debug("StudentModel.delete Success!!!");

		} catch (Exception e) {
			e.printStackTrace();
			log.error("StudentModel.delete Exception.." + e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}

	public StudentBean findByEmailId(String Email) throws Exception {
		log.debug("StudentModel.findBy Email Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_STUDENT WHERE EMAIL=?");
		StudentBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, Email);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new StudentBean();
				bean.setId(rs.getInt(1));
				bean.setCollegeId(rs.getInt(2));
				bean.setCollegeName(rs.getString(3));
				bean.setFirstName(rs.getString(4));
				bean.setLastName(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setEmail(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));

			}
			rs.close();
			log.debug("StudentModel.findBy Success!!!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("StudentModel.findBy Exception.." + e);
			throw new ApplicationException("Exception : Exception in getting User by Email");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("StudentModel.findBy Closed!!!");
		return bean;
	}

	public StudentBean findByPK(long pk) throws Exception {
		log.debug("StudentModel.findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_STUDENT WHERE ID=?");
		StudentBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new StudentBean();
				bean.setId(rs.getInt(1));
				bean.setCollegeId(rs.getInt(2));
				bean.setCollegeName(rs.getString(3));
				bean.setFirstName(rs.getString(4));
				bean.setLastName(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setEmail(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
			}
			rs.close();
			log.debug("StudentModel.findByPK Success!!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("StudentModel.findByPK Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("StudentModel.findByPK Closed!!!");
		return bean;
	}

	public void update(StudentBean bean) throws Exception {
		log.debug("StudentModel.update Started!!!");
		Connection conn = null;

		StudentBean beanExist = findByEmailId(bean.getEmail());

		// Check if updated Roll no already exist
		if (beanExist != null && beanExist.getId() != bean.getId()) {
			throw new DuplicateRecordException("Email Id is already exist");
		}

		// get College Name
		CollegeModel cModel = new CollegeModel();
		CollegeBean collegeBean = cModel.findByPk(bean.getCollegeId());
		bean.setCollegeName(collegeBean.getName());

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE ST_STUDENT SET COLLEGE_ID=?,COLLEGE_NAME=?,FIRST_NAME=?,LAST_NAME=?,DATE_OF_BIRTH=?,MOBILE_NO=?,EMAIL=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			ps.setLong(1, bean.getCollegeId());
			ps.setString(2, bean.getCollegeName());
			ps.setString(3, bean.getFirstName());
			ps.setString(4, bean.getLastName());
			ps.setDate(5, new java.sql.Date(bean.getDob().getTime()));
			ps.setString(6, bean.getMobileNo());
			ps.setString(7, bean.getEmail());
			ps.setString(8, bean.getCreatedBy());
			ps.setString(9, bean.getModifiedBy());
			ps.setTimestamp(10, bean.getCreatedDatetime());
			ps.setTimestamp(11, bean.getModifiedDatetime());
			ps.setLong(12, bean.getId());
			ps.executeUpdate();
			conn.commit(); // End transaction
			ps.close();
			log.debug("StudentModel.update Success!!!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("StudentModel.update Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Student ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("StudentModel.update Closed!!!");
	}

	public List<StudentBean> search(StudentBean bean) throws Exception {
		return search(bean, 0, 0);
	}

	public List<StudentBean> search(StudentBean bean, int pageNo, int pageSize) throws Exception {
		log.debug("StudentModel.search Started!!!");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_STUDENT WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND ID = " + bean.getId());
			}
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" AND FIRST_NAME LIKE '" + bean.getFirstName() + "%'");
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" AND LAST_NAME LIKE '" + bean.getLastName() + "%'");
			}
			if (bean.getDob() != null && bean.getDob().getDate() > 0) {
				sql.append(" AND DOB = " + bean.getDob());
			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
				sql.append(" AND MOBILE_NO LIKE '" + bean.getMobileNo() + "%'");
			}
			if (bean.getEmail() != null && bean.getEmail().length() > 0) {
				sql.append(" AND EMAIL LIKE '" + bean.getEmail() + "%'");
			}
			if (bean.getCollegeId() != 0 && bean.getCollegeId() > 0) {
				sql.append(" AND COLLEGE_ID = " + bean.getCollegeId());
			}
			if (bean.getCollegeName() != null && bean.getCollegeName().length() > 0) {
				sql.append(" AND COLLEGE_NAME = '" + bean.getCollegeName() + "'");
			}

		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}
		System.out.println(sql);
		log.debug("StudentModel.search Query Success!!");
		ArrayList<StudentBean> list = new ArrayList<StudentBean>();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new StudentBean();
				bean.setId(rs.getInt(1));
				bean.setCollegeId(rs.getInt(2));
				bean.setCollegeName(rs.getString(3));
				bean.setFirstName(rs.getString(4));
				bean.setLastName(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setEmail(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
				list.add(bean);
			}
			rs.close();
			log.debug("StudentModel.search Success!!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("StudentModel.search Exception.." + e);
			throw new ApplicationException("Exception : Exception in search Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("StudentModel.search Success!!!");
		return list;
	}

	public List<StudentBean> list() throws Exception {
		return list(0, 0);
	}

	/**
	 * Get List of Student with pagination
	 * 
	 * 
	 * 
	 * @param pageNo   reads
	 * @param pageSize reads
	 * @return values in List
	 * @throws Exception exception
	 */
	public List<StudentBean> list(int pageNo, int pageSize) throws Exception {
		log.debug("StudentModel.list Started!!!");
		ArrayList<StudentBean> list = new ArrayList<StudentBean>();
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_STUDENT");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" LIMIT " + pageNo + "," + pageSize);
		}

		Connection conn = null;
		log.debug("Query Success!!! " + sql);
		System.out.println(sql);
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				StudentBean bean = new StudentBean();
				bean.setId(rs.getInt(1));
				bean.setCollegeId(rs.getInt(2));
				bean.setCollegeName(rs.getString(3));
				bean.setFirstName(rs.getString(4));
				bean.setLastName(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setEmail(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
				list.add(bean);
			}
			rs.close();
			log.debug("StudentModel.list Success!!!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("StudentModel.list" + e);
			throw new ApplicationException("Exception : Exception in getting list of Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("StudentModel.list Closed!!!");
		return list;

	}

	public StudentBean findByName(String firstname) throws Exception {
		log.debug("StudentModel.findBy Name Started!!!!");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_STUDENT WHERE FIRST_NAME=?");
		StudentBean bean = null;
		Connection conn = null;
		if (firstname == null) {
			throw new ApplicationException("Enter Name");
		}
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, firstname);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new StudentBean();
				bean.setId(rs.getInt(1));
				bean.setCollegeId(rs.getInt(2));
				bean.setCollegeName(rs.getString(3));
				bean.setFirstName(rs.getString(4));
				bean.setLastName(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setEmail(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));

			}
			rs.close();
			log.debug("StudentModel.findByName Success!!!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("StudentModel.findByName Exception.." + e);
			throw new ApplicationException("Exception : Exception in getting User by Email");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("StudentModel.findByName Closed!!!");
		return bean;
	}

}
