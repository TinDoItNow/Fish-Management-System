/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import sample.dto.Nemo;
import sample.utils.DBUtils;

public class NemoDAO {

    public static ArrayList<Nemo> getNemos(String keyword, String searchby) {
        ArrayList<Nemo> list = new ArrayList();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null && searchby != null) {
                String sql = "select NID, NName,price,imgPath,description,status,Nemos.CateID as 'CateID',CateName\n"
                        + "from Nemos join Categories on Nemos.CateID = Categories.CateID\n";
                if (searchby.equalsIgnoreCase("by name")) {
                    sql = sql + "where Nemos.NName like ?";
                } else {
                    sql = sql + "where CateName like ?";
                }
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + keyword + "%");
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("NID");
                        String name = rs.getString("NName");
                        int price = rs.getInt("price");
                        String imgPath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateID = rs.getInt("CateID");
                        String cateName = rs.getString("CateName");
                        Nemo nemo = new Nemo(id, name, price, imgPath, description, status, cateID, cateName);
                        list.add(nemo);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static ArrayList<Nemo> getNemos() {
        ArrayList<Nemo> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = ("SELECT NID, NName, price, imgPath, description, status, n.CateID AS 'CateID', CateName\n"
                        + "FROM Categories c JOIN Nemos n ON c.CateID = n.CateID\n");

                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("NID");
                        String name = rs.getString("NName");
                        int price = rs.getInt("price");
                        String imgpath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateid = rs.getInt("CateID");
                        String catename = rs.getString("CateName");
                        Nemo nemo = new Nemo(id, name, price, imgpath, description, status, cateid, catename);
                        list.add(nemo);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Nemo getNemoByID(int PID) {
        Connection cn = null;
        Nemo nemo = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select NID,NName,price,imgPath,description,status,Nemos.CateID as CateID,CateName from Nemos, Categories where Nemos.CateID=Categories.CateID and NID =?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, PID);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("NID");
                        String name = rs.getString("NName");
                        int price = rs.getInt("price");
                        String imgPath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateID = rs.getInt("CateID");
                        nemo = new Nemo(id, name, price, imgPath, description, status, cateID, rs.getString("CateName"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return nemo;

    }

    public static boolean updateNemo(int id, String name, int price, String description, int status, int cateID) {
        boolean update = false;
        Connection cn = null;
        int t = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);
                String sql = "update Nemos set NName=? , price=?, description=?, status=? ,CateID=? where NID=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, name);
                pst.setInt(2, price);
                pst.setString(3, description);
                pst.setInt(4, status);
                pst.setInt(5, cateID);
                pst.setInt(6, id);
                if (pst.executeUpdate() > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.commit();
                    cn.setAutoCommit(true);
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return update;
    }

    public static boolean insertNemo(String name, int price, String imgPath, String description, int status, int cateID) {
        boolean update = false;
        Connection cn = null;
        int t = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);
                String sql = "insert into Nemos values(?,?,?,?,?,?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, name);
                pst.setInt(2, price);
                pst.setString(3, imgPath);
                pst.setString(4, description);
                pst.setInt(5, status);
                pst.setInt(6, cateID);
                if (pst.executeUpdate() > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.commit();
                    cn.setAutoCommit(true);
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return update;
    }
}
