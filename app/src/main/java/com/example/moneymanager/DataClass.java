package com.example.moneymanager;

import java.util.Date;
import java.util.List;

public class DataClass {
    private String date;
    private List<String> category;
    private List<Float> catExp;
    private List<Float> income;
    private List<String> notes;
    private List<String> incCat;
    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
    public int categorySize(){
        return category.size();
    }
    public int incomeSize(){
        return income.size();
    }
    public int catExpSize(){
        return catExp.size();
    }
    public int incCatSize(){
        return incCat.size();
    }
    public String getIncCat(int i) {
        return incCat.get(i);
    }

    public void setIncCat(List<String> incCat) {
        this.incCat = incCat;
    }
    public List<String> retcatList(){
        return category;
    }
    public List<Float> retCatExpList(){
        return catExp;
    }
    public List<String> retNoteList(){
        return notes;
    }
    public List<String> retIncCatList(){
        return incCat;
    }
    public List<Float> retIncList(){
        return income;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory(int i) {
        return category.get(i);
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public float getCatExp(int i) {
        return catExp.get(i);
    }

    public void setCatExp(List<Float> catExp) {
        this.catExp = catExp;
    }

    public float getIncome(int i) {
        return income.get(i);
    }

    public void setIncome(List<Float> income) {
        this.income = income;
    }

    public String getNotes(int i) {
        return notes.get(i);
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }
    public int notesSize(){
        return notes.size();
    }
}
