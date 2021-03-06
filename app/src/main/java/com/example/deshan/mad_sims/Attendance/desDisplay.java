package com.example.deshan.mad_sims.Attendance;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deshan.mad_sims.R;

import de.codecrafters.tableview.TableView;

public class desDisplay extends AppCompatActivity {

    Button btn,btn_del;
    TextView txt;
    DatabaseHelper mydb;
    EditText id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_des_display);
        mydb = new DatabaseHelper(this);
        onclickListener();

        id = (EditText)findViewById(R.id.editText);
        id.setHintTextColor(Color.WHITE);

        TableLayout tableLayout=(TableLayout)findViewById(R.id.table);


        TableRow rowHeader = new TableRow(this);
        rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"));
        rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        String[] headerText={"Id  ","Subject  ","Type  ","Date  "};
        for(String c:headerText) {
            TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(18);
            tv.setPadding(5, 5, 5, 5);
            tv.setText(c);
            rowHeader.addView(tv);
        }
        tableLayout.addView(rowHeader);

        Intent intent = getIntent();
        String SUB = intent.getStringExtra("sub");


        Cursor cursor = mydb.getSubData(SUB);

        if(cursor.getCount() >0)
        {
            while (cursor.moveToNext()) {

                String id = cursor.getString(0);
                String sub = cursor.getString(1);
                String ty = cursor.getString(2);
                String da = cursor.getString(3);


                TableRow row = new TableRow(this);
                row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));
                String[] colText={id+"",sub,ty,da};
                for(String text:colText) {
                    TextView tv = new TextView(this);
                    tv.setTextColor(Color.WHITE);
                    tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
                    tv.setGravity(Gravity.CENTER);
                    tv.setTextSize(16);
                    tv.setPadding(5, 5, 5, 5);
                    tv.setText(text);
                    row.addView(tv);
                    row.setClickable(true);
                }
                tableLayout.addView(row);

            }
        }
    }

    public void onclickListener(){
        btn_del = (Button)findViewById(R.id.button7);
        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id = (EditText)findViewById(R.id.editText);
                Cursor cursor = mydb.getForUpdate(id.getText().toString());

                if(id.getText().toString().equals("")){
                    AlertDialog alertDialog = new AlertDialog.Builder(desDisplay.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Insert Table Id!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                else if (cursor.getCount() == 0){
                    AlertDialog alertDialog = new AlertDialog.Builder(desDisplay.this).create();
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

                else {

                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(desDisplay.this, android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(desDisplay.this);
                    }
                    builder.setTitle("Delete entry")
                            .setMessage("Are you sure you want to delete this entry?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {


                                    int ret = mydb.deleteData(id.getText().toString());

                                    if (ret > 0)
                                        Toast.makeText(desDisplay.this, "Data Deleted", Toast.LENGTH_LONG).show();
                                    else
                                        Toast.makeText(desDisplay.this, "Data not Deleted", Toast.LENGTH_LONG).show();
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

            }
        });
    }

}