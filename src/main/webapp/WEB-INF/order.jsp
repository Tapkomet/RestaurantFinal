<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Item List</title>
</head>
    <body>
        <h2>
            The check <br/>
        </h2>

        <br>
        <br>
        <p>Check id: ${check.id}, total price: ${check.totalPrice}, time created: ${check.createTime}
        <br>
        Has been accepted: ${check.confirmed}
        <c:if test="${check.confirmed eq true}">
        , waiter: ${check.admin.id}, has been issued as a check: ${check.check}
        </c:if>
        </p>

        <br>
        <h2>
            The items: <br/>
        </h2>

        <table>
          <tr><th>Code</th><th>Name</th><th>Price</th>
          <th>Total number</th><th>Check</th><th>Category</th></tr>
          <c:forEach var="i" items="${items}">
          <tr><td><c:out value="${i.id}"/></td>
          <td>${i.name}</td><td>${i.price}</td>
          <td>${i.number}</td><td>${i.check.id}</td>
          <td>${i.category}</td>
          </c:forEach>
        </table>
        <br/>
        <c:if test="${check.check eq true}">
        <form action="${pageContext.request.contextPath}/api/client/order/pay?id=${check.id}" method="post">
            Tip: <input type="number" name = "tip" min = "0" value = "0">
            <br>
            <button type="submit" class="btn btn-default" name="nextPage">Pay</button>
        </form>
        </c:if>
        <a href="${pageContext.request.contextPath}/index.jsp">index</a>
    </body>
</html>