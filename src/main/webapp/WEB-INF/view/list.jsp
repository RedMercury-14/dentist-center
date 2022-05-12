<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html>

<head>
<title>List Customers</title>

<!-- reference our style sheet -->

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>"/>
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Список зарегистрированных юзеров</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">

			<!-- put new button: Add Customer -->

			<input type="button" value="Add Customer"
				onclick="window.location.href='showFormForAdd'; return false;"
				class="add-button" />

			<!--  add our html table here -->

			<table>
				<tr>
					<th>Login</th>
					<th>Password</th>
					<th>Name</th>
					<th>SurName</th>
					<th>eMail</th>
					<th>Telephone</th>
					<th>Address</th>
					<th>Активен</th>
				</tr>

				<!-- loop over and print our customers -->
				<c:forEach var="tempUser" items="${userlist}">

					<!-- construct an "update" link with customer id -->
					<c:url var="updateLink" value="/customer/showFormForUpdate">
						<c:param name="customerId" value="${tempUser.login}" />
					</c:url>

					<!-- construct an "delete" link with customer id -->
					<c:url var="deleteLink" value="/customer/delete">
						<c:param name="customerId" value="${tempUser.login}" />
					</c:url>

					<tr>						
						<td>${tempUser.login}</td>
						<td>${tempUser.password}</td>
						<td>${tempUser.name}</td>
						<td>${tempUser.surName}</td>
						<td>${tempUser.eMail}</td>
						<td>${tempUser.telephone}</td>
						<td>${tempUser.address}</td>
						<td>${tempUser.enabled}</td>

						<td>
							<!-- display the update link --> 
							<a href="${updateLink}">Update</a>
							| <a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
						</td>

					</tr>

				</c:forEach>

			</table>

		</div>

	</div>
		<p>
			<a href="signin">Вход</a>
		</p>
			<p>
			<a href="registration">Регистрация</a>
		</p>
			<p>
			<a href="<c:url value="/logout" />">Выход</a>
		</p>

</body>

</html>









