package com.android.tlessbell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahKalimatPesanActivity extends AppCompatActivity{

    EditText tambahPesanBaru;
    ImageButton buttonSimpan;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kalimat_pesan);

        tambahPesanBaru = findViewById(R.id.inputKalimatPesan);
        buttonSimpan = findViewById(R.id.buttonSimpan);
        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getPesan = tambahPesanBaru.getText().toString();

                if (getPesan.isEmpty()){
                    tambahPesanBaru.setError("Kalimat pesan harus diisi");
                }else if(getPesan.length()>80){
                    tambahPesanBaru.setError("Pesan dibatasi hingga 80 karakter");
                }else{
                    databaseReference.child("kalimat_pesan").push().setValue(new KalimatPesanModel(getPesan)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(TambahKalimatPesanActivity.this,"Kalimat pesan berhasil disimpan", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(TambahKalimatPesanActivity.this,PengaturanKalimatPesanActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(TambahKalimatPesanActivity.this,"Kalimat pesan gagal disimpan", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}