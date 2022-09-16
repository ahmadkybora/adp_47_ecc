package com.example.adp_47_ecc.EmailLoginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.adp_47_ecc.MainActivity;
import com.example.adp_47_ecc.R;

public class EmailLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_login);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
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