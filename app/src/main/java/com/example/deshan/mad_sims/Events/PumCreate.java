package com.example.deshan.mad_sims.Events;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.deshan.mad_sims.R;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;


public class PumCreate extends AppCompatActivity {

    private static final Pattern USER_ID = Pattern.compile("^[0-9_-]{3,10}$");
    private static final Pattern USER_NAME = Pattern.compile("^[a-zA-Z-]{3,15}$");
    // private static final Pattern USER_DATE = Pattern.compile("^[0-9_-]{3,10}$");
    private static final Pattern USER_TYPE = Pattern.compile("Type of Event");
    private static final Pattern USER_VENUE = Pattern.compile("^[a-zA-Z0-9_-]{3,10}$");

    Button btn;
    int year,month,date;
    static final int dialog_id = 0;
    TextView txt;

    DbHelper mydb;
    EditText editid,editname,editvenue;
    Spinner edittype;
    TextView editdate;
    Button btnaddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pum_create);

        showDialogButtonClicked();

        final Calendar cl = Calendar.getInstance();
        year = cl.get(Calendar.YEAR);
        month = cl.get(Calendar.MONTH);
        date = cl.get(Calendar.DAY_OF_MONTH);

        Spinner type = (Spinner)findViewById(R.id.spinner);

        ArrayAdapter<String> obj = new ArrayAdapter<String>(PumCreate.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.types));

        obj.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(obj);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mydb = new DbHelper(this);

        editid = (EditText)findViewById(R.id.eventid);
        editname = (EditText)findViewById(R.id.name);
        editdate = (TextView)findViewById(R.id.date);
        edittype = (Spinner)findViewById(R.id.spinner);
        editvenue = (EditText)findViewById(R.id.venue);

        btnaddData = (Button)findViewById(R.id.addbutton);

        addData();


    }

    public void addData(){
        btnaddData.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        String eid = editid.getText().toString();
                        String ename = editname.getText().toString();
                        String edate = editdate.getText().toString();
                        String evenue = editvenue.getText().toString();

                        if (TextUtils.isEmpty(eid)) {
                            editid.setError("Input Required");
                        } else if (!USER_ID.matcher(eid).matches()) {
                            editid.setError("Please enter a valid ID");
                        } else if (TextUtils.isEmpty(ename)) {
                            editname.setError("Input Required");
                        } else if (!USER_NAME.matcher(ename).matches()) {
                            editname.setError("Please enter a valid name");
                        } else if (TextUtils.isEmpty(edate)) {
                            editdate.setError("Input Required");
                        }else if (!USER_VENUE.matcher(evenue).matches()){
                            editvenue.setError("Please enter valid venue");
                        }else if (TextUtils.isEmpty(evenue)) {
                            editvenue.setError("Input Required");
                        } else {

                            boolean isinserted = mydb.insertData(editid.getText().toString(),
                                    editname.getText().toString(),
                                    editdate.getText().toString(),
                                    edittype.getSelectedItem().toString(),
                                    editvenue.getText().toString());

                            if (isinserted == true)
                                Toast.makeText(PumCreate.this, "Data Inserted ", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(PumCreate.this, "Data not Inserted ", Toast.LENGTH_LONG).show();
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

        btn = (Button) findViewById(R.id.button10);

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




}