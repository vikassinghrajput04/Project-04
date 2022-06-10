package com.rays.modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.rays.beans.CourseBean;
import com.rays.exception.ApplicationException;
import com.rays.exception.DatabaseException;
import com.rays.utility.JDBCDataSource;
/**
 * 
 * @author Vikas Singh
 *
 */
public class CourseModel {
	private static Logger log = Logger.getLogger(CourseModel.class);

	public Long add(CourseBean bean) throws Exception {
		log.debug("CourseModel.add Started!!");
		StringBuffer sql = new StringBuffer("INSERT INTO ST_COURSE VALUES(?,?,?,?,?,?,?,?)");
		Connection conn = null;
		Long pk = nextPK();
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, pk);
			ps.setString(2, bean.getCourseName());
			ps.setString(3, bean.getDescription());
			ps.setString(4, bean.getDuration());
			ps.setString(5, bean.getCreatedBy());
			ps.setString(6, bean.getModifiedBy());
			ps.setTimestamp(7, bean.getCreatedDatetime());
			ps.setTimestamp(8, bean.getModifiedDatetime());

			ps.executeUpdate();
			conn.commit();
			log.debug("CourseModel.add Success!!!");
			ps.close();

		} catch (Exception e) {
			log.error("CourseModel.add Exception");
			throw new DatabaseException("Exception in Inserting");
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("CourseModel.add Closed!!!");
		}
		
		return pk;
	}

	public Long nextPK() throws Exception {
		log.debug("CourseModel.nextPk Started!!!");
		StringBuffer sql = new StringBuffer("SELECT MAX(ID) FROM ST_COURSE");
		Long pk = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getLong(1);
			}
			log.debug("CourseModel.nextPk Success!!!");
		} catch (Exception e) {
			log.error("CourseModel.nextPk Exception");
			throw new DatabaseException("Exception in getting PK");

		}finally {
			log.debug("CourseModel.nextPk Closed!!!");
			JDBCDataSource.closeConnection(conn);
		}
		
		return pk + 1;
	}

	public void update(CourseBean bean) throws Exception {
		log.debug("CourseModel.update Started!!!");
		StringBuffer sql = new StringBuffer(
				"UPDATE ST_COURSE SET COURSE_NAME=?,DESCRIPTION=?,DURATION=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
		Connection conn = null;
		if (bean.getId() == 0) {
			throw new ApplicationException("User Does not Exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setString(1, bean.getCourseName());
			ps.setString(2, bean.getDescription());
			ps.setString(3, bean.getDuration());
			ps.setString(4, bean.getCreatedBy());
			ps.setString(5, bean.getModifiedBy());
			ps.setTimestamp(6, bean.getCreatedDatetime());
			ps.setTimestamp(7, bean.getModifiedDatetime());
			ps.setLong(8, bean.getId());

			ps.executeUpdate();
			conn.commit();
			log.debug("CourseModel.update Success!!!");

		} catch (Exception e) {
			log.error("CourseModel.update Exception");
			throw new DatabaseException("Exception in Update");
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("CourseModel.update Closed!!!");
		}
		
	}

	public void delete(CourseBean bean) throws Exception {
		log.debug("CourseModel.delete Started");
		StringBuffer sql = new StringBuffer("DELETE FROM ST_COURSE WHERE ID=?");
		Connection conn = null;
		if (bean.getId() == 0) {
			throw new ApplicationException("User Does not Exist");
		}
		
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, bean.getId());

			ps.executeUpdate();
			conn.commit();
			log.debug("CourseModel.delete Success!!!");
		} catch (Exception e) {
			log.error("CourseModel.delete Exception");
			throw new DatabaseException("Error in Deleting");
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("CourseModel.delete Closed!!");
		}
	
	}

	public CourseBean findByPk(int i) throws Exception {
		log.debug("CourseModel.findByPk Started!!!");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURSE WHERE ID=?");
		Connection conn = null;
		CourseBean bean = new CourseBean();
		if (i == 0) {
			throw new ApplicationException("User Does not Exist");
		}
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, i);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean.setId(rs.getInt(1));
				bean.setCourseName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setDuration(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
			}
			ps.close();
			rs.close();
			log.debug("CourseModel.findByPk Success!!!");

		} catch (Exception e) {
			log.error("CourseModel.findByPk Exception");
			throw new DatabaseException("Error in getting Data");
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("CourseModel.findByPk Closed!!!");
		}
		
		return bean;
	}

	public CourseBean findByName(String name) throws Exception {
		log.debug("CourseModel.findByName Started!!!");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURSE WHERE COURSE_NAME=?");
		Connection conn = null;
		CourseBean bean = new CourseBean();
		if (name == null) {
			throw new ApplicationException("User Does not Exist");
		}
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setString(1, name);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean.setId(rs.getInt(1));
				bean.setCourseName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setDuration(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
			}
			ps.close();
			rs.close();
			log.debug("CourseModel.findByName Success!!!");

		} catch (Exception e) {
			log.error("CourseModel.findByName Exception");
			throw new DatabaseException("Error in getting Data");
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("CourseModel.findByName Closed!!!");
		}
		
		return bean;
	}

	public List<CourseBean> search() throws Exception {
		log.debug("CourseModel.search null argument Started!!!");
		CourseBean bean = new CourseBean();
		return search(bean, 0, 0);
	}

	public List<CourseBean> search(CourseBean bean, int pageNo, int pageSize) throws Exception {
		log.debug("CourseModel.search Started!!!");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURSE WHERE 1=1");
		
		if (bean.getId() != 0) {
			Long id = bean.getId();
			sql.append(" AND ID = " + id);

		}
		if (bean.getCourseName() != null && bean.getCourseName().length() > 0) {
			sql.append(" AND COURSE_NAME LIKE '" + bean.getCourseName() + "%'");

		}
		if (bean.getDescription() != null && bean.getDescription().length() > 0) {
			sql.append(" AND DESCRIPTION LIKE '" + bean.getDescription() + "%'");

		}
		if (bean.getDuration() != null && bean.getDuration().length() > 0) {
			sql.append(" AND DURATION LIKE '" + bean.getDuration() + "%'");

		}
		
		
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" LIMIT " + pageNo + "," + pageSize);
		}
		log.debug("CourseModel.search Success Query!!!");
		System.out.println("Query--- "+sql);
		ArrayList<CourseBean> list = new ArrayList<CourseBean>();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			System.out.println(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(rs.getInt(1));
				bean.setCourseName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setDuration(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
				list.add(bean);
			}
				log.debug("CourseModel.search Success!!!");
			
		} catch (Exception e) {
			log.error("CourseModel.search Exception");
			throw new DatabaseException("Exception in Search Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("CourseModel.search Closed!!!");
		}
		
		return list;
	}
	public List<CourseBean> list() throws Exception {
		log.debug("CourseModel.list null Argument Started!!!");
		return list(0,0);

	}
	public List<CourseBean> list(int pageNo,int pageSize) throws Exception{
		log.debug("CourseModel.list Started!!!");
				StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURSE ");
		
				if(pageSize>0){
					pageNo = (pageNo-1)*pageSize;
					sql.append(" limit "+ pageNo +" , "+pageSize);
				}
				log.debug("CourseModel.list Query Success!!!");
				List<CourseBean> list = new ArrayList<CourseBean>();
				Connection conn = null;
				
				try{
					conn = JDBCDataSource.getConnection();
					PreparedStatement ps =conn.prepareStatement(sql.toString());
					ResultSet rs =ps.executeQuery();
					while (rs.next()) {
						CourseBean bean = new CourseBean();
						bean.setId(rs.getInt(1));
						bean.setCourseName(rs.getString(2));	
						bean.setDescription(rs.getString(3));
						bean.setDuration(rs.getString(4));
					    bean.setCreatedBy(rs.getString(5));
						bean.setModifiedBy(rs.getString(6));
						bean.setCreatedDatetime(rs.getTimestamp(7));
						bean.setModifiedDatetime(rs.getTimestamp(8));
						
						list.add(bean);
					}
					log.debug("CourseModel.list Success!!!");
					rs.close();
				}catch(Exception e){
					e.printStackTrace();
					log.error("CourseModel.list Exception"+e);
					throw new ApplicationException("Exception : Exception in CourseModel List method " + e.getMessage());		
				}finally{
					JDBCDataSource.closeConnection(conn);
					log.debug("CourseModel.list Closed!!!");
				}
				
				return list;
			}
}
