package com.rays.utility;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rays.beans.BaseBean;
import com.rays.ctl.BaseCtl;
import com.rays.ctl.ORSView;
import com.rays.modal.UserModel;

/**
 * This class provides utility operation for Servlet container like forward,
 * redirect, handle generic exception, manage success and error message, manage
 * default Bean and List, manage pagination parameters
 *
 * 
 */

public class ServletUtility {

	/**
	 * Forward to given JSP/Servlet
	 * 
	 * @param page reads
	 * @param request reads
	 * @param response reads
	 * @throws IOException exception
	 * @throws ServletException exception
	 */
	public static void forward(String page, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	/**
	 * Forward to Layout View
	 * @param page reads
	 * @param request reads
	 * @param response reads
	 * @throws IOException exception
	 * @throws ServletException exception
	 */
	public static void forwardView(String page, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		request.setAttribute("bodyPage", page);
		RequestDispatcher rd = request.getRequestDispatcher(ORSView.LAYOUT_VIEW);
		rd.forward(request, response);
	}

	/**
	 * Redirect to given JSP/Servlet
	 * @param page reads
	 * @param request reads
	 * @param response reads
	 * @throws IOException exception
	 * @throws ServletException exception
	 */ 
	public static void redirect(String page, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.sendRedirect(page);
	}

	/**
	 * Redirect to Application Error Handler Page
	 * 
	 * @param e reads
	 * @param request reads
	 * @param response reads
	 * @throws IOException exception
	 * @throws ServletException exception
	 */
	public static void handleException(Exception e, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setAttribute("exception", e);
		response.sendRedirect(ORSView.ERROR_CTL);

	}

	/**
	 * Gets error message from request
	 * @param property reads
	 * @param request reads
	 * @return returns val
	 */
	public static String getErrorMessage(String property, HttpServletRequest request) {

		String val = (String) request.getAttribute(property);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	/**
	 * returns all input error messages
	 * @param request reads
	 * @return values String
	 */
	public static String getErrorMessageHtml(HttpServletRequest request) {

		Enumeration<String> e = request.getAttributeNames();

		StringBuffer sb = new StringBuffer("<UL>");
		String name = null;

		while (e.hasMoreElements()) {
			name = e.nextElement();
			if (name.startsWith("error.")) {
				sb.append("<LI class='error'>" + request.getAttribute(name) + "</LI>");
			}
		}
		sb.append("</UL>");
		return sb.toString();
	}

	/**
	 * Gets a message from request
	 * @param property reads
	 * @param request reads
	 * @return values String
	 */
	public static String getMessage(String property, HttpServletRequest request) {
		String val = (String) request.getAttribute(property);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	/**
	 * Sets error message to request
	 * 
	 * @param msg reads
	 * @param request reads
	 */
	public static void setErrorMessage(String msg, HttpServletRequest request) {
		request.setAttribute(BaseCtl.MSG_ERROR, msg);
	}

	/**
	 * Gets error message from request
	 *
	 * 
	 * @param request reads
	 * @return values String
	 */
	public static String getErrorMessage(HttpServletRequest request) {
		String val = (String) request.getAttribute(BaseCtl.MSG_ERROR);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	/**
	 * Sets success message to request
	 * 
	 * 
	 * @param msg reads
	 * @param request reads
	 */ 
	public static void setSuccessMessage(String msg, HttpServletRequest request) {
		request.setAttribute(BaseCtl.MSG_SUCCESS, msg);
	}

	/**
	 * Gets success message from request
	 * 
	 * 
	 * @param request reads
	 * @return values String
	 */
	public static String getSuccessMessage(HttpServletRequest request) {
		String val = (String) request.getAttribute(BaseCtl.MSG_SUCCESS);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	
	/*
	 * public static void setModel(BaseModel model, HttpServletRequest request) {
	 * request.setAttribute("model", model); }
	 */

	/**
	 * Sets default Bean to request
	 *
	 * @param bean reads
	 * @param request reads
	 */
	public static void setBean(BaseBean bean, HttpServletRequest request) {
		request.setAttribute("bean", bean);
	}

	public static void setUserModel(UserModel model, HttpServletRequest request) {
		request.getSession().setAttribute("user", model);
	}

	/**
	 * Gets default bean from request
	 *
	 * 
	 * @param request reads
	 * @return values bean
	 */

	public static BaseBean getBean(HttpServletRequest request) {
		return (BaseBean) request.getAttribute("bean");
	}

	public static boolean isLoggedIn(HttpServletRequest request) {
		UserModel model = (UserModel) request.getSession().getAttribute("user");
		return model != null;
	}

	/**
	 * Returns logged in user role
	 *
	 * 
	 */
	/**
	 * gets Model from request scope
	 * 
	 * /* public static BaseModel getModel(HttpServletRequest request) { return
	 * (BaseModel) request.getAttribute("model"); }
	 */

	/**
	 * Get request parameter to display. If value is null then return empty string
	 *
	 */

	/**
	 * 
	 * @param property reads
	 * @param request reads
	 * @return values String
	 */
	public static String getParameter(String property, HttpServletRequest request) {
		String val = (String) request.getParameter(property);
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	/**
	 * Sets default List to request
	 * 
	 * 
	 * @param list reads 
	 * @param request reads
	 */
	public static void setList(List list, HttpServletRequest request) {
		request.setAttribute("list", list);
	}

	/**
	 * Gets default list from request
	 *  
	 * @param request reads
	 * @return values List 
	 */
	public static List getList(HttpServletRequest request) {
		return (List) request.getAttribute("list");
	}

	/**
	 * Sets Page Number for List pages
	 * 
	 * 
	 * @param pageNo reads
	 * @param request reads
	 */
	public static void setPageNo(int pageNo, HttpServletRequest request) {
		request.setAttribute("pageNo", pageNo);
	}

	/**
	 * Gets Page Number for List pages
	 * 
	 * 
	 * @param request reads
	 * @return values int
	 */
	public static int getPageNo(HttpServletRequest request) {
		return (Integer) request.getAttribute("pageNo");
	}

	/**
	 * Sets Page Size for list pages
	 * 
	 * @param pageSize reads
	 * @param request reads
	 */
	public static void setPageSize(int pageSize, HttpServletRequest request) {
		request.setAttribute("pageSize", pageSize);
	}

	/**
	 * Gets Page Size for List pages
	 * 
	 * 
	 * @param request reads
	 * @return values Int
	 */
	public static int getPageSize(HttpServletRequest request) {
		return (Integer) request.getAttribute("pageSize");
	}

}
