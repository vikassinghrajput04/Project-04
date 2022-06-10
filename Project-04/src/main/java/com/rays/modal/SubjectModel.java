package com.rays.modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.rays.beans.CourseBean;
import com.rays.beans.SubjectBean;
import com.rays.exception.ApplicationException;
import com.rays.exception.DuplicateRecordException;
import com.rays.utility.JDBCDataSource;
/**
 * 
 * @author Vikas Singh
 *
 */
public class SubjectModel {

	private Logger log = Logger.getLogger(SubjectModel.class);

	public int nextPk() throws Exception {
		log.debug("SubjectModel.nextPk started!!!");
		StringBuffer sql = new StringBuffer("SELECT MAX(id) FROM ST_SUBJECT");
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
			ps.close();
			log.debug("SubjectModel.nextPk Success!!!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SubjectModel.nextPk Exception ...", e);
			throw new ApplicationException("Exception in NextPk of subject Model");
		} finally {
			log.debug("SubjectModel.nextPk Closed!!!!");
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;
	}

	public int add(SubjectBean bean) throws Exception {
		log.debug("SubjectModel.add Started!!!");
		Connection conn = null;
		StringBuffer sql = new StringBuffer("INSERT INTO ST_SUBJECT VALUES(?,?,?,?,?,?,?,?,?)");
		// get Course Name
		CourseModel cModel = new CourseModel();
		CourseBean CourseBean = cModel.findByPk(bean.getCourseId());
		bean.setCourseName(CourseBean.getCourseName());

		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();

			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement(sql.toString());

			ps.setInt(1, pk);
			ps.setString(2, bean.getSubjectName());
			ps.setString(3, bean.getCourseName());
			ps.setInt(4, bean.getCourseId());
			ps.setString(5, bean.getDescription());
			ps.setString(6, bean.getCreatedBy());
			ps.setString(7, bean.getModifiedBy());
			ps.setTimestamp(8, bean.getCreatedDatetime());
			ps.setTimestamp(9, bean.getModifiedDatetime());
			ps.executeUpdate();
			conn.commit();
			ps.close();
			log.debug("SubjectModel.add Success!!!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SubjectModel.add Exception.." + e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Subject");
		} finally {
			log.debug("SubjectModel.add Closed!!!");
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	public void delete(SubjectBean bean) throws Exception {
		log.debug("SubjectModel.Delete Started!!!!");
		StringBuffer sql = new StringBuffer("DELETE FROM ST_SUBJECT WHERE ID=?");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, bean.getId());
			ps.executeUpdate();
			conn.commit();
			log.debug("SubjectModel.Delete Success!!!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SubjectModel.Delete Exception ...", e);

			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception in Rollback of Delte Method of Subject Model" + ex.getMessage());
			}
			throw new ApplicationException("Exception in Delte Method of Subject Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("SubjectModel.Delete Closed!!!");
	}

	public void update(SubjectBean bean) throws Exception {
		log.debug("SubjectModel.update Started");
		StringBuffer sql = new StringBuffer(	"UPDATE ST_SUBJECT SET SUBJECT_NAME=?,COURSE_NAME=?,COURSE_ID=?,DESCRIPTION=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
		Connection conn = null;

		SubjectBean beanExist = findByPk(bean.getId());

		// Check if updated id already exist
		if (beanExist != null && beanExist.getId() != bean.getId()) {
			throw new DuplicateRecordException("Subject Name is already exist");
		}

	
		 //get Course Name CourseModel cModel = new CourseModel(); CourseBean
		CourseModel courseModel = new CourseModel();
		  CourseBean courseBean= courseModel.findByPk(bean.getCourseId());
		  bean.setCourseName(courseBean.getCourseName());
		 

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setString(1, bean.getSubjectName());
			ps.setString(2, bean.getCourseName());
			ps.setInt(3, bean.getCourseId());
			ps.setString(4, bean.getDescription());
			ps.setString(5, bean.getCreatedBy());
			ps.setString(6, bean.getModifiedBy());
			ps.setTimestamp(7, bean.getCreatedDatetime());
			ps.setTimestamp(8, bean.getModifiedDatetime());
			ps.setLong(9, bean.getId());
			ps.executeUpdate();
			conn.commit();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SubjectModel.update Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Subject ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("SubjectModel.update Closed!!");
	}

	public SubjectBean findByName(String name) throws Exception {
		log.debug("SubjectModel.findByName Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_SUBJECT WHERE SUBJECT_NAME=?");
		Connection conn = null;
		SubjectBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new SubjectBean();
				bean.setId(rs.getInt(1));
				bean.setSubjectName(rs.getString(2));
				bean.setCourseName(rs.getString(3));
				bean.setCourseId(rs.getInt(4));
				bean.setDescription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
			}
			rs.close();
			ps.close();
			log.debug("SubjectModel.findByName Success!!!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SubjectModel.findByName Exception...."+ e);
			e.printStackTrace();
			throw new ApplicationException("Exception in findByName Method of SubjectModel");
		} finally {
			JDBCDataSource.closeConnection(conn);

		}
		 log.debug("SubjectModel.findByName Closed!!!");
		return bean;
	}

	
	public SubjectBean findByPk(long pk) throws Exception {
		log.debug("SubjectModel.findBypk Started!!!");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_SUBJECT WHERE ID=?");
		Connection conn = null;
		SubjectBean bean = new SubjectBean();

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, pk);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new SubjectBean();

