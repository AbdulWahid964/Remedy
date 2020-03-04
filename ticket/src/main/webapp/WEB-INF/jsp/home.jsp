<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<style type="text/css">
.table {
	font-family: verdana, arial, sans-serif;
	font-size: 13px;
	border-width: 1px;
	border-color: black;
	border-collapse: collapse;
}

.table td {
	border-width: 2px;
	padding: 8px;
	border-style: solid;
	border-color: black;
}

.table th {
	background-color: #c3dde0;
	border-width: 2px;
	padding: 8px;
	border-style: solid;
	border-color: black;
}
</style>
</head>
<body>

	<%-- <h2>Product Details</h2>

	<form action="products">
		Product Name:<input id="productname" name="productname"
			class="form-control" value="${product.productName}"
			placeholder="Enter Product Name" /> <input type="submit"
			value="Submit">
		<!-- <input type="reset" value="Reset"> -->
	</form>
	<form action="home">
		<button type="submit">Refresh</button>
	</form>
	<br>
	<br> --%>
	<br>
	<h1>Ticket Details</h1>
	<div>
		<c:if test="${role eq '[customer]'}">
		<input id="customerId" name="customerId" type="hidden" value="${customerId}" />
			<table class="table">
				<tr>
				
					<th width="80">Ticket Id</th>
					<th width="80">Caller-Name</th>
					<th width="80">State</th>
					<th width="80">Priority</th>
					<th width="80">Short Description</th>
				</tr>
				<c:if test="${ticketList.size() eq 0}">
					<tr>
						<td colspan=5>No Data to be display</td>
				</c:if>
				<c:forEach var="ticket" items="${ticketList}">
					<tr>
					
						<td>${ticket.ticketId}</td>
						<td>${ticket.user.customerName}</td>
						<td>${ticket.state}</td>
						<td>${ticket.priority}</td>
						<td>${ticket.shortDescription}</td>
					</tr>
				</c:forEach>
			</table>
			<br />
			<a href="/ticket">Create Ticket</a>
		</c:if>
		<c:if test="${role eq '[admin]'}">
			<table class="table">
				<tr>
					<th width="80">Ticket Id</th>
					<th width="80">Caller-Name</th>
					<th width="80">State</th>
					<th width="80">Priority</th>
					<th width="80">Short Description</th>
					<th width="80">Edit</th>
					<th width="80">Delete</th>
				</tr>
				<c:if test="${ticketList.size() eq 0}">
					<tr>
						<td colspan=7>No New Tickets is available</td>
				</c:if>
				<c:forEach var="ticket" items="${ticketList}">
					<tr>
						<td>${ticket.ticketId}</td>
						<td>${ticket.user.customerName}</td>
						<td>${ticket.state}</td>
						<td>${ticket.priority}</td>
						<td>${ticket.shortDescription}</td>
						<td><a href="<c:url value='/ticket/${ticket.ticketId}' />">Edit</a></td>
						<td><a
							href="<c:url value='/tickets/ticket/${ticket.ticketId}'/>">Close</a></td>
					</tr>
				</c:forEach>
			</table>
			<br />
			<br />
		</c:if>
	</div>
	<br>
	<div>
		Welcome Back
		<sec:authentication property="name" />
		<sec:authentication property="principal.authorities" />
		<a href="logout">Logout</a>
	</div>

</body>
</html>