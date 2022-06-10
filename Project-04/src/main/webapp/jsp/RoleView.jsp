
<%@page import="com.rays.ctl.RoleCtl"%>
<%@page import="com.rays.utility.DataUtility"%>
<%@page import="com.rays.utility.ServletUtility"%>
<html>
<body>
    <form action="<%=ORSView.ROLE_CTL%>" method="post">
      <title> Add Role</title> 
        <%@ include file="Header.jsp"%>

        <jsp:useBean id="bean" class="com.rays.beans.RoleBean"
            scope="request"></jsp:useBean>

        <center>
          
           <h1>
        	<%
        		if( bean != null && bean.getId()>0){
        	%> 
        	<tr><th><font>Update Role</font></th></tr>
        	<% }else{%>
        	<tr><th><font>Add Role</font></th></tr>
        	<% }%>
        </h1>
            <H2>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H2>
            <H2>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2>

            <input type="hidden" name="id" value="<%=bean.getId()%>">
                 <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

            <table>
                <tr>
                    <th align = "left">Name <font color="red" >*</font></th>
                    <td><input type="text" name="name"  placeholder="Enter _Name"
                        value="<%=DataUtility.getStringData(bean.getName())%>"><td><td style="position: fixed"><font
                        color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
                </tr>
                <tr>
                    <th align = "left"">Description <font color="red" >*</th>
                    <td><input type="text" name="description"  placeholder="Enter _Description"
                        value="<%=DataUtility.getStringData(bean.getDescription())%>"><td><td style="position: fixed"><font
                        color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font></td>
                </tr>
                <tr>
					<th></th>
					<%
 	if (bean.getId() > 0) {
 %><td colspan="2"> &nbsp; &nbsp; <input type="submit" name="operation"
						value="<%=RoleCtl.OP_UPDATE%>"> &nbsp; &nbsp; <input type="submit" name="operation"
						value="<%=RoleCtl.OP_CANCEL%>"></td>
						<%
						} else {
							%>
						
						<td  colspan="2">
						<input type="submit" name="operation"
						value="<%=RoleCtl.OP_SAVE%>"> &nbsp; &nbsp; <input type="submit" name="operation"
						value="<%=RoleCtl.OP_RESET%>"></td>
							<%
						} 
							%>
				</tr>

            </table>
    </form>
    </center>
    <%@ include file="Footer.jsp"%>
</body>
</html>