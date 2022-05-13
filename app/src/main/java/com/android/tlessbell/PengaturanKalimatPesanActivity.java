package com.android.tlessbell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PengaturanKalimatPesanActivity extends AppCompatActivity implements KalimatPesanClickListener{

    RelativeLayout tambahKalimatPesan;
    KalimatPesanAdapter kalimatPesanAdapter;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    ArrayList<KalimatPesanModel> pesanModelArrayList;
    RecyclerView rvKalimatPesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan_kalimat_pesan);

        tambahKalimatPesan = findViewById(R.id.tambahKalimatPesanBaru);
        tambahKalimatPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentTambahPesan = new Intent(PengaturanKalimatPesanActivity.this, TambahKalimatPesanActivity.class);
                startActivity(intentTambahPesan);
            }
        });

        rvKalimatPesan = findViewById(R.id.rvKalimatPesan);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvKalimatPesan.setLayoutManager(layoutManager);
        rvKalimatPesan.setItemAnimator(new DefaultItemAnimator());

        TampilData();
    }

    private void TampilData() {
        databaseReference.child("kalimat_pesan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                pesanModelArrayList = new ArrayList<>();
                for (DataSnapshot item: snapshot.getChildren()){
                    KalimatPesanModel kalimatPesan = item.getValue(KalimatPesanModel.class);
                    kalimatPesan.setIdKalimat(item.getKey());
                    pesanModelArrayList.add(kalimatPesan);
                }
                kalimatPesanAdapter = new KalimatPesanAdapter(pesanModelArrayList, PengaturanKalimatPesanActivity.this);
                rvKalimatPesan.setAdapter(kalimatPesanAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClickItem(KalimatPesanModel kalimatPesanModel) {
        Intent intent = new Intent(PengaturanKalimatPesanActivity.this,UbahKalimatPesanActivity.class);
        intent.putExtra("idKalimat",kalimatPesanModel.getIdKalimat());
        intent.putExtra("pesan",kalimatPesanModel.getPesan());
        startActivity(intent);
    }
}