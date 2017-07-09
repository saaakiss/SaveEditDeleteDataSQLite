package com.example.saveeditdeletedatasqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sak on 09-Jul-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //Name of the class
    private static final String TAG = "DatabaseHelper";

    //Table name and columns
    private static final String TABLE_NAME = "people_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";

    //Constructor that matches super
    public DatabaseHelper(Context context){
        super(context, TABLE_NAME, null, 1);
    }

    //Called in order ot create the table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                " (" + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " TEXT)";
        db.execSQL(createTable);
    }

    //Called in order to upgrade table, from older to newer version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP IF TABLE EXISTS " + TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }

    public boolean addDataToDB(String newEntry){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, newEntry);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if data is not inserted correctly, return -1
        if (result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}
