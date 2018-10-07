package com.example.deshan.mad_sims.diary;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deshan.mad_sims.R;

public class DiaryEditActivity extends AppCompatActivity {

    private EditText etTitle, etBody, etDate, etTime;
    private Button btnSave, btnDelete;

    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_edit);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int code = Integer.valueOf(intent.getStringExtra("requestCode"));

        item = (Item)bundle.getSerializable("item");

        etTitle = findViewById(R.id.etTitle);
        etBody = findViewById(R.id.etBody);
        etDate = findViewById(R.id.etDate);
        etTime = findViewById(R.id.etTime);

        btnSave = findViewById(R.id.btnSave);

        btnDelete = findViewById(R.id.btnDelete);

        etTitle.setText(item.getTitle());
        etBody.setText(item.getBody());
        etDate.setText(item.getDueDate().toString());
        etTime.setText(item.getDueTime().toString());


        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(DiaryEditActivity.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker dp, int i, int i1, int i2) {
                        Date dx = new Date();
                        dx.setYear(dp.getYear());
                        dx.setMonth(dp.getMonth()+1);
                        dx.setDay(dp.getDayOfMonth());
                        etDate.setText(dx.toString());
                    }
                }, 2018, 2, 1).show();
            }
        });

        if(code == 1) {

            btnSave.setText("Save");
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    item.setTitle(etTitle.getText().toString());
                    item.setBody(etBody.getText().toString());
                    item.setDueDate(Date.valueOf(etDate.getText().toString().trim()));
                    item.setDueTime(Time.valueOf(etTime.getText().toString().trim()));
                    item.setImportant(true);
                    item.save(DiaryEditActivity.this);
                    //sd
                    finish();
                }
            });
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    item.delete(DiaryEditActivity.this);
                    finish();
                }
            });
            btnDelete.setEnabled(true);

        }else if(code == 2){

            btnSave.setText("Add");
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    item.setTitle(etTitle.getText().toString());
                    item.setBody(etBody.getText().toString());
                    item.setDueDate(Date.valueOf(etDate.getText().toString().trim()));
                    item.setDueTime(Time.valueOf(etTime.getText().toString().trim()));
                    item.setImportant(true);
                    item.add(DiaryEditActivity.this);
                    //sd
                    finish();
                }
            });
            btnDelete.setEnabled(false);
        }
    }
}
