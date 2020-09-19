package com.example.ssenjoymusicapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ssenjoymusicapp.Activity.PlayNhacActivity;
import com.example.ssenjoymusicapp.Model.Baihat;
import com.example.ssenjoymusicapp.R;

import java.util.ArrayList;

public class PlayNhacAdapter  extends  RecyclerView.Adapter<PlayNhacAdapter.ViewHolder>{
    Context context;

    public PlayNhacAdapter(Context context, ArrayList<Baihat> mangBaihats) {
        this.context = context;
        this.mangBaihats = mangBaihats;
    }

    ArrayList<Baihat> mangBaihats;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_play_bai_hat,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Baihat baihat = mangBaihats.get(i);
        viewHolder.txtCaSi.setText(baihat.getCaSi());
        viewHolder.txtTenBaiHat.setText(baihat.getTenBaiHat());
        viewHolder.txtindex.setText(i + 1 + "");

    }

    @Override
    public int getItemCount() {
        return mangBaihats.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtindex, txtTenBaiHat,txtCaSi;
        public ViewHolder(View itemview){
            super(itemview);
            txtCaSi = itemview.findViewById(R.id.textviewplaynhactencasi);
            txtindex = itemview.findViewById(R.id.textviewplaynhacindex);
            txtTenBaiHat = itemview.findViewById(R.id.textviewplaynhactenbaihat);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc", mangBaihats.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
