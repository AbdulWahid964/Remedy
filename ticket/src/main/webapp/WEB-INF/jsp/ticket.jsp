<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.formError {
	color: red;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ticket Details</title>
</head>
<body>
	<center>
		<h1>Ticket Details Form</h1>

		<form:form action="/ticket" method="post" modelAttribute="ticket">
			<table>
				<tr>
					<td><form:input path="ticketId" type="hidden" /></td>
				</tr>
				<tr>
					<td> Caller Name</td>
					<td><form:input path="user.customerName" cssClass="formInput"
							placeholder="Enter User Name" readonly="true"/></td>
				</tr>
				<tr>
					<td>State</td>
					<td><form:select path="state">
						<form:option value="new">New</form:option>
						<form:option value="in-progess">In-progress</form:option>
						<form:option value="pending">Pending</form:option>
						<form:option value="closed">Closed</form:option>
						
					</form:select>
					</td>
				</tr>
				<tr>
					<td>Priority</td>
					<td><form:select path="priority">
						<form:option value="urgent">Urgent</form:option>
						<form:option value="low">Low</form:option>
						<form:option value="high">High</form:option>
					</form:select>
					</td>
				</tr>
				<tr>
					<td>Short Description</td>
					<td><form:input path="shortDescription" cssClass="formInput"
							placeholder="Enter  Short Description" /></td>
							<td><form:errors path="shortDescription" cssClass="formError" /></td>
				</tr>
				<tr>
					<%-- <td colspan="2"><c:if test="${!empty ticket.productName}">
							<input type="submit" value="Update Product" />
						</c:if> <c:if test="${empty products.productName}">
							<input type="submit" value="Save" />
						</c:if></td> --%>
					<td>
					<input type="submit" value="Create Ticket" />
					<input type=button value="Back" onClick="history.back()">
					</td>
				</tr>
				<!-- <a href="/login">Login</a> -->
			</table>
		</form:form>
	</center>
</body>
</html>