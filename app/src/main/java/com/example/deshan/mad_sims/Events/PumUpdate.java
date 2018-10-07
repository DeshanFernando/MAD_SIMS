package com.example.deshan.mad_sims.Events;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deshan.mad_sims.R;

import java.util.Calendar;
import java.util.regex.Pattern;


public class PumUpdate extends AppCompatActivity {

    private static final Pattern USER_ID = Pattern.compile("^[0-9_-]{3,10}$");
    private static final Pattern USER_NAME = Pattern.compile("^[a-zA-Z-]{3,15}$");
    // private static final Pattern USER_DATE = Pattern.compile("^[0-9_-]{3,10}$");
    private static final Pattern USER_TYPE = Pattern.compile("Type of Event");
    private static final Pattern USER_VENUE = Pattern.compile("^[a-zA-Z0-9_-]{3,10}$");

    Button btn;
    int year,month,date;
    static final int dialog_id = 0;
    TextView txt,txt1,txt2,txt3,txt4;

    DbHelper mydb;
    EditText editid,editname,editvenue;
    Spinner edittype;
    TextView editdate;
    Button btnupdateData;
    Button btnsearch;
    String label;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pum_update);

        showDialogButtonClicked();

        final Calendar cl = Calendar.getInstance();
        year = cl.get(Calendar.YEAR);
        month = cl.get(Calendar.MONTH);
        date = cl.get(Calendar.DAY_OF_MONTH);


        Spinner spintype = (Spinner)findViewById(R.id.spinner2);

        ArrayAdapter<String> obj = new ArrayAdapter<String>(PumUpdate.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.types));

        obj.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spintype.setAdapter(obj);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mydb = new DbHelper(this);

        editid = (EditText)findViewById(R.id.searchID);
        editname = (EditText)findViewById(R.id.name);
        editdate = (TextView)findViewById(R.id.date);
        edittype = (Spinner)findViewById(R.id.spinner2);
        editvenue = (EditText)findViewById(R.id.venue);


        btnupdateData = (Button)findViewById(R.id.updatebutton);


        updateData();
        searchName();

    }

    public void searchName(){

        btnsearch = (Button)findViewById(R.id.sel);

        btnsearch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        editid = (EditText)findViewById(R.id.searchID);

                        String eid = editid.getText().toString();
                        Cursor c = mydb.getName(eid);

                        Spinner spin = (Spinner)findViewById(R.id.spinner2);

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



    public void updateData(){
        btnupdateData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {





                        String eid = editid.getText().toString();
                        String ename = editname.getText().toString();
                        String edate = editdate.getText().toString();
                        String evenue = editvenue.getText().toString();
                        String etype = edittype.getSelectedItem().toString();





                        if (TextUtils.isEmpty(eid)) {
                            editid.setError("Input Required");
                        } else if (!USER_ID.matcher(eid).matches()) {
                            editid.setError("Please enter a valid ID");
                        } else if (TextUtils.isEmpty(ename)) {
                            editname.setError("Input Required");
                        } else if (!USER_NAME.matcher(ename).matches()) {
                            editname.setError("Please enter a valid name");
                        }else if (TextUtils.isEmpty(edate)) {
                            showMessage("Error","Select the Date");
                        }else if(etype.equalsIgnoreCase("Type of Event")){
                            showMessage("Error","Select the Type");
                        }else if (!USER_VENUE.matcher(evenue).matches()){
                            editvenue.setError("Please enter valid venue");
                        }else if (TextUtils.isEmpty(evenue)) {
                            editvenue.setError("Input Required");
                        } else{


                            boolean isupdate = mydb.updateData(editid.getText().toString(),
                                    editname.getText().toString(),
                                    editdate.getText().toString(),
                                    edittype.getSelectedItem().toString(),
                                    editvenue.getText().toString());

                            if (isupdate == true)
                                Toast.makeText(PumUpdate.this, "Data Updated ", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(PumUpdate.this, "Data not Updated ", Toast.LENGTH_LONG).show();
                        }
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

    public void showDialogButtonClicked() {

        btn = (Button) findViewById(R.id.button11);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(dialog_id);
            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id){

        if(id == dialog_id)
            return new DatePickerDialog(this,dpicker,year,month,date);
        return null;

    }

    private DatePickerDialog.OnDateSetListener dpicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            year = i;
            month = i1;
            date = i2;

            txt = (TextView)findViewById(R.id.date);
            txt.setText(year+"/"+month+"/"+date);
        }
    };

    public void showMessage(String title,String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }




}
