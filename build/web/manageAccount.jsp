<%-- 
    Document   : manageAccount
    Created on : Jun 13, 2023, 2:30:49 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"
           prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/Admincss.css" type="text/css"/>
        <title>Manage Account_Page</title>
    </head>
    <body>
        <c:import url="header_loginedAdmin.jsp" ></c:import>
        <c:if test="${sessionScope.name != null or sessionScope.name == 'Administrator'}">
            <form action="mainController" method="post">            
                <input type="text" name="txtSearch" value="${requestScope.txtSearch}" placeholder="Input Email" required>
                <input class="button" type="submit" value="searchAccount" name="action">    
            </form>
            <table class="order" border="2">
                <tr>
                    <th> Id</th>
                    <th> Email</th>
                    <th> Full name</th>
                    <th> Status</th>
                    <th> Phone</th>
                    <th> Role</th>
                    <th> Action</th>
                </tr>
                <c:forEach var="acc" items="${requestScope.accountList}">
                    <c:if test="${fn:contains(acc.getEmail(), requestScope.txtsearch) or requestScope.txtsearch eq null}">            
                        <tr>
                            <td><c:out value="${acc.getAccID()}"></c:out></td>
                            <td><c:out value="${acc.getEmail()}"></c:out></td>
                            <td><c:out value="${acc.getFullname()}"></c:out></td>
                            <td><c:choose>
                                    <c:when test="${acc.getStatus() eq 1}" >Active</c:when>
                                    <c:otherwise>InActive</c:otherwise>
                                </c:choose></td>
                            <td><c:out value="${acc.getPhone()}"></c:out></td>
                            <td><c:choose>
                                    <c:when test="${acc.getRole() eq 1}">Admin</c:when>
                                    <c:otherwise>User</c:otherwise>
                                </c:choose></td>
                            <td><c:if test="${acc.getRole() eq 0}">
                                    <form action="updateStatusAccount" method="post">                             
                                        <input type="hidden" value="${acc.getEmail()}" name="email">
                                        <input type="hidden" value="${acc.getStatus()}" name="status" >
                                        <button class="button" value="updateStatusAccount" name="action">Block/Unblock</button>
                                    </form>
                                </c:if>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </c:if>
    </body>
    <footer>
        <%@include file = "footer.jsp" %>
    </footer>
</html>

