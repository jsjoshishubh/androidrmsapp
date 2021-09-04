package com.example.rms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.LineNumberReader;

public class RmsProfile extends AppCompatActivity {
    Button btn_logout;
    TextView Cat,Cat1;
    LinearLayout add_cat, add_pro, view_cat, view_pro;

    SharedPreferences sharedPreferences;
    String sp_name = "rms_sp";
    String sp_logged = "isLoggedIn";
    String mobile_no = "mobile_no";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rms_profile);

        sharedPreferences = getSharedPreferences(sp_name, MODE_PRIVATE);
        add_cat = findViewById(R.id.add_cat);
        add_pro = findViewById(R.id.add_pro);
        view_cat = findViewById(R.id.view_cat);
        view_pro = findViewById(R.id.view_pro);
        btn_logout = findViewById(R.id.btn_logout);




        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.clear();
                editor.remove(sp_logged);
                editor.remove(mobile_no);
                editor.commit();

                Intent intent = new Intent(RmsProfile.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        add_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RmsProfile.this ,addcategory.class);
                startActivity(intent);
            }
        });
        view_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RmsProfile.this,Viewcategory.class);
                startActivity(intent);
            }
        });
        add_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RmsProfile.this,AddProduct.class);
                startActivity(intent);
            }
        });
        view_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RmsProfile.this,ViewProd.class);
                startActivity(intent);
            }
        });

    }
}