package com.rays.modal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.rays.beans.CollegeBean;
import com.rays.beans.CourseBean;
import com.rays.beans.FacultyBean;
import com.rays.beans.SubjectBean;
import com.rays.exception.ApplicationException;
import com.rays.exception.DatabaseException;
import com.rays.exception.RecordNotFoundException;
import com.rays.utility.JDBCDataSource;
/**
 * 
 * @author Vikas Singh
 *
 */

public class FacultyModel {
	private Logger log = Logger.getLogger(FacultyModel.class);

	public Long add(FacultyBean bean) throws Exception {
		log.debug("FacultyModel.add Started!!!");
		StringBuffer sql = new StringBuffer("INSERT INTO ST_FACULTY VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		Long pk = nextPK();
		CollegeModel collegeModel = new CollegeModel();
		CollegeBean collegeBean = collegeModel.findByPk(bean.getCollegeId());
		bean.setCollegeName(collegeBean.getName());

		CourseModel courseModel = new CourseModel();
		CourseBean courseBean = courseModel.findByPk(bean.getCollegeId());
		bean.setCourseName(courseBean.getCourseName());

		SubjectModel subjectModel = new SubjectModel();
		SubjectBean subjectBean = subjectModel.findByPk(bean.getSubjectId());
		bean.setSubjectName(subjectBean.getSubjectName());
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, pk);
			ps.setString(2, bean.getFirstName());
			ps.setString(3, bean.getLastName());
			ps.setString(4, bean.getGender());
			ps.setDate(5, new Date(bean.getdOJ().getTime()));
			ps.setString(6, bean.getQualification());
			ps.setString(7, bean.getEmailId());
			ps.setString(8, bean.getMobileNo());
			ps.setLong(9, bean.getCollegeId());
			ps.setString(10, bean.getCollegeName());
			ps.setLong(11, bean.getCourseId());
			ps.setString(12, bean.getCourseName());
			ps.setLong(13, bean.getSubjectId());
			ps.setString(14, bean.getSubjectName());
			ps.setString(15, bean.getCreatedBy());
			ps.setString(16, bean.getModifiedBy());
			ps.setTimestamp(17, bean.getCreatedDatetime());
			ps.setTimestamp(18, bean.getModifiedDatetime());

			ps.executeUpdate();
			conn.commit();
			log.debug("FacultyModel.add Success!!!");

		} catch (Exception e) {
			log.error("FacultyModel.add Exception!!!");
			throw new DatabaseException("Exception in Inserting Data");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("FacultyModel.add Closed!!!");
		return pk;
	}