				bean.setId(rs.getInt(1));
				bean.setSubjectName(rs.getString(2));
				bean.setCourseName(rs.getString(3));
				bean.setCourseId(rs.getInt(4));
				bean.setDescription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
			}
			log.debug("SubjectModel.findBypk Success!!!");
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("database Exception....", e);
			throw new ApplicationException("Exception in findByPk Method of Subject Model");
		} finally {
			JDBCDataSource.closeConnection(conn);

		}
		log.debug("SubjectModel.findByPk Closed!!!");
		return bean;
	}

	public List<SubjectBean> search(SubjectBean bean) throws Exception {
		return search(bean, 0, 0);
	}

	public List<SubjectBean> search(SubjectBean bean, int pageNo, int pageSize) throws Exception {
		log.debug("SubjectModel.search Started!!!");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_SUBJECT WHERE 1=1");
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getCourseId() > 0) {
				sql.append(" AND COURSE_ID = " + bean.getCourseId());
			}

			if (bean.getSubjectName() != null && bean.getSubjectName().length() > 0) {
				sql.append(" AND SUBJECT_NAME LIKE '" + bean.getSubjectName() + "%'");
			}
			if (bean.getCourseName() != null && bean.getCourseName().length() > 0) {
				sql.append(" AND COURSE_NAME LIKE '" + bean.getCourseName() + "%'");
			}
			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" AND DESCRIPTION LIKE '" + bean.getDescription() + " % '");
			}

		}

		// Page Size is greater then Zero then aplly pagination
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + " , " + pageSize);
		}
		System.out.println("Query -- " + sql);
		log.debug("Query Success -- "+sql);
		Connection conn = null;
		ArrayList<SubjectBean> list = new ArrayList<SubjectBean>();
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bean = new SubjectBean();

				bean.setId(rs.getInt(1));
				bean.setSubjectName(rs.getString(2));
				bean.setCourseName(rs.getString(3));
				bean.setCourseId(rs.getInt(4));
				bean.setDescription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
				list.add(bean);
			}
			rs.close();
			ps.close();
			log.debug("SubjectModel.search Success!!!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SubjectModel.search Exception....", e);
			throw new ApplicationException("Exception in search Method of Subject Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("SubjectModel.search Closed!!!!");
		return list;
	}

	public List<SubjectBean> list() throws Exception {
		return list(0, 0);
	}

	public List<SubjectBean> list(int pageNo, int pageSize) throws Exception {
		log.debug("SubjectModel.list Started!!!!!");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_SUBJECT ");

		// Page Size is greater then Zero then aplly pagination
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + " , " + pageSize);
		}

		Connection conn = null;
		ArrayList<SubjectBean> list = new ArrayList<SubjectBean>();
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SubjectBean bean = new SubjectBean();

				bean.setId(rs.getInt(1));
				bean.setSubjectName(rs.getString(2));
				bean.setCourseName(rs.getString(3));
				bean.setCourseId(rs.getInt(4));
				bean.setDescription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
				list.add(bean);
			}
			rs.close();
			ps.close();
			log.debug("SubjectModel.list Success!!!!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SubjectModel.list Exception....", e);
			throw new ApplicationException("Exception in list Method of Subject Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("SubjectModel.list Closed!!!");
		return list;
	}
}
