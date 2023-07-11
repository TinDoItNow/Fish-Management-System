<%-- 
    Document   : manageOrders
    Created on : Jun 13, 2023, 2:34:17 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="sample.dao.AccountDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/Admincss.css" type="text/css"/>
        <title>Manage Orders_Page</title>
    </head>
    <body>
        <%@include file="header_loginedAdmin.jsp" %>
        <c:if test="${sessionScope.name != null or sessionScope.name == 'Administrator'}">
            <form action = "mainController" method = "post">
                <input type="number" name="txtSearch">
                <input type="submit" value="searchOrderID" name="action" required class="button">
            </form>
            <table class="order" border="2">
                <tr>
                    <th> Order ID </th>
                    <th> Order Date </th>
                    <th> Ship Date </th>
                    <th> Status </th>
                    <th> AccID </th>
                    <th> Acc_Email</th>
                </tr>          
                <c:forEach var="order" items="${requestScope.orderList}">
                    <c:if test="${requestScope.email == null or requestScope.email == AccountDAO.getAccountByID(order.getAccID()).getEmail() or requestScope.email == ''}" >
                        <tr>
                            <td><c:out value="${order.getOrderID()}"></c:out></td>
                            <td><c:out value="${order.getOrderDate()}"></c:out></td>
                            <td><c:out value="${order.getShipDate()}"></c:out></td>
                            <td><c:out value="${order.getStatus()}"></c:out></td>
                            <td><c:out value="${order.getAccID()}"></c:out></td>
                            <td>${AccountDAO.getAccountByID(order.getAccID()).getEmail()}</td> 
                        </c:if>              
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>

