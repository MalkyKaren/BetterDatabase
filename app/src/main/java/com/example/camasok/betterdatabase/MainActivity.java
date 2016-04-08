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
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        findViews();

    }

    private Spinner ColSpinner;
    private Spinner MajorSpinner;
    private Spinner CourseSpinner;
    private Spinner GroupSpinner;
    private Spinner ZIPSpinner;
    private Button buttonQuery;
    private EditText editText;


    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2016-04-06 14:31:59 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        ColSpinner = (Spinner)findViewById( R.id.ColSpinner );
        MajorSpinner = (Spinner)findViewById( R.id.MajorSpinner );
        CourseSpinner = (Spinner)findViewById( R.id.CourseSpinner );
        GroupSpinner = (Spinner)findViewById( R.id.GroupSpinner );
        ZIPSpinner = (Spinner)findViewById( R.id.ZIPSpinner );
        //editText = (EditText)findViewById( R.id.editText );
        // buttonQuery = (Button)findViewById( R.id.buttonQuery );
        //editText.setText("Hello");
    }

    public void query(View view)  {
        CoffeeDBHelper cDBHelper = new CoffeeDBHelper((getApplicationContext()));
        SQLiteDatabase db = cDBHelper.getReadableDatabase();


        String[] colums = {"Student.FName AS Name" ,"Student.LName", "Student.Major", "Student.Stu_ID"};
        String test = "Hello!";
        Cursor c = db.query("Student " +  getResources().getString(R.string.lame_o_join),colums,null, null, null, null, null);
        c.moveToFirst();

        c.moveToNext();
        test += c.getColumnName(1)+ ": \n" +c.getString(1) + "\n" + c.getString(2) + "\n \n" + c.getString(2) + "\n" + c.getString(3) + "\n"+ test + "\n";

       // editText.setText(test);
        c.close();
   }

    public String createWhere()
    {
        String ColCondition = "";
        String MajCondition = "";
        String CourCondition = "";
        String GrouCondition = "";
        String ZIPCondition = "";

        String whereQuery = "";

        return whereQuery;
    }

}
