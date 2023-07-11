/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import sample.dto.Order;
import sample.dto.OrderDetail;
import sample.utils.DBUtils;

/**
 *
 * @author Admin
 */
public class OrderDAO {

    public static ArrayList<Order> getOrders(int ordID) {
        Connection cn = null;
        ArrayList<Order> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT OrderID, OrdDate, shipdate, o.status AS status, o.AccID AS AccID\n"
                        + "FROM Orders o JOIN Accounts a ON o.AccID = a.accID\n"
                        + "WHERE OrderID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, ordID);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {

                        int orderID = rs.getInt("OrderID");
                        String orderDate = rs.getString("OrdDate");
                        String shipDate = rs.getString("shipdate");
                        int status = rs.getInt("status");
                        int accID = rs.getInt("AccID");

                        Order order = new Order(orderID, orderDate, shipDate, status, accID);
                        list.add(order);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return list;
    }

    public static ArrayList<Order> getOrders(String email) throws Exception {
        Connection cn = null;
        Order order = null;
        ArrayList<Order> list = new ArrayList<>();
        cn = DBUtils.makeConnection();
        String sql = " SELECT * FROM Accounts a JOIN Orders o ON a.accID=o.AccID WHERE email= ?";
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        if (rs != null) {
            while (rs.next()) {
                int orderID = rs.getInt("OrderID");
                String orderDate = rs.getString("OrdDate");
                String shipDate = rs.getString("shipDate");
                int status = rs.getInt(12);
                int accID = rs.getInt("accID");
                order = new Order(orderID, orderDate, shipDate, status, accID);
                list.add(order);
            }
        }
        return list;
    }

    public static ArrayList<OrderDetail> getOrderDetail(int orderID) throws Exception {
        ArrayList<OrderDetail> list = new ArrayList<>();
        Connection cn = null;
        cn = DBUtils.makeConnection();
        String sql = "SELECT DetailId,OrderID,NID,NName,price,imgPath,quantity FROM OrderDetail, Nemos WHERE OrderID = ? AND OrderDetail.FID=Nemos.NID";
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setInt(1, orderID);
        ResultSet rs = pst.executeQuery();
        if (rs != null) {
            while (rs.next()) {
                int orderDetailID = rs.getInt("DetailId");
                int oID = rs.getInt("OrderID");
                int nemoID = rs.getInt("NID");
                String nemoName = rs.getString("NName");
                int price = rs.getInt("price");
                String ImgPath = rs.getString("imgPath");
                int quantity = rs.getInt("quantity");
                list.add(new OrderDetail(orderDetailID, oID, nemoID, nemoName, price, ImgPath, quantity));
            }
        }
        return list;
    }

