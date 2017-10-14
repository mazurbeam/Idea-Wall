<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Profile</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  	<meta http-equiv="x-ua-compatible" content="ie=edge">
  	<link rel="stylesheet" href="css/bootstrap.min.css">
</head>

<body>
	<h1>User Profile</h1>
	<p><a href="/ideas">Bright Ideas</a>
	<form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" />
    </form>
	</p>
	<p>Name: <c:out value="${user.name}"/></p>
	<p>Alias: <c:out value="${user.alias}"/></p>
	<p>Email: <c:out value="${user.username}"/></p>
	<hr>
	<p>Total number of posts: <c:out value="${user.ideas.size() }"/>
	<p>Total number of likes: <c:out value="${user.liked.size() }"/>	
	<script src="js/jquery.slim.min.js"></script>
	<script src="js/tether.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/scripts.js"></script>
</body>
</html>