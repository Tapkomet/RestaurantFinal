<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin Base</title>
</head>
    <body>
        <a href="${pageContext.request.contextPath}/api/logout">Logout</a>
        <br>
        <a href="${pageContext.request.contextPath}/api/admin/items">Item List</a>
        <br>
        <a href="${pageContext.request.contextPath}/api/admin/check">Check List</a>
        <br>
        <a href="${pageContext.request.contextPath}/api/client">View as Client</a>
        <br>
        <a href="${pageContext.request.contextPath}/index.jsp">View Start Page</a>
        <br>
        <a href="${pageContext.request.contextPath}/api/admin/checks">View all checks</a>
        <br>
        <a href="${pageContext.request.contextPath}/api/client/orders">View all your orders</a>
        <br>
        <a href="${pageContext.request.contextPath}/api/admin/users">View users</a>

    </body>
</html>