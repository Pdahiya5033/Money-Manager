package com.example.moneymanager;

import android.database.Cursor;
import android.database.CursorWindow;
import android.database.CursorWrapper;
import android.util.Log;

import java.util.Date;

public class AccessDBData extends CursorWrapper {
    private static final String TAG="AccessDBData";
    public AccessDBData(Cursor cursor) {
        super(cursor);
    }
    public DataClass getDataClassObj(){
        DataClass dataClass=new DataClass();
        String note=getString(getColumnIndex(DBSchema.DataTable.DataColumns.NOTE));
        String category=getString(getColumnIndex(DBSchema.DataTable.DataColumns.CATEGORY));
        float catExp=getFloat(getColumnIndex(DBSchema.DataTable.DataColumns.CATEXPENSE));
        float income=getFloat(getColumnIndex(DBSchema.DataTable.DataColumns.INCOME));
        long date=getLong(getColumnIndex(DBSchema.DataTable.DataColumns.DATE));
        float catInc=getFloat(getColumnIndex(DBSchema.DataTable.DataColumns.INC_CAT));
        String acc=getString(getColumnIndex(DBSchema.DataTable.DataColumns.ACCOUNT));
        Log.d(TAG,":::"+date);
        dataClass.setDate(new Date(date));
        dataClass.setCategory(category);
        dataClass.setCatExp(catExp);
        dataClass.setIncome(income);
        dataClass.setNotes(note);
        dataClass.setIncCat(catInc);
        dataClass.setAccount(acc);
        return dataClass;
    }
}
