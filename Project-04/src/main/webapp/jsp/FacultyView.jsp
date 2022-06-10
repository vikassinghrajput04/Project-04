
<%@page import="com.rays.ctl.FacultyCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.rays.utility.HTMLUtility"%>
<%@page import="com.rays.utility.DataUtility"%>
<%@page import="com.rays.utility.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="com.rays.beans.SubjectBean"%>
<%@page import="com.rays.beans.CourseBean"%>
<%@page import="com.rays.beans.CollegeBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<title> Faculty Registration Page</title>


<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 <!-- <script>
	$(function() {
		$("#datepicker").datepicker({
			dateFormat : 'dd/MM/yy',
			changeMonth : true,
			changeYear : true,
			yearRange : "1999:2022",
		maxDate:'0',
		 minDate:0
		yearRange : "-40:-0"
		});
	});
</script>  -->

</head>

<body>
	<jsp:useBean id="bean" class="com.rays.beans.FacultyBean" scope="request"></jsp:useBean>
	<form action="<%=ORSView.FACULTY_CTL%>" method="post">
		<%@include file="Header.jsp"%>

		<%
			List<CollegeBean> colist = (List<CollegeBean>) request.getAttribute("CollegeList");
			List<CourseBean> clist = (List<CourseBean>) request.getAttribute("CourseList");
			List<SubjectBean> slist = (List<SubjectBean>) request.getAttribute("SubjectList");
		%>

		<center>
			<h1>
			<% if(bean != null && bean.getId() >0) {%>
				<tr><th> Update Faculty </th></tr>
			<%}else{ %>
			<tr><th> Add Faculty </th></tr>
			<%} %>
			</h1>
			<div>
				<h2><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
				</h2>
			</div>
			
			<input type="hidden" name="id" value=<%=bean.getId()%>> <input
				type="hidden" name="createdby" value=<%=bean.getCreatedBy()%>>
			<input type="hidden" name="modifiedby"
				value=<%=bean.getModifiedBy()%>> <input type="hidden"
				name="createdDatetime"
				value=<%=DataUtility.getStringData(bean.getCreatedDatetime())%>>
			<input type="hidden" name="modifiedDatetime"
				value=<%=DataUtility.getStringData(bean.getModifiedDatetime())%>>


			<table>

				<tr>
					<th align="left" >CollegeName <span style="color: red">*</span></th>
					<td ><%=HTMLUtility.getList("collegeid", String.valueOf(bean.getCollegeId()), colist)%>
					</td><td><td style="position: fixed">
					<font color="red"><%=ServletUtility.getErrorMessage("collegeid", request)%></font>
					
					</td>
					
				</tr>
				<tr><th style="padding: 3px"></th></tr>
<tr>
					<th align="left">CourseName <span style="color: red">*</span></th>
					<td><%=HTMLUtility.getList("courseid", String.valueOf(bean.getCourseId()), clist)%>
					</td><td><td style="position: fixed">
						<font color="red"><%=ServletUtility.getErrorMessage("courseid", request)%></font>
					
				
					</td>
				</tr>
<tr><th style="padding: 3px"></th></tr> 
				<tr>
					<th align="left">SubjectName <span style="color: red">*</span></th>
					<td><%=HTMLUtility.getList("subjectid", String.valueOf(bean.getSubjectId()), slist)%>
					</td><td><td style="position: fixed">
					
					<font color="red"><%=ServletUtility.getErrorMessage("subjectid", request)%></font>
					</td>
				</tr>
<tr><th style="padding: 3px"></th></tr> 

				<tr>
					<th align="left">FirstName <span style="color: red">*</span>
					</th>
					<td><input type="text" name="firstname"
						placeholder=" Enter First Name" size="23" 
						value="<%=DataUtility.getStringData(bean.getFirstName())%>">
						</td><td><td style="position: fixed">
				
				<font color="red"><%=ServletUtility.getErrorMessage("firstname", request)%></font>
					</td>
				</tr>
<tr><th style="padding: 3px"></th></tr> 
				<tr>
					<th align="left">LastName <span style="color: red">*</span></th>
					<td><input type="text" name="lastname"
						placeholder=" Enter last Name" size="23"
						value="<%=DataUtility.getStringData(bean.getLastName())%>"></td><td><td style="position: fixed">
					
					 <font color="red"><%=ServletUtility.getErrorMessage("lastname", request)%></font>
					</td>
				</tr>
<tr><th style="padding: 3px"></th></tr> 
								<tr>
					<th align="left">LoginId <span style="color: red">*</span></th>
					<td><input type="text" name="loginid"
						placeholder=" Enter Login Id" size="23"
						value="<%=DataUtility.getStringData(bean.getEmailId())%>"></td><td><td style="position: fixed">
					
					<font color="red"><%=ServletUtility.getErrorMessage("loginid", request)%></font>
					</td>
				</tr>
<tr><th style="padding: 3px"></th></tr> 
				
				<tr>
					<th align="left">Gender <span style="color: red">*</span></th>
					<td>
							<%
							HashMap map = new HashMap();
							map.put("Male", "Male");
							map.put("Female", "Female");

							String hlist = HTMLUtility.getList("gender", String.valueOf(bean.getGender()), map);
						%> <%=hlist%></td><td><td style="position: fixed">
					<font color="red"><%=ServletUtility.getErrorMessage("gender", request)%></font>
					</td>
				</tr>
<tr><th style="padding: 3px"></th></tr> 
				<tr>
					<th align="left">Date Of Joining<font color="red">*</font></th>
					<td ><input type="text"  name="doj"  id="datepicker"size="23"
						placeholder="Enter Date Of joining" readonly="readonly"
						
						  value="<%=DataUtility.getDateString(bean.getdOJ())%>"></td><td><td style="position: fixed">
						&nbsp;<font style=";" color="red"> <%=ServletUtility.getErrorMessage("doj", request)%></font></td>
 				</tr> 
<tr><th style="padding: 3px"></th></tr> 

				<tr>
					<th align="left">Qualification <span style="color: red">*</span></th>
					<td><input type="text" name="qualification" size="23"
						placeholder=" Enter Qualification"
						value="<%=DataUtility.getStringData(bean.getQualification())%>">
						</td><td><td style="position: fixed">
					<font color="red"><%=ServletUtility.getErrorMessage("qualification", request)%></font>
					</td>
				</tr>
<tr><th style="padding: 3px"></th></tr> 

				<tr>
					<th align="left">MobileNo <span style="color: red">*</span></th>
					<td><input type="text" name="mobileno" size="23" maxlength="10"
						placeholder=" Enter Mobile No"
						value="<%=DataUtility.getStringData(bean.getMobileNo())%>"></td><td><td style="position: fixed">
					<font color="red"><%=ServletUtility.getErrorMessage("mobileno", request)%></font>
					</td>
				</tr>
<tr><th style="padding: 3px"></th></tr> 
				
				<tr>
					<th></th>
					<% if(bean.getId() >  0){%>
					
					<td>
					<input type="submit" name="operation" value="<%=FacultyCtl.OP_UPDATE%>"> 
					 <input type="submit" name="operation" value="<%=FacultyCtl.OP_CANCEL%>">
					</td>
					<%}else{ %>
					<td> 
					<input type="submit" name="operation" value="<%=FacultyCtl.OP_SAVE%>"> 
					 <input type="submit" name="operation" value="<%=FacultyCtl.OP_RESET%>">
					</td>					
					<% } %>
				</tr>
			</table>
		</center>
	</form>
	<br>
	<br>
	<br>
	
	
	
	<%@include file="Footer.jsp"%>
</body>
</html>