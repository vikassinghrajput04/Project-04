
<%@page import="com.rays.modal.MarksheetModel"%>
<%@page import="com.rays.utility.DataUtility"%>
<%@page import="com.rays.ctl.MarksheetListCtl"%>
<%@page import="com.rays.utility.HTMLUtility"%>
<%@page import="com.rays.beans.MarksheetBean"%>
<%@page import="com.rays.utility.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<html>
<head>
<title>MarksheetList View</title>
</head>
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script src="<%=ORSView.APP_CONTEXT%>/js/Checkbox11.js"></script>
<body>
	<jsp:useBean id="bean" class="com.rays.beans.MarksheetBean"
		scope="request"></jsp:useBean>
	<form action="<%=ORSView.MARKSHEET_LIST_CTL%>" method="POST">
		<%@include file="Header.jsp"%>

		<%
			List mlist = (List) request.getAttribute("mlist");
		%>






		<div align="center">
			<h1>Marksheet List</h1>
			<h3>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h3>
			<h3>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h3>
		</div>
		<%
			int pageNo = ServletUtility.getPageNo(request);
			int pageSize = ServletUtility.getPageSize(request);
			int index = (((pageNo - 1) * pageSize) + 1);

			List list = ServletUtility.getList(request);
		%>


		<table width="100%" align="center">
			<tr>
				<td align="center"><label><font> Student Name :</font></label>
					<%=HTMLUtility.getList("name", String.valueOf(bean.getName()), mlist)%>
					&emsp; <label>RollNo :</label> <input type="text" name="rollNo"
					placeholder="Enter Roll Number"
					value="<%=ServletUtility.getParameter("rollNo", request)%>">

					<%-- <%=HTMLUtility.getList("rollNo", (bean.getRollNo()), mlist)%> --%>
					&nbsp; <input type="submit" name="operation"
					value="<%=MarksheetListCtl.OP_SEARCH%>"> <input
					type="submit" name="operation"
					value="<%=MarksheetListCtl.OP_RESET%>"></td>
			</tr>
		</table>
		<br>




		<table border="1" width="100%" align="center" cellpadding=2px
			cellspacing=".2">
			<tr style="background: white">
				<th><input type="checkbox" id="select_all" name="select">
					Select All.</th>
				<th>S.NO.</th>
				<th>RollNo</th>
				<th>Name</th>
				<th>Physics</th>
				<th>Chemistry</th>
				<th>Maths</th>
				<th>Total</th>
				<th>Percentage</th>
				<th>Result</th>
				<th>Edit</th>
			</tr>
			<%
				Iterator<MarksheetBean> it = list.iterator();

				if (list.size() != 0) {
					while (it.hasNext()) {
						bean = it.next();

						int phy = DataUtility.getInt(DataUtility.getStringData(bean.getPhysics()));
						int chem = DataUtility.getInt(DataUtility.getStringData(bean.getChemistry()));
						int math = DataUtility.getInt(DataUtility.getStringData(bean.getMaths()));
						int total = (phy + chem + math);
						float perc = total / 3;
			%>

			<tr align="center">
				<td><input type="checkbox" class="checkbox" name="ids"
					value="<%=bean.getId()%>">
				<td><%=index++%></td>
				<td><%=bean.getRollNo()%></td>
				<td><%=bean.getName()%></td>
				<td><%=bean.getPhysics()%></td>
				<td><%=bean.getChemistry()%></td>
				<td><%=bean.getMaths()%></td>
				<td><%=total%></td>
				<td><%=((perc) + "%")%></td>

				<td>
					<%
						if (phy >= 33 && chem >= 33 && math >= 33) {
					%> <span style="color: black"> Pass</span> <%
 	} else {
 %> <span style="color: red"> Fail</span> <%
 	}
 %>
				</td>
				<td><a href="MarksheetCtl?id=<%=bean.getId()%>">Edit</a></td>

			</tr>

			<%
				}
			%>
		</table>

		<table width="100%">
			<tr>
				<%
					if (pageNo == 1) {
				%>
				<td><input type="submit" name="operation" disabled="disabled"
					value="<%=MarksheetListCtl.OP_PREVIOUS%>"></td>
				<%
					} else {
				%>
				<td><input type="submit" name="operation"
					value="<%=MarksheetListCtl.OP_PREVIOUS%>"></td>
				<%
					}
				%>

				<td><input type="submit" name="operation"
					value="<%=MarksheetListCtl.OP_DELETE%>"></td>
				<td><input type="submit" name="operation"
					value="<%=MarksheetListCtl.OP_NEW%>"></td>

				<%
					MarksheetModel model = new MarksheetModel();

						if (model.nextPK() - 1 == bean.getId()) {
				%>
				<td align="right"><input type="submit" name="operation"
					disabled="disabled" value="<%=MarksheetListCtl.OP_NEXT%>"></td>
				<%
					} else {
				%>
				<td align="right"><input type="submit" name="operation"
					value="<%=MarksheetListCtl.OP_NEXT%>"></td>
				<%
					}
				%>

				<%-- <td align="right"><input type="submit" name="operation"
					value="<%=MarksheetListCtl.OP_NEXT%>"
					<%=(list.size() < pageSize) ? "disabled" : ""%>></td>
 --%>
			</tr>
		</table>

		<%
			}
			if (list.size() == 0) {
		%>
		<td align="center"><input type="submit" name="operation"
			value="<%=MarksheetListCtl.OP_BACK%>"></td>
		<%
			}
		%>

		<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
			type="hidden" name="pageSize" value="<%=pageSize%>">


	</form>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>


	<%@include file="Footer.jsp"%>
</body>
</html>
