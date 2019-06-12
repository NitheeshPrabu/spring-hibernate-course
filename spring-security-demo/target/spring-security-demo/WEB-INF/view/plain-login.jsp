<%--
  Created by IntelliJ IDEA.
  User: nitheesh
  Date: 12/6/19
  Time: 7:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Login</title>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>

<h3>My Login Page</h3>

<form:form action="${pageContext.request.contextPath}/authenticate" method="POST">

    <c:if test="${param.error != null}">
        <i class="error">Invalid username/password</i>
    </c:if>

    <p>
        Username: <input type="text" name="username" />
    </p>
    <p>
        Password: <input type="password" name="password" />
    </p>
    <input type="submit" value="Login" />
</form:form>

</body>
</html>
