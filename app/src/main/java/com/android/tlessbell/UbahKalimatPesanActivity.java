package com.android.tlessbell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UbahKalimatPesanActivity extends AppCompatActivity{

    EditText ubahKalimatPesan;
    ImageButton buttonSimpan;
    ImageView buttonHapus;
    private String id;
    private String pesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_kalimat_pesan);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        id=getIntent().getStringExtra("idKalimat");
        pesan=getIntent().getStringExtra("pesan");

        ubahKalimatPesan = findViewById(R.id.inputKalimatPesan);
        buttonSimpan = findViewById(R.id.buttonSimpan);

        ubahKalimatPesan.setText(pesan);
        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getPesan = ubahKalimatPesan.getText().toString();

                if (getPesan.isEmpty()){
                    ubahKalimatPesan.setError("Kalimat pesan harus diisi");
                }else if(getPesan.length()>80){
                    ubahKalimatPesan.setError("Pesan dibatasi hingga 80 karakter");
                }else{
                    databaseReference.child("kalimat_pesan").child(id).setValue(new KalimatPesanModel(getPesan)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(UbahKalimatPesanActivity.this,"Kalimat pesan berhasil diubah", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(UbahKalimatPesanActivity.this,PengaturanKalimatPesanActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UbahKalimatPesanActivity.this,"Kalimat pesan gagal diubah", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        buttonHapus = findViewById(R.id.ikonHapus);
        buttonHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child("kalimat_pesan").child(id).removeValue();
                Toast.makeText(UbahKalimatPesanActivity.this,"Kalimat pesan berhasil dihapus", Toast.LENGTH_LONG).show();
                startActivity(new Intent(UbahKalimatPesanActivity.this,PengaturanKalimatPesanActivity.class));
                finish();
                /*AlertDialog.Builder builder = new AlertDialog.Builder(UbahKalimatPesanActivity.this,R.style.AlertDialogTheme);
                View itemView = LayoutInflater.from(UbahKalimatPesanActivity.this).inflate(R.layout.alert_dialog_hapus_kalimat_pesan
                        ,(ConstraintLayout)findViewById(R.id.layoutDialogHapusKalimatPesan));
                builder.setView(view);

                AlertDialog alertDialog = builder.create();

                ImageButton buttonYa = view.findViewById(R.id.buttonYa);
                buttonYa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        databaseReference.child("kalimat_pesan").child(id).removeValue();
                        Toast.makeText(UbahKalimatPesanActivity.this,"Kalimat pesan berhasil dihapus", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(UbahKalimatPesanActivity.this,PengaturanKalimatPesanActivity.class));
                        finish();
                    }
                });

                ImageButton buttonTidak = view.findViewById(R.id.buttonTidak);
                buttonTidak.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();*/
            }
        });
    }
}