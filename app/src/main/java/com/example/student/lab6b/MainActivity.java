package com.example.student.lab6b;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.sql.SQLException;


public class MainActivity extends ActionBarActivity {


    String keyWord;



    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ListView lv = (ListView) findViewById(R.id.listView);
        Button bCheckDb;

     final DHelper myDbHelper = new DHelper(this);
        db = myDbHelper.getWritableDatabase();
        String o = "Oleg Mytryniuk";
       // Cursor curs = db.rawQuery("SELECT rowid _id, * FROM lab6 where name = '" + o + "'", null);
        final Cursor curs = db.rawQuery("SELECT rowid _id, name FROM lab6", null);
        //final Cursor curs = db.rawQuery("SELECT name FROM lab6", null);
        CAdapter adapt = new CAdapter(this, curs);
      // Cursor curs1 = db.rawQuery("SELECT email FROM lab6 where name = '"+o+"'",null);
       // CAdapter adapt1 = new CAdapter(this, curs1);


        //Cursor choice = db.rawQuery("SELECT row_id from lab6 where name = " + keyWord,null);
        //CAdapter adapt1 = new CAdapter(this, choice);

        lv.setAdapter(adapt);

     //   viewAll(curs1);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, int position, long id) {


                String query = "SELECT rowid _id, email, phonen from lab6 where ids = " + (position+1);
                Cursor cur = db.rawQuery(query,null);
                String email=" ";
                String phone=" ";
                Log.i("gsfg", cur.getCount() + "fhgdfhdf");
                cur.moveToFirst();
                do {
                 //   String email = cur.getString(cur.getColumnIndex("ContactNumber"));
                   email = cur.getString(cur.getColumnIndexOrThrow("email"));
                    phone = cur.getString(cur.getColumnIndexOrThrow("phonen"));

                       cur.close();
                }while (cur.moveToNext());




                TextView c = (TextView) view.findViewById(R.id.tvname);
                String keyWord = c.getText().toString();
         Toast.makeText(getApplicationContext(), "Phone: " + phone + " Email: " +email, Toast.LENGTH_SHORT).show();





              //  String sd = getApplicationContext().getResources().getString(R.string.toastString);
                 //   TextView t = (TextView)findViewById(R.id.ere);
                     //   t.setText(playerChanged);





            }
        });




    }



    public void viewAll(Cursor s){
        CAdapter adapt1 = new CAdapter(this, s);

                        StringBuffer buffer = new StringBuffer();
                        while(s.moveToNext()){
                             buffer.append("Id :"+ s.getString(0)+"\n");
                           buffer.append("Name :"+ s.getString(1)+"\n");
                          //  buffer.append("Surname :"+ res.getString(2)+"\n");
                           // buffer.append("Marks :"+ res.getString(3)+"\n\n");
                        }
                        showMessage("Data", buffer.toString());

                    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }


        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
