package com.example.deshan.mad_sims.diary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "student_dairy.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Item.TABLE_NAME + " (" +
                    Item._ID + " INTEGER PRIMARY KEY," +
                    Item.COLUMN_NAME_TITLE + " TEXT," +
                    Item.COLUMN_NAME_BODY + " TEXT," +
                    Item.COLUMN_NAME_DATE + " TEXT," +
                    Item.COLUMN_NAME_TIME + " TEXT," +
                    Item.COLUMN_NAME_LEVEL + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Item.TABLE_NAME;

    public DatabaseManager(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);//ss
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
