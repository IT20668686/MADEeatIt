package com.example.myapplication;

public class DiscountHelperClass {
    String name,type,amount,date;

    public DiscountHelperClass() {

    }
    public DiscountHelperClass(String name, String type, String amount, String date) {
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
