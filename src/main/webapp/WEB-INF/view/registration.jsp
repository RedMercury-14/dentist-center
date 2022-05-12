<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
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
<title>Регистрация</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
		<div class="row">
		<h1>Регистрация юзера</h1>
		</div>		
		<form:form modelAttribute="user" method="POST">
					
			<div class="form-group">
				<label>Введите логин:</label>
				<form:input path="login" class="form-control"/>
			</div>
				<br>
			<div class="form-group">
				<label>Введите пароль:</label>
				<form:input path="password" class="form-control"/>
			</div>
			<br>
			<div class="form-group">
				<label>Повторите пароль:</label>
				<form:input path="confirmPassword" class="form-control"/>
			</div>
			<br>
			<div class="form-group">
				<label>Введите имя:</label>
				<form:input path="name" class="form-control"/>
			</div>
					<br>
			<div class="form-group">
				<label>Введите фамилию:</label>
				<form:input path="surName" class="form-control"/>
			</div>
			<c:out value="${errorMessage}" />
			<br>
						<td><input type="submit" value="Зарегистрироваться" class="save" /></td>
		
		</form:form>
</div>
</body>
</html>