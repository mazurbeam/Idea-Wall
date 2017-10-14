<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Idea Status</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  	<meta http-equiv="x-ua-compatible" content="ie=edge">
  	<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
	<p><a href="/ideas">Ideas</a>
	<form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" />
    </form>
	</p>
	<p><c:out value="${idea.user.alias}"/> says:</p>
	<p><c:out value="${idea.text}"/></p>
	
	<h3>People who liked this post:</h3>
	<c:forEach items="${idea.liked}" var="user">
		<p><a href="/user/${user.id}">${user.alias}</a>
		<c:out value="${user.name}"/></p>
	</c:forEach>
	<script src="js/jquery.slim.min.js"></script>
	<script src="js/tether.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/scripts.js"></script>
</body>
</html>