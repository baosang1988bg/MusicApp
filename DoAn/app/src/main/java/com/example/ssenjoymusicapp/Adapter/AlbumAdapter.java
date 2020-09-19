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
import com.example.ssenjoymusicapp.Activity.PlayNhacActivity;
import com.example.ssenjoymusicapp.Model.Album;
import com.example.ssenjoymusicapp.Model.Baihat;
import com.example.ssenjoymusicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends  RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    Context context;
    ArrayList<Album> mangAlbums;

    public AlbumAdapter(Context context, ArrayList<Album> mangAlbums) {
        this.context = context;
        this.mangAlbums = mangAlbums;
    }

    @NonNull
    @Override
    public AlbumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_album_hot,viewGroup,false);

        return new AlbumAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.ViewHolder viewHolder, int i) {
        Album album = mangAlbums.get(i);
        viewHolder.txtCaSiAlbum.setText(album.getTenCaSiAlbum());
        viewHolder.txtTenAlbum.setText(album.getTenAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(viewHolder.imgHinhAlbum);

    }

    @Override
    public int getItemCount() {
        return mangAlbums.size();
    }

    //Cung cấp lại các view bên phần danh sáchđể cho tương tác là gắn dữ liệu lại
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenAlbum,txtCaSiAlbum;
        ImageView imgHinhAlbum;
        public ViewHolder(View itemview){
            super(itemview);
            txtCaSiAlbum = itemview.findViewById(R.id.textviewCaSiAlbum);
            txtTenAlbum = itemview.findViewById(R.id.textviewTenAlbum);
            imgHinhAlbum = itemview.findViewById(R.id.imageviewAlbum);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("itemalbum",mangAlbums.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
