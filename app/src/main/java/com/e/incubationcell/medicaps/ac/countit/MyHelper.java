package com.e.incubationcell.medicaps.ac.countit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static java.lang.Integer.parseInt;

public class MyHelper extends SQLiteOpenHelper {
    private String name1 = "Questions";
    SQLiteDatabase sqLiteDatabase;
    public MyHelper(@Nullable Context context) {
        super(context,"Questions",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Questions (ID INTERGER PRIMARY KEY AUTOINCREMENT , SUBJECT TEXT , COUNT INTEGER )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + name1);
        onCreate(sqLiteDatabase);
    }

    public boolean InsertIt(String subjectName){
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("SUBJECT",subjectName);
        values.put("COUNT",0);
        int z = (int) sqLiteDatabase.insert("Questions",null,values);
        if(z == -1)
            return false;
        return true;
    }

    public Cursor Viewit(String subjectName){
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Questions WHERE SUBJECT = " +subjectName ,null);
        return  cursor;
    }

    public boolean UpdateIt(String subjectName){
        sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Questions WHERE SUBJECT = "+subjectName,null);
        int i = parseInt(cursor.getString(1));
        i++;
        ContentValues values = new ContentValues();
        values.put("COUNT",i);
        if(sqLiteDatabase.insert(name1,null,values) == -1)
            return false;
        return true;
    }

}
