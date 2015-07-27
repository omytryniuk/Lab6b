package com.example.student.lab6b;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by student on 7/23/15.
 */
public class CAdapter extends CursorAdapter {
    public CAdapter(Context context, Cursor cursor) {
       super(context, cursor,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.activity_listview, parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView name = (TextView) view.findViewById(R.id.tvname);
        String body = cursor.getString(cursor.getColumnIndexOrThrow("name"));
   //     TextView email = (TextView) view.findViewById(R.id.tvemail);
     //   String em = cursor.getString(cursor.getColumnIndexOrThrow("email"));




        name.setText(body);
    }


}


