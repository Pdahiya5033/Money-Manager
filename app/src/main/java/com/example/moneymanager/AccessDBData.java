package com.example.moneymanager;

import android.database.Cursor;
import android.database.CursorWindow;
import android.database.CursorWrapper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccessDBData extends CursorWrapper {
    private static final String TAG="AccessDBData";
    public AccessDBData(Cursor cursor) {
        super(cursor);
    }
    public DataClass getDataClassObj(){
        List<String> catList=new ArrayList<>();
        List<Float> incomeList=new ArrayList<>();
        List<Float> catExpList=new ArrayList<>();
        List<String> catIncList=new ArrayList<>();
        List<String> notesList=new ArrayList<>();
        DataClass dataClass=new DataClass();
        String note=getString(getColumnIndex(DBSchema.DataTable.DataColumns.NOTE));
        String category=getString(getColumnIndex(DBSchema.DataTable.DataColumns.CATEGORY));
        String catExp=getString(getColumnIndex(DBSchema.DataTable.DataColumns.CATEXPENSE));
        String income=getString(getColumnIndex(DBSchema.DataTable.DataColumns.INCOME));
        String date=getString(getColumnIndex(DBSchema.DataTable.DataColumns.DATE));
        String catInc=getString(getColumnIndex(DBSchema.DataTable.DataColumns.INC_CAT));
        String acc=getString(getColumnIndex(DBSchema.DataTable.DataColumns.ACCOUNT));
        Log.d(TAG,":::"+date);
        Log.d(TAG,">>>> categories are : "+category);
        Log.d(TAG,">>>> notes are : "+note);
        Log.d(TAG,">>>> expenses are : "+catExp);
        String str="";
        for(int i=0;i<category.length();i++){
            if(category.charAt(i)==','){
                catList.add(str);
                str="";
            }
            else{
                str+=category.charAt(i);
            }
        }
        catList.add(str);
        Log.d(TAG,"??/"+catList.get(0));
        str="";
        for(int i=0;i<catExp.length();i++){
            if(catExp.charAt(i)==','){
                catExpList.add(Float.parseFloat(str));
                str="";
            }
            else{
                str+=catExp.charAt(i);
            }
        }
        //Log.d(TAG,"???"+catExpList.get(0));
        catExpList.add(Float.parseFloat(str));
        str="";
        for(int i=0;i<income.length();i++){
            if(income.charAt(i)==','){
                incomeList.add(Float.parseFloat(str));
                str="";
            }
            else{
                str+=income.charAt(i);
            }
        }
        //incomeList.add(Float.parseFloat(str));
        str="";
        for(int i=0;i<catInc.length();i++){
            if(catInc.charAt(i)==','){
                catIncList.add(str);
                str="";
            }
            else{
                str+=catInc.charAt(i);
            }
        }
        catIncList.add(str);
        str="";
        for(int i=0;i<note.length();i++){
            if(note.charAt(i)==','){
                notesList.add(str);
                str="";
            }
            else
                str+=note.charAt(i);
        }
        notesList.add(str);

        dataClass.setDate(date);
        Log.d(TAG,":::>>>"+dataClass.getDate());
        dataClass.setCategory(catList);
        dataClass.setCatExp(catExpList);
        dataClass.setIncome(incomeList);
        dataClass.setIncCat(catIncList);
        dataClass.setAccount(acc);
        dataClass.setNotes(notesList);
        return dataClass;
    }
    public String getIncExpDB(){
        String income=getString(getColumnIndex(DBSchema.DataTable.DataColumns.CATEXPENSE));
        return income;
    }
}
