<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style> <%@include file="/mystyle.css"%> </style>
<title>Insert title here</title>
</head>
<body>

<%@ page import="java.util.*,mvc.*" %>  

<p>${message}</p>
<div class="boxwrapper">
	<div class="centerbox">
		<div class="centerboxchild">
			<details>
				<summary>Testando elemento para criar nota</summary>
				<div class="centerboxchild">
				<form id="signup" action='/Projeto1/SignIn' method='post' >
					<input type='text' name='noteMessage' placeholder='Nota'><br>
				</form>
				</div>
				<div class="centerboxchild">
					<button type="submit" form="signup">Confirmar</button>
				</div>
			</details>
		</div>
	</div>

	<c:forEach items="${notes}" var="note">
		<div class="centerbox">
			<div class="centerboxchild">
				<p>${note.getMessageBody()}</p>
			</div>
		</div>
	</c:forEach>
</div>

</body>
</html>