package com.example.ssenjoymusicapp.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.StrictMode;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ssenjoymusicapp.Adapter.DanhSachBaihatAdapter;
import com.example.ssenjoymusicapp.Model.Album;
import com.example.ssenjoymusicapp.Model.Baihat;
import com.example.ssenjoymusicapp.Model.Playlist;
import com.example.ssenjoymusicapp.Model.QuangCao;
import com.example.ssenjoymusicapp.Model.TheLoai;
import com.example.ssenjoymusicapp.R;
import com.example.ssenjoymusicapp.Service.APIService;
import com.example.ssenjoymusicapp.Service.DataService;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatActivity extends AppCompatActivity {
    CollapsingToolbarLayout collapsingToolbarLayout;
    CoordinatorLayout coordinatorLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewDachSachBaiHat;
    FloatingActionButton floatingActionButton;
    ImageView imgDanhSachBaiHat;
    QuangCao quangcao;
    Playlist playlistToday;
    Album album;
    TheLoai theLoai;
    ArrayList<Baihat> mangBaiHat;
    DanhSachBaihatAdapter danhSachBaihatAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DataIntent();
        anhXa();
        init();
        if(quangcao != null && !quangcao.getIdQuangCao().equals(""))
        {
            setValueInView(quangcao.getTenBaiHat(),quangcao.getHinhBaiHat());
            getDataQuangCao(quangcao.getIdQuangCao());
        }
        if(playlistToday != null && !playlistToday.getIdPlaylist().equals(""))
        {
            setValueInView(playlistToday.getTen(),playlistToday.getHinhNen());
            getDataPlaylistToday(playlistToday.getIdPlaylist());
        }
        if(album != null && !album.getIdAlbum().equals(""))
        {
            setValueInView(album.getTenAlbum(),album.getHinhAlbum());
            getDataAlbum(album.getIdAlbum());

        }
        if(theLoai != null && !theLoai.getIdTheLoai().equals(""))
        {
            setValueInView(theLoai.getTenTheLoai(),theLoai.getHinhTheLoai());
            getDataTheLoai(theLoai.getIdTheLoai());

        }
    }

    private void getDataTheLoai(String idTheLoai) {
        //Khởi tạo retrofit và class interface dùng để kết nối từ phía server qua cho client.
        DataService dataService = APIService.getService();
        //Nhận dữ liệu vào tương tác dữ liệu bên phía server
        Call<List<Baihat>> callback = dataService.GetDanhSachBaihatTheoTheLoai(idTheLoai);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangBaiHat = (ArrayList<Baihat>) response.body();
                danhSachBaihatAdapter = new DanhSachBaihatAdapter(DanhSachBaiHatActivity.this,mangBaiHat);
                recyclerViewDachSachBaiHat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerViewDachSachBaiHat.setAdapter(danhSachBaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void getDataAlbum(String idAlbum) {
        //Khởi tạo retrofit và class interface dùng để kết nối từ phía server qua cho client.
        DataService dataService = APIService.getService();
        //Nhận dữ liệu vào tương tác dữ liệu bên phía server
        Call<List<Baihat>> callback = dataService.GetDanhSachBaihatTheoAlbum(idAlbum);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangBaiHat = (ArrayList<Baihat>) response.body();
                danhSachBaihatAdapter = new DanhSachBaihatAdapter(DanhSachBaiHatActivity.this,mangBaiHat);
                recyclerViewDachSachBaiHat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerViewDachSachBaiHat.setAdapter(danhSachBaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void getDataPlaylistToday(String idPlaylist) {
        //Khởi tạo retrofit và class interface dùng để kết nối từ phía server qua cho client.
        DataService dataService = APIService.getService();
        //Nhận dữ liệu vào tương tác dữ liệu bên phía server
        Call<List<Baihat>> callback = dataService.GetDanhSachBaihatTheoPLaylist(idPlaylist);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangBaiHat = (ArrayList<Baihat>) response.body();
                danhSachBaihatAdapter = new DanhSachBaihatAdapter(DanhSachBaiHatActivity.this,mangBaiHat);
                recyclerViewDachSachBaiHat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerViewDachSachBaiHat.setAdapter(danhSachBaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void getDataQuangCao(String idquangcao) {
        //Khởi tạo retrofit và class interface dùng để kết nối từ phía server qua cho client.
        DataService dataService = APIService.getService();
        //Nhận dữ liệu vào tương tác dữ liệu bên phía server
        Call<List<Baihat>> callback = dataService.GetDanhSachBaihatTheoQuangCao(idquangcao);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangBaiHat = (ArrayList<Baihat>) response.body();
                danhSachBaihatAdapter = new DanhSachBaihatAdapter(DanhSachBaiHatActivity.this,mangBaiHat);
                recyclerViewDachSachBaiHat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerViewDachSachBaiHat.setAdapter(danhSachBaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void setValueInView(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url = new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                collapsingToolbarLayout.setBackground(bitmapDrawable);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(hinh).into(imgDanhSachBaiHat);
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButton.setEnabled(false);
    }

    private void anhXa() {
        imgDanhSachBaiHat = findViewById(R.id.imageviewdanhsachcakhuc);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        coordinatorLayout= findViewById(R.id.coordinatorlayout);
        toolbar = findViewById(R.id.toolbardanhsach);
        recyclerViewDachSachBaiHat = findViewById(R.id.recylerviewdanhsachbaihat);
        floatingActionButton = findViewById(R.id.floatingactionbutton);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("QuangCao")){
                quangcao = (QuangCao) intent.getSerializableExtra("QuangCao");
            }
            if(intent.hasExtra("itemplaylist")){
                playlistToday = (Playlist) intent.getSerializableExtra("itemplaylist");
            }
            if(intent.hasExtra("itemalbum")){
                album = (Album) intent.getSerializableExtra("itemalbum");
            }
            if(intent.hasExtra("itemtheloai")){
                theLoai = (TheLoai) intent.getSerializableExtra("itemtheloai");
            }
        }
    }
    private void eventClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (DanhSachBaiHatActivity.this,PlayNhacActivity.class);
                intent.putExtra("tatcacakhuc",mangBaiHat);
                startActivity(intent);
             }
        });
    }
}