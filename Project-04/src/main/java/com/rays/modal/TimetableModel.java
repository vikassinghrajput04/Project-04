package com.rays.modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.rays.beans.CourseBean;
import com.rays.beans.SubjectBean;
import com.rays.beans.TimetableBean;
import com.rays.exception.ApplicationException;
import com.rays.utility.JDBCDataSource;
/**
 * 
 * @author Vikas Singh
 *
 */
public class TimetableModel {

	private static Logger log = Logger.getLogger(TimetableModel.class);

	public Integer nextPK() throws Exception {
		log.debug("TimetableModel.nextPk method Started!!!");
		StringBuffer sql = new StringBuffer("SELECT MAX(ID) FROM ST_TIMETABLE");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
			log.debug("TimetableModel.nextPk method Success!!!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("TimetableModel.nextPk Exception ..." + e);
			throw new ApplicationException("Exception in NextPk of TIMETABLE Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;
	}

	public int add(TimetableBean tb) throws Exception {
		log.debug("TimeTableModel.add Started!!!");
		Connection conn = null;
		int pk = 0;
		pk = nextPK();
		CourseModel courseModel = new CourseModel();
		CourseBean coubean = courseModel.findByPk(tb.getCourseId());
		String courseName = coubean.getCourseName();

		SubjectModel subjectmodel = new SubjectModel();
		SubjectBean sbean = subjectmodel.findByPk(tb.getSubjectId());
		String subjectName = sbean.getSubjectName();

		// TimetableBean bean11 = checkBycds(tb.getCourse_Id(),tb.getSemester(), new
		// java.sql.Date(tb.getExam_Date().getTime()));
		// TimetableBean bean12 = checkBycss(tb.getCourse_Id(), tb.getSubject_Id(),
		// tb.getSemester());
		/*
		 * if(bean11 != null || bean12 != null){ throw new
		 * DuplicateRecordException("TimeTable Already Exsist"); }
		 */
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO ST_TIMETABLE VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setLong(1, pk);
			ps.setString(2, courseName);
			ps.setInt(3, tb.getCourseId());
			ps.setString(4, subjectName);
			ps.setInt(5, tb.getSubjectId());
			ps.setDate(6, new java.sql.Date(tb.getExamDate().getTime()));
			ps.setString(7, tb.getExamTime());
			ps.setString(8, tb.getSemester());
			ps.setString(9, tb.getCreatedBy());
			ps.setString(10, tb.getModifiedBy());
			ps.setTimestamp(11, tb.getCreatedDatetime());
			ps.setTimestamp(12, tb.getModifiedDatetime());
			ps.executeUpdate();

			conn.commit();
			ps.close();
			log.debug("TimeTableModel.add Success!!!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("TimeTableModel.add Exception ...", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception in the Rollback of TIMETABLE Model" + ex.getMessage());
			}
			e.printStackTrace();
			throw new ApplicationException("Exception in Add method of TIMETABLE Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;

	}

	public void delete(TimetableBean tb) throws Exception {
		log.debug("TimetableModel.delete Started");
		StringBuffer sql = new StringBuffer("DELETE FROM ST_TIMETABLE WHERE ID=?");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, tb.getId());
			ps.executeUpdate();
			conn.commit();
			log.debug("TimetableModel.delete Success!!!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("TimetableModel.delete Exception ..." + e);

			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception in Rollback of Delte Method of TIMETABLE Model" + ex.getMessage());
			}
			throw new ApplicationException("Exception in Delte Method of TIMETABLE Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("TimetableModel.delete Closed!!!!");
	}

	public void update(TimetableBean tb) throws Exception {
		log.debug("TimetableModel.update Started!!!!");
		Connection conn = null;
		StringBuffer sql = new StringBuffer(
				"UPDATE ST_TIMETABLE SET COURSE_NAME=?,COURSE_ID=?,SUBJECT_NAME=?,SUBJECT_ID=?,EXAM_DATE=?,EXAM_TIME=?,SEMESTER=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
		CourseModel coumodel = new CourseModel();
		CourseBean coubean = coumodel.findByPk(tb.getCourseId());
		String courseName = coubean.getCourseName();

		SubjectModel smodel = new SubjectModel();
		SubjectBean sbean = smodel.findByPk(tb.getSubjectId());
		String subjectName = sbean.getSubjectName();

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql.toString());

			ps.setString(1, courseName);
			ps.setInt(2, tb.getCourseId());
			ps.setString(3, subjectName);
			ps.setInt(4, tb.getSubjectId());
			ps.setDate(5, new java.sql.Date(tb.getExamDate().getTime()));
			ps.setString(6, tb.getExamTime());
			ps.setString(7, tb.getSemester());
			ps.setString(8, tb.getCreatedBy());
			ps.setString(9, tb.getModifiedBy());
			ps.setTimestamp(10, tb.getCreatedDatetime());
			ps.setTimestamp(11, tb.getModifiedDatetime());
			ps.setLong(12, tb.getId());

			ps.executeUpdate();

			conn.commit();
			ps.close();
			log.debug("TimetableModel.update Success!!!!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("TimetableModel.update Exception....", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception in Rollback of Update of TimeTable Model" + ex.getMessage());
			}
			throw new ApplicationException("Exception in update of TimeTable Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public TimetableBean findByName(String name) throws Exception {
		log.debug("TimeTableModel.findByName Started!!!");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE Subject_Name = ?");
		Connection conn = null;
		TimetableBean tb = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tb = new TimetableBean();

				tb.setId(rs.getInt(1));
				tb.setCourseName(rs.getString(2));
				tb.setCourseId(rs.getInt(3));
				tb.setSubjectName(rs.getString(4));
				tb.setSubjectId(rs.getInt(5));
				tb.setExamDate(rs.getDate(6));
				tb.setExamTime(rs.getString(7));
				tb.setSemester(rs.getString(8));
				tb.setCreatedBy(rs.getString(9));
				tb.setModifiedBy(rs.getString(10));
				tb.setCreatedDatetime(rs.getTimestamp(11));
				tb.setModifiedDatetime(rs.getTimestamp(12));
			}
			rs.close();
			log.debug("TimeTableModel.findByName Success!!!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("TimeTableModel.findByName Exception....", e);
			throw new ApplicationException("Exception in findByName Method of TimeTable Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("TimeTable Model findByName method End");
		return tb;
	}

	public TimetableBean findByPK(long pk) throws Exception {
		log.debug("TimeTableModel.findBypk method Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE ID=?");
		Connection conn = null;
		TimetableBean tb = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, pk);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tb = new TimetableBean();

				tb.setId(rs.getInt(1));
				tb.setCourseName(rs.getString(2));
				tb.setCourseId(rs.getInt(3));
				tb.setSubjectName(rs.getString(4));
				tb.setSubjectId(rs.getInt(5));
				tb.setExamDate(rs.getDate(6));
				tb.setExamTime(rs.getString(7));
				tb.setSemester(rs.getString(8));
				tb.setCreatedBy(rs.getString(9));
				tb.setModifiedBy(rs.getString(10));
				tb.setCreatedDatetime(rs.getTimestamp(11));
				tb.setModifiedDatetime(rs.getTimestamp(12));
			}
			rs.close();
			log.debug("TimeTableModel.findBypk method Success");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("TimeTableModel.findBypk Exception....", e);
			throw new ApplicationException("Exception in findByPk Method of TimeTable Model");
		} finally {
			JDBCDataSource.closeConnection(conn);

		}
		log.debug("TimeTable Model findByPk method End");
		return tb;
	}

