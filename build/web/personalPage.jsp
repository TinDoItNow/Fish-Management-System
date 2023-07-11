<%-- 
    Document   : personalPage
    Created on : Jun 13, 2023, 2:35:24 AM
    Author     : Admin
--%>

<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="java.sql.Date"%>
<%@page import="sample.dao.AccountDAO"%>
<%@page import="sample.dto.Account"%>
<%@page import="sample.dao.OrderDAO"%>
<%@page import="sample.dto.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Personal_Page</title>
    </head>
    <body>
        <%
            String name = (String) session.getAttribute("name");
            String email = (String) session.getAttribute("email");
            String action = (String) request.getParameter("orderprocess");
            Cookie[] c = request.getCookies();
            boolean login = false;
            int t = -1;
            if (action != null) {
                t = Integer.parseInt(action);
            }
            if (name == null) {
                String token = "";
                for (Cookie aCookie : c) {
                    if (aCookie.getName().equals("selector")) {
                        token = aCookie.getValue();
                        Account acc = AccountDAO.getAccount(token);
                        if (acc != null) {
                            name = acc.getFullname();
                            email = acc.getEmail();
                            login = true;
                        }
                    }
                }
            } else {
                login = false;
            }
            if (login || name != null) {
        %>
        <header><%@include file="header_loginedUser.jsp" %></header>
        <section>
            <h2 style="color: red">${requestScope.msg}</h2>
            <h3 style="color: aquamarine">Welcome <%=name%> come back</h3>                   
            <h3><a href="mainController?action=logout">Logout</a></h3>
        </section>
        <section>
            <%
                ArrayList<Order> list = OrderDAO.getOrders(email);
                String[] status = {"", "processing", "completed", "canceled"};
                String from = request.getParameter("from");
                String to = request.getParameter("to");
                LocalDate date1 = LocalDate.MIN;
                LocalDate date2 = LocalDate.MAX;
                if (from != null && to != null) {
                    date1 = LocalDate.parse(from);
                    date2 = LocalDate.parse(to);
                }
                if (list != null && !list.isEmpty()) {
                    for (Order order : list) {
                        LocalDate date3 = LocalDate.parse(order.getOrderDate());
                        if (order.getStatus() == t || action == null) {
                            if (date1.isBefore(date3) && date2.isAfter(date3)) {
            %>
            <table class="order" border="2">
                <tr>
                    <th> Order ID </th>
                    <th> Order Date </th>
                    <th> Ship Date </th>
                    <th> Order's status </th>
                    <th> Action </th>
                </tr>
                <tr>
                    <td><%=order.getOrderID()%></td>
                    <td><%=order.getOrderDate()%></td>
                    <td><%=order.getShipDate()%></td>
                    <td><%=status[order.getStatus()]%><br/><% if (order.getStatus() == 1) {%><a href="mainController?action=cancelOrder&orderID=<%=order.getOrderID()%>">Cancel order</a><% }%></td>
                    <td><a href="orderDetail.jsp?orderid=<%=order.getOrderID()%>">Detail</a></br>
                        <%if (order.getStatus() == 3) {%><a href="mainController?action=orderAgain&orderID=<%=order.getOrderID()%>">Order again</a><% } %></td>
                </tr>
            </table>
            <%        }
                    }
                }
            } else {
            %>
            <p>You don't have any order</p>
            <%}%>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
        <%}%>
    </body>
</html>

