/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dto;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class OrderDetail implements Serializable {

    private int orderDetailID;
    private int orderID;
    private int nemoID;
    private String nemoName;
    private int price;
    private String imgPath;
    private int quantity;

    public OrderDetail() {
    }

    public OrderDetail(int orderDetailID, int orderID, int nemoID, String nemoName, int price, String imgPath, int quantity) {
        this.orderDetailID = orderDetailID;
        this.orderID = orderID;
        this.nemoID = nemoID;
        this.nemoName = nemoName;
        this.price = price;
        this.imgPath = imgPath;
        this.quantity = quantity;
    }

    public int getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getNemoID() {
        return nemoID;
    }

    public void setNemoID(int nemoID) {
        this.nemoID = nemoID;
    }

    public String getNemoName() {
        return nemoName;
    }

    public void setNemoName(String nemoName) {
        this.nemoName = nemoName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
