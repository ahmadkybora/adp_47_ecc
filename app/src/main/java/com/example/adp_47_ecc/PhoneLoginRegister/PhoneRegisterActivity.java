package com.example.adp_47_ecc.PhoneLoginRegister;

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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.adp_47_ecc.EmailLoginRegister.EmailRegisterActivity;
import com.example.adp_47_ecc.MainActivity;
import com.example.adp_47_ecc.R;
import com.example.adp_47_ecc.RetrofitApi.ApiClient;
import com.example.adp_47_ecc.RetrofitApi.ApiInterface;
import com.example.adp_47_ecc.RetrofitApi.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhoneRegisterActivity extends AppCompatActivity {

    private EditText phone, otp;
    private Button btnReg, btnOtp;
    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_register);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        init();
    }

    private void init() {
        phone = (EditText) findViewById(R.id.phone);
        otp = (EditText) findViewById(R.id.otp);
        btnReg = (Button) findViewById(R.id.button2);
        btnOtp = (Button) findViewById(R.id.button3);

        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("Registration.....");
        dialog.setMessage("Please with ...");
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });

        btnOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(otp.getText().toString().equals(""))
                {
                    Toast.makeText(PhoneRegisterActivity.this, "please enter your 6 digit opt", Toast.LENGTH_SHORT).show();
                }
                else
                {

                }
            }
        });
    }

    private void Register() {
        String user_phone = phone.getText().toString().trim();
        if(TextUtils.isEmpty(user_phone))
        {
            phone.setError("Phone is required");
        }
        else
        {
            ProgressDialog dialog = new ProgressDialog(this);
            dialog.setTitle("Register...");
            dialog.setMessage("Please wait white we are checking your credentials!");
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);

            Call<Users> call = apiInterface.performPhoneRegistration(user_phone);
            call.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    if(response.body().getResponse().equals("ok"))
                    {
                        Toast.makeText(PhoneRegisterActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                    else if(response.body().getResponse().equals("failed"))
                    {
                        Toast.makeText(PhoneRegisterActivity.this, "Something went wrong please try again", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                    else if(response.body().getResponse().equals("already"))
                    {
                        Toast.makeText(PhoneRegisterActivity.this, "This email is already taken", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {
                    Toast.makeText(PhoneRegisterActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                }
            });
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    public void goToLogin(View view) {
        Intent intent = new Intent(PhoneRegisterActivity.this, PhoneLoginActivity.class);
        startActivity(intent);
        Animatoo.animateSlideUp(this);
        finish();
    }

    public void backToMainPage(View view) {
        Intent intent = new Intent(PhoneRegisterActivity.this, MainActivity.class);
        startActivity(intent);
        Animatoo.animateSwipeRight(this);
        finish();
    }
}