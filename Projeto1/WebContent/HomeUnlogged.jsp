<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="mystyle.css">
<title>Insert title here</title>
</head>
<body>

<form id="signup" action='/Projeto1/SignUp' method='get'></form>
<form id="signin" action='/Projeto1/SignIn' method='get'></form>

<p>${param.message}</p>

<div class="centerbox">
	<div class="centerboxchild">
		<h1>Projeto Notinhas</h1>
	</div>
	
	<div class="centerboxchild">
		<button type="submit" form='signup'>Inscrever-se</button>
		<button type="submit" form="signin">Acessar Perfil</button>
	</div>
</div>
	
<%@ page import="java.util.*,mvc.*" %>

</body>
</html>