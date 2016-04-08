package com.example.camasok.betterdatabase;

import android.database.*;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
    private ListView listView;


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
        listView = (ListView)findViewById( R.id.listView );

    }

    public void query(View view)  {
        CoffeeDBHelper cDBHelper = new CoffeeDBHelper((getApplicationContext()));
        SQLiteDatabase db = cDBHelper.getReadableDatabase();


        String[] colums = {"Student.FName" ,"Student.LName", "Student.Major", "Student.Phone_Number", "Communicates_Using.SM_Acc"};
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
        String ColCondition = null;
        String MajCondition = null;
        String CourCondition = null;
        String GrouCondition = null;
        String ZIPCondition = null;
        String whereQuery = null;

        if(ColSpinner.getSelectedItem().equals("ANY"))
        {
            // nuffin
        }
        else
        {
            whereQuery += "";
        }
        if(MajorSpinner.getSelectedItem().equals("ALL"))
        {
            // nuffin
        }
        else
        {
            whereQuery += "";
        }

        if(CourseSpinner.getSelectedItem().equals("ANY"))
        {
            // nuffin
        }
        else
        {
            whereQuery += "";
        }

        if(GroupSpinner.getSelectedItem().equals("ANY") || GroupSpinner.getSelectedItem().equals("NONE"))
        {
            // nuffin
        }
        else
        {
            whereQuery += "";
        }
        if(ZIPSpinner.getSelectedItem().equals("ANY"))
        {
            // nuffin
        }
        else
        {
             whereQuery += "";
        }



        return whereQuery;
    }

}
