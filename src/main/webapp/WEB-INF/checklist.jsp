<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Check List</title>
</head>
    <body>
        <h2>
            List Checks <br/>
        </h2>
        <table>
        <tr><th>Id</th><th>Total price</th><th>Time</th>
        <th>Client</th><th>Accepted by an Admin</th><th></th><th>Admin</th>
        <th>Issued as a Check</th><th></th><th>Has been paid</th><th>Tip</th></tr>
        <c:forEach var="i" items="${checks}">
            <tr><td><a href="check?id=<c:out value='${i.id}' />"> <c:out value="${i.id}"/></a></td>
            <td>${i.totalPrice}</td><td>${i.createTime}</td>
            <td>${i.client.id}</td><td>${i.confirmed}</td>
            <td>
            <c:if test="${i.confirmed eq false}">
            <form action="${pageContext.request.contextPath}/api/admin/check/confirm?id=${i.id}" method="post">
            <input type="submit" value="Confirm"/>
            </form>
            </c:if>
            </td>
            <td>${i.admin.id}</td>
            <td>${i.check}</td>
            <td>
            <c:if test="${i.check eq false && i.confirmed eq true}">
            <form action="${pageContext.request.contextPath}/api/admin/check/issue?id=${i.id}" method="post">
            <input type="submit" value="Issue"/>
            </form>
            </c:if>
            </td>
            <td>${i.paid}</td><td>${i.tip}</td>
        </c:forEach>
        </table>
        <br>
        <a href="${pageContext.request.contextPath}/index.jsp">index</a>
    </body>
</html>