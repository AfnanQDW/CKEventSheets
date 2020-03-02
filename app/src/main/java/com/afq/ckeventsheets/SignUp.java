package com.afq.ckeventsheets;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmail;
    private EditText edtMobile;
    private EditText edtFullName;
    private EditText edtZipCode;
    private EditText edtPassword;
    private Button btnSignUp;
    private TextView txtLoginNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        edtEmail = findViewById(R.id.edtEmail);
        edtMobile = findViewById(R.id.edtMobile);
        edtFullName = findViewById(R.id.edtFullName);
        edtZipCode = findViewById(R.id.edtZipCode);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        txtLoginNow = findViewById(R.id.txtLoginNow);
        txtLoginNow.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignUp:

                break;
            case R.id.txtLoginNow:
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
                break;
        }
    }
}
