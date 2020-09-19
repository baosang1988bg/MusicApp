package com.example.ssenjoymusicapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.ssenjoymusicapp.Adapter.DanhSachBaihatAdapter;
import com.example.ssenjoymusicapp.Adapter.DanhSachCacPlaylistAdapter;
import com.example.ssenjoymusicapp.Model.Baihat;
import com.example.ssenjoymusicapp.Model.Playlist;
import com.example.ssenjoymusicapp.R;
import com.example.ssenjoymusicapp.Service.APIService;
import com.example.ssenjoymusicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachPLaylistActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerViewDanhSachCacPlaylist;
    DanhSachCacPlaylistAdapter danhSachCacPlaylistAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_p_laylist);
        anhXa();
        init();
        GetData();
    }

    private void GetData() {
        //Khởi tạo retrofit và class interface dùng để kết nối từ phía server qua cho client.
        DataService dataService = APIService.getService();
        //Nhận dữ liệu vào tương tác dữ liệu bên phía server
        Call<List<Playlist>> callback = dataService.GetDataDanhSachPLaylist();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> mangPlaylist = (ArrayList<Playlist>) response.body();
                danhSachCacPlaylistAdapter = new DanhSachCacPlaylistAdapter(DanhSachPLaylistActivity.this,mangPlaylist);
                recyclerViewDanhSachCacPlaylist.setLayoutManager(new LinearLayoutManager(DanhSachPLaylistActivity.this));
                recyclerViewDanhSachCacPlaylist.setAdapter(danhSachCacPlaylistAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Play lists");
        toolbar.setTitleTextColor(getResources().getColor(R.color.mautim));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void anhXa() {
        toolbar = findViewById(R.id.toolbarDanhSachCacPlaylist);
        recyclerViewDanhSachCacPlaylist = findViewById(R.id.recyclerviewDanhSachCacPlaylist);
    }
}