package com.rays.utility;

import java.util.Date;

/**
 * This class validates input data
 * 
 * @author Vikas Singh
 * 
 */
public class DataValidator {

	public static boolean isNull(String val) {
		if (val == null || val.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotNull(String val) {
		return !isNull(val);
	}

	public static boolean isInteger(String val) {

		if (isNotNull(val)) {
			try {
				int i = Integer.parseInt(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	public static boolean isLong(String val) {
		if (isNotNull(val)) {
			try {

				long i = Long.parseLong(val);
				return true;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return false;
			}

		} else {
			System.out.println("dataValidator else");
			return false;
		}
	}

	public static boolean isEmail(String val) {

		String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		if (isNotNull(val)) {
			try {
				return val.matches(emailreg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	public static boolean isDate(String val) {

		System.out.println("date in validator-------------" + val);
		Date d = null;
		if (isNotNull(val)) {
			d = DataUtility.getDate(val);
		}
		return d != null;
	}

	public static void main(String[] args) {

		System.out.println("Not Null 2" + isNotNull("ABC"));
		System.out.println("Not Null 3" + isNotNull(null));
		System.out.println("Not Null 4" + isNull("123"));

		System.out.println("Is Int " + isInteger(null));
		System.out.println("Is Int " + isInteger("ABC1"));
		System.out.println("Is Int " + isInteger("123"));
		System.out.println("Is Int " + isNotNull("123"));
	}

	public static boolean isMobileNo(String val) {

		String mobreg = "^[6-9]{1}+[0-9]{9}$";

		if (isNotNull(val) && val.matches(mobreg)) {

			return true;
		} else {
			return false;
		}
	}

	public static boolean isValidName(String val) {

		String namereg = "^[a-zA-Z_-]+$";

		// String namereg = "^[^-\\s][\\p{L} .']+$";

		if (DataValidator.isNotNull(val)) {
			boolean check = val.matches(namereg);

			return check;
		} else {
			return false;
		}
	}

	public static boolean isvalidateAge(String val) {

		Date today = new Date();
		Date enterDate = DataUtility.getDate(val);

		int age = today.getYear() - enterDate.getYear();

		if (age > 18 && isNotNull(val)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * check if value is password.
	 *
	 * @param val the val
	 * @return true, if is password
	 */
	public static boolean isPassword(String val) {

		String pass = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,15})";

		if (DataValidator.isNotNull(pass)) {
			boolean check = val.matches(pass);
			return check;
		} else {
			return false;
		}
	}

	/**
	 * check if value is Roll no.
	 *
	 * @param val the val
	 * @return true, if is roll no
	 */
	public static boolean isRollNo(String val) {

		String roll = "^[0-9]{4}[A-Z]{2}[0-9]{2,6}$";

		if (DataValidator.isNotNull(roll)) {
			boolean check = val.matches(roll);
			return check;
		} else {
			return false;
		}
	}

	public static boolean isName(String val) {

		// String namereg = "^[a-zA-Z]+$";
		String namereg = "^[a-zA-Z_-]+$";
		//String namereg = "^[^-\\s][\\p{L} .']+$";

		// System.out.println(val);
		if (DataValidator.isNotNull(val)) {
			boolean check = val.matches(namereg);
			// System.out.println(check);
			return check;
		} else {
			return false;
		}
	}
}
