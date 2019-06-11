<%--
  Created by IntelliJ IDEA.
  User: nitheesh
  Date: 11/6/19
  Time: 4:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>List Customers</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css" />
</head>
<body>

<div id="wrapper">
    <div id="header">
        <h2>CRM - Customer Relationship Manager</h2>
    </div>
</div>

<div id="container">
    <div id="content">

        <!--  add a search box -->
        <form:form action="search" method="POST">
            Search customer: <input type="text" name="name" />

            <input type="submit" value="Search" class="add-button" />
        </form:form>

        <input type="button" value="Add Customer" onclick="window.location.href='showFormForAdd'; return false;"
        class="add-button" />

        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Action</th>
            </tr>

            <c:forEach var="customer" items="${customers}">

                <!-- construct an 'update' link with customer id -->
                <c:url var="updateLink" value="/customer/showFormForUpdate">
                    <c:param name="customerId" value="${customer.id}" />
                </c:url>

                <!-- construct an 'delete' link with customer id -->
                <c:url var="deleteLink" value="/customer/delete">
                    <c:param name="customerId" value="${customer.id}" />
                </c:url>

                <tr>
                    <td>${customer.firstName}</td>
                    <td>${customer.lastName}</td>
                    <td>${customer.email}</td>
                    <td>
                        <a href="${updateLink}">Update</a> |
                        <a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false;">Delete
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
