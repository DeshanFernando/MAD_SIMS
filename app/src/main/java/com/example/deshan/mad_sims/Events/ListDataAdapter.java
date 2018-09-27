package com.example.deshan.mad_sims.Events;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.deshan.mad_sims.R;

import java.util.ArrayList;
import java.util.List;

public class ListDataAdapter extends ArrayAdapter {

    List list = new ArrayList();


    public ListDataAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }


    static class LayoutHandler{

        TextView id,name,date,type,venue;
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);

        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        LayoutHandler layoutHandler;

        if(row == null){

            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);

            layoutHandler = new LayoutHandler();
            layoutHandler.id = (TextView)row.findViewById(R.id.eid);
            layoutHandler.name = (TextView)row.findViewById(R.id.ename);
            layoutHandler.date = (TextView)row.findViewById(R.id.edate);
            layoutHandler.type = (TextView)row.findViewById(R.id.etype);
            layoutHandler.venue = (TextView)row.findViewById(R.id.evenue);

            row.setTag(layoutHandler);
        }else{

            layoutHandler = (LayoutHandler)row.getTag();

        }

        DataProvider dataProvider = (DataProvider)this.getItem(position);
        layoutHandler.id.setText(dataProvider.getId());
        layoutHandler.name.setText(dataProvider.getName());
        layoutHandler.date.setText(dataProvider.getDate());
        layoutHandler.type.setText(dataProvider.getType());
        layoutHandler.venue.setText(dataProvider.getVenue());


        return row;

    }
}