	public Long nextPK() throws Exception {
		log.debug("FacultyModel.nextPk Started!!!");
		StringBuffer sql = new StringBuffer("SELECT MAX(ID) FROM ST_FACULTY");
		Connection conn = null;
		Long pk = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getLong(1);
			}
			log.debug("FacultyModel.nextPk Success!!!");
		} catch (Exception e) {
			log.error("FacultyModel.nextPk Exception!!!");
			throw new DatabaseException("Error in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("FacultyModel.nextPk Closed!!!");
		return pk + 1;
	}

	public void update(FacultyBean bean) throws Exception {
		log.debug("FacultyModel.update Started!!!");
		StringBuffer sql = new StringBuffer(
				"UPDATE ST_FACULTY SET FIRST_NAME=?,LAST_NAME=?,GENDER=?,DOJ=?,QUALIFICATION=?,EMAIL_ID=?,MOBILE_NO=?,COLLEGE_ID=?,COLLEGE_NAME=?,COURSE_ID=?,COURSE_NAME=?,SUBJECT_ID=?,SUBJECT_NAME=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
		Connection conn = null;
		CollegeModel collegeModel = new CollegeModel();
		CollegeBean collegeBean = collegeModel.findByPk(bean.getCollegeId());
		bean.setCollegeName(collegeBean.getName());

		CourseModel courseModel = new CourseModel();
		CourseBean courseBean = courseModel.findByPk(bean.getCollegeId());
		bean.setCourseName(courseBean.getCourseName());

		SubjectModel subjectModel = new SubjectModel();
		SubjectBean subjectBean = subjectModel.findByPk(bean.getSubjectId());
		bean.setSubjectName(subjectBean.getSubjectName());
		if (bean.getFirstName() == null) {
			throw new ApplicationException("User not Found");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setString(1, bean.getFirstName());
			ps.setString(2, bean.getLastName());
			ps.setString(3, bean.getGender());
			ps.setDate(4, new Date(bean.getdOJ().getTime()));
			ps.setString(5, bean.getQualification());
			ps.setString(6, bean.getEmailId());
			ps.setString(7, bean.getMobileNo());
			ps.setLong(8, bean.getCollegeId());
			ps.setString(9, bean.getCollegeName());
			ps.setLong(10, bean.getCourseId());
			ps.setString(11, bean.getCourseName());
			ps.setLong(12, bean.getSubjectId());
			ps.setString(13, bean.getSubjectName());
			ps.setString(14, bean.getCreatedBy());
			ps.setString(15, bean.getModifiedBy());
			ps.setTimestamp(16, bean.getCreatedDatetime());
			ps.setTimestamp(17, bean.getModifiedDatetime());
			ps.setLong(18, bean.getId());

			ps.executeUpdate();
			conn.commit();
			log.debug("FacultyModel.Update Success!!!");
			ps.close();
		} catch (Exception e) {
			log.error("FacultyModel.Update Exception");
			throw new DatabaseException("Exception in Update");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("FacultyModel.Update Closed!!!");
	}

	public FacultyBean findByPk(long id) throws Exception {
		log.debug("FacultyModel.findByPk Started!!!");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_FACULTY WHERE ID = ?");
		FacultyBean bean = new FacultyBean();
		Connection conn = null;
		if (id == 0) {
			System.err.println("Please Enter id");
			System.exit(1);
		} else {
			try {
				conn = JDBCDataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql.toString());
				ps.setLong(1, id);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					bean.setId(rs.getLong(1));
					bean.setFirstName(rs.getString(2));
					bean.setLastName(rs.getString(3));
					bean.setGender(rs.getString(4));
					bean.setdOJ(rs.getDate(5));
					bean.setQualification(rs.getString(6));
					bean.setEmailId(rs.getString(7));
					bean.setMobileNo(rs.getString(8));
					bean.setCollegeId(rs.getInt(9));
					bean.setCollegeName(rs.getString(10));
					bean.setCourseId(rs.getInt(11));
					bean.setCourseName(rs.getString(12));
					bean.setSubjectId(rs.getInt(13));
					bean.setSubjectName(rs.getString(14));
					bean.setCreatedBy(rs.getString(15));
					bean.setModifiedBy(rs.getString(16));
					bean.setCreatedDatetime(rs.getTimestamp(17));
					bean.setModifiedDatetime(rs.getTimestamp(18));
				}
				if (bean.getFirstName() == null) {
					System.out.println("User Not Found");
					throw new RecordNotFoundException("Record Not Found!!!");
				}
				log.debug("FacultyModel.findByPk Success!!!");
			} catch (Exception e) {
				throw new DatabaseException("Exception in findByPk");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
		}
		log.debug("FacultyModel.findByPk Closed!!!");
		return bean;
	}

	public FacultyBean findByName(String name) throws Exception {
		log.debug("FacultyModel.findByName Started!!!");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_FACULTY WHERE FIRST_NAME = ?");
		FacultyBean bean = new FacultyBean();
		Connection conn = null;
		if (name == null) {
			System.err.println("Please Enter First_Name");
			System.exit(1);
		} else {
			try {
				conn = JDBCDataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql.toString());
				ps.setString(1, name);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					bean.setId(rs.getLong(1));
					bean.setFirstName(rs.getString(2));
					bean.setLastName(rs.getString(3));
					bean.setGender(rs.getString(4));
					bean.setdOJ(rs.getDate(5));
					bean.setQualification(rs.getString(6));
					bean.setEmailId(rs.getString(7));
					bean.setMobileNo(rs.getString(8));
					bean.setCollegeId(rs.getInt(9));
					bean.setCollegeName(rs.getString(10));
					bean.setCourseId(rs.getInt(11));
					bean.setCourseName(rs.getString(12));
					bean.setSubjectId(rs.getInt(13));
					bean.setSubjectName(rs.getString(14));
					bean.setCreatedBy(rs.getString(15));
					bean.setModifiedBy(rs.getString(16));
					bean.setCreatedDatetime(rs.getTimestamp(17));
					bean.setModifiedDatetime(rs.getTimestamp(18));
				}
				if (bean.getMobileNo() == null) {
					throw new RecordNotFoundException("Record Not Found");
				}
			} catch (Exception e) {
				throw new DatabaseException("Exception in findByName");
			} finally {
				JDBCDataSource.closeConnection(conn);
				log.debug("FacultyModel.findByName Closed!!!");
			}
		}

		return bean;
	}

	public FacultyBean findByEmail(String email) throws Exception {
		log.debug("FacultyModel.findByEmail Started!!!");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_FACULTY WHERE EMAIL_ID = ?");
		FacultyBean bean = new FacultyBean();
		Connection conn = null;
		if (email == null) {
			throw new ApplicationException("Please Enter Email");
		} else {
			try {
				conn = JDBCDataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql.toString());
				ps.setString(1, email);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					bean.setId(rs.getLong(1));
					bean.setFirstName(rs.getString(2));
					bean.setLastName(rs.getString(3));
					bean.setGender(rs.getString(4));
					bean.setdOJ(rs.getDate(5));
					bean.setQualification(rs.getString(6));
					bean.setEmailId(rs.getString(7));
					bean.setMobileNo(rs.getString(8));
					bean.setCollegeId(rs.getInt(9));
					bean.setCollegeName(rs.getString(10));
					bean.setCourseId(rs.getInt(11));
					bean.setCourseName(rs.getString(12));
					bean.setSubjectId(rs.getInt(13));
					bean.setSubjectName(rs.getString(14));
					bean.setCreatedBy(rs.getString(15));
					bean.setModifiedBy(rs.getString(16));
					bean.setCreatedDatetime(rs.getTimestamp(17));
					bean.setModifiedDatetime(rs.getTimestamp(18));
				}
				if (bean.getFirstName() == null) {
					System.out.println("User Not Found");
					throw new RecordNotFoundException("Record Not Found!!!");
				}
			} catch (Exception e) {
				log.debug("FacultyModel.findByEmail Exception!!!");
				throw new DatabaseException("Exception in findByEmail");
			} finally {
				JDBCDataSource.closeConnection(conn);
				log.debug("FacultyModel.findByEmail Closed!!!");
			}
		}

		return bean;
	}

	public void delete(FacultyBean bean) throws Exception {
		log.debug("FacultyModel.delete Started!!!");
		StringBuffer sql = new StringBuffer("DELETE FROM ST_FACULTY WHERE ID = ?");
		Connection conn = null;
		if (bean.getId() == 0) {
			throw new ApplicationException("Enter Id");
		} else {
			bean = findByPk(bean.getId());
			if (bean.getFirstName() == null) {
				throw new RecordNotFoundException("Record Not Found");
			}
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, bean.getId());

			ps.executeUpdate();
			conn.commit();
			log.debug("FacultyModel.delete Success!!!");
		} catch (Exception e) {
			log.error("FacultyModel.delete Exception!!!");
			throw new DatabaseException("Exception in delete");
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("FacultyModel.delete Closed!!");
		}
	}

	public List<FacultyBean> list() throws Exception {
		log.debug("FacultyModel.list null argument Started!!!");
		return list(0, 0);
	}

	public List<FacultyBean> list(int pageNo, int pageSize) throws Exception {
		log.debug("FacultyModel.list Started!!!");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_FACULTY");
		ArrayList<FacultyBean> list = new ArrayList<FacultyBean>();
		Connection conn = null;

		// if page is greater than zero then apply pagination
		if (pageSize > 0) {
			sql.append(" LIMIT " + pageNo + " , " + pageSize);
		}
		log.debug("FacultyModel.list Query Success!!!");
		try {
			System.out.println(sql);
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				FacultyBean bean = new FacultyBean();

				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setGender(rs.getString(4));
				bean.setdOJ(rs.getDate(5));
				bean.setQualification(rs.getString(6));
				bean.setEmailId(rs.getString(7));
				bean.setMobileNo(rs.getString(8));
				bean.setCollegeId(rs.getInt(9));
				bean.setCollegeName(rs.getString(10));
				bean.setCourseId(rs.getInt(11));
				bean.setCourseName(rs.getString(12));
				bean.setSubjectId(rs.getInt(13));
				bean.setSubjectName(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));

				list.add(bean);
			}
			rs.close();
			ps.close();
			log.debug("FacultyModel.list Success!!!");
		} catch (Exception e) {
			log.error("FacultyModel.list Exception!!!");
			throw new DatabaseException("Error in List of Faculty Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("FacultyModel.list Closed!!!");
		}
		return list;
	}

	public List<FacultyBean> Search(FacultyBean bean) throws Exception {
		log.debug("FacultyModel.list having only bean not Page No and Page Size");
		return search(bean, 0, 0);
	}

	public List<FacultyBean> search(FacultyBean bean, int pageNo, int pageSize) throws Exception {
		log.debug("FacultyModel.search method Started!!!!");

				StringBuffer sql = new StringBuffer("SELECT * FROM ST_FACULTY WHERE 1=1");
				if (bean != null) {
					if (bean.getId() > 0) {
						sql.append(" AND id = " + bean.getId());
					}
					if (bean.getCourseId() > 0) {
						sql.append(" AND college_Id = " + bean.getCourseId());
					}
					if (bean.getFirstName() != null && bean.getFirstName().trim().length() > 0) {
						sql.append(" AND First_Name like '" + bean.getFirstName() + "%' ");
					}
					if (bean.getLastName() != null && bean.getLastName().trim().length() > 0) {
						sql.append(" AND LAST_NAME like '" + bean.getLastName() + "%' ");
					}

					if (bean.getEmailId() != null && bean.getEmailId().length() > 0) {
						sql.append(" AND Email_Id like '" + bean.getEmailId() + "%' ");
					}

					if (bean.getGender() != null && bean.getGender().length() > 0) {
						sql.append(" AND Gender like '" + bean.getGender() + "%' ");
					}

					if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
						sql.append(" AND Mobile_No like '" + bean.getMobileNo() + "%' ");
					}

					if (bean.getCollegeName() != null && bean.getCollegeName().length() > 0) {
						sql.append(" AND college_Name like '" + bean.getCollegeName() + "%' ");
					}
					if (bean.getCourseId() > 0) {
						sql.append(" AND course_Id = " + bean.getCourseId());
					}
					if (bean.getCollegeName() != null && bean.getCollegeName().length() > 0) {
						sql.append(" AND course_Name like '" + bean.getCollegeName() + "%' ");
					}
					if (bean.getSubjectId() > 0) {
						sql.append(" AND Subject_Id = " + bean.getSubjectId());
					}
					if (bean.getSubjectName() != null && bean.getSubjectName().length() > 0) {
						sql.append(" AND subject_Name like '" + bean.getSubjectName() + "%' ");
					}
				}

				// if page no is greater then zero then apply pagination
				System.out.println("model page ........" + pageNo + " " + pageSize);
				if (pageSize > 0) {
					pageNo = (pageNo - 1) * pageSize;
					sql.append(" limit " + pageNo + " , " + pageSize);
				}

				Connection conn = null;
				ArrayList list = new ArrayList();
				try {
					System.out.println(sql);
					conn = JDBCDataSource.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql.toString());
					ResultSet rs = pstmt.executeQuery();
					while (rs.next()) {
						bean = new FacultyBean();

						bean.setId(rs.getLong(1));
						bean.setFirstName(rs.getString(2));
						bean.setLastName(rs.getString(3));
						bean.setGender(rs.getString(4));
						bean.setdOJ(rs.getDate(5));
						bean.setQualification(rs.getString(6));
						bean.setEmailId(rs.getString(7));
						bean.setMobileNo(rs.getString(8));
						bean.setCollegeId(rs.getInt(9));
						bean.setCollegeName(rs.getString(10));
						bean.setCourseId(rs.getInt(11));
						bean.setCourseName(rs.getString(12));
						bean.setSubjectId(rs.getInt(13));
						bean.setSubjectName(rs.getString(14));
						bean.setCreatedBy(rs.getString(15));
						bean.setModifiedBy(rs.getString(16));
						bean.setCreatedDatetime(rs.getTimestamp(17));
						bean.setModifiedDatetime(rs.getTimestamp(18));

						list.add(bean);
					}
					
					rs.close();

				} catch (Exception e) {
					e.printStackTrace();
					// log.error("database Exception .. " , e);
					e.printStackTrace();
					throw new ApplicationException("Exception : Exception in Search method of Faculty Model");
				} finally {
					JDBCDataSource.closeConnection(conn);
				}
				log.debug("Faculty Model search method End");
				return list;

	}
}
