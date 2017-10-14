<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Bright Idea's</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  	<meta http-equiv="x-ua-compatible" content="ie=edge">
  	<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
	<h1>Hi <c:out value="${currentUser.alias}"></c:out></h1>
	
	<section class="content" id="ideas">
		<c:forEach items="${ideas}" var="idea">
			<div class="card">
	
				<a class="card-title" href="/user/${idea.user.id}"><c:out value="${idea.user.alias}"/></a>
				<c:out class="card-body" value="${idea.text}"/>
				
				<p>
					<a href="/like/${idea.id}">Like</a>
					<a href="/idea/${idea.id}"><c:out value="${idea.liked.size()}"/> people</a> like this
					<c:if test="${idea.user.id == currentUser.id}">
						<a href="/delete/${idea.id}">Delete</a>
					</c:if>
				</p>	
			</div>
			
		</c:forEach>
	</section>
	<div>
		<p><form:errors path="idea.*"/></p>
	<form:form method="POST" action="/newIdea" modelAttribute="idea">
		<p>
			<form:label path="text">Name:</form:label>
			<form:input path="text" value="Post something witty here..."/>
			<input type="submit" value="Idea!"/>
		</p>
        
        		
        
	</form:form>
	</div>
	
	<form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" />
    </form>
	
	<script src="js/jquery.slim.min.js"></script>
	<script src="js/tether.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/scripts.js"></script>
</body>
</html>
