package com.example.deshan.mad_sims.Events;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deshan.mad_sims.R;


public class PumDelete extends AppCompatActivity {

    DbHelper mydb;
    EditText editid,editname,editvenue;
    Spinner edittype;
    TextView editdate;
    Button btndeleteData;
    Button btnsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pum_delete);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mydb = new DbHelper(this);

        editid = (EditText)findViewById(R.id.dsearchID);

        btndeleteData = (Button)findViewById(R.id.deletebutton);
        editname = (EditText)findViewById(R.id.dname);
        editdate = (TextView)findViewById(R.id.ddate);
        edittype = (Spinner)findViewById(R.id.spinner3);
        editvenue = (EditText)findViewById(R.id.dvenue);

        deleteData();
        searchName();
    }

    public void deleteData(){
        btndeleteData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deletedRow = mydb.deleteData(editid.getText().toString());

                        if(deletedRow>0)
                            Toast.makeText(PumDelete.this,"Data Deleted ",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(PumDelete.this,"Data not Deleted ",Toast.LENGTH_LONG).show();
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

    public void searchName(){

        btnsearch = (Button)findViewById(R.id.dsel);

        btnsearch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        editid = (EditText)findViewById(R.id.dsearchID);

                        String eid = editid.getText().toString();
                        Cursor c = mydb.getName(eid);

                        Spinner spin = (Spinner)findViewById(R.id.spinner3);

                        if(c.getCount() == 0){

                            showMessage("Error ","No data Available");
                            return;
                        }

                        while(c.moveToNext()){


                            //String eName= c.getString(1);
                            //String dat = c.getString(2);

                            //  String ven = c.getString(4);

                            editname.setText(c.getString(1));
                            editdate.setText(c.getString(2));


                            String typ = c.getString(3);

                            if(typ.equals("Final Exam")){
                                spin.setSelection(2);
                            }else if(typ.equals("Mid Exam")){
                                spin.setSelection(3);
                            }else if(typ.equals("Viva")){
                                spin.setSelection(4);
                            }else if(typ.equals("Assignments")){
                                spin.setSelection(5);
                            }else if(typ.equals("Group Work")){
                                spin.setSelection(6);
                            }


                            editvenue.setText(c.getString(4));

                        }

                    }
                });

    }

    public void showMessage(String title,String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
