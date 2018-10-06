package com.example.deshan.mad_sims.Attendance;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deshan.mad_sims.R;

import java.util.Calendar;

public class desUpdate extends AppCompatActivity {

    Button btn,btnUp;
    int year,month,date;
    static  final int dilog_id = 0;
    TextView txt,txtId;
    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_des_update);
        onClickListner();
        onUpdateListener();
        mydb = new DatabaseHelper(desUpdate.this);

        final Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        date = cal.get(Calendar.DAY_OF_MONTH);

        txtId = findViewById(R.id.editText2);
        txtId.setHintTextColor(Color.WHITE);

        showDialogOnButtonClick();

        Spinner subjects2 = (Spinner)findViewById(R.id.spinner7);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(desUpdate.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.subs));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjects2.setAdapter(myAdapter2);
        subjects2.setBackgroundColor(Color.WHITE);

        Spinner classTypes = (Spinner)findViewById(R.id.spinner4);
        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(desUpdate.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.types));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classTypes.setAdapter(myAdapter1);
        classTypes.setBackgroundColor(Color.WHITE);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    public void onClickListner(){
        btn = (Button)findViewById(R.id.button17);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtId = findViewById(R.id.editText2);
                String id = txtId.getText().toString();

                if(id.equals("")){
                    AlertDialog alertDialog = new AlertDialog.Builder(desUpdate.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Insert Table Id");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                else {
                    Cursor cursor = mydb.getForUpdate(id);

                    Spinner subjects2 = (Spinner) findViewById(R.id.spinner7);
                    Spinner classTypes = (Spinner) findViewById(R.id.spinner4);

                    if (cursor.getCount() == 0)
                        Toast.makeText(desUpdate.this, "Data doesn't exist", Toast.LENGTH_LONG).show();

                    while (cursor.moveToNext()) {
                        String sub = cursor.getString(1);
                        String ty = cursor.getString(2);
                        String da = cursor.getString(3);

                        if (sub.equals("SE"))
                            subjects2.setSelection(0);
                        else if (sub.equals("DBMS"))
                            subjects2.setSelection(1);
                        else if (sub.equals("OOP"))
                            subjects2.setSelection(2);
                        else if (sub.equals("PS"))
                            subjects2.setSelection(3);

                        if (ty.equals("Lab"))
                            classTypes.setSelection(0);
                        else if (ty.equals("Lecture"))
                            classTypes.setSelection(1);
                        else if (ty.equals("Practical"))
                            classTypes.setSelection(2);

                        txt = (TextView) findViewById(R.id.textView18);
                        txt.setText(da);
                    }
                }

            }
        });
    }

    public void onUpdateListener(){
        btnUp = (Button)findViewById(R.id.button10);
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtId = findViewById(R.id.editText2);
                Cursor cursor = mydb.getForUpdate(txtId.getText().toString());
                txt = (TextView) findViewById(R.id.textView18);

                if(txtId.getText().toString().equals("")){
                    AlertDialog alertDialog = new AlertDialog.Builder(desUpdate.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Check table first!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    return;
                }
                else if (cursor.getCount() == 0){
                    AlertDialog alertDialog = new AlertDialog.Builder(desUpdate.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Table Id Does not Exist!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    return;
                }


                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(desUpdate.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(desUpdate.this);
                }
                builder.setTitle("Update entry")
                        .setMessage("Are you sure you want to update this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Spinner subjects2 = (Spinner)findViewById(R.id.spinner7);
                                Spinner classTypes = (Spinner)findViewById(R.id.spinner4);
                                txt = (TextView)findViewById(R.id.textView18);
                                txtId = findViewById(R.id.editText2);

                                int isUpdated = mydb.updateData(txtId.getText().toString(), subjects2.getSelectedItem().toString(),
                                        classTypes.getSelectedItem().toString(), txt.getText().toString());

                                String c = Integer.toString(isUpdated);

                                if(isUpdated == 0)
                                    Toast.makeText(desUpdate.this, "Data not Updated", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(desUpdate.this,"Data Updated", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


            }
        });
    }

    public void showDialogOnButtonClick(){
        btn = (Button)findViewById(R.id.button12);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(dilog_id);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id){
        if(id == dilog_id)
            return new DatePickerDialog(this,dpickerListener,year,month,date);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            year = i;
            month = i1;
            date = i2;

            txt = (TextView)findViewById(R.id.textView18);
            txt.setText(year+"/"+month+"/"+date);
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);

    }
}
