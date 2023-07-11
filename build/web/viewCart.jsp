<%-- 
    Document   : viewCart
    Created on : Jun 13, 2023, 2:36:23 AM
    Author     : Admin
--%>

<%@page import="sample.dao.NemoDAO"%>
<%@page import="sample.dto.Nemo"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart_Page</title>
    </head>
    <body>
        <header><%@include file="header.jsp" %></header>
        <section>
            <%
                String name = (String) session.getAttribute("name");
                if (name != null) {
            %>
            <h3>Welcome <%=name%> come back!</h3>
            <h3><a href="mainController?action=logout">Logout</a></h3>
            <h3><a href="personalPage.jsp">Personal Page</a></h3>
            <%
                }
            %>
        </section>
        <font style="color: red"><%=request.getAttribute("WARNING") == null ? "" : (String) request.getAttribute("WARNING")%></font>
        <section>
            <table style="width: 100%" class="shopping" border="1">       
                <tr><th>Product ID</th><th>Quantity</th><th colspan="2">Action</th></tr>
                        <%
                            HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");
                            int money = 0;
                            if (cart != null) {
                                Set<String> nids = cart.keySet();
                                for (String nid : nids) {
                                    int quantity = cart.get(nid);
                                    Nemo nemo = NemoDAO.getNemoByID(Integer.parseInt(nid));
                                    if (nemo != null) {
                        %>
                <form action="mainController" method="post">
                    <tr><td><input type="hidden" value="<%=nid%>" name="nid"><a href="mainController?action=getNemo&nid=<%=nid%>"><%=nid%></a></td>
                        <td><input type="number" value="<%=quantity%>" min="0" name="quantity" required="" ></td>
                        <td>Price: <%=nemo.getPrice()%></td>
                        <td><img src="<%=nemo.getImgpath()%>" class="plantimg"></td>
                        <td><button value="update" name="action">Update</button></td>
                        <td><button value="delete" name="action">Delete</button></td>
                        </td>
                    </tr>
                </form>
                <%
                    money = money + nemo.getPrice() * quantity;
                %>

                <%}
                    }
                } else {
                %>
                <tr><td>Your cart is empty</td></tr>
                <%
                    }
                %>
                <tr><td>Total money:</td><td colspan="5"> <%=money%></td></tr>
                <tr><td>Order Date:</td><td colspan="5"> <%= new java.util.Date()%></td></tr>
                <tr><td>Ship Date:</td><td colspan="5"> N/A</td></tr>
            </table>
        </section>
        <section>
            <form action="mainController" method="post">
                <button value="saveOrder" name="action" class="button">Save Orders</button>
            </form>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
