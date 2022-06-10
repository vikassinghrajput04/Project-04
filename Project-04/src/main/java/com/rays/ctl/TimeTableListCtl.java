package com.rays.ctl;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.rays.beans.BaseBean;
import com.rays.beans.CourseBean;
import com.rays.beans.SubjectBean;
import com.rays.beans.TimetableBean;
import com.rays.modal.CourseModel;
import com.rays.modal.SubjectModel;
import com.rays.modal.TimetableModel;
import com.rays.utility.DataUtility;
import com.rays.utility.PropertyReader;
import com.rays.utility.ServletUtility;

/**
 * Timetable List functionality Controller. Performs operation for list, search
 * and delete operations of Timetable
 *
 * @author Vikas Singh
 * 
 */
@WebServlet(name = "TimeTableListCtl", urlPatterns = "/ctl/TimeTableListCtl")
public class TimeTableListCtl extends BaseCtl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(TimeTableListCtl.class);

	protected void preload(HttpServletRequest request) {

		CourseModel crsm = new CourseModel();
		SubjectModel stm = new SubjectModel();
		List<CourseBean> list = null;
		List<SubjectBean> list2 = null;
		try {
			list = crsm.list();
			list2 = stm.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("courseList", list);
		request.setAttribute("subjectList", list2);

	}

	protected BaseBean populateBean(HttpServletRequest request) {
		TimetableBean tb = new TimetableBean();

//		bean.setId(DataUtility.getLong(request.getParameter("id")));
		tb.setCourseId(DataUtility.getInt(request.getParameter("clist")));
		tb.setSubjectId(DataUtility.getInt(request.getParameter("slist")));
		// bean.setSubjectName(DataUtility.getString(request.getParameter("slist")));
		tb.setExamDate(DataUtility.getDate(request.getParameter("Exdate")));
		// System.out.println("populate bean==========>>>> " + bean.getExamDate());
		populateDTO(tb, request);
		return tb;
	}

	/**
	 * Contains display logics
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List list;

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		TimetableModel tm = new TimetableModel();
		TimetableBean tb = (TimetableBean) populateBean(request);

//		String op = DataUtility.getString(request.getParameter("operation"));
		// String[] ids = request.getParameterValues("ids");

		try {
			list = tm.search(tb, pageNo, pageSize);
			ServletUtility.setBean(tb, request);

			// ServletUtility.setList(list, request);
			if (list == null && list.size() == 0) {
				ServletUtility.setErrorMessage("No record Found", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			ServletUtility.handleException(e, request, response);
		}
	}

	/**
	 * Contains Submit logics
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List list;
		String op = DataUtility.getString(request.getParameter("operation"));

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		TimetableBean bean = (TimetableBean) populateBean(request);
		TimetableModel model = new TimetableModel();
		String[] ids = (String[]) request.getParameterValues("ids");

		if (OP_SEARCH.equalsIgnoreCase(op)) {
			pageNo = 1;
		} else if (OP_NEXT.equalsIgnoreCase(op)) {
			pageNo++;
		} else if (OP_PREVIOUS.equalsIgnoreCase(op)) {
			if (pageNo < 1) {
				pageNo--;
			} else {
				pageNo = 1;
			}
		} else if (OP_NEW.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.TIMETABLE_CTL, request, response);
			return;
		}

		else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
			return;
		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			pageNo = 1;
			if (ids != null && ids.length > 0) {
				TimetableBean bean3 = new TimetableBean();

				for (String id2 : ids) {
					int id1 = DataUtility.getInt(id2);
					bean3.setId(id1);
					try {
						model.delete(bean3);
					} catch (Exception e) {
						e.printStackTrace();
						ServletUtility.handleException(e, request, response);
						return;
					}
					ServletUtility.setSuccessMessage("Data Deleted Succesfully", request);
				}

			} else {
				ServletUtility.setErrorMessage("Select at least one Record", request);
			}
		}
		try {
			list = model.search(bean, pageNo, pageSize);
			ServletUtility.setBean(bean, request);
		} catch (Exception e) {
			ServletUtility.handleException(e, request, response);
			return;
		}
		if (list == null || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op)) {
			ServletUtility.setErrorMessage("No Record Found", request);
		}
		ServletUtility.setBean(bean, request);
		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.TIMETABLE_LIST_VIEW;
	}

}
