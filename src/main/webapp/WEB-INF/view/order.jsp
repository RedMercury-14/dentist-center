<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
    
    
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
	<sec:authorize access="authenticated" var="authenticated" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dentist Center</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
	
    	
	
		<div class="row">
			<h1>Доступные записи</h1>
			<div class="row">
				<h3 class="col col-md-8">Выберете удобное время для посещения.</h3>
				<div class="col col-md-4">							
				<c:choose>
				<c:when test="${authenticated}">
				<sec:authorize access="hasRole('ROLE_ADMIN')">
				<form action="addschedule" method="GET">
					<button type="submit" id="btn-save" class="btn btn-primary">Создать рассписание</button>
				</form>
				</sec:authorize>
				</c:when>
				</c:choose>
				</div>
			</div>
			<table  class="table">
				<tr>
					<th>Дата</th>
					<th>Время</th>
					<th>Врач</th>
					<th>Записи</th>
				</tr>

				<!-- loop over and print our customers -->
				<c:forEach var="schedules" items="${schedules}">

					<!-- construct an "update" link with customer id -->
					<c:url var="enterLink" value="/home/userpage/proof">
						<c:param name="scheduleId" value="${schedules.idSchedule}" />
					</c:url>


					<tr>
						<td>${schedules.date}</td>
						<td>${schedules.localTime}</td>
						<td>${schedules.login}</td>

						<td>
							<!-- display the update link --> 
							<a href="${enterLink}">Записаться</a>							
						</td>

					</tr>

				</c:forEach>

			</table>			
		</div>
	</div>
</body>
</html>