package com.afq.ckeventsheets;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText edtFullName;
    private Spinner spnzZpCode;
    private EditText edtMobile;
    private RadioButton rbRememberMe;
    private Button btnLogin;
    private TextView txtSignUpNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtFullName = findViewById(R.id.edtFullName);
        spnzZpCode = findViewById(R.id.spnzZpCode);
        edtMobile = findViewById(R.id.edtMobile);
        rbRememberMe = findViewById(R.id.rbRememberMe);
        btnLogin = findViewById(R.id.btnLogin);
        txtSignUpNow = findViewById(R.id.txtSignUpNow);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:

                break;
            case R.id.txtSignUpNow:

                break;

        }
    }
}
