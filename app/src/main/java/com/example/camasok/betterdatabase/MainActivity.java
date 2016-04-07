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
        createEverything();

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

    SQLiteDatabase database;



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
        Cursor c = db.query("Student", null, null, null, null, null, null);
        c.moveToFirst();
        editText.setText(c.getString(2));

   }

    public void createEverything()
    {

    try {
        CoffeeDBHelper cDBHelper = new CoffeeDBHelper((getApplicationContext()));
        SQLiteDatabase db = cDBHelper.getReadableDatabase();
        String create_student_table = "CREATE TABLE Student\n" +
                "(\n" +
                "  Major VARCHAR(40),\n" +
                "  Minor VARCHAR(40),\n" +
                "  Stu_ID VARCHAR(40)  ,\n" +
                "  FName VARCHAR(40)  ,\n" +
                "  LName VARCHAR(40)  ,\n" +
                "  Phone_Number VARCHAR(40)  ,\n" +
                "  PRIMARY KEY (Stu_ID)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE Availability\n" +
                "(\n" +
                "  Morn INT  ,\n" +
                "  Aft INT  ,\n" +
                "  Eve INT  ,\n" +
                "  Day INT  ,\n" +
                "  Stu_ID VARCHAR(40)  ,\n" +
                "  FOREIGN KEY (Stu_ID) REFERENCES Student(Stu_ID)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE Transportation\n" +
                "(\n" +
                "  Close_Bus_Route VARCHAR(40)  ,\n" +
                "  Close_Train_Line VARCHAR(40)  ,\n" +
                "  Stu_ID VARCHAR(40)  ,\n" +
                "  FOREIGN KEY (Stu_ID) REFERENCES Student(Stu_ID)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE Courses\n" +
                "(\n" +
                "  C_Name VARCHAR(40)  ,\n" +
                "  C_Loc VARCHAR(40)  ,\n" +
                "  C_ID INT  ,\n" +
                "  Stu_ID VARCHAR(40)  ,\n" +
                "  PRIMARY KEY (C_ID),\n" +
                "  FOREIGN KEY (Stu_ID) REFERENCES Student(Stu_ID)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE Interests\n" +
                "(\n" +
                "  I_Loc VARCHAR(40)  ,\n" +
                "  I_Name VARCHAR(40)  ,\n" +
                "  IsSport INT  ,\n" +
                "  Stu_ID VARCHAR(40)  ,\n" +
                "  FOREIGN KEY (Stu_ID) REFERENCES Student(Stu_ID)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE Communicates_Using\n" +
                "(\n" +
                "  SM_Acc VARCHAR(40)  ,\n" +
                "  Stu_ID VARCHAR(40)  ,\n" +
                "  FOREIGN KEY (Stu_ID) REFERENCES Student(Stu_ID)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE Availability_Weekday\n" +
                "(\n" +
                "  Monday INT  ,\n" +
                "  Tuesday INT  ,\n" +
                "  Wednesday INT  ,\n" +
                "  Thursday INT  ,\n" +
                "  Friday INT  ,\n" +
                "  Stu_ID VARCHAR(40)  ,\n" +
                "  FOREIGN KEY (Stu_ID) REFERENCES Student(Stu_ID)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE Transportation_Preference\n" +
                "(\n" +
                "  Preference VARCHAR(40)  ,\n" +
                "  Stu_ID VARCHAR(40)  ,\n" +
                "  FOREIGN KEY (Stu_ID) REFERENCES Student(Stu_ID)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE Stu_Courses\n" +
                "(\n" +
                "  Stu_ID VARCHAR(40)  ,\n" +
                "  C_ID INT  ,\n" +
                "  FOREIGN KEY (C_ID) REFERENCES Courses(C_ID)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE Food\n" +
                "(\n" +
                "  Allergies VARCHAR(40)  ,\n" +
                "  Preferences VARCHAR(40)  ,\n" +
                "  Stu_ID VARCHAR(40)  ,\n" +
                "  FOREIGN KEY (Stu_ID) REFERENCES Student(Stu_ID)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE Address\n" +
                "(\n" +
                "  Street VARCHAR(40)  ,\n" +
                "  City VARCHAR(40)  ,\n" +
                "  State VARCHAR(40)  ,\n" +
                "  ZIP INT  ,\n" +
                "  Carpool_Avail INT  ,\n" +
                "  Stu_ID VARCHAR(40)  ,\n" +
                "  FOREIGN KEY (Stu_ID) REFERENCES Student(Stu_ID)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE Groups\n" +
                "(\n" +
                "  G_ID INT  ,\n" +
                "  Name VARCHAR(40)  ,\n" +
                "  Purpose VARCHAR(40)  ,\n" +
                "  Stu_ID VARCHAR(40)  ,\n" +
                "  PRIMARY KEY (G_ID),\n" +
                "  FOREIGN KEY (Stu_ID) REFERENCES Student(Stu_ID)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE College\n" +
                "(\n" +
                "  College_ID INT  ,\n" +
                "  Col_Name VARCHAR(40)  ,\n" +
                "  C_ID INT  ,\n" +
                "  PRIMARY KEY (College_ID),\n" +
                "  FOREIGN KEY (C_ID) REFERENCES Courses(C_ID)\n" +
                ");\n";

        db.execSQL(create_student_table);

        String add_student_data = "INSERT INTO Student (Stu_ID, Major, Minor, FName, LName, Phone_Number)\n" +
                "VALUES ('W00456123', 'Computer Science', 'Business', 'Stephanie', 'Lyons', '6315678231');\n" +
                "INSERT INTO Transportation (Close_Bus_Route, Close_Train_Line, Stu_ID)\n" +
                "VALUES (NULL, 'E Branch', 'W00456123');\n" +
                "INSERT INTO Transportation_Preference (Preference, Stu_ID)\n" +
                "VALUES ('None', 'W00456123');\n" +
                "INSERT INTO Address (Address, City, State, ZIP, Carpool_Avail, Stu_ID)\n" +
                "VALUES ('64 Main Street', 'Somerset', 'NJ', '07059', 0, 'W00456123');\n" +
                "INSERT INTO Availability (Day, Morn, Aft, Eve, Stu_ID)\n" +
                "VALUES ('Thursday', 0, 0, 1, 'W00456123');\n" +
                "INSERT INTO Availability_Weekday (Monday, Tuesday, Wednesday, Thursday, Friday, Stu_ID)\n" +
                "VALUES (0, 1, 0, 1, 1, 'W00456123');\n" +
                "INSERT INTO Courses (C_ID, C_Name, C_Loc, Stu_ID)\n" +
                "VALUES ('MATH678', 'Discrete Math', 'BEATT910', 'W00456123');\n" +
                "INSERT INTO College (College_ID, Col_Name, C_ID)\n" +
                "VALUES ('1112', 'Wentworth Institute of Technology', 'MATH678');\n" +
                "INSERT INTO Stu_Courses (Stu_ID, C_ID)\n" +
                "VALUES ('W00456123', 'MATH678');\n" +
                "INSERT INTO Food (Allergies, Preferences, Stu_ID)\n" +
                "VALUES ('None', 'Japanese', 'W00456123');\n" +
                "INSERT INTO Groups (G_ID, Name, Purpose, Stu_ID)\n" +
                "VALUES ('288', 'Guitar Club', 'Music', 'W00456123');\n" +
                "INSERT INTO Interests (I_Loc, I_Name, IsSport, Stu_ID)\n" +
                "VALUES ('BEATT628', 'Music', 0, 'W00456123');\n" +
                "INSERT INTO Communicates_Using (SM_Acc, Stu_ID)\n" +
                "VALUES ('twitter.com/stephisbest', 'W00456123');\n" +

                "INSERT INTO Student (Stu_ID, Major, Minor, FName, LName, Phone_Number)\n" +
                "VALUES ('M00697586', 'Photography', NULL, 'Rachel', 'Johnson', '9085547632');\n" +
                "INSERT INTO Transportation (Close_Bus_Route, Close_Train_Line, Stu_ID)\n" +
                "VALUES ('M66', 'E Branch', 'M00697586');\n" +
                "INSERT INTO Transportation_Preference (Preference, Stu_ID)\n" +
                "VALUES ('None', 'M00697586');\n" +
                "INSERT INTO Address (Address, City, State, ZIP, Carpool_Avail, Stu_ID)\n" +
                "VALUES ('43 Teak Street', 'Basking Ridge', 'NJ', '07059', 0, 'M00697586');\n" +
                "INSERT INTO Availability (Day, Morn, Aft, Eve, Stu_ID)\n" +
                "VALUES ('Friday', 1, 1, 1, 'M00697586');\n" +
                "INSERT INTO Availability_Weekday (Monday, Tuesday, Wednesday, Thursday, Friday, Stu_ID)\n" +
                "VALUES (1, 1, 1, 1, 1, 'M00697586');\n" +
                "INSERT INTO Courses (C_ID, C_Name, C_Loc, Stu_ID)\n" +
                "VALUES ('PHOT231', 'Photography 101', 'ENGLE707', 'M00697586');\n" +
                "INSERT INTO College (College_ID, Col_Name, C_ID)\n" +
                "VALUES (('1120', 'Massachusetts College of Art and Design', 'PHOT231');\n" +
                "INSERT INTO Stu_Courses (Stu_ID, C_ID)\n" +
                "VALUES ('M00697586', 'PHOT231');\n" +
                "INSERT INTO Food (Allergies, Preferences, Stu_ID)\n" +
                "VALUES ('None', 'None', 'M00697586');\n" +
                "INSERT INTO Groups (G_ID, Name, Purpose, Stu_ID)\n" +
                "VALUES ('12', 'Hair Dye Club', 'Hair Colors', 'M00697586');\n" +
                "INSERT INTO Interests (I_Loc, I_Name, IsSport, Stu_ID)\n" +
                "VALUES ('NULL', 'Photography', 0, 'M00697586');\n" +
                "INSERT INTO Communicates_Using (SM_Acc, Stu_ID)\n" +
                "VALUES ('facebook.com/im.cool.foreal55, 'M00697586');\n" +

                "INSERT INTO Student (Stu_ID, Major, Minor, FName, LName, Phone_Number)\n" +
                "VALUES ('W00867530', 'Computer Science', 'Basket Weaving', 'Karen', 'Camaso', '5555555555');\n" +
                "INSERT INTO Transportation (Close_Bus_Route, Close_Train_Line, Stu_ID)\n" +
                "VALUES (NULL, 'E Branch', 'W00867530');\n" +
                "INSERT INTO Transportation_Preference (Preference, Stu_ID)\n" +
                "VALUES ('None', 'W00867530');\n" +
                "INSERT INTO Address (Address, City, State, ZIP, Carpool_Avail, Stu_ID)\n" +
                "VALUES ('108 Lexington St', 'East Boston', 'MA', '02128', 0, 'W00867530');\n" +
                "INSERT INTO Availability (Day, Morn, Aft, Eve, Stu_ID)\n" +
                "VALUES ('Thursday', 0, 0, 1, 'W00867530');\n" +
                "INSERT INTO Availability_Weekday (Monday, Tuesday, Wednesday, Thursday, Friday, Stu_ID)\n" +
                "VALUES (0, 1, 0, 1, 1, 'W00867530');\n" +
                "INSERT INTO Courses (C_ID, C_Name, C_Loc, Stu_ID)\n" +
                "VALUES ('MATH678', 'Discrete Math', 'BEATT910', 'W00867530');\n" +
                "INSERT INTO College (College_ID, Col_Name, C_ID)\n" +
                "VALUES ('1112', 'Wentworth Institute of Technology', 'MATH678');\n" +
                "INSERT INTO Stu_Courses (Stu_ID, C_ID)\n" +
                "VALUES ('W00867530', 'MATH678');\n" +
                "INSERT INTO Food (Allergies, Preferences, Stu_ID)\n" +
                "VALUES ('None', 'Japanese', 'W00867530');\n" +
                "INSERT INTO Groups (G_ID, Name, Purpose, Stu_ID)\n" +
                "VALUES ('288', 'Guitar Club', 'Music', 'W00867530');\n" +
                "INSERT INTO Interests (I_Loc, I_Name, IsSport, Stu_ID)\n" +
                "VALUES ('BEATT628', 'Music', 0, 'W00867530');\n" +
                "INSERT INTO Communicates_Using (SM_Acc, Stu_ID)\n" +
                "VALUES ('twitter.com/LiterallyTheGreatest', 'W00867530');\n" +

                "INSERT INTO Student (Stu_ID, Major, Minor, FName, LName, Phone_Number)\n" +
                "VALUES ('S00999666', 'Music', 'Basket Weaving', 'Peter', 'Peterson', '5555555555');\n" +
                "INSERT INTO Transportation (Close_Bus_Route, Close_Train_Line, Stu_ID)\n" +
                "VALUES ('M76', 'E Branch', 'S00999666');\n" +
                "INSERT INTO Transportation_Preference (Preference, Stu_ID)\n" +
                "VALUES ('None', 'S00999666');\n" +
                "INSERT INTO Address (Address, City, State, ZIP, Carpool_Avail, Stu_ID)\n" +
                "VALUES ('108 Road St', 'Lowell', 'MA', '02145', 0, 'S00999666');\n" +
                "INSERT INTO Availability (Day, Morn, Aft, Eve, Stu_ID)\n" +
                "VALUES ('Tuesday', 1, 0, 1, 'S00999666');\n" +
                "INSERT INTO Availability (Day, Morn, Aft, Eve, Stu_ID)\n" +
                "VALUES ('Thursday', 0, 1, 1, 'S00999666');\n" +
                "INSERT INTO Availability (Day, Morn, Aft, Eve, Stu_ID)\n" +
                "VALUES ('Friday', 0, 0, 1, 'S00999666');\n" +
                "INSERT INTO Availability_Weekday (Monday, Tuesday, Wednesday, Thursday, Friday, Stu_ID)\n" +
                "VALUES (0, 1, 0, 1, 1, 'S00999666');\n" +
                "INSERT INTO Courses (C_ID, C_Name, C_Loc, Stu_ID)\n" +
                "VALUES ('MUSC324', 'Music Theory', 'ANXS910', 'S00999666');\n" +
                "INSERT INTO College (College_ID, Col_Name, C_ID)\n" +
                "VALUES ('1112', 'Wentworth Institute of Technology', 'MUSC324');\n" +
                "INSERT INTO Stu_Courses (Stu_ID, C_ID)\n" +
                "VALUES ('S00999666', 'MUSC324');\n" +
                "INSERT INTO Food (Allergies, Preferences, Stu_ID)\n" +
                "VALUES ('Peanuts', 'Japanese', 'S00999666');\n" +
                "INSERT INTO Groups (G_ID, Name, Purpose, Stu_ID)\n" +
                "VALUES ('288', 'Guitar Club', 'Music', 'S00999666');\n" +
                "INSERT INTO Interests (I_Loc, I_Name, IsSport, Stu_ID)\n" +
                "VALUES ('BEATT628', 'Music', 0, 'S00999666');\n" +
                "INSERT INTO Communicates_Using (SM_Acc, Stu_ID)\n" +
                "VALUES ('twitter.com/BetterThnU', 'S00999666');\n" +

                "INSERT INTO Student (Stu_ID, Major, Minor, FName, LName, Phone_Number)\n" +
                "VALUES ('W00487256', 'Business', NULL, 'AntsInMyEyes', 'Johnson', '6368538035');\n" +
                "INSERT INTO Transportation (Close_Bus_Route, Close_Train_Line, Stu_ID)\n" +
                "VALUES ('M34', 'D Branch', 'W00487256');\n" +
                "INSERT INTO Transportation_Preference (Preference, Stu_ID)\n" +
                "VALUES ('Train', 'W00487256');\n" +
                "INSERT INTO Address (Address, City, State, ZIP, Carpool_Avail, Stu_ID)\n" +
                "VALUES ('729 Harper Rd', 'English', 'TX', '20423', 0, 'W00487256');\n" +
                "INSERT INTO Availability (Day, Morn, Aft, Eve, Stu_ID)\n" +
                "VALUES ('Monday', 1, 0, 1, 'W00487256');\n" +
                "INSERT INTO Availability_Weekday (Monday, Tuesday, Wednesday, Thursday, Friday, Stu_ID)\n" +
                "VALUES (1, 0, 0, 0, 0, 'W00487256');\n" +
                "INSERT INTO Courses (C_ID, C_Name, C_Loc, Stu_ID)\n" +
                "VALUES ('BSKT971', 'Advanced Basket Weaving', 'ENGLE445', 'W00487256');\n" +
                "INSERT INTO College (College_ID, Col_Name, C_ID)\n" +
                "VALUES ('1120', 'Massachusetts College of Art and Design', 'BSKT971');\n" +
                "INSERT INTO Stu_Courses (Stu_ID, C_ID)\n" +
                "VALUES ('W00487256', 'BSKT971');\n" +
                "INSERT INTO Food (Allergies, Preferences, Stu_ID)\n" +
                "VALUES ('None', 'American', 'W00487256');\n" +
                "INSERT INTO Groups (G_ID, Name, Purpose, Stu_ID)\n" +
                "VALUES ('22', 'Glee Club', 'Theater', 'W00487256');\n" +
                "INSERT INTO Interests (I_Loc, I_Name, IsSport, Stu_ID)\n" +
                "VALUES ('ROPM112', 'Biology', 0, 'W00487256');\n" +
                "INSERT INTO Communicates_Using (SM_Acc, Stu_ID)\n" +
                "VALUES ('facebook.com/OwThePain', 'W00487256');\n" +
                "INSERT INTO Student (Stu_ID, Major, Minor, FName, LName, Phone_Number)\n" +
                "VALUES ('W00858672', 'Computer Science', 'Computer Engineering', 'John', 'Doe', '7776269678');\n" +
                "INSERT INTO Transportation (Close_Bus_Route, Close_Train_Line, Stu_ID)\n" +
                "VALUES ('M76', 'Red', 'W00858672');\n" +
                "INSERT INTO Transportation_Preference (Preference, Stu_ID)\n" +
                "VALUES ('Train', 'W00858672');\n" +
                "INSERT INTO Address (Address, City, State, ZIP, Carpool_Avail, Stu_ID)\n" +
                "VALUES ('12 Threefour Lane', 'Fiveton', 'MA', '02114', 1, 'W00858672');\n" +
                "INSERT INTO Availability (Day, Morn, Aft, Eve, Stu_ID)\n" +
                "VALUES ('Monday', 1, 0, 0, 'W00858672');\n" +
                "INSERT INTO Availability_Weekday (Monday, Tuesday, Wednesday, Thursday, Friday, Stu_ID)\n" +
                "VALUES (1, 1, 0, 0, 1, 'W00858672');\n" +
                "INSERT INTO Courses (C_ID, C_Name, C_Loc, Stu_ID)\n" +
                "VALUES ('MATH678', 'Discrete Math', 'BEATT910', 'W00858672');\n" +
                "INSERT INTO College (College_ID, Col_Name, C_ID)\n" +
                "VALUES ('1112', 'Wentworth Institute of Technology', 'MATH678');\n" +
                "INSERT INTO Stu_Courses (Stu_ID, C_ID)\n" +
                "VALUES ('W00858672', 'MATH678');\n" +
                "INSERT INTO Food (Allergies, Preferences, Stu_ID)\n" +
                "VALUES ('None', 'American', 'W00858672');\n" +
                "INSERT INTO Groups (G_ID, Name, Purpose, Stu_ID)\n" +
                "VALUES ('287', 'Music Club', 'Music', 'W00858672');\n" +
                "INSERT INTO Interests (I_Loc, I_Name, IsSport, Stu_ID)\n" +
                "VALUES ('BEATT628', 'Music', 0, 'W00858672');\n" +
                "INSERT INTO Communicates_Using (SM_Acc, Stu_ID)\n" +
                "VALUES ('facebook.com/i.am.cool12', 'W00858672');\n" +

                "INSERT INTO Student (Stu_ID, Major, Minor, FName, LName, Phone_Number)\n" +
                "VALUES ('W00767535', 'Computer Networking', NULL, 'Carter', 'Sheehan', '9785307767');\n" +
                "INSERT INTO Transportation (Close_Bus_Route, Close_Train_Line, Stu_ID)\n" +
                "VALUES (NULL, 'Commuter Rail', 'W00767535');\n" +
                "INSERT INTO Transportation_Preference (Preference, Stu_ID)\n" +
                "VALUES ('Car', 'W00767535');\n" +
                "INSERT INTO Address (Address, City, State, ZIP, Carpool_Avail, Stu_ID)\n" +
                "VALUES ('78 Marsten Road', 'Salem', 'MA', '02155', 1, 'W00767535');\n" +
                "INSERT INTO Availability (Day, Morn, Aft, Eve, Stu_ID)\n" +
                "VALUES ('Wednesday', 0, 0, 1, 'W00767535');\n" +
                "INSERT INTO Availability_Weekday (Monday, Tuesday, Wednesday, Thursday, Friday, Stu_ID)\n" +
                "VALUES (1, 0, 0, 1, 1, 'W00767535');\n" +
                "INSERT INTO Courses (C_ID, C_Name, C_Loc, Stu_ID)\n" +
                "VALUES ('MATH135', 'College Math', 'WENT637', 'W00767535');\n" +
                "INSERT INTO College (College_ID, Col_Name, C_ID)\n" +
                "VALUES ('1112', 'Wentworth Institute of Technology', 'MATH135');\n" +
                "INSERT INTO Stu_Courses (Stu_ID, C_ID)\n" +
                "VALUES ('W00767535', 'MATH135');\n" +
                "INSERT INTO Food (Allergies, Preferences, Stu_ID)\n" +
                "VALUES ('Peanuts', 'Pizza', 'W00767535');\n" +
                "INSERT INTO Groups (G_ID, Name, Purpose, Stu_ID)\n" +
                "VALUES ('22', 'Glee Club', 'Theater', 'W00767535');\n" +
                "INSERT INTO Interests (I_Loc, I_Name, IsSport, Stu_ID)\n" +
                "VALUES ('WATSONAUD', 'Theater', 0, 'W00767535');\n" +
                "INSERT INTO Communicates_Using (SM_Acc, Stu_ID)\n" +
                "VALUES ('facebook.com/my.project.is.bad', 'W00767535');\n" +

                "INSERT INTO Student (Stu_ID, Major, Minor, FName, LName, Phone_Number)\n" +
                "VALUES ('W00798247', 'Architecture', NULL, 'Merluzzon', 'Nick', '5658897823');\n" +
                "INSERT INTO Transportation (Close_Bus_Route, Close_Train_Line, Stu_ID)\n" +
                "VALUES (NULL, 'E Branch', 'W00798247');\n" +
                "INSERT INTO Transportation_Preference (Preference, Stu_ID)\n" +
                "VALUES ('Walk', 'W00798247');\n" +
                "INSERT INTO Address (Address, City, State, ZIP, Carpool_Avail, Stu_ID)\n" +
                "VALUES ('5567 Tremont Street', 'Boston', 'MA', '02115', 0, 'W00798247');\n" +
                "INSERT INTO Availability (Day, Morn, Aft, Eve, Stu_ID)\n" +
                "VALUES ('Thursday', 1, 1, 1, 'W00798247');\n" +
                "INSERT INTO Availability_Weekday (Monday, Tuesday, Wednesday, Thursday, Friday, Stu_ID)\n" +
                "VALUES (1, 1, 0, 0, 1, 'W00798247');\n" +
                "INSERT INTO Courses (C_ID, C_Name, C_Loc, Stu_ID)\n" +
                "VALUES ('ARCH556', 'Intro to Architecture', 'ANXC243', 'W00798247');\n" +
                "INSERT INTO College (College_ID, Col_Name, C_ID)\n" +
                "VALUES ('1112', 'Wentworth Institute of Technology', 'ARCH556');\n" +
                "INSERT INTO Stu_Courses (Stu_ID, C_ID)\n" +
                "VALUES ('W00798247', 'ARCH556');\n" +
                "INSERT INTO Food (Allergies, Preferences, Stu_ID)\n" +
                "VALUES ('None', 'Burgers', 'W00798247');\n" +
                "INSERT INTO Groups (G_ID, Name, Purpose, Stu_ID)\n" +
                "VALUES ('66', 'Architecture Club', 'Architecture', 'W00798247');\n" +
                "INSERT INTO Interests (I_Loc, I_Name, IsSport, Stu_ID)\n" +
                "VALUES ('Soccer Field', 'Football', 1, 'W00798247');\n" +
                "INSERT INTO Communicates_Using (SM_Acc, Stu_ID)\n" +
                "VALUES ('facebook.com/merluzzon', 'W00798247');\n" +

                "INSERT INTO Student (Stu_ID, Major, Minor, FName, LName, Phone_Number)\n" +
                "VALUES ('M00134962', 'Art', 'Basket Weaving', 'Abby', 'Pino', '5618885542');\n" +
                "INSERT INTO Transportation (Close_Bus_Route, Close_Train_Line, Stu_ID)\n" +
                "VALUES ('M66', 'E Branch', 'M00134962');\n" +
                "INSERT INTO Transportation_Preference (Preference, Stu_ID)\n" +
                "VALUES ('Walk', 'M00134962');\n" +
                "INSERT INTO Address (Address, City, State, ZIP, Carpool_Avail, Stu_ID)\n" +
                "VALUES ('615 Huntington Ave', 'Boston', 'MA', '02115', 0, 'M00134962');\n" +
                "INSERT INTO Availability (Day, Morn, Aft, Eve, Stu_ID)\n" +
                "VALUES ('Monday', 0, 1, 1, 'M00134962');\n" +
                "INSERT INTO Availability_Weekday (Monday, Tuesday, Wednesday, Thursday, Friday, Stu_ID)\n" +
                "VALUES (0, 1, 1, 0, 0, 'M00134962');\n" +
                "INSERT INTO Courses (C_ID, C_Name, C_Loc, Stu_ID)\n" +
                "VALUES ('BSKT971', 'Advanced Basket Weaving', 'ENGLE445', 'M00134962');\n" +
                "INSERT INTO College (College_ID, Col_Name, C_ID)\n" +
                "VALUES ('1120', 'Massachusetts College of Art and Design', 'BSKT971');\n" +
                "INSERT INTO Stu_Courses (Stu_ID, C_ID)\n" +
                "VALUES ('M00134962', 'BSKT971');\n" +
                "INSERT INTO Food (Allergies, Preferences, Stu_ID)\n" +
                "VALUES ('Strawberries', 'Sushi', 'M00134962');\n" +
                "INSERT INTO Groups (G_ID, Name, Purpose, Stu_ID)\n" +
                "VALUES ('12', 'Hair Dye Club', 'Hair Colors', 'M00134962');\n" +
                "INSERT INTO Interests (I_Loc, I_Name, IsSport, Stu_ID)\n" +
                "VALUES (NULL, NULL, NULL, 'M00134962');\n" +
                "INSERT INTO Communicates_Using (SM_Acc, Stu_ID)\n" +
                "VALUES ('facebook.com/artmajor782', 'M00134962');\n" +

                "INSERT INTO Student (Stu_ID, Major, Minor, FName, LName, Phone_Number)\n" +
                "VALUES ('S00192837', 'Biology', 'Chemistry', 'Alex', 'Taylor', '7733365412');\n" +
                "INSERT INTO Transportation (Close_Bus_Route, Close_Train_Line, Stu_ID)\n" +
                "VALUES ('M34', 'D Branch', 'S00192837');\n" +
                "INSERT INTO Transportation_Preference (Preference, Stu_ID)\n" +
                "VALUES ('Walk', 'S00192837');\n" +
                "INSERT INTO Address (Address, City, State, ZIP, Carpool_Avail, Stu_ID)\n" +
                "VALUES ('2100 Fenway', 'Boston', 'MA', '02115', 0, 'S00192837');\n" +
                "INSERT INTO Availability (Day, Morn, Aft, Eve, Stu_ID)\n" +
                "VALUES ('Thursday', 1, 0, 1, 'S00192837');\n" +
                "INSERT INTO Availability_Weekday (Monday, Tuesday, Wednesday, Thursday, Friday, Stu_ID)\n" +
                "VALUES (1, 0, 1, 0, 1, 'S00192837');\n" +
                "INSERT INTO Courses (C_ID, C_Name, C_Loc, Stu_ID)\n" +
                "VALUES ('CHEM555', 'Intro to Chemistry', 'YTRB677', 'S00192837');\n" +
                "INSERT INTO College (College_ID, Col_Name, C_ID)\n" +
                "VALUES ('1119', 'Simmons College', 'CHEM555');\n" +
                "INSERT INTO Stu_Courses (Stu_ID, C_ID)\n" +
                "VALUES ('S00192837', 'CHEM555');\n" +
                "INSERT INTO Food (Allergies, Preferences, Stu_ID)\n" +
                "VALUES ('None', 'Mexican', 'S00192837');\n" +
                "INSERT INTO Groups (G_ID, Name, Purpose, Stu_ID)\n" +
                "VALUES ('13', 'Chemistry Club', 'Chemistry', 'S00192837');\n" +
                "INSERT INTO Interests (I_Loc, I_Name, IsSport, Stu_ID)\n" +
                "VALUES ('ROPM112', 'Biology', 0, 'S00192837');\n" +
                "INSERT INTO Communicates_Using (SM_Acc, Stu_ID)\n" +
                "VALUES ('facebook.com/chemperson', 'S00192837');\n";

        db.execSQL(add_student_data);
    }
    catch(android.database.SQLException ex)
    {
            throw ex;
    }

    }



}
