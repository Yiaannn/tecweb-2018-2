<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
				<summary>Nova Nota</summary>
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
				<h1>Prioridade: ${note.getDecoratedPriorityLevel()}</h1>
			</div>
			<div class="centerboxchild">
				<p>${note.getMessageBody()}</p>
			</div>
			<div class="centerboxchild">
				<form id="deleteNote" action='/Projeto1/Notes' method='post'>
					<input type="hidden" name="_method" value="DELETE">
				</form>
				<button type="submit" form="deleteNote" name="target" value="${note.getID()}">Apagar</button>
			</div>
		</div>
	</c:forEach>
</div>

</body>
</html>