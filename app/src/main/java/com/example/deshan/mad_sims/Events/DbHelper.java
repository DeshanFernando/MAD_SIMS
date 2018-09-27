package com.example.deshan.mad_sims.Events;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DbHelper extends SQLiteOpenHelper {





    public static final String DATABASE_NAME = "StudentInfo.db";
    public static final String TABLE_NAME = "StudentEvent_Table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "EVENTDATE";
    public static final String COL_4 = "TYPE";
    public static final String COL_5 = "VENUE";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create table "+TABLE_NAME+" (ID INT PRIMARY KEY, NAME TEXT, EVENTDATE TEXT,TYPE TEXT,VENUE TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("Drop TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String id, String name, String Dat, String type, String venue){

       /* SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
        String date = sdf.format(Dat);
*/
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3, Dat);
        contentValues.put(COL_4,type);
        contentValues.put(COL_5,venue);

        long result = db.insert(TABLE_NAME,null,contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }


    public boolean updateData(String id,String name,String Date, String type, String venue){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3, Date);
        contentValues.put(COL_4,type);
        contentValues.put(COL_5,venue);

        db.update(TABLE_NAME,contentValues,"ID = ? ",new String[] {id});
        return true;
    }

    public int deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ? ", new String[] {id});
    }


    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * from "+ TABLE_NAME, null);
        if(res != null){
            res.moveToNext();
        }
        return res;
    }

    public Cursor getName(String search){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        Cursor c = db.rawQuery("select * from "+TABLE_NAME+" where ID = ?",new String[]{search} );



        return c;
    }
}

