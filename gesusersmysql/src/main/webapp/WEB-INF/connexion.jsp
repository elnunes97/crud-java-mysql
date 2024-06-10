<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "inc/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 id="titre-principal">Authentification</h1>
	
	<form method="post" class="main">
		<div class="formItem">
			<label>Login</label>
			<input type="text" id="login" name="login" value="${ login }">
			
		</div>
		
		<div class="formItem">
			<label>Password</label>
			<input type="password" id="passwor" name="password">
			
		</div>	
		<div class="formItem">
			<input type="submit" value="login">
		</div>
	</form>

</body>
</html>