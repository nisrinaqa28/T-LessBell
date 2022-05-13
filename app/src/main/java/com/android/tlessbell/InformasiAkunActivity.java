package com.android.tlessbell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class InformasiAkunActivity extends AppCompatActivity {

    ImageButton backButton;
    TextView ubahNama, ubahIdAlat, ubahEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi_akun);

        backButton = findViewById(R.id.ikonBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment akunFragment = new AkunFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.informasiAkun, akunFragment).commit();
            }
        });

        ubahNama = findViewById(R.id.ubahNama);
        ubahNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentUbahNama = new Intent(InformasiAkunActivity.this, UbahNamaActivity.class);
                startActivity(intentUbahNama);
            }
        });

        ubahIdAlat = findViewById(R.id.ubahIdAlat);
        ubahIdAlat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentUbahIdAlat = new Intent(InformasiAkunActivity.this, UbahIdAlatActivity.class);
                startActivity(intentUbahIdAlat);
            }
        });

        ubahEmail = findViewById(R.id.ubahEmail);
        ubahEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentUbahEmail = new Intent(InformasiAkunActivity.this, UbahEmailActivity.class);
                startActivity(intentUbahEmail);
            }
        });
    }
}