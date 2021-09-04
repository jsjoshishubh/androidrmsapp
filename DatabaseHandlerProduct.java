package com.example.rms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHandlerProduct extends SQLiteOpenHelper {

    private static final int DB_Version = 1;
    private static final String DB_NAME = "Rmsprod_db";

    private static final String TABLE_NAME = "Product_table";

    private static final String _ID = "_id";
    private static final String Prod_CATEGORY = "prod_category";
    private static final String _Pname = "_Pname";
    private static final String _ProdDesc = "_ProdDesc";
    private static final String _Price = "_Price";

    public DatabaseHandlerProduct(@Nullable Context context) {
        super(context, DB_NAME, null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + _ID + " INTEGER PRIMARY KEY," + Prod_CATEGORY + " TEXT," + _Pname
                + " TEXT," + _ProdDesc + " TEXT," + _Price + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }

    void add_product(String Category, String Pname, String ProdDesc, String price) {
        try {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Prod_CATEGORY, Category);
            values.put(_Pname, Pname);
            values.put(_ProdDesc, ProdDesc);
            values.put(_Price, price);

            db.insert(TABLE_NAME, null, values);
        }catch (Exception ex){
            System.out.println("Category:: catch: " + ex.toString());
            System.out.println("Pname:: catch: " + ex.toString());
            System.out.println("ProdDesc:: catch: " + ex.toString());
            System.out.println("price:: catch: " + ex.toString());
        }
    }

    public ArrayList<ArrayList<String>> getAllcategoryProduct(String Search ) {
        ArrayList<ArrayList<String>> array1 = new ArrayList<>();
        SQLiteDatabase db =this.getReadableDatabase();

      String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + _Pname + " LIKE '%" +Search + "%'";


        Cursor cursor=db.rawQuery(query,null);

        if(cursor.moveToNext()) {
            do {
                ArrayList<String> data = new ArrayList<>();

                data.add(cursor.getString(cursor.getColumnIndex(_ID)));
                data.add(cursor.getString(cursor.getColumnIndex(Prod_CATEGORY)));
                data.add(cursor.getString(cursor.getColumnIndex(_Pname)));
                data.add(cursor.getString(cursor.getColumnIndex(_ProdDesc)));
                data.add(cursor.getString(cursor.getColumnIndex(_Price)));
                array1.add(data);

            } while (cursor.moveToNext());
        }

            return array1;

    }

    public int Iv_Delete(int id){
        SQLiteDatabase db =this.getWritableDatabase();

        return db.delete(TABLE_NAME, _ID + " = ? ", new String[]{Integer.toString(id)});


    }

    public void getdata(){


        getdata();

    }

    // yaha par Get Data karo, or ViewProd me call karke listview me populate kar dena
}


