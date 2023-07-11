<%-- 
    Document   : header_loginedAdmin
    Created on : Jun 13, 2023, 2:23:46 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header_Login ADMIN_Page</title>
        <link rel="stylesheet" href="css/Admincss.css" type="text/css"/>
    </head>
    <body>
        <header>
            <ul>
                <li><a href="mainController?action=manageAccount">Manage Account</a></li>
                <li><a href="mainController?action=manageOrders">Manage Orders</a></li>
                <li><a href="mainController?action=manageNemos">Manage Nemos</a></li>
                <li><a href="mainController?action=manageCategories">Manage Categories</a></li>
                <li>Welcome ${sessionScope.name}  | <a href="mainController?action=logout">Logout</a></li>
            </ul>
        </header>
    </body>
</html>

