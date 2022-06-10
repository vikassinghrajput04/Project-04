package com.rays.ctl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rays.utility.ServletUtility;
/**
 * 
 * @author Vikas Singh
 *
 */
@WebServlet(name = "WelcomeCtl", urlPatterns = "/WelcomeCtl")
public class WelcomeCtl extends BaseCtl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ServletUtility.forward(getView(), req, resp);

	}

	@Override
	protected String getView() {
		return ORSView.WELCOME_VIEW;
	}

}
