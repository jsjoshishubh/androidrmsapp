package com.example.rms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DB_Version = 1;
    private static final String DB_Name = "Rms_Db";
    private static final String Cat_Table_Name = "category_table";
    private static final String Cat_ID = "_id";
    private static final String _Category = "_category";


    public DatabaseHandler(@Nullable Context context) {
        super(context, DB_Name, null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + Cat_Table_Name + "(" + Cat_ID + " INTEGER PRIMARY KEY," + _Category + " TEXT )";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + Cat_Table_Name;
        db.execSQL(query);
    }

    void add_category(String category) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(_Category, category);

            db.insert(Cat_Table_Name, null, values);
//            db.close();

            System.out.println("category:: ADDED: ");
        } catch(Exception ex) {
            System.out.println("category:: catch: " + ex.toString());
        }
    }

    public int getCatCount(){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = " SELECT *FROM " + Cat_Table_Name;

        Cursor cursor = db.rawQuery(query,null);
        int bh = cursor.getCount();
        cursor.close();

        return bh;
    }

    public ArrayList<ArrayList<String>> getAllcategory() {
        ArrayList<ArrayList<String>> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + Cat_Table_Name;

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                ArrayList<String> data = new ArrayList<>();

                data.add(cursor.getString(cursor.getColumnIndex(Cat_ID)));
                data.add(cursor.getString(cursor.getColumnIndex(_Category)));

                arrayList.add(data);
            } while (cursor.moveToNext());
        }

        System.out.println("category:: get: " + arrayList.toString());

        return arrayList;
    }


}
