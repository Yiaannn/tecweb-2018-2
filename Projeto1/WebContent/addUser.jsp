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

<form id="voltar" action='/Projeto1/' method='get'></form>

<p>${param.message}</p>

<div class="centerbox">
	<div class="centerboxchild">
		<h1>Sign Up</h1>
	</div>
	
	<div class="centerboxchild">
		<form id="signup" action='/Projeto1/addUser' method='post' >
			<input type='text' name='login' placeholder='login'><br>
			<input type='password' name='pass' placeholder='senha'><br>
		</form>
	</div>
	
	<div class="centerboxchild">
		<button type="submit" form='voltar'>Voltar</button>
		<button type="submit" form="signup">Confirmar</button>
	</div>
</div>
	
<%@ page import="java.util.*,mvc.*" %>

<%
//logins a princípio são únicos, posso usar eles como quesito de busca como um "pseudo id"

%>



</body>
</html>