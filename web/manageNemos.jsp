<%-- 
    Document   : manageNemos
    Created on : Jun 13, 2023, 2:33:42 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/Admincss.css" type="text/css"/>
        <title>Manage Nemos_Page</title>
    </head>
    <body>
        <%@include file="header_loginedAdmin.jsp" %>
        <c:if test="${sessionScope.name != null or sessionScope.name == 'Administrator'}">
            <form action="mainController" method="post">
                <details>
                    <summary><a>Add Nemo</a></summary>
                    <input type="hidden" name="NID" value="" required="">
                    <div><span><p> Nemo Name:</p><input type="text" name="NName" required=""></span></div>
                    <div><span><p> Price:</p><input type="text" name="Price" pattern="\d{1,}" required=""></span></div>
                    <div><span><p> Image</p><input type="text" name="imgPath" required=""></span></div>
                    <div><span><p> Description:</p><input type="text" name="Description" required=""></span></div>
                    <div><span><p> Status:</p><input type="number" name="status" min="0" max="1" required=""></span></div>
                    <div><span><p> Cate ID:</p><input type="text" name="CateID" pattern="\d" required=""></span></div>
                    <div><span><button type="submit" value="createNemo" name="action" class="button">Create</button></span></div>                  
                </details>
            </form>
            <table class="order" border="2">
                <tr>
                    <th> Nemo ID </th>
                    <th> Nemo Name </th>
                    <th> Price </th>
                    <th> Image </th> 
                    <th> Description </th>
                    <th> Status </th>
                    <th> CateID </th>
                    <th> CateName </th>
                    <th> Update </th>
                </tr>          
                <c:forEach var="nemo" items="${requestScope.nemosList}">
                    <tr>
                        <td><c:out value="${nemo.getId()}"></c:out></td>
                        <td><c:out value="${nemo.getName()}"></c:out></td>
                        <td><c:out value="${nemo.getPrice()}"></c:out></td>
                        <td><img src="${nemo.getImgpath()}" /></td>
                        <td><c:out value="${nemo.getDescription()}"></c:out></td>
                        <td><c:out value="${nemo.getStatus()}"></c:out></td>
                        <td><c:out value="${nemo.getCateid()}"></c:out></td>
                        <td><c:out value="${nemo.getCatename()}"></c:out></td>
                            <td class="updatebutton">
                                <form action="mainController">
                                    <button type="submit" value="updateNemo" name="action" class="button">Update</button>
                                    <div><input type="hidden" name="NID" value="${nemo.getId()}">
                                    <p>Nemo Name:</p><input type="text" name="NName" required="">
                                    <p>Price:</p><input type="text" name="Price" pattern="\d{1,}" required="">
                                    <p>Description:</p><input type="text" name="Description" required="">
                                    <p>Status:</p><input type="number" name="status" min="0" max="1" required="">
                                    <p>CateID:</p><input type="text" name="CateID" pattern="\d" required=""></div>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>  
        </c:if>
    </body>
</html>

