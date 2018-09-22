package com.example.deshan.mad_sims.Attendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context) {
        super(context,"Attendance.db" , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table attendance (id integer primary key autoincrement,subject text,type text,date text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists attendance");
    }

    public boolean insertData(String subject, String type, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("subject", subject);
        contentValues.put("type", type);
        contentValues.put("date", date);
        long result = db.insert("attendance", null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from attendance", null);
        return res;
    }

    public Cursor getForUpdate(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM attendance WHERE id = ?", new String[]{ id });
        return res;
    }

    public int updateData(String id, String subject, String type, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("subject", subject);
        contentValues.put("type", type);
        contentValues.put("date", date);
        int i = db.update("attendance", contentValues , "id = ?", new String[] {id});
        return i;

    }

    public int deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("attendance", "id = ?", new String[] {id});
    }

    public Cursor getSubData(String sub){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM attendance WHERE subject = ?", new String[]{ sub });
        return res;
    }

}
