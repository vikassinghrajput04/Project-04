package com.rays.utility;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.rays.beans.DropdownListBean;

/**
 * HTML Utility class to produce HTML contents like Dropdown List.
 *
 *@author Vikas Singh
 * 
 */
public class HTMLUtility {
/**
 * 
 * @param name reads name
 * @param selectedVal reads selected Val
 * @param map reads map
 * @return msg String value
 */
	public static String getList(String name, String selectedVal, HashMap<String, String> map) {

		StringBuffer sb = new StringBuffer(
				"<select style='width: 200px;  height: 23px;' class='form-control' name='" + name + "'>");

		Set<String> keys = map.keySet();
		String val = null;
		boolean select = true;
		if (select) {

			sb.append(
					"<option style='width: 500px;  height: 30px;' selected value=''>--------------Select--------------------</option>");
		}

		System.out.println("htmlllll    " + selectedVal);
		for (String key : keys) {
			val = map.get(key);
			if (key.trim().equals(selectedVal)) {
				sb.append("<option selected value='" + val + "'>" + val + "</option>");
			} else {
				sb.append("<option value='" + key + "'>" + val + "</option>");
			}
		}

		sb.append("</select>");
		System.out.println("get list 1=========" + sb.toString());

		return sb.toString();
	}
	/**
	 * 
	 * @param name reads
	 * @param selectedVal reads
	 * @param list reads
	 * @return values String
	 */
		public static String getList(String name, String selectedVal, List list) {

			Collections.sort(list);
			StringBuffer sb = new StringBuffer(
					"<select style='width: 200px;  height: 23px;' class='form-control' name='" + name + "'>");

			boolean select = true;
			if (select) {

				sb.append(
						"<option style='width: 203px;  height: 30px;' selected value=''>--------------Select--------------------`</option>");
			}

			List<DropdownListBean> dd = (List<DropdownListBean>) list;

			// StringBuffer sb = new StringBuffer( "<select style='width: 163px; height:
			// 23px;' class='form-control' name='" + name + "'>");

			String key = null;
			String val = null;

			for (DropdownListBean obj : dd) {
				key = obj.getKey();
				val = obj.getValue();

				if (key.trim().equals(selectedVal)) {
					sb.append("<option selected value='" + key + "'>" + val + "</option>");
				} else {
					sb.append("<option value='" + key + "'>" + val + "</option>");
				}
			}
			sb.append("</select>");
			System.out.println("get list 2=========" + sb.toString());
			return sb.toString();
		}
/**
 * 
 * @param request reads
 * @return values String
 */
	public static String getErrorMessage(HttpServletRequest request) {
		String msg = ServletUtility.getErrorMessage("Error", request);
		if (!DataValidator.isNull(msg)) {
			msg = "<p class='st-error-header'>" + msg + "</p>";
		}
		return msg;
	}

	public static String getSuccessMessage(HttpServletRequest request) {
		String msg = ServletUtility.getSuccessMessage(request);
		if (!DataValidator.isNull(msg)) {
			msg = "<p class='st-success-header'>" + msg + "</p>";
		}
		return msg;
	}
/**
 * 
 * @param label reads
 * @param access reads
 * @param request reads
 * @return values String
 */
	public static String getSubmitButton(String label, boolean access, HttpServletRequest request) {

		String button = "";

		if (access) {
			button = "<input type='submit' name='operation'    value='" + label + "' >";
		}
		return button;
	}

	/*
	 * public static String getCommonFields(HttpServletRequest request) {
	 * 
	 * BaseModel model = ServletUtility.getModel(request);
	 * 
	 * StringBuffer sb = new StringBuffer();
	 * 
	 * sb.append("<input type='hidden' name='id' value=" + model.getId() + ">");
	 * 
	 * return sb.toString(); }
	 */}