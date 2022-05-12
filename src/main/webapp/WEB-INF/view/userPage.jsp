<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dentist Center</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

<!-- Latest Jquery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"
	type="text/javascript"></script>
<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
		<div class="row">
			<h1>Страница юзера <sec:authentication property="name" /></h1>
		</div>
		<div class="row">
			<h2>Ваши записи</h2>
		</div>
		<table  class="table">
				<tr>
					<th>Дата</th>
					<th>Время</th>
					<th>Врач</th>
					<th>Записи</th>
				</tr>

				<!-- loop over and print our customers -->
				<c:forEach var="orders" items="${orders}">

					<!-- construct an "update" link with customer id -->
					<c:url var="enterLink" value="/home/userpage/cancel">
						<c:param name="scheduleId" value="${orders.idSchedule}" />
					</c:url>


					<tr>
						<td>${orders.date}</td>
						<td>${orders.localTime}</td>
						<td>${orders.login}</td>

						<td>
							<!-- display the update link --> 
							<a href="${enterLink}">Отменить запись</a>							
						</td>

					</tr>

				</c:forEach>

			</table>
		Просмотреть имещюеся записи (кнопка)
</div>

</body>
</html>