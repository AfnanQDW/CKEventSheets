package com.afq.ckeventsheets;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText edtUsername;
    private EditText edtPassword;
    private RadioButton rbRememberMe;
    private Button btnLogin;
    private TextView txtSignUpNow;
    private TextView txtForgotPass;


    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sp = getSharedPreferences("sp",MODE_PRIVATE);
        editor = sp.edit();

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
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
        Intent intent;
        switch (view.getId()) {
            case R.id.btnLogin:
                postLoginInfo();
                break;
//            case R.id.txtForgotPass:
//                intent = new Intent(Login.this, ResetPassword.class);
//                startActivity(intent);
//                break;
            case R.id.txtSignUpNow:
                intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
                break;
        }
    }

    public void postLoginInfo() {

        String url = "https://dev.eventsheets.com/api/auth/login";

        StringRequest request = new StringRequest(Request.Method.POST, url,
                response -> {
                    try {

                        JSONObject o = new JSONObject(response);
                        String message = o.getString("message");
                        editor.putString("message",message);

                        String status = o.getString("status");
                        if (status.equals("success")) {
                            JSONObject data = o.getJSONObject("data");

                            String access_token = data.getString("access_token");
                            String token_type = data.getString("token_type");

                            editor.putString("access_token",access_token);

                            JSONObject user = data.getJSONObject("user");

                            user.getString("s_username");
                            user.getString("s_nickname");
                            user.getString("s_country");
                            user.getString("email");
                            user.getInt("i_gender");
                            user.getString("s_mobile_number");
                            user.getInt("s_avatar");


                        }
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                    Toast.makeText(Login.this, sp.getString("message",""), Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(Login.this, MainActivity.class);
                    startActivity(i);
                },
                error -> Toast.makeText(Login.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show()) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap m = new HashMap();

                m.put("username", edtUsername.getText().toString());
                m.put("password", edtPassword.getText().toString());

                m.put("device_token", "et");
                m.put("device", "sit");

                return m;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
//                return super.getHeaders();

                Map<String, String>  params = new HashMap<String, String>();
                params.put("Authorization", sp.getString("access_token",""));

                params.put("Accept", "application/json");

                params.put("Accept-Language", "ar");

                return params;

            }
        };
        Volley.newRequestQueue(Login.this).add(request);
    }

}
