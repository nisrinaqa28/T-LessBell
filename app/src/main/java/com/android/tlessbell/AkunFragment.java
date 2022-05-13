package com.android.tlessbell;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class AkunFragment extends Fragment {

    ImageButton buttonEditAkun;
    CardView pengaturanKalimatPesan, keamananAkun, tentangKami, signOut;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_akun, container, false);

        buttonEditAkun = view.findViewById(R.id.iconEdit);
        buttonEditAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentInformasiAkun = new Intent(AkunFragment.this.getActivity(), InformasiAkunActivity.class);
                startActivity(intentInformasiAkun);
            }
        });

        pengaturanKalimatPesan = view.findViewById(R.id.pengaturanKalimatPesan);
        pengaturanKalimatPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPengaturanKalimatPesan = new Intent(AkunFragment.this.getActivity(), PengaturanKalimatPesanActivity.class);
                startActivity(intentPengaturanKalimatPesan);
            }
        });

        keamananAkun = view.findViewById(R.id.keamananAkun);
        keamananAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentKeamananAkun = new Intent(AkunFragment.this.getActivity(), UbahPasswordActivity.class);
                startActivity(intentKeamananAkun);
            }
        });

        return view;
    }
}