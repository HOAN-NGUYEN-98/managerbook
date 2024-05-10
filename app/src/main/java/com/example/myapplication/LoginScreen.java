package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Activity.MainActivity;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.model.LoginBody;
import com.example.myapplication.model.LoginResp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreen extends AppCompatActivity {
    private Button btnLogin, btnSignup, btnForgotPass;

    private EditText edtUserName, edtPassword;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        tvResult = findViewById(R.id.tv_result);
        edtUserName = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_pass);
        btnLogin = findViewById(R.id.btn_login);
        btnSignup = findViewById(R.id.btn_SignUp);
        btnForgotPass = findViewById(R.id.quenmk);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginScreen.this, SignUpScreen.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }


        });

        btnForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenForgotPassword();
            }


        });
    }

    public void login() {
        String name = edtUserName.getText().toString();
        String pass = edtPassword.getText().toString();
        if (name.isEmpty() || pass.isEmpty()) {
            Toast.makeText(LoginScreen.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        LoginBody loginBody = new LoginBody(name, pass);
        ApiService.apiService.login(loginBody)
                .enqueue(new Callback<LoginResp>() {
                    @Override
                    public void onResponse(Call<LoginResp> call, Response<LoginResp> response) {
                        LoginResp res = response.body();
                        if (res != null) {
                            Toast.makeText(LoginScreen.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginScreen.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResp> call, Throwable t) {
                        Toast.makeText(LoginScreen.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    public void OpenForgotPassword() {
        Toast.makeText(LoginScreen.this, "Liên hệ admin để lấy lại mật khẩu !!!", Toast.LENGTH_SHORT).show();
    }


}
