<%-- 
    Document   : viewNemo
    Created on : Jun 18, 2023, 8:09:20 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/Admincss.css" type="text/css">
    </head>
    <body>
        <jsp:useBean id="nemoobj" class="sample.dto.Nemo" scope="request" />
        <table>
            <tr><td rowspan="8"><img src="<jsp:getProperty name="nemoobj" property="imgpath"></jsp:getProperty>"</td></tr>
            <tr><td>id:<jsp:getProperty name="nemoobj" property="id"></jsp:getProperty></td></tr>
            <tr><td>Product name:<jsp:getProperty name="nemoobj" property="name"></jsp:getProperty></td></tr>
            <tr><td>Price:${nemoobj.price}</td></tr>
            <tr><td>Description:${nemoobj.description}</td></tr>
            <tr><td>Status:${nemoobj.status}</td></tr>
            <tr><td>Cate id:${nemoobj.cateid}</td></tr>
            <tr><td>Category:${nemoobj.catename}</td></tr>
        </table>
    </body>
</html>

