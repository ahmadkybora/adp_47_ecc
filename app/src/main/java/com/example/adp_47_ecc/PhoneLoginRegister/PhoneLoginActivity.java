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
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.adp_47_ecc.EmailLoginRegister.EmailRegisterActivity;
import com.example.adp_47_ecc.MainActivity;
import com.example.adp_47_ecc.R;
public class PhoneLoginActivity extends AppCompatActivity {

    private EditText phone;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init();
    }

    private void init() {
        phone = (EditText) findViewById(R.id.phone);
        btnLogin = (Button) findViewById(R.id.button2);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
    }

    private void Login() {
        String user_phone = phone.getText().toString().trim();
        if(TextUtils.isEmpty(user_phone))
        {
            phone.setError("Phone is required");
        }
        else
        {
            ProgressDialog dialog = new ProgressDialog(this);
            dialog.setTitle("Logging...");
            dialog.setMessage("Please wait white we are checking your credentials!");
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    public void goToRegister(View view) {
        Intent intent = new Intent(PhoneLoginActivity.this, PhoneRegisterActivity.class);
        startActivity(intent);
        Animatoo.animateSlideUp(this);
        finish();
    }

    public void backToMainPage(View view) {
        Intent intent = new Intent(PhoneLoginActivity.this, MainActivity.class);
        startActivity(intent);
        Animatoo.animateSwipeRight(this);
        finish();
    }
}