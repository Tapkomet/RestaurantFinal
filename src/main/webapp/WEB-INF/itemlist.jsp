<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Item List</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="/css/style.css" type="text/css">
</head>
    <body>
        <h2>
            List Items <br/>
        </h2>
        <c:if test="${not empty sql_error_message}">
            <p class="error">${sql_error_message}</p>
        </c:if>
        <table>
        <tr><th>Id</th><th>Name</th><th>Price</th>
        <th>Total number</th><th></th></tr>
        <c:forEach var="i" items="${items}">
            <tr><td><a href="item?id=<c:out value='${i.id}' />"> <c:out value="${i.id}"/></a></td>
            <td>${i.name}</td><td>${i.price}</td>
            <td>${i.number}</td>
            <td>
            <form action="${pageContext.request.contextPath}/api/admin/deleteItem?id=${i.id}"
             method="post">
            <input type="submit" value="Delete"/>
            </form>
            </td>
        </c:forEach>
        </table>

        <form action="${pageContext.request.contextPath}/api/admin/items" method="post">
        	<c:if test="${page > 1}">
        	    <button type="submit" class="btn btn-default" name="nextPage" value='previous'>
                    Previous
        	    </button>
        	</c:if>
        	<c:if test="${page < lastPage}">
                <button type="submit" class="btn btn-default" name="nextPage" value='next'>
        	            Next
        	    </button>
        	</c:if>
        	<input type="hidden" name = "page" value="${page}">
        	<input type="hidden" name = "tosort" value="${tosort}">
        </form>

        <br>
        <form action="${pageContext.request.contextPath}/api/admin/items" method="get">
        Sort by: <br>
        <input type="radio" name="tosort" value="id"
         <c:if test="${tosort eq 'id'}">checked</c:if>>Id<br>
        <input type="radio" name="tosort" value="name"
         <c:if test="${tosort eq 'name'}">checked</c:if>>Name<br>
        <input type="radio" name="tosort" value="price"
         <c:if test="${tosort eq 'price'}">checked</c:if>>Price<br>
        <input type="submit" value="Sort"/>
        </form>
        <br>
        <form action="${pageContext.request.contextPath}/api/admin/addItem" method="post">
             Id <input type="number" name="id"/><br>
             <c:if test="${not empty id_error_message}">
                <p class="error">${id_error_message}</p>
             </c:if>
             Name <input type="text" name="name"/><br>
             <c:if test="${not empty name_error_message}">
                <p class="error">${name_error_message}</p>
             </c:if>
             Number in stock <input type="number" name="number"/><br>
             <c:if test="${not empty number_error_message}">
                <p class="error">${number_error_message}</p>
             </c:if>
             Price per unit or kilo <input type="number" name="price"/><br>
             <c:if test="${not empty price_error_message}">
                <p class="error">${price_error_message}</p>
             </c:if>
             <input type="submit"/>
        </form>

        <br/>
        <a href="${pageContext.request.contextPath}/index.jsp">index</a>
    </body>
</html>