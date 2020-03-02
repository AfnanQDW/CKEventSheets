package com.afq.ckeventsheets;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText edtFullName;
    private EditText edtZipCode;
    private EditText edtMobile;
    private RadioButton rbRememberMe;
    private Button btnLogin;
    private TextView txtSignUpNow;
    private TextView txtForgotPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtFullName = findViewById(R.id.edtFullName);
        edtZipCode = findViewById(R.id.edtZipCode);
        edtMobile = findViewById(R.id.edtMobile);
        rbRememberMe = findViewById(R.id.rbRememberMe);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        txtSignUpNow = findViewById(R.id.txtSignUpNow);
        txtSignUpNow.setOnClickListener(this);

        txtForgotPass = findViewById(R.id.txtForgotPass);
        txtForgotPass.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:

                break;
            case R.id.txtForgotPass:

                break;
            case R.id.txtSignUpNow:
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
                break;
        }
    }
}
