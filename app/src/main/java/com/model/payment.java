package com.model;

public class payment {
    private String name,number,DOE,CVC,amount;


    public payment() {
    }

    public payment(String name, String number, String DOE, String CVC, String amount) {
        this.name = name;
        this.number = number;
        this.DOE = DOE;
        this.CVC = CVC;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getDOE() {
        return DOE;
    }

    public String getCVC() {
        return CVC;
    }

    public String getAmount() {
        return amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDOE(String DOE) {
        this.DOE = DOE;
    }

    public void setCVC(String CVC) {
        this.CVC = CVC;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
