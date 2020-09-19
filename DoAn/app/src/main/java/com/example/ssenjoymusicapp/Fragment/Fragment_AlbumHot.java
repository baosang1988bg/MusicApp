package com.example.ssenjoymusicapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ssenjoymusicapp.Activity.DanhSachTatCaAlbumActivity;
import com.example.ssenjoymusicapp.Adapter.AlbumAdapter;
import com.example.ssenjoymusicapp.Model.Album;
import com.example.ssenjoymusicapp.R;
import com.example.ssenjoymusicapp.Service.APIService;
import com.example.ssenjoymusicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_AlbumHot extends Fragment {
    View view;
    RecyclerView recyclerViewAlbumHot;
    TextView txtXemThemalBum;
    AlbumAdapter albumAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album_hot, container, false);
        recyclerViewAlbumHot = view.findViewById(R.id.recylerviewAlbumHot);
        txtXemThemalBum = view.findViewById(R.id.textviewXemThemAlbum);
        GetData();
        txtXemThemalBum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhSachTatCaAlbumActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void GetData() {
        //Khởi tạo retrofit và gửi cấu hình retrofit cách tương tác dữ liệu.
        DataService dataService = APIService.getService();
        //Gọi lại function call.
        Call<List<Album>> callback = dataService.GetDataALbumHot();
        //Sự kiện lắng nghe của dữ liệu trả về.
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                //Lấy dữ liệu ra.
                ArrayList<Album> albumArrayList = (ArrayList<Album>) response.body();
                albumAdapter = new AlbumAdapter(getActivity(), albumArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewAlbumHot.setLayoutManager(linearLayoutManager);
                recyclerViewAlbumHot.setAdapter(albumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }


}

