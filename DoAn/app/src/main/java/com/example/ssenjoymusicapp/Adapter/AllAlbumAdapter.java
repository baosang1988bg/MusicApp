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
import com.example.ssenjoymusicapp.Model.Playlist;
import com.example.ssenjoymusicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllAlbumAdapter extends  RecyclerView.Adapter<AllAlbumAdapter.ViewHolder> {

    Context context;
    ArrayList<Album> mangAlbum;

    public AllAlbumAdapter(Context context, ArrayList<Album> mangAlbum) {
        this.context = context;
        this.mangAlbum = mangAlbum;
    }

    @NonNull
    @Override
    public AllAlbumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_all_album,viewGroup,false);

        return new AllAlbumAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllAlbumAdapter.ViewHolder viewHolder, int i) {
        Album album = mangAlbum.get(i);
        viewHolder.txtTenAlbum.setText(album.getTenAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(viewHolder.imgHinhAlbum);

    }

    @Override
    public int getItemCount() {
        return mangAlbum.size();
    }

    //Cung cấp lại các view bên phần danh sáchđể cho tương tác là gắn dữ liệu lại
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenAlbum;
        ImageView imgHinhAlbum;
        public ViewHolder(View itemview){
            super(itemview);
            txtTenAlbum = itemview.findViewById(R.id.textviewAllAlbum);
            imgHinhAlbum = itemview.findViewById(R.id.imageAllAlbum);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("itemalbum",mangAlbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
