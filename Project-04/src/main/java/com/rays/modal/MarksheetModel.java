package com.rays.modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.rays.beans.MarksheetBean;
import com.rays.exception.ApplicationException;
import com.rays.exception.DatabaseException;
import com.rays.exception.RecordNotFoundException;
import com.rays.utility.JDBCDataSource;
/**
 * 
 * @author Vikas Singh
 *
 */
public class MarksheetModel {
	private static Logger log = Logger.getLogger(MarksheetModel.class);

	public int nextPK() throws Exception {
		log.debug("MarksheetModel.nextPk Started!!!");
		StringBuffer sql = new StringBuffer("SELECT MAX(ID) FROM ST_MARKSHEET");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			log.debug("MarksheetModel.nextPk Success!!!");
			ps.close();
			rs.close();
		} catch (Exception e) {
			log.error("MarksheetModel.nextPk Error");
			throw new DatabaseException("Exception in nextPk of Marksheet");
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("MarksheetModel.nextPk close");
		}
		return pk + 1;
	}

	public int add(MarksheetBean bean) throws Exception {
		log.debug("MarksheetModel.add Started!!!");
		StringBuffer sql = new StringBuffer("INSERT INTO ST_MARKSHEET VALUES(?,?,?,?,?,?,?,?,?,?,?)");
		Connection conn = null;
		int pk = nextPK();
		/*
		 * if (bean.getName() == null) { throw new ApplicationException("Enter Name"); }
		 * if (bean.getStudentId()==0) { StudentModel m = new StudentModel();
		 * StudentBean stbean = new StudentBean(); stbean =
		 * m.findByName(bean.getName()); if (stbean.getId()==0) { throw new
		 * ApplicationException("Student id not found Please Add Student"); }
		 * bean.setStudentId((int) stbean.getId());
		 * 
		 * }
		 */
		
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, pk);
			ps.setString(2, bean.getRollNo());
			ps.setInt(3, bean.getStudentId());
			ps.setString(4, bean.getName());
			ps.setInt(5, bean.getPhysics());
			ps.setInt(6, bean.getChemistry());
			ps.setInt(7, bean.getMaths());
			ps.setString(8, bean.getCreatedBy());
			ps.setString(9, bean.getModifiedBy());
			ps.setTimestamp(10, bean.getCreatedDatetime());
			ps.setTimestamp(11, bean.getModifiedDatetime());

			ps.executeUpdate();
			conn.commit();
			log.debug("MarksheetModel.add Success!!!");

		} catch (Exception e) {
			log.error("MarksheetModel.add Exception!!!");
			throw new DatabaseException("Exception in add of Marksheet");
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("MarksheetModel.add Closed!!!");
		}
		return pk;
	}

	public void delete(MarksheetBean bean) throws Exception {
		StringBuffer sql = new StringBuffer("DELETE FROM ST_MARKSHEET WHERE ID=?");
		log.debug("MarksheetModel.Delete Started!!!");
		Connection conn = null;
		long id = bean.getId();
		if (id == 0) {
			log.error("Enter Id");
			throw new ApplicationException("Enter ID!!!!");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, id);
			ps.executeUpdate();
			conn.commit();
			log.debug("MarksheetModel.delete Success!!!");
		} catch (Exception e) {
			log.error("MarksheetModel.delete Exception");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public MarksheetBean findByPK(int pk) throws Exception {
		log.debug("MarksheetModel.findByPk Started!!!");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_MARKSHEET WHERE ID=?");
		Connection conn = null;
		MarksheetBean bean = new MarksheetBean();
		if (pk == 0) {
			throw new ApplicationException("Enter Id");
		}
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, pk);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean.setId(rs.getInt(1));
				bean.setRollNo(rs.getString(2));
				bean.setStudentId(rs.getInt(3));
				bean.setName(rs.getString(4));
				bean.setPhysics(rs.getInt(5));
				bean.setChemistry(rs.getInt(6));
				bean.setMaths(rs.getInt(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));

			}
			if (bean.getName() == null) {
				throw new RecordNotFoundException("Record Not found!!!!");
			}
			log.debug("MarksheetModel.findByPk Success!!!");
		} catch (Exception e) {
			log.error("MarksheetModel.findByPk Exception");
			throw new DatabaseException("Exception in FindByPK of Marksheet");
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.error("MarksheetModel.findByPk Closed!!!");
		}
		return bean;
	}

	public MarksheetBean findByRollNo(String rollNo) throws Exception {
		log.debug("MarksheetModel.findByRollNo Started!!!");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_MARKSHEET WHERE ROLL_NO=?");
		Connection conn = null;
		MarksheetBean bean = new MarksheetBean();
		if (rollNo == null) {
			throw new ApplicationException("Enter RollNo");
		}
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setString(1, rollNo);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean.setId(rs.getInt(1));
				bean.setRollNo(rs.getString(2));
				bean.setStudentId(rs.getInt(3));
				bean.setName(rs.getString(4));
				bean.setPhysics(rs.getInt(5));
				bean.setChemistry(rs.getInt(6));
				bean.setMaths(rs.getInt(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));

			}
			if (bean.getName() == null) {
				throw new RecordNotFoundException("Record Not found!!!!");
			}
			log.debug("MarksheetModel.findByRollNo Success!!!");
		} catch (Exception e) {
			log.error("MarksheetModel.findByRollNo Exception");
			throw new DatabaseException("Exception in FindByRollNo of Marksheet");
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.error("MarksheetModel.findByRollNo Closed!!!");
		}
		return bean;
	}

	public List<MarksheetBean> getMeritList(int pageNo, int pageSize) throws Exception {
		log.debug("MarksheetModel.getMeritList Started!!!");
		StringBuffer sql = new StringBuffer(
				"SELECT *,(PHYSICS+CHEMISTRY+MATHS) AS TOTAL_MARKS FROM ST_MARKSHEET WHERE PHYSICS>40 AND CHEMISTRY>40 AND MATHS>40 ORDER BY TOTAL_MARKS DESC");
		Connection conn = null;

		List<MarksheetBean> list = new ArrayList<MarksheetBean>();
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}
		log.debug("MarksheetModel.getMeritList Query Success!!!");
		System.out.println(sql);
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MarksheetBean bean = new MarksheetBean();
				bean.setId(rs.getInt(1));
				bean.setRollNo(rs.getString(2));
				bean.setStudentId(rs.getInt(3));
				bean.setName(rs.getString(4));
				bean.setPhysics(rs.getInt(5));
				bean.setChemistry(rs.getInt(6));
				bean.setMaths(rs.getInt(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
				list.add(bean);
			}
			rs.close();
			log.debug("MarksheetModel.getMeritList Success!!!");
		} catch (Exception e) {
			log.error("MarksheetModel.getMeritList Exception");
			throw new ApplicationException("Exception : Exception in getting merit list of student");
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("MarksheetModel.getMeritList Closed!!!");
		}
		return list;
	}

	public void update(MarksheetBean bean) throws Exception {
		log.debug("MarksheetModel.update Started!!!!");
		StringBuffer sql = new StringBuffer(
				"UPDATE ST_MARKSHEET SET ROLL_NO=?,STUDENT_ID=?,NAME=?,PHYSICS=?,CHEMISTRY=?,MATHS=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setString(1, bean.getRollNo());
			ps.setInt(2, bean.getStudentId());
			ps.setString(3, bean.getName());
			ps.setInt(4, bean.getPhysics());
			ps.setInt(5, bean.getChemistry());
			ps.setInt(6, bean.getMaths());
			ps.setString(7, bean.getCreatedBy());
			ps.setString(8, bean.getModifiedBy());
			ps.setTimestamp(9, bean.getCreatedDatetime());
			ps.setTimestamp(10, bean.getModifiedDatetime());
			ps.setLong(11, bean.getId());

			ps.executeUpdate();
			conn.commit();
			log.debug("MarksheetModel.update Success!!!!");
		} catch (Exception e) {
			log.error("MarksheetModel.update Exception");
			throw new DatabaseException("Exception in MarksheetModel.update");
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("MarksheetModel.update Closed!!!!");
		}

	}

	public List<MarksheetBean> search(MarksheetBean bean) throws Exception {
		return search(bean, 0, 0);
	}

	public List<MarksheetBean> search(MarksheetBean bean, int pageNo, int pageSize) throws Exception {

		log.debug("MarksheetModel.search Started!!!");

		StringBuffer sql = new StringBuffer("SELECT * FROM ST_MARKSHEET WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND ID = " + bean.getId());
			}
			if (bean.getRollNo() != null && bean.getRollNo().length() > 0) {
				sql.append(" AND ROLL_NO LIKE '" + bean.getRollNo() + "%'");
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND NAME LIKE '" + bean.getName() + " % '");
			}
			if (bean.getPhysics() > 0) {
				sql.append(" AND PHYSICS = " + bean.getPhysics());
			}
			if (bean.getChemistry() > 0) {
				sql.append(" AND CHEMISTRY = " + bean.getChemistry());
			}
			if (bean.getMaths() > 0) {
				sql.append(" AND MATHS = '" + bean.getMaths());
			}

		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}
		log.debug("MarksheetModel.search Query Success!!!");
		log.debug("Query--" + sql);
		System.out.println(sql);
		ArrayList<MarksheetBean> list = new ArrayList<MarksheetBean>();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new MarksheetBean();
				bean.setId(rs.getInt(1));
				bean.setRollNo(rs.getString(2));
				bean.setStudentId(rs.getInt(3));
				bean.setName(rs.getString(4));
				bean.setPhysics(rs.getInt(5));
				bean.setChemistry(rs.getInt(6));
				bean.setMaths(rs.getInt(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
				list.add(bean);
			}
			rs.close();
			log.debug("MarksheetModel.search Success!!!!");
		} catch (Exception e) {
			log.error("MarksheetModel.search Exception!!!");
			throw new ApplicationException("MarksheetModel search exception " + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("MarksheetModel.search Closed!!!");
		return list;
	}

	public List<MarksheetBean> list() throws Exception {
		return list(0,0);
	}

	public List<MarksheetBean> list(int pageNo, int pageSize) throws Exception {
		log.debug("MarksheetModel.list Started!!!");

		StringBuffer sql = new StringBuffer("SELECT * FROM ST_MARKSHEET");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}
		log.debug("MarksheetModel.list Query Success!!!");
		log.debug("Query-- " + sql);
		System.out.println(sql);
		ArrayList<MarksheetBean> list = new ArrayList<MarksheetBean>();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MarksheetBean bean = new MarksheetBean();
				bean = new MarksheetBean();
				bean.setId(rs.getInt(1));
				bean.setRollNo(rs.getString(2));
				bean.setStudentId(rs.getInt(3));
				bean.setName(rs.getString(4));
				bean.setPhysics(rs.getInt(5));
				bean.setChemistry(rs.getInt(6));
				bean.setMaths(rs.getInt(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
				list.add(bean);
			}
			rs.close();
			log.debug("MarksheetModel.list Success!!!!");
		} catch (Exception e) {
			log.error("MarksheetModel.list Exception!!!");
			throw new ApplicationException("MarksheetModel list exception " + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("MarksheetModel.list Closed!!!");
		return list;

	}

}