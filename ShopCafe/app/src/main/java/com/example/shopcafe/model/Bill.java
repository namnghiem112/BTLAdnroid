package com.example.shopcafe.model;

import java.io.Serializable;

public class Bill implements Serializable {
    private String id ;
    private String name ;
    private String phone, dateOrder, billProduct, address;
    private int totalPrices;

    public Bill(String id, String name, String phone, String dateOrder, String billProduct, String address, int totalPrices) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.dateOrder = dateOrder;
        this.billProduct = billProduct;
        this.address = address;
        this.totalPrices = totalPrices;
    }

    public Bill() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getBillProduct() {
        return billProduct;
    }

    public void setBillProduct(String billProduct) {
        this.billProduct = billProduct;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalPrices() {
        return totalPrices;
    }

    public void setTotalPrices(int totalPrices) {
        this.totalPrices = totalPrices;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", dateOrder='" + dateOrder + '\'' +
                ", billProduct='" + billProduct + '\'' +
                ", address='" + address + '\'' +
                ", totalPrices=" + totalPrices +
                '}';
    }
}
