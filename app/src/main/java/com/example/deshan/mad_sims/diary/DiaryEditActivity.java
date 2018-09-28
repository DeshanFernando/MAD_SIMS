package com.example.deshan.mad_sims.diary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deshan.mad_sims.R;

public class DiaryEditActivity extends AppCompatActivity {

    private EditText etTitle, etBody, etDate, etTime;
    private Button btnSave;

    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_edit);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        item = (Item)bundle.getSerializable("item");

        etTitle = findViewById(R.id.etTitle);
        etBody = findViewById(R.id.etBody);
        etDate = findViewById(R.id.etDate);
        etTime = findViewById(R.id.etTime);

        btnSave = findViewById(R.id.btnSave);

        etTitle.setText(item.getTitle());
        etBody.setText(item.getBody());
        etDate.setText(item.getDueDate().toString());
        etTime.setText(item.getDueTime().toString());


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
    }
}