	public List<TimetableBean> search(TimetableBean bean) throws Exception {
		return search(bean, 0, 0);
	}

	public List<TimetableBean> search(TimetableBean tb, int pageNo, int pageSize) throws Exception {
		log.debug("TimeTableModel.search Started!!!");

		Connection conn = null;
		ArrayList<TimetableBean> list = new ArrayList<TimetableBean>();
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE 1=1");

		if (tb != null) {
			if (tb.getId() > 0) {
				sql.append(" AND id = " + tb.getId());
			}

			if (tb.getCourseId() > 0) {
				sql.append(" AND Course_ID = " + tb.getCourseId());
			}
			if (tb.getSubjectId() > 0) {
				sql.append(" AND Subject_ID = " + tb.getSubjectId());
			}
			if (tb.getExamDate() != null && tb.getExamDate().getTime() > 0) {
				Date d = new Date(tb.getExamDate().getTime());
				sql.append(" AND Exam_Date = '" + d + "'");
			}

			if (tb.getCourseName() != null && tb.getCourseName().length() > 0) {
				sql.append(" AND Course_Name like '" + tb.getCourseName() + "%'");
			}

			if (tb.getSubjectName() != null && tb.getSubjectName().length() > 0) {
				sql.append(" AND Subject_Name like '" + tb.getSubjectName() + "%'");
			}
		}

		// Page Size is greater then Zero then apply pagination
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + " , " + pageSize);
		}
		log.debug("Query Success!!!");
		System.out.println("Query-- " + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tb = new TimetableBean();

				tb.setId(rs.getInt(1));
				tb.setCourseName(rs.getString(2));
				tb.setCourseId(rs.getInt(3));
				tb.setSubjectName(rs.getString(4));
				tb.setSubjectId(rs.getInt(5));
				tb.setExamDate(rs.getDate(6));
				tb.setExamTime(rs.getString(7));
				tb.setSemester(rs.getString(8));
				tb.setCreatedBy(rs.getString(9));
				tb.setModifiedBy(rs.getString(10));
				tb.setCreatedDatetime(rs.getTimestamp(11));
				tb.setModifiedDatetime(rs.getTimestamp(12));
				list.add(tb);
			}
			rs.close();
			log.debug("TimeTableModel.search Success!!!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("TimeTableModel.search Exception...." + e);
			throw new ApplicationException("Exception in search Method of TimeTable Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("TimeTableModel.search Closed!!!");
		return list;
	}

	public List<TimetableBean> list() throws Exception {
		return list(0, 0);
	}

	public List<TimetableBean> list(int pageNo, int pageSize) throws Exception {
		log.debug("TimeTableModel.list Started!!!");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE ");

		// Page Size is greater then Zero then aplly pagination
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + " , " + pageSize);
		}
		log.debug("Query Success");
		System.out.println("Query ---  " + sql);
		Connection conn = null;
		ArrayList<TimetableBean> list = new ArrayList<TimetableBean>();
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				TimetableBean tb = new TimetableBean();

				tb.setId(rs.getInt(1));
				tb.setCourseName(rs.getString(2));
				tb.setCourseId(rs.getInt(3));
				tb.setSubjectName(rs.getString(4));
				tb.setSubjectId(rs.getInt(5));
				tb.setExamDate(rs.getDate(6));
				tb.setExamTime(rs.getString(7));
				tb.setSemester(rs.getString(8));
				tb.setCreatedBy(rs.getString(9));
				tb.setModifiedBy(rs.getString(10));
				tb.setCreatedDatetime(rs.getTimestamp(11));
				tb.setModifiedDatetime(rs.getTimestamp(12));
				list.add(tb);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("TimeTableModel.list Exception....", e);
			throw new ApplicationException("Exception in list Method of timetable Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

	public TimetableBean checkBycss(int CourseId, int SubjectId, String sem) throws Exception {
		log.debug("TimeTableModel.checkBycss Started!!!");
		Connection conn = null;

		// java.util.Date ExamDAte,String ExamTime
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_TIMETABLE WHERE COURSE_ID=? AND SUBJECT_ID=? AND SEMESTER=?");
		TimetableBean bean = new TimetableBean();
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, CourseId);
			ps.setLong(2, SubjectId);
			ps.setString(3, sem);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				bean = new TimetableBean();
				bean.setId(rs.getInt(1));
				bean.setCourseName(rs.getString(2));
				bean.setCourseId(rs.getInt(3));
				bean.setSubjectName(rs.getString(4));
				bean.setSubjectId(rs.getInt(5));
				bean.setExamDate(rs.getDate(6));
				bean.setExamTime(rs.getString(7));
				bean.setSemester(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
			}
			rs.close();
			log.debug("TimeTableModel.checkBycss Success!!!!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("TimeTableModel.checkBycss Exception....", e);
			throw new ApplicationException("Exception in list Method of timetable Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("TimeTableModel.checkBycss Closed!!!");
		return bean;
	}

	public TimetableBean checkBycds(int CourseId, int sem, Date ExamDate) throws Exception {
		log.debug("TimeTableModel.checkBycds Started!!!");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_TIMETABLE WHERE Course_ID=? AND Semester=? AND Exam_Date=?");

		Connection conn = null;
		TimetableBean tb = null;
		Date ExDate = new Date(ExamDate.getTime());

		try {
			Connection con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setInt(1, CourseId);
			ps.setInt(2, sem);
			ps.setDate(3, (java.sql.Date) ExamDate);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				tb = new TimetableBean();
				tb.setId(rs.getInt(1));
				tb.setCourseName(rs.getString(2));
				tb.setCourseId(rs.getInt(3));
				tb.setSubjectName(rs.getString(4));
				tb.setSubjectId(rs.getInt(5));
				tb.setExamDate(rs.getDate(6));
				tb.setExamTime(rs.getString(7));
				tb.setSemester(rs.getString(8));
				tb.setCreatedBy(rs.getString(9));
				tb.setModifiedBy(rs.getString(10));
				tb.setCreatedDatetime(rs.getTimestamp(11));
				tb.setModifiedDatetime(rs.getTimestamp(12));
			}
			rs.close();
			log.debug("TimeTableModel.checkBycds Success!!!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("TimeTableModel.checkBycds Exception....", e);
			throw new ApplicationException("Exception : TimeTableModel.checkBycds Exception");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return tb;
	}

	public static TimetableBean checkBysemester(long CourseId, long SubjectId, String semester, Date ExamDate)
			throws Exception {
		log.debug("TimeTableModel.checkBysemester Started!!!");
		TimetableBean bean = null;

		Date ExDate = new Date(ExamDate.getTime());

		StringBuffer sql = new StringBuffer(
				"SELECT * FROM TIMETABLE WHERE COURSE_ID=? AND SUBJECT_ID=? AND" + " SEMESTER=? AND EXAM_DATE=?");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, CourseId);
			ps.setLong(2, SubjectId);
			ps.setString(3, semester);
			ps.setDate(4, (java.sql.Date) ExDate);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bean = new TimetableBean();
				bean.setId(rs.getInt(1));
				bean.setCourseName(rs.getString(2));
				bean.setCourseId(rs.getInt(3));
				bean.setSubjectName(rs.getString(4));
				bean.setSubjectId(rs.getInt(5));
				bean.setExamDate(rs.getDate(6));
				bean.setExamTime(rs.getString(7));
				bean.setSemester(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
			}
			log.debug("TimeTableModel.checkBysemester Success!!!");
		} catch (Exception e) {
			log.error("TimeTableModel.checkBysemester Exception!!!");
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);

		}
		return bean;
	}

	public static TimetableBean checkByCourseName(int CourseId, Date ExamDate) throws Exception {
		Connection conn = null;
		TimetableBean bean = null;

		Date Exdate = new Date(ExamDate.getTime());

		StringBuffer sql = new StringBuffer("SELECT * FROM TIMETABLE WHERE COURSE_ID=? " + "AND EXAM_DATE=?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, CourseId);
			ps.setDate(2, (java.sql.Date) Exdate);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bean = new TimetableBean();
				bean.setId(rs.getInt(1));
				bean.setCourseId(rs.getInt(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getInt(4));
				bean.setSubjectName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setExamTime(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

}