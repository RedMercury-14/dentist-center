<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
	
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

</head>
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
		<div class="row">
		<h1>Создание рассписания</h1>
		</div>		
		<form action="addschedule"  method="POST">
					
			<div class="form-group">
				<label>Дата начала рассписания:</label>				
				<input type="date" name="dateStart" class="form-control"/>
			</div>
				<br>
			<div class="form-group">
				<label>Дата окончания рассписания:</label>				
				<input type="date" name="dateFinish" class="form-control"/>
			</div>
			<br>
			<sec:csrfInput />
			<div class="form-group">			
				<label>Смена:</label>
				<select	name="isShift" autofocus>	
				<option value= "1">1</option>
  				<option value= "2">2</option>
			</select> 	<br><br> 
			</div> 
			<br>			
			<div class="form-group">			
				<label>Врач:</label>
				<select	name="isDoctor" autofocus>				
				<c:forEach var="doctor" items="${doctors}">
					<option value="<c:out value="${doctor.login}"/> "><c:out value="${doctor.login}" /> </option>
				</c:forEach>
			</select> 	<br><br> 
			</div> 
					<br>
			
			<c:out value="${errorMessage}" />
			<br>
						<td><input type="submit" value="Создать" class="save" /></td>
		
		</form>
</div>
</body>
</html>