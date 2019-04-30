<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Menu Item</title>
</head>
    <body>
        <h2>
            The item <br/>
        </h2>

        <br>
        <br>
        <form action="${pageContext.request.contextPath}/api/admin/editItem" method="post">
             Id <input type="number" name="id" value="${item.id}" readonly/><br>
             Name <input type="text" name="name" value="${item.name}" readonly/><br>
             Number in stock <input type="number" name="number" value="${item.number}"  min="1" required/><br>
             Price<input type="number" name="price" value="${item.price}"  min="1" required/><br>
             Category <input type="text" name="category" value="${item.category}" required/><br>
             <input type="submit" value="Edit"/>
        </form>

        <br/>
        <a href="${pageContext.request.contextPath}/index.jsp">index</a>
    </body>
</html>