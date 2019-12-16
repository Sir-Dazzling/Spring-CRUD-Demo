<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of Customers</title>
<link 	type="text/css" 
	  	rel="stylesheet" 
	  	href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>

<body>
	
	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
			
			<input type="button" value="Add Customer" onclick="window.location.href = 'showFormForAdd'; return false;"
				class="add-button"/>
			
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
				</tr>
				<!--  EUI157649812635-->
				<!-- Looping over and printing all the customers -->
				<c:forEach var="tempCustomer" items="${customers}">
					
					<!-- constructing an update link with customer id -->
					<c:url var="updateLink" value="/customer/showFormForUpdate">
						<c:param name="customerId" value="${tempCustomer.id}"></c:param>
					</c:url>
					<!-- constructing an delete link with customer id -->
					<c:url var="deleteLink" value="/customer/delete">
						<c:param name="customerId" value="${tempCustomer.id}"></c:param>
					</c:url>
					
					<tr>
						<td>${tempCustomer.firstName}</td>
						<td>${tempCustomer.lastName}</td>
						<td>${tempCustomer.email}</td>
						<td>
							<!-- Displaying the Update and Delete Link -->
							<a href="${updateLink}">Update</a>
							|
							<a href="${deleteLink}" onclick="if(!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
						</td>
					</tr>
					
				</c:forEach>
				
			</table>
		</div>
	</div>	
	
</body>

</html>