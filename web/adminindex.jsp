<%-- 
    Document   : adminindex
    Created on : Jun 13, 2023, 2:18:52 AM
    Author     : Admin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADMIN Index_Page</title>
    </head>
    <body>
        <c:import url="header_loginedAdmin.jsp"></c:import>
        <c:if test="${sessionScope.name != null or sessionScope.name != 'Administrator'}">
            <section>
                <h1 style="font-size: 80px; color: aquamarine; align-items: center">Welcome Admin♥</h1>
            </section>
        </c:if>
    </body>
</html>

