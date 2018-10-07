package com.example.deshan.mad_sims.diary;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.deshan.mad_sims.R;

import java.util.ArrayList;

public class DiaryActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Item> items;

    private EditText title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        listView = findViewById(R.id.listView);
        title = findViewById(R.id.etSearch);
        title.setText("2018-10-07");

        Button btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(getSearchListener());

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(getAddEntryListener());

        /*title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(DiaryActivity.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker dp, int i, int i1, int i2) {
                        Date dx = new Date();
                        dx.setYear(dp.getYear());
                        dx.setMonth(dp.getMonth()+1);
                        dx.setDay(dp.getDayOfMonth());
                        title.setText(dx.toString());
                    }
                }, 2018, 2, 1).show();
            }
        });*/

        init();

    }

    private View.OnClickListener getSearchListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Date dl = new Date();
                dl.setDay(7);
                dl.setMonth(9);
                dl.setYear(2018);

                new DatePickerDialog(DiaryActivity.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker dp, int i, int i1, int i2) {
                        Date dx = new Date();
                        dx.setYear(dp.getYear());
                        dx.setMonth(dp.getMonth()+1);
                        dx.setDay(dp.getDayOfMonth());
                        title.setText(dx.toString());
                        search();
                    }
                }, dl.getYear(), dl.getMonth(), dl.getDay()).show();

            }
        };
    }

    private View.OnClickListener getAddEntryListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        };
    }

    public AdapterView.OnItemClickListener getSelectItemListener(){
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DiaryActivity.this, DiaryEditActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", (Item)listView.getItemAtPosition(i));
                intent.putExtras(bundle);
                intent.putExtra("requestCode", "1");
                startActivityForResult(intent, 1);
            }
        };
    }

    private void add(){
        Intent intent = new Intent(DiaryActivity.this, DiaryEditActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("item", new Item());
        intent.putExtras(bundle);
        intent.putExtra("requestCode", "2");
        startActivityForResult(intent, 2);
    }

    private void search(){
        String title = this.title.getText().toString();
        init();
    }

    private void init(){
        initItemList();
        listView.setOnItemClickListener(getSelectItemListener());
        listView.setAdapter(getDiaryAdapter());
    }

    private ListAdapter getDiaryAdapter(){
        return new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1, items);
    }

    private void initItemList(){

        DatabaseManager dbmgr = new DatabaseManager(this);
        SQLiteDatabase db = dbmgr.getReadableDatabase();

        String d = Date.valueOf(title.getText().toString()).toString();

        Cursor cursor = db.rawQuery("SELECT * FROM "+Item.TABLE_NAME + " WHERE "+Item.COLUMN_NAME_DATE+ "= '"+ d  +"' ORDER BY "+Item._ID+" DESC", null);

        items = new ArrayList<>();

        while(cursor.moveToNext()) {

            int itemId = cursor.getInt(cursor.getColumnIndexOrThrow(Item._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(Item.COLUMN_NAME_TITLE));
            String body = cursor.getString(cursor.getColumnIndexOrThrow(Item.COLUMN_NAME_BODY));
            Date date = Date.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(Item.COLUMN_NAME_DATE)));
            Time time = Time.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(Item.COLUMN_NAME_TIME)));
            Boolean level = Boolean.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(Item.COLUMN_NAME_LEVEL)));
            items.add(new Item(itemId, title, body, date, time, level));

        }

        cursor.close();

        //sd



    }

    @Override
    protected void onResume() {
        init();
        super.onResume();
    }
}
