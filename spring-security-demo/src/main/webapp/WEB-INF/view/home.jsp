<%--
  Created by IntelliJ IDEA.
  User: nitheesh
  Date: 12/6/19
  Time: 3:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Home Page</title>
</head>
<body>

<h2>Example Company Home Page</h2>
<hr>

<p>Welcome to the Example company home page!</p>

<!-- Display username and role -->
<p>
    User: <security:authentication property="principal.username" />
    <br><br>
    Role(s): <security:authentication property="principal.authorities" />
</p>

<hr>

<security:authorize access="hasRole('MANAGER')">
    <!-- For managers -->
    <p>
        <a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a>
    </p>
</security:authorize>

<security:authorize access="hasRole('ADMIN')">
    <!-- For admins -->
    <p>
        <a href="${pageContext.request.contextPath}/systems">IT Systems Meeting</a>
    </p>
</security:authorize>

<!-- Add logout button -->
<form:form action="${pageContext.request.contextPath}/logout" method="POST">
    <input type="submit" value="Logout" />
</form:form>

</body>
</html>
