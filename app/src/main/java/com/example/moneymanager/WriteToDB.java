package com.example.moneymanager;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.Functions;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class WriteToDB {
    private static final String TAG="WriteToDB";
    private static WriteToDB writeToDB;
    private Context mContext;
    private SQLiteDatabase sqLiteDatabase;
    public static WriteToDB getDB(Context context){
        if(writeToDB==null){
            writeToDB=new WriteToDB(context);
        }
        return writeToDB;
    }
    private WriteToDB(Context context){
        mContext=context;
        sqLiteDatabase=new CreatingDB(mContext).getWritableDatabase();
    }
    public void addData(DataClass dc){
        ContentValues contentValues=getContentValues(dc);
        sqLiteDatabase.insert(DBSchema.DataTable.NAME,null,contentValues);
    }
    private static ContentValues getContentValues(DataClass dc){
        List<Float> list;
        List<String> list1;
        ContentValues contentValues=new ContentValues();
        contentValues.put(DBSchema.DataTable.DataColumns.DATE,dc.getDate());
        String str="";
        str=String.join(",",dc.retcatList());
        Log.d(TAG,">>........0"+str);
        contentValues.put(DBSchema.DataTable.DataColumns.CATEGORY,str);
        list =dc.retCatExpList();
        list1=Lists.transform(list, Functions.toStringFunction());
        str=String.join(",", list1);
        Log.d(TAG,">>........0"+str);
        contentValues.put(DBSchema.DataTable.DataColumns.CATEXPENSE,str);
        list=dc.retIncList();
        list1=Lists.transform(list,Functions.toStringFunction());
        str=String.join(",", list1);
        Log.d(TAG,">>........0"+str);
        contentValues.put(DBSchema.DataTable.DataColumns.INCOME,str);

        str=String.join(",",dc.retIncCatList());
        Log.d(TAG,">>........0"+str);
        contentValues.put(DBSchema.DataTable.DataColumns.INC_CAT,str);

        str=String.join(",",dc.retNoteList());
        Log.d(TAG,">>........0"+str);
        contentValues.put(DBSchema.DataTable.DataColumns.NOTE,str);

        contentValues.put(DBSchema.DataTable.DataColumns.ACCOUNT,dc.getAccount());

        return contentValues;
    }
    public List<DataClass> getDCObjDB(){
        List<DataClass> list=new ArrayList<>();
        AccessDBData cursor=queryData(null,null);
        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                list.add(cursor.getDataClassObj());
                cursor.moveToNext();
            }
        }finally{
            cursor.close();
        }
        return list;
    }

    private AccessDBData queryData(String whereClause,String[] whereArgs){
        Cursor cursor=sqLiteDatabase.query(DBSchema.DataTable.NAME,null,
                whereClause,whereArgs,null,null,null);
        return new AccessDBData(cursor);
    }
//    public void deleteData(String note){
//        //sqLiteDatabase.delete(DBSchema.DataTable.NAME,NOTE+"=?",new String[]{note});
//
//        sqLiteDatabase.execSQL("DELETE FROM " + DBSchema.DataTable.NAME + " WHERE " + DBSchema.DataTable.DataColumns.NOTE + "= '" + note + "'");
//
//    }
    public void updateData(DataClass dc){
        Log.d(TAG,"???/"+"update called");
        ContentValues contentValues=getContentValues(dc);
        String selection=DBSchema.DataTable.DataColumns.DATE+" = ? ";
        String[] selectArgs={dc.getDate()};
        int i=sqLiteDatabase.update(DBSchema.DataTable.NAME,contentValues,selection,selectArgs);
        Log.d(TAG,"..... update result "+i);

    }

}
