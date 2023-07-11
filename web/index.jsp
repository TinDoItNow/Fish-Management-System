<%-- 
    Document   : index
    Created on : Jun 13, 2023, 2:25:41 AM
    Author     : Admin
--%>

<%@page import="sample.dao.NemoDAO"%>
<%@page import="sample.dto.Nemo"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <section>
            <%
                String keyword = request.getParameter("txtsearch");
                String searchby = request.getParameter("searchby");
                ArrayList<Nemo> list = null;
                String[] tmp = {"out of stock", "available"};
                if (keyword == null && searchby == null) {
                    list = NemoDAO.getNemos("", "");
                } else {
                    list = NemoDAO.getNemos(keyword, searchby);
                }
                if (list != null && !list.isEmpty()) {
                    for (Nemo nemo : list) {%>
            <table class="displaytable">                     
                <tr><td><img src="<%=nemo.getImgpath()%>" class="plantimg" >
                    <td>Product ID :<%=nemo.getId()%></td>
                    <td>Name :<%=nemo.getName()%></td>
                    <td>Price :<%=nemo.getPrice()%></td>
                    <td>Category :<%=nemo.getCatename()%></td>
                    <td>Status : <%=tmp[nemo.getStatus()]%></td>
                    <td><a href="mainController?action=addToCart&nid=<%= nemo.getId()%>">Add to cart</a></td></tr>
            </table>
            <%
                    }
                }
            %>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>

