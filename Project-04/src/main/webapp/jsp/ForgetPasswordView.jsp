

<%@page import="com.rays.ctl.ForgetPasswordCtl"%>
<%@page import="com.rays.utility.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forget Password</title>
<style type="text/css">
#po1 {
	padding: 20px;
}

.p1 {
	font-size: 18px;
}

.p2 {
	padding: 5px;
	margin: 3px;
}
</style>

</head>
<body>
	<form action="<%=ORSView.FORGET_PASSWORD_CTL%>" method="post">
		<%@include file="Header.jsp"%>
		<jsp:useBean id="bean" class="com.rays.beans.UserBean" scope="request"></jsp:useBean>

		<center>
			<h1 style="font-size: 40px;">Forget Your Password?</h1>
			<input type="hidden" name="id" value="<%=bean.getId()%>">
			<table>
				<tr>
					<td><font color="blue">Submit your Email Address and we
							will Send You password </font></td>
				</tr>
			</table>
			<br> <br>

			<h1><div align="center">
				<font color="green" size="5px"> <%=ServletUtility.getSuccessMessage(request)%></font>
				<font color="red" size="5px"> <%=ServletUtility.getErrorMessage(request)%></font>
			</div></h1>
			<table>
				<tr>
					<th class="p1">Email Id <span style="color: red;">*</span></th>&emsp;
					<td><input type="text" size="40" name="login" class="p2"
						placeholder="Enter ID Here"
						value="<%=ServletUtility.getParameter("login", request)%>">&emsp;

					
					<td style="position: fixed; padding-top: 10px;"><font
						color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font>
					</td>
				</tr>

				<tr>
					<th style="padding: 3px"></th>
					<td><input type="submit" name="operation"
						value="<%=ForgetPasswordCtl.OP_GO%>" style="padding: 3px;">
						<input type="submit" name="operation"
						value="<%=ForgetPasswordCtl.OP_RESET%>" style="padding: 3px;"><br>
						</td>
				</tr>
			</table>
	</form>
	</center>
	<%@include file="Footer.jsp"%>
</body>
</html>