    public static boolean orderAgain(int orderID) {
        Order order = null;
        int neworderid = 0;
        ArrayList<OrderDetail> detail = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT * FROM Orders WHERE OrderID=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, orderID);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int orderid = rs.getInt("OrderID");
                        String orderDate = rs.getString("OrdDate");
                        String shipDate = rs.getString("shipDate");
                        int status = rs.getInt("status");
                        int accID = rs.getInt("accID");
                        order = new Order(orderid, orderDate, shipDate, status, accID);
                    }
                }
                sql = "SELECT * FROM OrderDetail WHERE OrderID = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, orderID);
                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int orderDetailID = rs.getInt("DetailId");
                        int oID = rs.getInt("OrderID");
                        int fishID = rs.getInt("FID");
                        int quantity = rs.getInt("quantity");
                        detail.add(new OrderDetail(orderDetailID, oID, fishID, null, 0, null, quantity));
                    }
                }

                cn.setAutoCommit(false);
                sql = "INSERT Orders(OrdDate,status,AccID) VALUES(?,?,?)";
                pst = cn.prepareStatement(sql);
                pst.setDate(1, new Date(System.currentTimeMillis()));
                pst.setInt(2, 1);
                pst.setInt(3, order.getAccID());
                pst.executeUpdate();
                cn.commit();
                sql = "SELECT TOP1 1 orderID FROM Orders ORDER BY orderId DESC";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    neworderid = rs.getInt("orderID");
                }
                for (OrderDetail de : detail) {
                    sql = "INSERT OrderDetail VALUES(?,?,?)";
                    pst = cn.prepareStatement(sql);
                    pst.setInt(1, neworderid);
                    pst.setInt(2, de.getNemoID());
                    pst.setInt(3, de.getQuantity());
                    pst.executeUpdate();
                    cn.commit();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.setAutoCommit(true);
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static boolean insertOrder(String email, HashMap<String, Integer> cart) {
        boolean result = true;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                int accid = 0, orderid = 0;
                cn.setAutoCommit(false);
                String sql = "SELECT accID FROM Accounts WHERE Accounts.email=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    accid = rs.getInt("accID");
                }
                System.out.println("accountid:" + accid);
                Date d = new Date(System.currentTimeMillis());
                System.out.println("order date:" + d);
                sql = "INSERT Orders(OrdDate,status,AccID) VALUES(?,?,?)";
                pst = cn.prepareStatement(sql);
                pst.setDate(1, d);
                pst.setInt(2, 1);
                pst.setInt(3, accid);
                pst.executeUpdate();
                sql = "SELECT TOP 1 orderID FROM Orders ORDER BY orderId DESC";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    orderid = rs.getInt("orderID");
                }
                System.out.println("orderid:" + orderid);
                Set<String> nids = cart.keySet();
                for (String nid : nids) {
                    sql = "INSERT OrderDetail VALUES(?,?,?)";
                    pst = cn.prepareStatement(sql);
                    pst.setInt(1, orderid);
                    pst.setInt(2, Integer.parseInt(nid.trim()));
                    pst.setInt(3, cart.get(nid));
                    pst.executeUpdate();
                    cn.commit();
                    cn.setAutoCommit(true);
                }
                return true;
            } else {
                System.out.println("Cannot insert order");
            }
        } catch (Exception e) {
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (Exception a) {
                e.printStackTrace();

            }
            e.printStackTrace();
            result = false;
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                };
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static ArrayList<Order> getOrders() {
        Connection cn = null;
        ArrayList<Order> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT OrderID, OrdDate, shipdate, o.status AS status, o.AccID AS AccID\n"
                        + "FROM Orders o JOIN Accounts a ON o.AccID = a.accID\n";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int orderID = rs.getInt("OrderID");
                        String orderDate = rs.getString("OrdDate");
                        String shipDate = rs.getString("shipdate");
                        int status = rs.getInt("status");
                        int accID = rs.getInt("AccID");
                        Order order = new Order(orderID, orderDate, shipDate, status, accID);
                        list.add(order);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return list;
    }

//    public static ArrayList<Order> getOrders() {
//        Connection cn = null;
//        Order order = null;
//        ArrayList<Order> list = new ArrayList<>();
//        try {
//            cn = DBUtils.makeConnection();
//            if (cn != null) {
//                String sql = "select * from  Orders";
//                PreparedStatement pst = cn.prepareStatement(sql);
//                ResultSet rs = pst.executeQuery();
//                if (rs != null) {
//                    while (rs.next()) {
//                        int orderID = rs.getInt(1);
//                        String orderDate = rs.getString(2);
//                        String shipDate = rs.getString(3);
//                        int status = rs.getInt(4);
//                        int accID = rs.getInt(5);
//                        order = new Order(orderID, orderDate, shipDate, status, accID);
//                        list.add(order);
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (cn != null) {
//                try {
//                    cn.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return list;
//    }
    public static ArrayList<Order> getOrdersByDate(String email, String from, String to) {
        Connection cn = null;
        ArrayList<Order> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT OrderID, OrdDate, shipdate, o.status AS status, o.AccID AS AccID\n"
                        + "FROM Orders o JOIN Accounts a ON o.AccID = a.accID\n"
                        + "WHERE o.OrdDate <= ? AND o.OrdDate >= ? AND email=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, to);
                pst.setString(2, from);
                pst.setString(3, email);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {

                        int orderID = rs.getInt("OrderID");
                        String orderDate = rs.getString("OrdDate");
                        String shipDate = rs.getString("shipdate");
                        int status = rs.getInt("status");
                        int accID = rs.getInt("AccID");

                        Order order = new Order(orderID, orderDate, shipDate, status, accID);
                        list.add(order);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return list;
    }
    public static boolean cancelOrder(int orderID) {
        boolean status = false;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "UPDATE Orders SET status=3 WHERE OrderID=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, orderID);
                if (pst.executeUpdate() > 0) {
                    status = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return status;
    }
}
