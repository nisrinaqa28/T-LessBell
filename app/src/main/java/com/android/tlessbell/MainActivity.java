package com.android.tlessbell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    BerandaFragment berandaFragment = new BerandaFragment();
    RiwayatFragment riwayatFragment = new RiwayatFragment();
    AkunFragment akunFragment = new AkunFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, berandaFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.beranda:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, berandaFragment).commit();
                        return true;
                    case R.id.riwayat:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, riwayatFragment).commit();
                        return true;
                    case R.id.akun:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, akunFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }
}