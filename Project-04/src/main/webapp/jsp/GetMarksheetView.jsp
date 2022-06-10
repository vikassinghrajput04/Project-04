
<%@page import="com.rays.utility.DataUtility"%>
<%@page import="com.rays.ctl.GetMarksheetCtl"%>
<%@page import="com.rays.utility.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<title> Get marksheet</title>

</head>
<body>
    <jsp:useBean id="bean" class="com.rays.beans.MarksheetBean" scope="request"></jsp:useBean>
   <form action="<%=ORSView.GET_MARKSHEET_CTL%>" method="post">
    <%@ include file="Header.jsp"%>

			<input type="hidden" name="id" value="<%=bean.getId()%>">

    <center>
        <div align="center">
	        <h1>Get Marksheet</h1>
	
        	<h2><font color="red"> <%=ServletUtility.getErrorMessage(request)%></font></h2>
       		<H2><font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font></H2> 
        </div>
        
            <table>
           
            <br>
				<tr><th align="right"> Roll No <span style="color:red">*</span></th>
				<td><input type="text"  name="rollNo" placeholder="Enter Roll_No." size="25" value="<%=ServletUtility.getParameter("rollNo", request)%>">
				</td><td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("rollNo", request)%></font></td>
				</tr>                
               
                
                <tr><th style="padding: 3px"></th></tr>
                
                <tr><th></th><td><input type="submit" name="operation" value="<%=GetMarksheetCtl.OP_GO%>">
                			&nbsp;<input type="submit" name="operation" value="<%=GetMarksheetCtl.OP_RESET%>">
                </td></tr>
                
                </table>

                <%
                    if ( bean.getMaths() != 0  && bean.getRollNo().trim().length() > 0 ) {
                %>
			<table >
				<table border="1" width="55%">
				  <tr align="center" >
				  	<td><h2>Rays Technologies</h2></td>
				  </tr></table>
				  
				 <table border="1" width="55%" >
				 	<tr>
				 		<th align="center"> Name</th>
				 		<td align="center"> <%=DataUtility.getStringData(bean.getName()) %></td>
				 		<th align="center"> Roll No</th>
				 		<td align="center"> <%=DataUtility.getStringData(bean.getRollNo()) %> </td>
				 						 
					</tr>
				 	<tr>
				 		<td align="center"> Status</td>
				 		<th align="center">Regular</th>
				 		<td align="center"> Course</td>
				 		<th align="center">BE</th>				 
					</tr>			
				 </table> 
	<%
	int phy =DataUtility.getInt(DataUtility.getStringData(bean.getPhysics()));
	int chem =DataUtility.getInt(DataUtility.getStringData(bean.getChemistry()));
	int math =DataUtility.getInt(DataUtility.getStringData(bean.getMaths()));
	int total = (phy+chem+math);
	float perc = total/3;
	%>
					  
			<table border="1" width="55%">
				<tr>
					<th align="center" style="width: 25%">Subject</th>
					<th align="center" style="width: 25%">Maximum Marks</th>
					<th align="center" style="width: 25%">Minimum Marks</th>
					<th align="center" style="width: 25%">Marks Obtained</th>
					<th align="center" style="width: 25%">Grade</th>
				</tr>
				<tr>
				<td align="center">Physics</td>
				<td align="center">100</td>
				<td align="center">33</td>
				<td align="center"><%=phy %>
				
				<%if(phy<33){%>
					<span style="color: red">*</span>
				<% } %>	</td>
				
				<td align="center">
				
				<%
					if(phy <=100 && phy >85){ %> A+
				<%} else if(phy<=85 && phy > 75 ) {%>B+
				<%} else if(phy<=75 && phy > 65 ) {%>B
				<%} else if(phy<=65 && phy > 55 ){ %>C+
				<%} else if(phy<=55 && phy >=33  ){ %>C
				
				<%} else if(phy< 33 && phy >= 0 ) {%><span style="color: red"> Fail</span>
				<% } %>
				</td>
				</tr>
			
			<tr>
				<td align="center">Chemistry</td>
				<td align="center">100</td>
				<td align="center">33</td>
				<td align="center"><%=chem %>
				
				<%if(chem<33){%>
					<span style="color: red">*</span>
				<% } %>	</td>
				
				<td align="center">
				
				<%
					if(chem <=100 && chem >85){ %> A+
				<%} else if(chem<=85 && chem > 75 ) {%>B+
				<%} else if(chem <=75 && chem > 65 ) {%>B
				<%} else if(chem <=65 && chem > 55 ){ %>C+
				<%} else if(chem <=55 && chem >=33  ){ %>C
				
				<%} else if(chem < 33 && chem >= 0 ) {%><span style="color: red"> Fail</span>
				<% } %>
				</td>
				</tr>
					
			<tr>
				<td align="center"> Maths</td>
				<td align="center">100</td>
				<td align="center">33</td>
				<td align="center"><%=math %>
				
				<%if(math<33){%>
					<span style="color: red">*</span>
				<% } %>	</td>
				
				<td align="center">
				
				<%
					if(math <=100 && math >85){ %> A+
				<%} else if(math <=85 && math > 75 ) {%>B+
				<%} else if( math <=75 && math > 65 ) {%>B
				<%} else if(math <=65 && math > 55 ){ %>C+
				<%} else if(math <=55 && math >=33  ){ %>C
				
				<%} else if(math < 33 && math >= 0 ) {%><span style="color: red"> Fail</span>
				<% } %>
				</td>
				</tr>
			</table>	  
			
			<table border="1" width="55%">
			<tr>
				<th>Total</th>
				<th>Percentage</th>
				<th>Division</th>
				<th>Result</th>
			</tr>
			<tr>
				<th><%=total %>
				<% if(total<99 || phy<33|| chem<33|| 	math<33){ %>
				<span style="color: red">*</span>
				<% } %>
				</th>
			
				<th><%=perc %> %</th>
				<th>
				<% if(perc <100 && perc >= 60){ %>1<sup>st</sup>
				<%} else if(perc <60 && perc >=40){ %>2<sup>st</sup>
				<%} else if(perc <40 && perc >=0){ %>3<sup>st</sup>
				<%} %>
				</th>
				
				<th>
				<%
				if(phy >=33 && chem>=33 && math >= 33) { %>
				<span style="color: green"> Pass</span>
				<%}else{ %>
				<span style="color: red"> Fail</span>
				<% } %>
				
				</th>
				</tr>
			</table>
			
			<%} %>
	</table>		
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
    </center>
    <%@ include file="Footer.jsp"%>
</body>
</html>