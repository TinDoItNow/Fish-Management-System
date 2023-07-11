<%-- 
    Document   : orderDetail
    Created on : Jun 13, 2023, 2:34:55 AM
    Author     : Admin
--%>

<%@page import="sample.dao.OrderDAO"%>
<%@page import="sample.dto.OrderDetail"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Details_Page</title>
    </head>
    <body>
        <header><%@include file="header_loginedUser.jsp" %></header>
        <section>
            <h3><a href="mainController?action=logout">Logout</a></h3>
            <a href="personalPage.jsp">View all orders</a>
        </section>
        <section>
            <% String orderid = request.getParameter("orderid");
                if (orderid != null) {
                    int orderID = Integer.parseInt(orderid.trim());
                    ArrayList<OrderDetail> list = OrderDAO.getOrderDetail(orderID);
                    if (list != null && !list.isEmpty()) {
                        int money = 0;
                        for (OrderDetail detail : list) {
            %>
            <table class="order" border="2">
                <tr>
                    <th> Order ID </th>
                    <th> Nemo ID </th>
                    <th> Nemo Name </th>
                    <th> Image </th>
                    <th> Quantity </th>
                </tr>
                <tr><th><%=detail.getOrderID()%></th>
                    <th><%=detail.getNemoID()%></th>
                    <th><%=detail.getNemoName()%></th>
                    <th><img class="plantimg"  src="<%=detail.getImgPath()%>" /></th>
                    <th><%=detail.getQuantity()%></th>
                        <% money = money + detail.getPrice() * detail.getQuantity(); %>
                </tr>
            </table>
            <%
                }
            %>
            <h3>
                Total Money : <%=money%>
            </h3>
            <%
            } else {
            %>
            <h1>You don't have any order</h1>
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
