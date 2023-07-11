<%-- 
    Document   : sendOTP
    Created on : Jun 13, 2023, 1:56:16 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Send OTP!</title>
    </head>
    <body>
        <header>
            <%@include file = "header.jsp" %>
        </header>
        <h1>Servlet send OTP at ${contextPath} </h1>
        <p> Please check your email : ${email} a confirm code is sent to you! Successfull </p>
        <a href="LoARe.jsp"> Main Page</a>
    </body>
</html>