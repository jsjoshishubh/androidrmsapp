package com.example.rms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class RmsVerificationCode extends AppCompatActivity {
    private EditText editTextCode;
    private String mVerificationId;
    private FirebaseAuth mAuth;
    private Button Bt_SignIn;
    String mobile = "";

    SharedPreferences sharedPreferences;
    String sp_name = "rms_sp";
    String sp_logged = "isLoggedIn";
    String mobile_no = "mobile_no";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rms_verification_code);

        mAuth = FirebaseAuth.getInstance();
        editTextCode = findViewById(R.id.editTextCode);
        Bt_SignIn = findViewById(R.id.Bt_SignIN);

        sharedPreferences = getSharedPreferences(sp_name, MODE_PRIVATE);

        Intent intent = getIntent();
        mobile = intent.getStringExtra("Mobile");
        sendVerificationCode(mobile);

        Bt_SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editTextCode.getText().toString().trim();

                if (code.isEmpty() || code.length() < 6) {
                    editTextCode.setError("Enter Vaild Code");
                    editTextCode.requestFocus();
                    return;

                }
                verifyRmsVerificationCode(code);
            }
        });
    }

    private void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + mobile,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                editTextCode.setText(code);
                verifyRmsVerificationCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(RmsVerificationCode.this, e.getMessage() + "Failed", Toast.LENGTH_SHORT).show();

        }

        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            mVerificationId = s;
        }
    };

    private void verifyRmsVerificationCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential((String) mVerificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(RmsVerificationCode.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Login Success

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean(sp_logged, true);
                            editor.putString(mobile_no, mobile);
                            editor.commit();

                            Intent intent = new Intent(RmsVerificationCode.this, RmsProfile.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            String message = "Something Is Wrong!! We Will Fix This Soon...";
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid Code Entered";
                            }
                            Toast.makeText(RmsVerificationCode.this, message, Toast.LENGTH_SHORT).show();
                        }


                    }
                });

    }


}