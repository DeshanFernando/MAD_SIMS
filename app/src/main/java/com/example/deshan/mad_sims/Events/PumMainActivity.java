package com.example.deshan.mad_sims.Events;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deshan.mad_sims.R;



public class PumMainActivity extends AppCompatActivity {


    DbHelper mydb;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pum_main);

        btn = (Button)findViewById(R.id.button4);
        mydb = new DbHelper(this);

        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(PumMainActivity.this,PumView.class);
                        startActivity(intent);

                    }
                }
        );

    }



    public void onClick(View view){

        Intent I = new Intent(this,PumCreate.class);
        startActivity(I);

    }

    public void onClick1(View view){

        Intent I = new Intent(this,PumUpdate.class);
        startActivity(I);

    }

    public void onClick2(View view){

        Intent I = new Intent(this,PumDelete.class);
        startActivity(I);

    }

    public void onClick3(View view){

        Intent I = new Intent(this, PumView.class);
        startActivity(I);

    }


}
