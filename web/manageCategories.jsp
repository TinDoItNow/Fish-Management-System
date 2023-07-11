<%-- 
    Document   : manageCategories
    Created on : Jun 13, 2023, 2:31:17 AM
    Author     : Admin
--%>
<%@page import="sample.dao.CategoryDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/Admincss.css" type="text/css"/>
        <title>Manage Categories_Page</title>
    </head>
    <body>
        <%@include file="header_loginedAdmin.jsp" %>
        <c:if test="${sessionScope.name != null or sessionScope.name == 'Administrator'}">
            <form action="mainController" method="post">
                <details>
                    <summary><a>Create new category</a></summary>
                    <div><p>Category ID: </p><input type="text" name="CateID" value="${CategoryDAO.getNextCateID()}" readonly=""></div>
                    <div><p></p>Category Name: </p><input type="text" name="CateName" value="" required=""></span></div>
                    <div><p><button type="submit" value="createCategories" name="action" class="button">Save</button></p></div>                     
                </details>
            </form> 
            <table class="order" border="2">
                <tr>
                    <th>Cate ID</th>
                    <th>Cate Name</th>               
                    <th>Update</th>
                </tr>          
                <c:forEach var="cate" items="${requestScope.categoriesList}">
                    <tr>
                        <td>${cate.getCateID()}</td>
                        <td>${cate.getCateName()}</td>
                        <td class="updatebutton">
                            <form action="mainController" method="post">
                                <button type="submit" value="updateCategories" name="action" class="button">Update</button>
                                <div><input type="hidden" name="CateID" value="${cate.getCateID()}"></div>
                                <p>Cate Name:</p><input type="text" name="CateName" required="">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table> 
        </c:if>      
    </body>
</html>
