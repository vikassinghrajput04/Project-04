package com.rays.modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.rays.beans.CollegeBean;
import com.rays.utility.JDBCDataSource;
import com.rays.exception.ApplicationException;
import com.rays.exception.DatabaseException;
/**
 * 
 * @author Vikas Singh
 *
 */
public class CollegeModel {
	private static Logger log = Logger.getLogger(CollegeModel.class);

	public int nextPK() throws Exception {
		log.debug("CollegeModel.nextPk started");
		StringBuffer sql = new StringBuffer("SELECT MAX(ID) FROM ST_COLLEGE");
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
			log.debug("CollegeModel,next Pk Fetched");
		} catch (Exception e) {
			log.error("CollegeModel.nextPk Exception");
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("CollegeModel.nextPk closed");
		}
		return pk + 1;
	}

	public long add(CollegeBean bean) throws Exception {

		log.debug("CollegeModel.Add started");
		StringBuffer sql = new StringBuffer("INSERT INTO ST_COLLEGE VALUES(?,?,?,?,?,?,?,?,?,?)");
		Connection conn = null;

		int pk = nextPK();
		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, pk);
			ps.setString(2, bean.getName());
			ps.setString(3, bean.getAddress());
			ps.setString(4, bean.getState());
			ps.setString(5, bean.getCity());
			ps.setString(6, bean.getPhoneNo());
			ps.setString(7, bean.getCreatedBy());
			ps.setString(8, bean.getModifiedBy());
			ps.setTimestamp(9, bean.getCreatedDatetime());
			ps.setTimestamp(10, bean.getModifiedDatetime());

			ps.executeUpdate();
			conn.commit();
			JDBCDataSource.closeConnection(conn, ps);
			log.debug("CollegeModel.add Success");
		} catch (Exception e) {
			log.error("CollegeModel.add Exception");
			throw new ApplicationException("Error in Inserting Data");
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("CollegeModel.add");
		}

		return pk;

	}

	public void delete(CollegeBean bean) throws Exception {
		log.debug("CollegeModel.Delete started");
		StringBuffer sql = new StringBuffer("DELETE FROM ST_COLLEGE WHERE ID = ?");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, bean.getId());
			ps.executeUpdate();
			conn.commit();
			ps.close();
			System.out.println("Deleted!!!!");
			log.debug("CollegeModel.delete Success");
		} catch (Exception e) {
			log.error("CollegeModel.delete Exception");
			throw new DatabaseException("Exception in Delete");
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("CollegeModel.delete Closed");
		}

	}

	public CollegeBean findByName(String name) throws Exception {
		log.debug("CollegeModel.findByName Started!!!");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COLLEGE WHERE NAME = ?");

		Connection conn = null;
		CollegeBean bean = new CollegeBean();
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setString(1, name);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bean.setId(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setAddress(rs.getString(3));
				bean.setState(rs.getString(4));
				bean.setCity(rs.getString(5));
				bean.setPhoneNo(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
			}
			log.debug("CollegeModel.findByName Success!!!");
			ps.close();
			rs.close();
		} catch (Exception e) {
			log.error("CollegeModel.findByName Exception");
			throw new ApplicationException("Exception in Record Fetching");
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("CollegeModel.findByName Closed!!!");
		}

		return bean;
	}

	public CollegeBean findByPk(int id) throws Exception {
		log.debug("CollegeModel.findByPk Started!!!");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COLLEGE WHERE ID = ?");
		Connection conn = null;
		CollegeBean bean = new CollegeBean();
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean.setId(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setAddress(rs.getString(3));
				bean.setState(rs.getString(4));
				bean.setCity(rs.getString(5));
				bean.setPhoneNo(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
			}
			log.debug("CollegeModel.findByPk Success!!!");
			ps.close();
			rs.close();
		} catch (Exception e) {
			log.error("CollegeModel.findByPk Exception");
			throw new ApplicationException("Exception in Record Fetching");
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("CollegeModel.findByPk Closed!!!");
		}

		return bean;

	}

	public void update(CollegeBean bean) throws Exception {
		log.debug("CollegeModel.update Started!!!");
		StringBuffer sql = new StringBuffer(
				"UPDATE ST_COLLEGE SET NAME=?,ADDRESS=?,STATE=?,CITY=?,PHONE_NO=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getAddress());
			ps.setString(3, bean.getState());
			ps.setString(4, bean.getCity());
			ps.setString(5, bean.getPhoneNo());
			ps.setString(6, bean.getCreatedBy());
			ps.setString(7, bean.getModifiedBy());
			ps.setTimestamp(8, bean.getCreatedDatetime());
			ps.setTimestamp(9, bean.getModifiedDatetime());
			ps.setLong(10, bean.getId());
			ps.executeUpdate();
			conn.commit();
			ps.close();

			log.debug("CollegeModel.update Success!!!");

		} catch (Exception e) {
			log.error("CollegeModel.Update Exception");
			throw new ApplicationException("Exception in Update College");
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("CollegeModel.update Closed!!!");
		}

	}

	public CollegeBean findByCity(String city) throws Exception {
		log.debug("CollegeModel.findByCity Started!!!");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COLLEGE WHERE CITY = ?");
		Connection conn = null;
		CollegeBean bean = new CollegeBean();
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setString(1, city);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bean.setId(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setAddress(rs.getString(3));
				bean.setState(rs.getString(4));
				bean.setCity(rs.getString(5));
				bean.setPhoneNo(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));

			}
			log.debug("CollegeModel.findByCity Success!!!");
			ps.close();
			rs.close();
		} catch (Exception e) {
			log.error("CollegeModel.findByCity Exception", e);
			throw new ApplicationException("Exception in Record Fetching");
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("CollegeModel.findByCity Closed!!!");

		}

		return bean;

	}

	public List<CollegeBean> search(CollegeBean bean, int pageNo, int pageSize) throws Exception {
		log.debug("CollegeModel.Search Started!!!");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COLLEGE WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND NAME like '" + bean.getName() + "%'");
			}
			if (bean.getAddress() != null && bean.getAddress().length() > 0) {
				sql.append(" AND ADDRESS like '" + bean.getAddress() + "%'");
			}
			if (bean.getState() != null && bean.getState().length() > 0) {
				sql.append(" AND STATE like '" + bean.getState() + "%'");
			}
			if (bean.getCity() != null && bean.getCity().length() > 0) {
				sql.append(" AND CITY like '" + bean.getCity() + "%'");
			}
			if (bean.getPhoneNo() != null && bean.getPhoneNo().length() > 0) {
				sql.append(" AND PHONE_NO = " + bean.getPhoneNo());
			}

		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}
		log.debug("CollegeModel.Search Query Success!!!");
		ArrayList<CollegeBean> list = new ArrayList<CollegeBean>();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new CollegeBean();
				bean.setId(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setAddress(rs.getString(3));
				bean.setState(rs.getString(4));
				bean.setCity(rs.getString(5));
				bean.setPhoneNo(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
				list.add(bean);
			}
			log.debug("CollegeModel.Search Success!!!");
			rs.close();
			ps.close();
		} catch (Exception e) {
			log.error("CollegeModel.Search Exception");
			throw new ApplicationException("Exception : Exception in search college");
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("CollegeModel.Search Closed!!!");
		}

		return list;
	}

	public List<CollegeBean> list(int pageNo, int pageSize) throws Exception {
		log.debug("CollegeModel.list Started!!!");
		List<CollegeBean> list = new ArrayList<CollegeBean>();
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COLLEGE");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" LIMIT " + pageNo + "," + pageSize);
		}
		log.debug("CollegeModel.list Query Success!!!");
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				CollegeBean bean = new CollegeBean();
				bean.setId(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setAddress(rs.getString(3));
				bean.setState(rs.getString(4));
				bean.setCity(rs.getString(5));
				bean.setPhoneNo(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
				list.add(bean);
			}
			rs.close();
			log.debug("CollegeModel.list Success!!!");
		} catch (Exception e) {
			log.error("CollegeModel.list Exception");
			throw new ApplicationException("Exception : Exception in getting list of users");
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("CollegeModel.list Closed!!!");
		}

		return list;

	}

}
