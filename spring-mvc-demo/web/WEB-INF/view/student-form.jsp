<%--
  Created by IntelliJ IDEA.
  User: nitheesh
  Date: 30/5/19
  Time: 11:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Student Registration Form</title>
</head>
<body>

<h2>Student Registration Form</h2>

<form:form action="processForm" modelAttribute="student">
    First Name: <form:input path="firstName" />

    <br><br>

    Last Name: <form:input path="lastName" />

    <br><br>

    Country:
    <%--     Manual populate of dropdown --%>
    <%--<form:select path="country"> --%>
    <%--    <form:option value="BRA" label="Brazil" /> --%>
    <%--    <form:option value="FRA" label="France" /> --%>
    <%--    <form:option value="GER" label="Germany" /> --%>
    <%--    <form:option value="IND" label="India" /> --%>
    <%--</form:select>--%>

    <%--    Populate from array using getter method --%>
    <form:select path="country">
        <form:options items="${student.countries}" />
    </form:select>

    <br><br>

    <form:radiobutton path="language" value="Java" /> Java
    <form:radiobutton path="language" value="C++" /> C++
    <form:radiobutton path="language" value="Python" /> Python
    <form:radiobutton path="language" value="JavaScript" /> JavaScript

    <br><br>

    <form:checkbox path="operatingSystems" value="Linux" /> Linux
    <form:checkbox path="operatingSystems" value="Windows" /> Windows
    <form:checkbox path="operatingSystems" value="maxOS" /> macOS

    <br><br>

    <input type="submit" value="Submit" />
</form:form>

</body>
</html>
