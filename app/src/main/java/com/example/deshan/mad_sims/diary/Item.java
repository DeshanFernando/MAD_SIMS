package com.example.deshan.mad_sims.diary;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.io.Serializable;

public class Item implements BaseColumns, Serializable{

    private int id;
    private String title;
    private String body;
    private Date dueDate;
    private Time dueTime;
    private boolean important;

    public static final String TABLE_NAME = "student_diary";
    public static final String COLUMN_NAME_TITLE = "title";
    public static final String COLUMN_NAME_BODY = "body";
    public static final String COLUMN_NAME_DATE = "date";
    public static final String COLUMN_NAME_TIME = "time";
    public static final String COLUMN_NAME_LEVEL = "level";

    public Item(String title) {
        this.id = 0;
        this.title = title;
        this.important = false;
        this.dueDate = new Date(2018, 9, 23);
        this.dueTime = new Time(15, 32);
        this.body = "Test Body";

    }

    public Item(){
        this.id = 0;
        this.title = "Test Title";
        this.important = false;
        this.dueDate = new Date(2018, 10, 7);
        this.dueTime = new Time(15, 32);
        this.body = "Test Body";
    }

    public Item(int id, String title, String body, Date dueDate, Time dueTime, boolean important) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        this.important = important;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Time getDueTime() {
        return dueTime;
    }

    public void setDueTime(Time dueTime) {
        this.dueTime = dueTime;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    public void delete(Context context){
        DatabaseManager dbmgr = new DatabaseManager(context);
        SQLiteDatabase db = dbmgr.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TITLE, title);
        values.put(COLUMN_NAME_BODY, body);
        values.put(COLUMN_NAME_TITLE, title);
        values.put(COLUMN_NAME_DATE, dueDate.toString());
        //values.put(COLUMN_NAME_DATE, Date.toSQLDate(new java.util.Date(dueDate.toString())).toString());
        values.put(COLUMN_NAME_TIME, dueTime.toString());
        values.put(COLUMN_NAME_LEVEL, String.valueOf(important));

        //long newRowId = db.insert(TABLE_NAME, null, values);
        //System.out.println(_ID);
        //db.update(TABLE_NAME, values, _ID+"="+id, null);
        db.delete(TABLE_NAME, _ID+"="+id, null);
    }

    public void save(Context context){
        DatabaseManager dbmgr = new DatabaseManager(context);
        SQLiteDatabase db = dbmgr.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TITLE, title);
        values.put(COLUMN_NAME_BODY, body);
        values.put(COLUMN_NAME_TITLE, title);
        values.put(COLUMN_NAME_DATE, dueDate.toString());
        //values.put(COLUMN_NAME_DATE, Date.toSQLDate(new java.util.Date(dueDate.toString())).toString());
        values.put(COLUMN_NAME_TIME, dueTime.toString());
        values.put(COLUMN_NAME_LEVEL, String.valueOf(important));

        //long newRowId = db.insert(TABLE_NAME, null, values);
        //System.out.println(_ID);
        db.update(TABLE_NAME, values, _ID+"="+id, null);

    }

    public void add(Context context){
        DatabaseManager dbmgr = new DatabaseManager(context);
        SQLiteDatabase db = dbmgr.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TITLE, title);
        values.put(COLUMN_NAME_BODY, body);
        values.put(COLUMN_NAME_TITLE, title);
        values.put(COLUMN_NAME_DATE, dueDate.toString());
        values.put(COLUMN_NAME_TIME, dueTime.toString());
        values.put(COLUMN_NAME_LEVEL, String.valueOf(important));

        //long newRowId = db.insert(TABLE_NAME, null, values);
        //System.out.println(_ID);
        //db.update(TABLE_NAME, values, _ID+"="+id, null);
        db.insert(TABLE_NAME, null, values);
    }

    @Override
    public String toString() {
        return title;
    }
}
