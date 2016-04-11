package com.example.camasok.betterdatabase;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.database.*;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SimpleCursorAdapter;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        /*
        ProgressDialog dialog=new ProgressDialog(this);
        dialog.setMessage("Please wait... \n Creating and populating Database...");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.show();
        dialog.hide(); */

        setContentView(R.layout.activity_main);
        findViews();



    }

    private Spinner ColSpinner;
    private Spinner MajorSpinner;
    private Spinner CourseSpinner;
    private Spinner GroupSpinner;
    private Spinner ZIPSpinner;
    private ListView listView;
    private RelativeLayout relativeLayout;

    String whereQuery = "";





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
        listView = (ListView)findViewById(R.id.listView);
        relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout);
       // editText = (EditText)findViewById(R.id.editText);

    }

    public void query(View view)  {
        CoffeeDBHelper cDBHelper = new CoffeeDBHelper((getApplicationContext()));
        SQLiteDatabase db = cDBHelper.getReadableDatabase();


        String[] colums = {"Student.FName" ,"Student.LName", "Student.Major", "Student.Phone_Number", "Communicates_Using.SM_Acc"};
        createWhere();
        Cursor c = db.query(true, "Student " +  getResources().getString(R.string.lame_o_join),colums,whereQuery,null,null,null,null,null);
        ArrayList<String> results = new ArrayList<String>();

        if(c.moveToFirst())
        {
            do{
                results.add("Name: " + c.getString(0) + " " + c.getString(1) + "\nMajor: " + c.getString(2)+ "\nPhone #: " + c.getString(3)+ "\nSocial Media: " + c.getString(4));
            }while (c.moveToNext());

        }


        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, results);
        listView.setAdapter((arrayAdapter));
        c.close();
        Snackbar snackbar = Snackbar
                .make(relativeLayout, "Search returned <" + results.size() + "> Student(s).", Snackbar.LENGTH_LONG);
        db.close();
        snackbar.show();
   }


    public String createWhere()
    {
        String ColCondition = null;
        String MajCondition = null;
        String CourCondition = null;
        String GrouCondition = null;
        String ZIPCondition = null;

        whereQuery = "";

        if(ColSpinner.getSelectedItem().equals("ANY"))
        {
            // nuffin
        }
        else
        {
            if(whereQuery.equals(""))
            {
                whereQuery += "College.Col_Name LIKE '%" + ColSpinner.getSelectedItem().toString() + "%' ";
            }

        }
        if(MajorSpinner.getSelectedItem().equals("ALL"))
        {
            // nuffin
        }
        else
        {
            if(whereQuery.equals(""))
            {
                whereQuery += "Student.Major LIKE '%" + MajorSpinner.getSelectedItem().toString() + "%' ";
            }
            else
            {
                whereQuery += "AND Student.Major LIKE '%" + MajorSpinner.getSelectedItem().toString() + "%' ";
            }
        }

        if(CourseSpinner.getSelectedItem().equals("ANY"))
        {
            // nuffin
        }
        else
        {
            if(whereQuery.equals(""))
            {
                whereQuery += "Courses.C_Name LIKE '%" + CourseSpinner.getSelectedItem().toString() + "%'";
            }
            else
            {
                whereQuery += "AND Courses.C_Name LIKE '%" + CourseSpinner.getSelectedItem().toString() + "%'";
            }
        }

        if(GroupSpinner.getSelectedItem().equals("ANY") || GroupSpinner.getSelectedItem().equals("NONE"))
        {
            // nuffin
        }
        else
        {
            if(whereQuery.equals(""))
            {
                whereQuery += "Groups.Name LIKE '%" + GroupSpinner.getSelectedItem().toString() + "%'";
            }
            else
            {
                whereQuery += "AND Groups.Name LIKE '%" + GroupSpinner.getSelectedItem().toString() + "%'";
            }
        }
        if(ZIPSpinner.getSelectedItem().equals("ANY"))
        {
            // nuffin
        }
        else
        {
            if(whereQuery.equals(""))
            {
                whereQuery += "Address.ZIP LIKE '%" + ZIPSpinner.getSelectedItem().toString() + "%'";
            }
            else
            {
                whereQuery += "AND Address.ZIP LIKE '%" + ZIPSpinner.getSelectedItem().toString() + "%'";
            }
        }

        return whereQuery;
    }

}
