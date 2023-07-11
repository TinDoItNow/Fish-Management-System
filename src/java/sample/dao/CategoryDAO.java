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
import sample.dto.Category;
import sample.utils.DBUtils;

public class CategoryDAO {

    public static ArrayList<Category> getCates() {
        Connection cn = null;
        Category cate = null;
        ArrayList<Category> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT * FROM Categories";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        cate = new Category(rs.getInt(1), rs.getString(2));
                        list.add(cate);
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

    public static boolean updateCate(int id, String name) {
        boolean update = false;
        Connection cn = null;
        int t = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);
                String sql = "UPDATE Categories SET CateName=? WHERE CateID=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, name);
                pst.setInt(2, id);
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

    public static boolean insertCate(String name) {
        boolean update = false;
        Connection cn = null;
        int t = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);
                String sql = "INSERT INTO Categories VALUES(?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, name);
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

    public static void addCategory(String cateName) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "INSERT INTO Categories\n"
                        + " VALUES (?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, cateName);
                pst.execute();
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
    }

    public static int getNextCateID() {
        int result = 0;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT TOP 1CateID\n"
                        + "FROM Categories\n"
                        + "ORDER BY CateID DESC";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        result = rs.getInt("CateID");
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
        return (result + 1);
    }
}
