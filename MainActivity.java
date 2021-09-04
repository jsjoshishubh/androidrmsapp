package com.example.rms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {
    private EditText editTextMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);

        editTextMobile = findViewById(R.id.editTextMobile);

        findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = editTextMobile.getText().toString().trim();
                if (mobile.isEmpty() || mobile.length() < 10) {
                    Toast.makeText(MainActivity.this, "Enter Vaild Number", Toast.LENGTH_SHORT).show();
                    editTextMobile.setText("");
                } else {
                    Intent intent = new Intent(MainActivity.this, RmsVerificationCode.class);
                    intent.putExtra("Mobile", mobile);
                    startActivity(intent);
                }
            }
        });
    }
}