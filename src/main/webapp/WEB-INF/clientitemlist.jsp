<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Item List</title>

     <script src="/scripts/jquery-3.3.1.min.js"></script>
        <script>
         $(window).on('load', function () {
         var itemsNumber = 1;
            $('#itemtable').on('click','.addbutton',function(){
                 var self = $(this).closest("tr");
                 var id = self.find(".id").text();
                 var name = self.find(".name").text();
                 var price = self.find(".price").text();
                 var number = self.find(".number").text();
                 var numberToAdd = self.find(".numberInput").find("input").val();
                 var category = self.find(".category").text();
                 var itemToAdd = "";
                 itemToAdd += "Id: "+id+" <input type='hidden' name='id"+itemsNumber+
                 "' value='"+id+"'/>"
                 itemToAdd += "Name: "+name+" <input type='hidden' name='name"+itemsNumber+
                 "' value='"+name+"'/>"
                 var priceToAdd;
                 priceToAdd = price * numberToAdd;
                 itemToAdd += "Number sold: "+numberToAdd+" <input type='hidden' name='numberToAdd"+itemsNumber+
                 "' value='"+numberToAdd+"'/>"
                 itemToAdd += "Total price: "+priceToAdd+" <input type='hidden' name='priceToAdd"+itemsNumber+
                 "' value='"+priceToAdd+"'/>"
                 itemToAdd += "Category: "+category+" <input type='hidden' name='category"+itemsNumber+
                 "' value='"+category+"'/>"
                 $(".addToCheck").prepend(itemToAdd);
                 itemsNumber++;
             });
         });
         </script>
</head>
    <body>
        <h2>
            List Items <br/>
        </h2>
        <button id="btn1">Append text</button>
        <table id="itemtable">
        <tr><th>Id</th><th>Name</th><th>Price</th>
        <th>Total number</th>
        <th>Number to add</th>
        <th>Category</th><th></th></tr>
        <c:forEach var="i" items="${items}">
            <tr><td class="id"><a href="item?id=<c:out value='${i.id}'/>"><c:out value="${i.id}"/></a></td>
            <td class="name">${i.name}</td><td class="price">${i.price}</td>
            <td class="numberInput"><input type="number"></td><td class="category">${i.category}</td>
            <td>
            <button class="addbutton" id="btn1">Add to check</button>
            </td>
        </c:forEach>
        </table>
        <br>
        <p>Add check:</p> <br>
        <form class="addToCheck" action="${pageContext.request.contextPath}/api/client/orders/add" method="post">
            <input type="submit" value="Make order"/>
        </form>
        <br>

        <a href="${pageContext.request.contextPath}/index.jsp">index</a>
    </body>
</html>