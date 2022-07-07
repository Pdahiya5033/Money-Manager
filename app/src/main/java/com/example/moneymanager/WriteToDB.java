package com.example.moneymanager;

import static com.example.moneymanager.DBSchema.DataTable.DataColumns.CATEXPENSE;
import static com.example.moneymanager.DBSchema.DataTable.DataColumns.NOTE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class WriteToDB {
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
        ContentValues contentValues=new ContentValues();
        contentValues.put(DBSchema.DataTable.DataColumns.DATE,dc.getDate().getTime());
        contentValues.put(DBSchema.DataTable.DataColumns.CATEGORY,dc.getCategory());
        contentValues.put(CATEXPENSE,dc.getCatExp());
        contentValues.put(NOTE,dc.getNotes());
        contentValues.put(DBSchema.DataTable.DataColumns.INCOME,dc.getIncome());
        contentValues.put(DBSchema.DataTable.DataColumns.ACCOUNT,dc.getAccount());
        contentValues.put(DBSchema.DataTable.DataColumns.INC_CAT,dc.getIncCat());
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
    public void deleteData(String note){
        //sqLiteDatabase.delete(DBSchema.DataTable.NAME,NOTE+"=?",new String[]{note});

        sqLiteDatabase.execSQL("DELETE FROM " + DBSchema.DataTable.NAME + " WHERE " + NOTE + "= '" + note + "'");

    }

}
