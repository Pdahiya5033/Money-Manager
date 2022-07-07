package com.example.moneymanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreatingDB extends SQLiteOpenHelper {
    private static final int VERSION=1;
    private static final String DB_NAME="expSav.db";

    public CreatingDB(Context context){
        super(context,DB_NAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ DBSchema.DataTable.NAME+"("+"_id integer primary key autoincrement, "
        +DBSchema.DataTable.DataColumns.DATE+","
        +DBSchema.DataTable.DataColumns.CATEGORY+","
        +DBSchema.DataTable.DataColumns.INCOME+","
        +DBSchema.DataTable.DataColumns.CATEXPENSE+","
        +DBSchema.DataTable.DataColumns.NOTE+","
                +DBSchema.DataTable.DataColumns.INC_CAT+","
                +DBSchema.DataTable.DataColumns.ACCOUNT+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
