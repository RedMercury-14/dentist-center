<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html>
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
	<jsp:include page="header.jsp" />
	<div class="container">
		<div class="row">
			<h1>Services</h1>
		</div>
		<c:url value="/signin" var="loginVar" />
		<form:form action="${loginVar}" modelAttribute="user" method="POST">
			<div class="form-group">
				<label for="make">Username</label>
				<input name="login" class="form-control" />
			</div>
			<div class="form-group">
				<label for="model">Password</label>
				<input type="password" name="password" class="form-control" />
			</div>
			<sec:csrfInput />
			<c:if test="${param.error != null}" >
				<p>Invalid Username and Password.</p>
			</c:if>
			<button type="submit" id="btn-save" class="btn btn-primary">Login</button>
		</form:form>
	</div>
</body>
</html>
