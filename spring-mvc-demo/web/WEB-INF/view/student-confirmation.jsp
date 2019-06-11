<%--
  Created by IntelliJ IDEA.
  User: nitheesh
  Date: 30/5/19
  Time: 11:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Student Confirmation</title>
</head>
<body>

The following student details were received: ${student.firstName} ${student.lastName}

<br><br>

Country: ${student.country}

<br><br>

Favourite Language: ${student.language}

<br><br>

Operating Systems:

<ul>
    <c:forEach var="os" items="${student.operatingSystems}">
        <li>${os}</li>
    </c:forEach>
</ul>

</body>
</html>
