<%--
  Created by IntelliJ IDEA.
  User: nitheesh
  Date: 30/5/19
  Time: 12:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Customer Confirmation</title>
</head>
<body>

The following customer details were received: ${customer.firstName} ${customer.lastName}
        ${customer.freePasses} ${customer.postalCode} ${customer.courseCode}

</body>
</html>
