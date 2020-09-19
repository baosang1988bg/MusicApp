package com.example.ssenjoymusicapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.ssenjoymusicapp.Adapter.AllAlbumAdapter;
import com.example.ssenjoymusicapp.Adapter.DanhSachCacPlaylistAdapter;
import com.example.ssenjoymusicapp.Model.Album;
import com.example.ssenjoymusicapp.Model.Playlist;
import com.example.ssenjoymusicapp.R;
import com.example.ssenjoymusicapp.Service.APIService;
import com.example.ssenjoymusicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachTatCaAlbumActivity extends AppCompatActivity {
    Toolbar toolbarAllAlbum;
    RecyclerView recyclerViewAllAlbum;
    AllAlbumAdapter allAlbumAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_tat_ca_album);
        init();
        GetData();
    }

    private void GetData() {
        //Khởi tạo retrofit và class interface dùng để kết nối từ phía server qua cho client.
        DataService dataService = APIService.getService();
        //Nhận dữ liệu vào tương tác dữ liệu bên phía server
        Call<List<Album>> callback = dataService.GetDataTatCaAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> mangAlbum = (ArrayList<Album>) response.body();
                allAlbumAdapter = new AllAlbumAdapter(DanhSachTatCaAlbumActivity.this,mangAlbum);
                recyclerViewAllAlbum.setLayoutManager(new LinearLayoutManager(DanhSachTatCaAlbumActivity.this));
                recyclerViewAllAlbum.setAdapter(allAlbumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void init() {
        toolbarAllAlbum = findViewById(R.id.toolbalAllAlbum);
        recyclerViewAllAlbum = findViewById(R.id.recylerviewAllAlbum);
        setSupportActionBar(toolbarAllAlbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất cả Album");
        toolbarAllAlbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}