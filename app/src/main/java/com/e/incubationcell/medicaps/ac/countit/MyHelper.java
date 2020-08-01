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
        sqLiteDatabase.execSQL("CREATE TABLE Questions (ID INTEGER PRIMARY KEY AUTOINCREMENT,SUBJECT Text , COUNT Integer )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Questions");
        onCreate(sqLiteDatabase);
    }

    public boolean InsertIt(String subjectName){
        int s = 0;
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("SUBJECT",subjectName);
        values.put("COUNT",s);
        int z = (int) sqLiteDatabase.insert("Questions",null,values);
        if(z == -1)
            return false;
        return true;
    }

    public Cursor Viewit(String subjectName){
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Questions WHERE TRIM(SUBJECT) = '"+subjectName.trim()+"'",null);
        return  cursor;
    }

    public boolean UpdateIt(String subjectName){
        sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Questions WHERE TRIM(SUBJECT) = '"+subjectName.trim()+"'",null);
        cursor.moveToNext();
        int i = parseInt(cursor.getString(2));
        i++;
        ContentValues values = new ContentValues();
        values.put("COUNT",i);
        int a = (int) sqLiteDatabase.update("Questions",values,"SUBJECT=?",new String[]{subjectName});
        if( a == -1)
            return false;
        return true;
    }

}
