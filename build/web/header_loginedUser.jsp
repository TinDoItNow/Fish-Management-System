<%-- 
    Document   : header_loginedUser
    Created on : Jun 13, 2023, 2:24:11 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header_Login User_Page</title>
        <link rel="stylesheet" href="css/Admincss.css" type="text/css"/>
    </head>
    <body>
        <nav>
            <header>
                <ul>
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="changeProfile.jsp">Change profile</a></li>
                    <li><a href="personalPage.jsp?orderprocess=2">Complete orders</a></li>
                    <li><a href="personalPage.jsp?orderprocess=3">Canceled orders</a></li>
                    <li><a href="personalPage.jsp?orderprocess=1">Processing orders</a></li>
                    <li><form action="personalPage.jsp" method="post">
                            From<input type="date" name="from" required=""> to <input type="date" name="to" required="">
                            <button value="SearchByDate" name="action" class="button">Search</button>
                        </form>
                    </li>
                </ul>
            </header>
        </nav>
    </body>
</html>

