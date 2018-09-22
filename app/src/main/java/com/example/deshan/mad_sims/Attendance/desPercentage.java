package com.example.deshan.mad_sims.Attendance;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.deshan.mad_sims.R;

public class desPercentage extends AppCompatActivity {

    Button btn;
    TextView txt;
    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_des_percentage);
        mydb = new DatabaseHelper(desPercentage.this);

        onClickListener();

        Spinner subjects = (Spinner)findViewById(R.id.spinner6);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(desPercentage.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.subs));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjects.setAdapter(myAdapter);
        subjects.setBackgroundColor(Color.WHITE);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);

    }

    public void onClickListener(){
        btn = (Button)findViewById(R.id.button4);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner subjects = (Spinner)findViewById(R.id.spinner6);
                String subject = subjects.getSelectedItem().toString();
                Cursor cursor = mydb.getSubData(subject);
                int count = cursor.getCount();
                double percentage = (count/26.0) * 100;
                String format = String.format("%.1f", percentage);
                txt = (TextView)findViewById(R.id.textView10);
                txt.setText("Your Percentage of Attendance for " + subject + " is " + format + "%");
            }
        });
    }


}

