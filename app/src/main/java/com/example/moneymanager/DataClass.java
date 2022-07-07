package com.example.moneymanager;

import java.util.Date;

public class DataClass {
    private Date date;
    private String category;
    private float catExp;
    private float income;
    private String notes;
    private float incCat;
    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public float getIncCat() {
        return incCat;
    }

    public void setIncCat(float incCat) {
        this.incCat = incCat;
    }


    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getCatExp() {
        return catExp;
    }

    public void setCatExp(float catExp) {
        this.catExp = catExp;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
