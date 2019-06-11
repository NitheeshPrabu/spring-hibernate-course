<%--
  Created by IntelliJ IDEA.
  User: nitheesh
  Date: 11/6/19
  Time: 7:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Add New Customer</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/add-customer-styles.css" />
</head>
<body>

<div id="wrapper">
    <div id="header">
        <h2>CRM - Customer Relationship Manager</h2>
    </div>
</div>

<div id="container">
    <h3>Add New Customer</h3>
    <form:form action="addCustomer" modelAttribute="customer" method="POST">

        <!-- Associating this data with customer id using hidden form fields -->
        <form:hidden path="id" />

        <table>
            <tbody>
            <tr>
                <td><Label>First Name:</Label></td>
                <td><form:input path="firstName" /></td>
            </tr>
            <tr>
                <td><Label>Last Name:</Label></td>
                <td><form:input path="lastName" /></td>
            </tr>
            <tr>
                <td><Label>Email:</Label></td>
                <td><form:input path="email" /></td>
            </tr>
            <tr>
                <td><label /></td>
                <td><input type="submit" value="Save" class="save" /></td>
            </tr>
            </tbody>
        </table>
    </form:form>

    <div style="clear; both;" />

    <p>
        <a href="${pageContext.request.contextPath}/customer/list">Back to Home</a>
    </p>

</div>

</body>
</html>
