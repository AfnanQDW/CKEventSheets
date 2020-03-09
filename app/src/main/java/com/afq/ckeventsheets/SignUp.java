package com.afq.ckeventsheets;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmail;
    private EditText edtMobile;
    private EditText edtNickName;
    private EditText edtUsername;
    private EditText edtZipCode;
    private EditText edtPassword;
    private EditText edtConfirmPassword;
    private Button btnSignUp;
    private TextView txtLoginNow;

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sp = getSharedPreferences("sp",MODE_PRIVATE);
        editor = sp.edit();

        edtEmail = findViewById(R.id.edtEmail);
        edtMobile = findViewById(R.id.edtMobile);
        edtNickName = findViewById(R.id.edtNickName);
        edtUsername = findViewById(R.id.edtUsername);
        edtZipCode = findViewById(R.id.edtZipCode);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);
        txtLoginNow = findViewById(R.id.txtLoginNow);
        txtLoginNow.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignUp:
                postRegisterInfo();
                break;
            case R.id.txtLoginNow:
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
                break;
        }
    }


    public void postRegisterInfo() {

        String url = "https://dev.eventsheets.com/api/auth/register";

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
                    Toast.makeText(SignUp.this, sp.getString("message",""), Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(SignUp.this, MainActivity.class);
                    startActivity(i);
                },
                error -> Toast.makeText(SignUp.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show()) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap m = new HashMap();

                m.put("email", edtEmail.getText().toString());
                m.put("nickname", edtNickName.getText().toString());
                m.put("username", edtUsername.getText().toString());
                m.put("password", edtPassword.getText().toString());
                m.put("password_confirmation", edtConfirmPassword.getText().toString());
                m.put("phone_number", edtMobile.getText().toString());


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
        Volley.newRequestQueue(SignUp.this).add(request);
    }
}
