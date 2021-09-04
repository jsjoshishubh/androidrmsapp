package com.example.rms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewProd extends AppCompatActivity {
    ListView ProdLv;

    DatabaseHandlerProduct handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_prod);

        handler = new DatabaseHandlerProduct(ViewProd.this);
        ProdLv = findViewById(R.id.ProdLv);
    }

    private void getData() {
        ArrayList<ArrayList<String>> array = handler.getAllcategoryProduct("");
        //        System.out.println("category:: length:: " + arrayList.size());
        CustomAdapterProd adapter = new CustomAdapterProd(ViewProd.this, array);
        ProdLv.setAdapter(adapter);
    }

    protected void onResume() {
        super.onResume();
        getData();
    }
}