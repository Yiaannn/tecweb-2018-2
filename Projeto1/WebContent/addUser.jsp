<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action='/Projeto1/addUser' method='post' >
	<input type='text' name='login' placeholder='login'><br>
	<input type='password' name='pass' placeholder='senha'><br>
	<input type='submit' value='Confirmar'>
</form>
	
<%@ page import="java.util.*,mvc.*" %>

<%
//logins a princípio são únicos, posso usar eles como quesito de busca como um "pseudo id"

%>



</body>
</html>