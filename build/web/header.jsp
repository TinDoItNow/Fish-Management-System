<%-- 
    Document   : header
    Created on : Jun 13, 2023, 2:22:45 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header_Page</title>
        <link rel="stylesheet" href="css/mycss.css" type="text/css"/>
    </head>
    <body>
        <header>
            <nav>
                <a href="LoARe.jsp"><img src="images/logo.jpg" id="logo"></a>
                <ul>                 
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="viewCart.jsp">View cart</a></li>
                    <li><a href="personalPage.jsp">Personal Page</a></li>
                    <li><form action="index.jsp" method="post" class="formsearch">
                            <input type="text" name="txtsearch" value="<%= (request.getParameter("txtsearch") == null) ? "" : request.getParameter("txtsearch")%>">
                            <select name="searchby">
                                <option>By Name</option>
                                <option>By Category</option>                                      
                            </select>
                            <button value="search" name="action">Search</button>
                        </form></li>
                </ul>
            </nav>
        </header>
    </body>
</html>

