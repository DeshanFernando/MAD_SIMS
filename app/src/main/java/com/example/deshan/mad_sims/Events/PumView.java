package com.example.deshan.mad_sims.Events;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deshan.mad_sims.R;

import java.util.ArrayList;
import java.util.List;

public class PumView extends AppCompatActivity {

    DbHelper mydb;
    EditText editid,editname,editvenue;
    Spinner edittype;
    TextView editdate;
    Button btnviewData;

    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    ListDataAdapter listDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pum_view);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mydb = new DbHelper(this);

        editid = (EditText)findViewById(R.id.eventid);
        editname = (EditText)findViewById(R.id.name);
        editdate = (TextView)findViewById(R.id.date);
        edittype = (Spinner)findViewById(R.id.spinner);
        editvenue = (EditText)findViewById(R.id.venue);

        btnviewData = (Button)findViewById(R.id.view);

        listView = (ListView)findViewById(R.id.list_view);
        mydb = new DbHelper(getApplicationContext());
        listDataAdapter = new ListDataAdapter(getApplicationContext(),R.layout.row_layout);
        listView.setAdapter(listDataAdapter);
        sqLiteDatabase = mydb.getReadableDatabase();
        Cursor cursor = mydb.getAllData();

        if(cursor.moveToFirst()){
            do{

                String eid,ename,edate,etype,evenue;
                eid = cursor.getString(0);
                ename = cursor.getString(1);
                edate = cursor.getString(2);
                etype = cursor.getString(3);
                evenue = cursor.getString(4);

                DataProvider dataProvider = new DataProvider(eid,ename,edate,etype,evenue);
                listDataAdapter.add(dataProvider);

            }while (cursor.moveToNext());
        }

        viewAll();
    }


    public void viewAll(){
        btnviewData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = mydb.getAllData();

                        if(res.getCount() == 0){
                            showMessage("Error ","No data Available");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("ID "+res.getString(0)+"\n");
                            buffer.append("Name  "+res.getString(1)+"\n");
                            buffer.append("Date  "+res.getString(2)+"\n");
                            buffer.append("Type  "+res.getString(3)+"\n");
                            buffer.append("Venue "+res.getString(4)+"\n\n");
                        }
                        showMessage("Data ",buffer.toString());
                    }
                }
        );
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void showMessage(String title,String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }


   /* private void populateListView(){

        Cursor cursor = mydb.getAllData();

        if(cursor.getCount() == 0){
            showMessage("Error ","No data Available");
            return;
        }



            String[] fromnames = new String[]{DbHelper.COL_1,DbHelper.COL_2,DbHelper.COL_3,DbHelper.COL_4,DbHelper.COL_5};
            int[] toviewdetails = new int[]{R.id.idview,R.id.nameview,R.id.dateview,R.id.typeview,R.id.venueview};

            SimpleCursorAdapter myCursorAdapter;

            myCursorAdapter = new SimpleCursorAdapter(getBaseContext(),R.layout.item_layout,cursor,fromnames,toviewdetails,0);
            ListView mylist = (ListView)findViewById(R.id.listview);
            mylist.setAdapter(myCursorAdapter);




    }*/

}

