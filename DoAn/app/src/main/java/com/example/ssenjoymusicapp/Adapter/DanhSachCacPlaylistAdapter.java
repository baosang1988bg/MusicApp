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
import com.example.ssenjoymusicapp.Model.Baihat;
import com.example.ssenjoymusicapp.Model.Playlist;
import com.example.ssenjoymusicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachCacPlaylistAdapter extends  RecyclerView.Adapter<DanhSachCacPlaylistAdapter.ViewHolder> {

    Context context;
    ArrayList<Playlist> mangPlaylists;

    public DanhSachCacPlaylistAdapter(Context context, ArrayList<Playlist> mangPlaylists) {
        this.context = context;
        this.mangPlaylists = mangPlaylists;
    }

    @NonNull
    @Override
    public DanhSachCacPlaylistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_cac_playlist,viewGroup,false);

        return new DanhSachCacPlaylistAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhSachCacPlaylistAdapter.ViewHolder viewHolder, int i) {
        Playlist playlist = mangPlaylists.get(i);
        viewHolder.txtTenPlaylist.setText(playlist.getTen());
        Picasso.with(context).load(playlist.getHinhNen()).into(viewHolder.imgHinhNen);

    }

    @Override
    public int getItemCount() {
        return mangPlaylists.size();
    }

    //Cung cấp lại các view bên phần danh sáchđể cho tương tác là gắn dữ liệu lại
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenPlaylist;
        ImageView imgHinhNen;
        public ViewHolder(View itemview){
            super(itemview);
            txtTenPlaylist = itemview.findViewById(R.id.textviewDanhSachCacPlaylist);
            imgHinhNen = itemview.findViewById(R.id.imageDanhSachCacPlaylist);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("itemplaylist",mangPlaylists.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
