package com.example.deshan.mad_sims.Attendance;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.deshan.mad_sims.R;

public class desMainActivity extends AppCompatActivity {

    private static Button button_enterAtt;
    private static Button button_view;
    private static Button button_update;
    DatabaseHelper mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_des_main);
        onClickButtonListener();
        onClickButtonListenerView();
        onClickButtonListenerUpdate();
        onClickButtonListenerPercentage();


        Spinner subjects = (Spinner)findViewById(R.id.spinner2);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(desMainActivity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.subs));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjects.setAdapter(myAdapter);
        subjects.setBackgroundColor(Color.WHITE);



    }

    public void onClickButtonListener(){
        button_enterAtt = (Button)findViewById(R.id.button);
        button_enterAtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(".desEnter_Attendance");
                startActivity(intent1);
            }
        });
    }

    public void onClickButtonListenerView(){
        button_view = (Button)findViewById(R.id.button3);
        button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(".desDisplay");
                Spinner sub = (Spinner)findViewById(R.id.spinner2);
                String subs = sub.getSelectedItem().toString();
                intent1.putExtra("sub", subs);
                startActivity(intent1);
            }
        });
    }

    public void onClickButtonListenerUpdate(){
        button_update = (Button)findViewById(R.id.button9);
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(".desUpdate");
                startActivity(intent1);
            }
        });
    }

    public void onClickButtonListenerPercentage(){
        button_update = (Button)findViewById(R.id.button6);
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(".desPercentage");
                startActivity(intent1);
            }
        });
    }
}

