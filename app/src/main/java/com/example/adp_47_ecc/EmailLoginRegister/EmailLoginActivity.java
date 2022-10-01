package com.example.adp_47_ecc.EmailLoginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.adp_47_ecc.MainActivity;
import com.example.adp_47_ecc.PhoneLoginRegister.PhoneRegisterActivity;
import com.example.adp_47_ecc.R;
import com.example.adp_47_ecc.RetrofitApi.ApiClient;
import com.example.adp_47_ecc.RetrofitApi.ApiInterface;
import com.example.adp_47_ecc.RetrofitApi.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmailLoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button btnLogin;
    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_login);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        init();
    }

    private void init() {
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.button2);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
    }

    private void Login() {
        String user_email = email.getText().toString().trim();
        String user_password = password.getText().toString().trim();

        if(TextUtils.isEmpty(user_email))
        {
            email.setError("Email is required!");
        }
        else if(TextUtils.isEmpty(user_password))
        {
            password.setError("Password is required!");
        }
        else
        {
            ProgressDialog dialog = new ProgressDialog(this);
            dialog.setTitle("Logging...");
            dialog.setMessage("Please wait white we are checking your credentials!");
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);

            Call<Users> call = apiInterface.performEmailLogin(user_email, user_password);
            call.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    if(response.body().getResponse().equals("ok"))
                    {
                        String user_id = response.body().getUserId();
                        Toast.makeText(EmailLoginActivity.this, user_id, Toast.LENGTH_SHORT).show();

                        Toast.makeText(EmailLoginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                    else if(response.body().getResponse().equals("no_account"))
                    {
                        Toast.makeText(EmailLoginActivity.this, "No Account Found", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                    else if(response.body().getResponse().equals("already"))
                    {
                        Toast.makeText(EmailLoginActivity.this, "This email is already taken", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {
                    Toast.makeText(EmailLoginActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                }
            });
            Toast.makeText(EmailLoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
        }
    }
    public void goToRegister(View view) {
        Intent intent = new Intent(EmailLoginActivity.this, EmailRegisterActivity.class);
        startActivity(intent);
        Animatoo.animateSwipeLeft(this);
        finish();
    }

    public void backToMainPage(View view) {
        Intent intent = new Intent(EmailLoginActivity.this, MainActivity.class);
        startActivity(intent);
        Animatoo.animateSwipeRight(this);
        finish();
    }
}