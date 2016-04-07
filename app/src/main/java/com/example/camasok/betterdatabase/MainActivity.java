package com.example.camasok.betterdatabase;

import android.database.*;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.database.sqlite.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        findViews();

    }



    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private Button buttonQuery;
    public String querySelect = "SELECT Student.FName, Student.LName, Student.Phone_Number";
    public String queryFrom = "FROM Student ";
    public String queryFAddon = "";
    public String queryWhere = "WHERE ";
    public String queryWAddon = "";
    private EditText editText;




    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2016-04-06 14:31:59 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        spinner1 = (Spinner)findViewById( R.id.spinner1 );
        spinner2 = (Spinner)findViewById( R.id.spinner2 );
        spinner3 = (Spinner)findViewById( R.id.spinner3 );
        editText = (EditText)findViewById( R.id.editText );
        buttonQuery = (Button)findViewById( R.id.buttonQuery );
        editText.setText(new String("Hello"));

    }

    public void query(View view)  {
        CoffeeDBHelper cDBHelper = new CoffeeDBHelper((getApplicationContext()));
        SQLiteDatabase db = cDBHelper.getReadableDatabase();
        Cursor c = db.query("Student", {"Student.FName" ,"Student.LName", "Student.Major"}, null, null, null, null, null);
        c.moveToFirst();
        editText.setText(c.getString(0) + "\n" + c.getString(1) + "\n" + c.getString(2));


   }




}
