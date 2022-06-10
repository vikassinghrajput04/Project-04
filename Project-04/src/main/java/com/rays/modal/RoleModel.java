package com.rays.modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.rays.beans.RoleBean;
import com.rays.exception.ApplicationException;
import com.rays.exception.DatabaseException;
import com.rays.exception.DuplicateRecordException;
import com.rays.exception.RecordNotFoundException;
import com.rays.utility.JDBCDataSource;
/**
 * 
 * @author Vikas Singh
 *
 */
public class RoleModel {
	private static Logger log = Logger.getLogger(RoleModel.class);

	public Integer nextPK() throws Exception {
		log.debug("RoleModel.nextPK Started");
		StringBuffer sql = new StringBuffer("SELECT MAX(ID) FROM ST_ROLE");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
			log.debug("RoleModel.nextPK Success!!!");
		} catch (Exception e) {
			log.error("RoleModel.nextPK Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			log.debug("RoleModel.nextPK Closed!!!");
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;
	}

	public int add(RoleBean bean) throws Exception {
		log.debug("RoleModel.add Started");
		StringBuffer sql = new StringBuffer("INSERT INTO ST_ROLE VALUES(?,?,?,?,?,?,?)");
		Connection conn = null;
		int pk = 0;
		/*
		 * RoleBean Duplicatebean = findByName(bean.getName()); // create role if
		 * already exists if (Duplicatebean != null) { throw new
		 * DuplicateRecordException("Role already exists"); }
		 */
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, pk);
			ps.setString(2, bean.getName());
			ps.setString(3, bean.getDescription());
			ps.setString(4, bean.getCreatedBy());
			ps.setString(5, bean.getModifiedBy());
			ps.setTimestamp(6, bean.getCreatedDatetime());
			ps.setTimestamp(7, bean.getModifiedDatetime());
			ps.executeUpdate();
			conn.commit();
			ps.close();

		} catch (Exception e) {
			log.error("RoleModel.add Exception", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception: add rollback exception" + ex.getMessage());
			}
			throw new DatabaseException("Exception in RoleModel.add");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("RoleModel.add Closed!!!!");
		return pk;
	}

	public void delete(RoleBean bean) throws Exception {
		log.debug("RoleModel.deleted started");
		StringBuffer sql = new StringBuffer("DELETE FROM ST_ROLE WHERE ID=?");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, bean.getId());
			ps.executeUpdate();
			conn.commit();
			ps.close();
		} catch (Exception e) {
			log.error("RoleModel.delete Exception" + e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception:Delete rollback exception" + ex.getMessage());
			}
			throw new ApplicationException("Exception: RoleModel.delete");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("RoleModel.delete Closed!!!");

	}

	public void update(RoleBean bean) throws Exception {
		log.debug("RoleModel.update Started!!!");
		StringBuffer sql = new StringBuffer("UPDATE ST_ROLE SET NAME=?,DESCRIPTION=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
		Connection conn = null;
		/*
		 * RoleBean duplicateRole = findByName(bean.getName()); // Check if updated Role
		 * already exists if (duplicateRole != null && duplicateRole.getId() !=
		 * bean.getId()) { throw new DuplicateRecordException("Role already exists"); }
		 */
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getDescription());
			ps.setString(3, bean.getCreatedBy());
			ps.setString(4, bean.getModifiedBy());
			ps.setTimestamp(5, bean.getCreatedDatetime());
			ps.setTimestamp(6, bean.getModifiedDatetime());
			ps.setLong(7, bean.getId());
			ps.executeUpdate();
			conn.commit();
			ps.close();
			log.debug("RoleModel.update Success!!!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("RoleModel.update Exception.."+ e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in Updating Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("RoleModel.update Closed!!!");
	}

	
	public RoleBean findByPK(long pk) throws Exception {
		 log.debug("RoleModel.findByPK Started");
		 StringBuffer sql = new StringBuffer("SELECT * FROM ST_ROLE WHERE ID=?");
		RoleBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new RoleBean();
				bean.setId(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDatetime(rs.getTimestamp(6));
				bean.setModifiedDatetime(rs.getTimestamp(7));
			}
			rs.close();
			log.debug("RoleModel.findByPK Success!!!!");
		} catch (Exception e) {
			 e.printStackTrace();
			 log.error("RoleModel.findByPk Exception.."+e);
			throw new ApplicationException("Exception :Exception in getting by user pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("RoleModel.findByPk Closed!!!");
		return bean;

	}

	
	public RoleBean findByName(String Name) throws Exception {
		log.debug("RoleModel.findByName Started!!!!");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_ROLE WHERE NAME=?");
		RoleBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setString(1, Name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new RoleBean();
				bean.setId(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDatetime(rs.getTimestamp(6));
				bean.setModifiedDatetime(rs.getTimestamp(7));
			}
			rs.close();
			log.debug("RoleModel.findByName Success!!!");
			if (bean.getName() == null) {
				throw new RecordNotFoundException("Record Not Found!!!");
			}
		} catch (Exception e) {
			log.error("RoleModel.findByName Exception!!!!");
			throw new DatabaseException("RoleModel.findByName Exception");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("RoleModel.findByEmail Closed!!!!");
		return bean;

	}

	
	public List<RoleBean> search(RoleBean bean) throws Exception {
		return search(bean, 0, 0);
	}



	public List<RoleBean> search(RoleBean bean, int pageNo, int pageSize) throws Exception {
		log.debug("RoleModel.search Started");

		StringBuffer sql = new StringBuffer("SELECT * FROM ST_ROLE WHERE 1=1");

		ArrayList<RoleBean> list = new ArrayList<RoleBean>();

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND ID = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND NAME LIKE '" + bean.getName() + "%'");
			}
			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" AND DESCRIPTION LIKE '" + bean.getDescription() + "%'");
				System.out.println(sql);
			}
		}
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" Limit " + pageNo + "," + pageSize);
		}
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new RoleBean();
				bean.setId(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDatetime(rs.getTimestamp(6));
				bean.setModifiedDatetime(rs.getTimestamp(7));
				list.add(bean);
			}
			log.debug("RoleModel.search Success!!!");
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		 log.debug("RoleModel.search Closed!!!");
		return list;
	}

	
	public List<RoleBean> list() throws Exception {
		return list(0, 0);

	}

	public List<RoleBean> list(int pageNo, int pageSize) throws Exception {
		log.debug("RoleModel.list Started");
		ArrayList<RoleBean> list = new ArrayList<RoleBean>();
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_ROLE");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			System.out.println("list p");
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				RoleBean bean = new RoleBean();
				bean.setId(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDatetime(rs.getTimestamp(6));
				bean.setModifiedDatetime(rs.getTimestamp(7));
				list.add(bean);
			}
			rs.close();
			log.debug("RoleModel.list Success!!!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("RoleModel.list Exception", e);
			throw new ApplicationException("RoleModel.Exception : Exception in getting list of Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("RoleModel.list Closed!!!");
		return list;

	}
}
