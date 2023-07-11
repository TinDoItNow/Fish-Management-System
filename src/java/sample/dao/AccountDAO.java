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
import sample.dto.Account;
import sample.utils.DBUtils;

public class AccountDAO extends DBUtils {

    public static Account getAccounts(String email, String password) {

        Connection cn = null;
        Account acc = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT accID,email,password,fullname,phone,status,role\n"
                        + "FROM dbo.Accounts\n"
                        + "WHERE status=1 AND email= ? and password = ? COLLATE Latin1_General_CS_AS";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int AccID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String Fullname = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int Status = rs.getInt("status");
                    int Role = rs.getInt("role");
                    acc = new Account(AccID, Email, Password, Fullname, Status, Phone, Role);
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
        return acc;
    }

    public static ArrayList<Account> getAccount() {
        ArrayList<Account> list = new ArrayList();
        Account acc = null;
        Connection cn = null;
        int max = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select MAX(accID) as maxID from Accounts";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    max = rs.getInt("maxID");
                }
                for (int t = 1; t <= max; t++) {
                    String getAcc = "select accID,email,password,fullname,phone,status,role\n"
                            + "from dbo.Accounts\n"
                            + "where accID=?";
                    pst = cn.prepareStatement(getAcc);
                    pst.setInt(1, t);
                    rs = pst.executeQuery();
                    if (rs != null && rs.next()) {
                        int AccID = rs.getInt("accID");
                        String Email = rs.getString("email");
                        String Password = rs.getString("password");
                        String FullName = rs.getString("fullname");
                        String Phone = rs.getString("phone");
                        int Status = rs.getInt("status");
                        int Role = rs.getInt("role");
                        acc = new Account(AccID, Email, Password, FullName, Status, Phone, Role);
                        list.add(acc);
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

    public static ArrayList<Account> searchAccounts(String email) {

        Connection cn = null;
        ArrayList<Account> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT accID,email,password,fullname,phone,status,role\n"
                        + "FROM dbo.Accounts\n"
                        + "WHERE status=1 AND email LIKE ? ";
                PreparedStatement pst = cn.prepareStatement(sql);
                email = "%" + email + "%";
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int AccID = rs.getInt("accID");
                        String Email = rs.getString("email");
                        String Password = rs.getString("password");
                        String Fullname = rs.getString("fullname");
                        String Phone = rs.getString("phone");
                        int Status = rs.getInt("status");
                        int Role = rs.getInt("role");
                        Account acc = new Account(AccID, Email, Password, Fullname, Status, Phone, Role);
                        list.add(acc);
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

    public static boolean updateAccountToken(String email, String token) {
        boolean reStatus = false;
        Connection cn = null;
        int t = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update Accounts set token= ? where email = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, token);
                pst.setString(2, email);
                pst.execute();
                t = pst.getUpdateCount();
                if (t > 0) {
                    reStatus = true;
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
        return reStatus;
    }

    public static boolean updateAccount(String email, String newPassword, String newFullname, String newPhone) {
        boolean update = false;
        Connection cn = null;
        int t = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);
                String sql = "update Accounts set password=?, fullname=?, phone=? where email=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(4, email);
                pst.setString(1, newPassword);
                pst.setString(2, newFullname);
                pst.setString(3, newPhone);
                pst.execute();
                t = pst.getUpdateCount();
                if (t > 0) {
                    update = true;
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

    public static boolean insertAccount(String newEmail, String newPassword, String newFullname, String newPhone, int newSatus, int newRole) {
        boolean insert = false;
        Connection cn = null;
        int t = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);
                String sql = "insert into Accounts(email,fullname,password,phone,role,status) values(?,?,?,?,?,?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, newEmail);
                pst.setString(2, newFullname);
                pst.setString(3, newPassword);
                pst.setString(4, newPhone);
                pst.setInt(5, newRole);
                pst.setInt(6, newSatus);
                pst.execute();
                t = pst.getUpdateCount();
                if (t > 0) {
                    insert = true;
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
        return insert;
    }

    public static Account getAccount(String token) {
        Connection cn = null;
        Account acc = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select accID,email,password,fullname,phone,status,role\n"
                        + "from dbo.Accounts\n"
                        + "where status=1 and token = ? COLLATE Latin1_General_CS_AS";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, token);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int AccID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String FullName = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int Status = rs.getInt("status");
                    int Role = rs.getInt("role");
                    acc = new Account(AccID, Email, Password, FullName, Status, Phone, Role);
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
        return acc;
    }

    public static boolean updateAccountStatus(String email, int status) {
        boolean reStatus = false;
        Connection cn = null;
        int t = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update Accounts set status= ? where email = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setString(2, email);
                pst.execute();
                t = pst.getUpdateCount();
                if (t > 0) {
                    reStatus = true;
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
        return reStatus;
    }

    public static boolean updateAccountProfile(String email, String newFullname, String newPhone) {
        boolean update = false;
        Connection cn = null;
        int t = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);
                String sql = "update Accounts set fullname=?, phone=? where email=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, newFullname);
                pst.setString(2, newPhone);
                pst.setString(3, email);
                pst.execute();
                t = pst.getUpdateCount();
                if (t > 0) {
                    update = true;
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

    public static Account getAccountByID(int id) {
        Connection cn = null;
        Account acc = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select accID,email,password,fullname,phone,status,role\n"
                        + "from dbo.Accounts\n"
                        + "where accID= ? ";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, id);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int AccID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String FullName = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int Status = rs.getInt("status");
                    int Role = rs.getInt("role");
                    acc = new Account(AccID, Email, Password, FullName, Status, Phone, Role);
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
        return acc;
    }

    public static void main(String[] args) {
        //test login
        Account acc = AccountDAO.getAccounts("test@gmail.com", "test");
        if (acc != null) {
            if (acc.getRole() == 1) {
                System.out.println("i am an admin");
            } else {
                System.out.println("i am a user");
            }
        } else {
            System.out.println("login fail");
        }
    }
}
