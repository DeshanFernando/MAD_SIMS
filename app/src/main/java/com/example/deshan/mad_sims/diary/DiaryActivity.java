package com.example.deshan.mad_sims.diary;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

        Button btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(getSearchListener());

        init();

    }

    private View.OnClickListener getSearchListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
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
                startActivityForResult(intent, 1);
            }
        };
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

        Cursor cursor = db.rawQuery("SELECT * FROM "+Item.TABLE_NAME, null);

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





    }

    @Override
    protected void onResume() {
        init();
        super.onResume();
    }
}
