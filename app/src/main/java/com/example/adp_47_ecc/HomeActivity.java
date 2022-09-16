package com.example.adp_47_ecc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigation);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigation =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId())
                    {
                        case R.id.orders:
                            Toast.makeText(HomeActivity.this, "orders", Toast.LENGTH_SHORT).show();
                            break;

                        case R.id.goout:
                            Toast.makeText(HomeActivity.this, "go out", Toast.LENGTH_SHORT).show();
                            break;

                        case R.id.gold:
                            Toast.makeText(HomeActivity.this, "gold", Toast.LENGTH_SHORT).show();
                            break;

                        case R.id.videos:
                            Toast.makeText(HomeActivity.this, "videos", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    return true;
                }
    };
}