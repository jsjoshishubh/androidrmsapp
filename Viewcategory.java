package com.example.rms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Viewcategory extends AppCompatActivity {
    ListView lv;
    DatabaseHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcategory);

        handler = new DatabaseHandler(Viewcategory.this);

        lv= findViewById(R.id.list_cat);
    }
    protected void onResume() {

        super.onResume();
        getData();
    }

    private void getData() {
        ArrayList<ArrayList<String>> arrayList = handler.getAllcategory();

//        System.out.println("category:: length:: " + arrayList.size());

        CustomAdapter adapter = new CustomAdapter(Viewcategory.this, arrayList);

        lv.setAdapter(adapter);
    }
}