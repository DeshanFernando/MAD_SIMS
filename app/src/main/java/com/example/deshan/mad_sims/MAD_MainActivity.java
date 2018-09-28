package com.example.deshan.mad_sims;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.deshan.mad_sims.diary.DiaryActivity;
import com.example.deshan.mad_sims.Events.PumMainActivity;

public class MAD_MainActivity extends AppCompatActivity {

    private static Button button_enterAtt;
    private static Button eventbtn;
  
    //Start of Diary Button
    private Button btnDiary;
    //End of Diary Button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mad__main);
        onClickButtonListener();
       // onClicklistener();

    }

    public void onClickButtonListener(){
        button_enterAtt = (Button)findViewById(R.id.button14);
        button_enterAtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(".desMainActivity");
                startActivity(intent1);
            }
        });

        //Start of Diary Activity Navigation - Action
        btnDiary = findViewById(R.id.btnDiary);
        btnDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MAD_MainActivity.this, DiaryActivity.class);
                startActivity(intent);
            }
        });
        //End of Diary Activity Navigation
    }



    public void onClick1(View view){

        Intent myint  = new Intent(this, PumMainActivity.class);
        startActivity(myint);
    }


}
