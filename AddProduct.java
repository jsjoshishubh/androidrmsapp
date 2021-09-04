package com.example.rms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.Locale;

import static android.media.CamcorderProfile.get;

public class AddProduct extends AppCompatActivity {
    private Spinner Cat_Spinner;
    EditText txt_Pname, Et_ProdDesc, Et_ProdPrice;
    private Button Btn_select;

    ArrayList<String> category = new ArrayList<>();

    String Category_value, Pname, ProdDesc, ProdPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        final DatabaseHandlerProduct handlerProduct = new DatabaseHandlerProduct(AddProduct.this);

        txt_Pname = findViewById(R.id.txt_Pname);
        Et_ProdDesc = findViewById(R.id.Et_ProdDesc);
        Et_ProdPrice = findViewById(R.id.Et_ProdPrice);
        Btn_select = findViewById(R.id.Btn_select);

        Cat_Spinner = findViewById(R.id.Cat_Spinner);

        Cat_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Category_value = category.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pname = txt_Pname.getText().toString().trim();
                ProdDesc = Et_ProdDesc.getText().toString().toLowerCase();
                ProdPrice = Et_ProdPrice.getText().toString().trim();

                if (Category_value.isEmpty()) {
                    Toast.makeText(AddProduct.this, "Please Select Category", Toast.LENGTH_SHORT).show();
                } else if (Pname.isEmpty()) {
                    Toast.makeText(AddProduct.this, "Enter Product Name", Toast.LENGTH_SHORT).show();
                } else if (ProdDesc.isEmpty()) {
                    Toast.makeText(AddProduct.this, "Enter Product Description", Toast.LENGTH_SHORT).show();
                } else {
                    handlerProduct.add_product(Category_value, Pname, ProdDesc, ProdPrice);
                    System.out.println("category::" + Category_value);
                    System.out.println("Pname::" + Pname);
                    System.out.println("ProdDesc::" + ProdDesc);
                    System.out.println("ProdPrice::" + ProdPrice);

                    Toast.makeText(AddProduct.this, "Product Added", Toast.LENGTH_SHORT).show();

                    txt_Pname.setText("");
                    Et_ProdDesc.setText("");
                    Et_ProdPrice.setText("");

                    finish();

                    Toast.makeText(AddProduct.this, "view your product ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loadSpinnerData();
    }

    private void loadSpinnerData() {
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        ArrayList<ArrayList<String>> category_table = db.getAllcategory();

        for (int i = 0; i < category_table.size(); i++) {
            category.add(category_table.get(i).get(1));
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AddProduct.this, R.layout.spinner_list, R.id.spinner_list, category);

        Cat_Spinner.setAdapter(dataAdapter);
        System.out.println("Cat_Spinner::" + Cat_Spinner);
    }
}