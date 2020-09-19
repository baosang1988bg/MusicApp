package com.example.ssenjoymusicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ssenjoymusicapp.Activity.DanhSachBaiHatActivity;
import com.example.ssenjoymusicapp.Model.Album;
import com.example.ssenjoymusicapp.Model.TheLoai;
import com.example.ssenjoymusicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TheLoaiAdapter extends  RecyclerView.Adapter<TheLoaiAdapter.ViewHolder> {

    Context context;
    ArrayList<TheLoai> mangTheLoai;

    public TheLoaiAdapter(Context context, ArrayList<TheLoai> mangTheLoai) {
        this.context = context;
        this.mangTheLoai = mangTheLoai;
    }

    @NonNull
    @Override
    public TheLoaiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_the_loai, viewGroup, false);

        return new TheLoaiAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TheLoaiAdapter.ViewHolder viewHolder, int i) {
        TheLoai theLoai = mangTheLoai.get(i);
        viewHolder.txtTenTheLoai.setText(theLoai.getTenTheLoai());
        Picasso.with(context).load(theLoai.getHinhTheLoai()).into(viewHolder.imgHinhTheLoai);

    }

    @Override
    public int getItemCount() {
        return mangTheLoai.size();
    }

    //Cung cấp lại các view bên phần danh sáchđể cho tương tác là gắn dữ liệu lại
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenTheLoai;
        ImageView imgHinhTheLoai;

        public ViewHolder(View itemview) {
            super(itemview);
            txtTenTheLoai = itemview.findViewById(R.id.textviewTheLoai);
            imgHinhTheLoai = itemview.findViewById(R.id.imageTheLoai);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("itemtheloai",mangTheLoai.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
