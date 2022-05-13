package com.android.tlessbell;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class KalimatPesanAdapter extends RecyclerView.Adapter<KalimatPesanAdapter.MyViewHolder> {
    private List<KalimatPesanModel> pesanModelList;
    KalimatPesanClickListener kalimatPesanClickListener;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public KalimatPesanAdapter(List<KalimatPesanModel> pesanModelList, KalimatPesanClickListener kalimatPesanClickListener) {
        this.pesanModelList = pesanModelList;
        this.kalimatPesanClickListener = kalimatPesanClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_kalimat_pesan, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final KalimatPesanModel data = pesanModelList.get(position);
        holder.pesanTextView.setText(data.getPesan());
        holder.kalimatPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kalimatPesanClickListener.onClickItem(pesanModelList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return pesanModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView pesanTextView;
        RelativeLayout kalimatPesan;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pesanTextView = itemView.findViewById(R.id.isiKalimatPesan);
            kalimatPesan = itemView.findViewById(R.id.kalimatPesan);
        }
    }
}
