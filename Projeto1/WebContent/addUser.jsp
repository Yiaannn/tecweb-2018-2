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
	Login: <input type='text' name='login'><br>
	Senha: <input type='password' name='pass'><br>
	<input type='submit' value='Submit'>
</form>
	
<%@ page import="java.util.*,mvc.*" %>

<%
//logins a princípio são únicos, posso usar eles como quesito de busca como um "pseudo id"

%>



</body>
</html>