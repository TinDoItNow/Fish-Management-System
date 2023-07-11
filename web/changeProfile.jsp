<%-- 
    Document   : changeProfile.jsp
    Created on : Jun 13, 2023, 2:20:19 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Profile_Page</title>
    </head>
    <body>
        <div>
            <%@include file="header_loginedUser.jsp" %>
        </div>
        <div style="padding: 200px">
            <c:if test="${sessionScope.name != null}">
                <form action="mainController" method="post">
                    <div>New full name: <input type="text" placeholder="Old Name: ${sessionScope.name}" name="name" required=""></div>
                    <div>New phone:     <input type="text" name="phone" pattern="\d{1,}" required=""></div>
                    <input type="hidden" value="${sessionScope.email}" name="email">
                    <button class="button" action="name" value="changeProfile">Save</button>
                </form>
            </div>
        </c:if>
    </body>
</html>

