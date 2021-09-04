package com.example.rms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addcategory extends AppCompatActivity {
    EditText add_cat;
    Button btn_add;

    DatabaseHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcategory);

        handler = new DatabaseHandler(addcategory.this);

        add_cat = findViewById(R.id.txt_add_cat);
        btn_add = findViewById(R.id.btn_add_cat);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = add_cat.getText().toString().trim();

                if (category.isEmpty()) {
                    Toast.makeText(addcategory.this, "Enter Category..", Toast.LENGTH_SHORT).show();
                } else {
                    handler.add_category(category);
                    Toast.makeText(addcategory.this, "Category Added : " + handler.getCatCount(), Toast.LENGTH_SHORT).show();
                    add_cat.setText("");
                }
            }
        });
    }
}