
<%@page import="com.rays.ctl.ChangePasswordCtl"%>
<%@page import="com.rays.utility.DataUtility"%>
<%@page import="com.rays.utility.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change password</title>

<style>
.p1 {
	font-size: 18px;
}

.p2 {
	padding: 5px;
	margin: 3px;
}
</style>
</head>
<body >
	<center>
		<form action="<%=ORSView.CHANGE_PASSWORD_CTL%>" method="POST">
			<%@include file="Header.jsp"%>
			<jsp:useBean id="bean" class="com.rays.beans.UserBean"
				scope="request"></jsp:useBean>

			<h1 style="font-size: 30px;">Change Password</h1>
			<h2>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h2>
			<h2>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h2>
			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime" value="<%=bean.getCreatedDatetime()%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=bean.getModifiedDatetime()%>">

			<table>
				<tr>
					<th align="left" class="p1">Old Password<span
						style="color: red;">*</span></th>
					<td><input type="password" name="oldPassword" class="p2"
						size="30" placeholder="Enter Old Password"
						value=<%=DataUtility.getString(request.getParameter("oldPassword") == null ? ""
					: DataUtility.getString(request.getParameter("oldPassword")))%>></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("oldPassword", request)%></font>
					</td>
				</tr>
				<tr>
					<th align="left" class="p1">New Password<span
						style="color: red;">*</span></th>
					<td><input type="password" name="newPassword" class="p2"
						size="30" placeholder="Enter New Password"
						value=<%=DataUtility.getString(request.getParameter("newPassword") == null ? ""
					: DataUtility.getString(request.getParameter("newPassword")))%>></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("newPassword", request)%></font>
					</td>
				</tr>
				<tr>
					<th align="left" class="p1">Confirm Password<span
						style="color: red;">*</span></th>
					<td><input type="password" name="confirmPassword" class="p2"
						size="30" placeholder="Enter Confirm password"
						value=<%=DataUtility.getString(request.getParameter("confirmPassword") == null ? ""
					: DataUtility.getString(request.getParameter("confirmPassword")))%>></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("confirmPassword", request)%></font>
					</td>
				</tr>



				<tr>
					<th></th>
					<td colspan="2" align="center"><input type="submit"
						name="operation" value="<%=ChangePasswordCtl.OP_SAVE%>"
						style="padding: 5px;"> &nbsp; <input type="submit"
						name="operation"
						value="<%=ChangePasswordCtl.OP_CHANGE_MY_PROFILE%>"
						style="padding: 5px;"> &nbsp; &nbsp;<input type="submit"
						name="operation" value="<%=ChangePasswordCtl.OP_RESET%>"
						style="padding: 5px;""></td>
				</tr>
			</table>

			<%@include file="Footer.jsp"%>
		</form>
	</center>
</body>
</html>