
<%@page import="com.rays.ctl.UserRegistrationCtl"%>
<%@page import="com.rays.utility.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.rays.utility.DataUtility"%>
<%@page import="com.rays.utility.ServletUtility"%>
<html>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<head>
<title>User Registration</title>
</head>

<body>
	<form action="<%=ORSView.USER_REGISTRATION_CTL%>" method="post">

		<%@ include file="Header.jsp"%>
		<script type="text/javascript" src="./js/calendar.js"></script>
		<jsp:useBean id="bean" class="com.rays.beans.UserBean" scope="request"></jsp:useBean>

		<center>
			<h1>User Registration</h1>

			<H2>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</H2>
			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H2>

			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">


			<table>

				<tr>
					<th align="left">First Name <font color="red">*</font></th>
					<td><input type="text" name="firstName" size="23"
						placeholder="Enter  First_Name"
						value="<%=DataUtility.getStringData(bean.getFirstName())%>"></td>
					<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Last Name <font color="red">*</font></th>
					<td><input type="text" name="lastName" size="23"
						placeholder="Enter  Last_Name"
						value="<%=DataUtility.getStringData(bean.getLastName())%>"></td>
					<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
				</tr>
				<tr>
					<th align="left">LoginId <font color="red">*</font></th>
					<td><input type="text" name="login" size="23"
						placeholder="Must be Email ID"
						value="<%=DataUtility.getStringData(bean.getLogin())%>"></td>
					<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Password <font color="red">*</font></th>
					<td><input type="password" name="password" size="23"
						placeholder="Enter  password_"
						value="<%=DataUtility.getStringData(bean.getPassword())%>"></td>
					<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Confirm Password <font color="red">*</font></th>
					<td><input type="password" name="confirmPassword" size="23"
						placeholder="Enter  confirm_password"
						value="<%=DataUtility.getStringData(bean.getConfirmPassword())%>"></td>
					<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Mobile no. <font color="red">*</font></th>
					<td><input type="text" name="mobileNo" size="23"
						placeholder="Enter  Mobile_no" maxlength="10"
						value="<%=DataUtility.getStringData(bean.getMobileNo())%>"></td>
					<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Gender<font color="red">*</font></th>
					<td>
						<%
							HashMap<String, String> map = new HashMap<String, String>();
							map.put("M", "Male");
							map.put("F", "Female");

							String htmlList = HTMLUtility.getList("gender", bean.getGender(), map);
						%> <%=htmlList%>
					</td>
					<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("gender", request)%></font></td>
				</tr>

				<tr>
					<th align="left">Date Of Birth <font color="red">*</font></th>
					<td><input type="text" name="dob" readonly="readonly"
						id="datepicker" size="23"
						placeholder="Enter Date Of Birth_ (mm/dd/yy)"
						value="<%=DataUtility.getDateString(bean.getDob())%>"></td>
					<td style="position: fixed"><a
						href="javascript:getCalendar(document.forms[0].dob);"> 
					</a><font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
				</tr>
				<tr>
					<th></th>
					<td colspan="2">&nbsp; &nbsp; 
						&nbsp; <input type="submit" name="operation"
						value="<%=UserRegistrationCtl.OP_SIGN_UP%>"> &nbsp; &nbsp; &nbsp; &nbsp;<input
						type="submit" name="operation"
						value="<%=UserRegistrationCtl.OP_RESET%>">
					</td>
				</tr>
			</table>
	</form>
	<br>


	<br>
	<br>
	<br>
	<br>
	</center>
	<%@ include file="Footer.jsp"%>
</body>
</html>