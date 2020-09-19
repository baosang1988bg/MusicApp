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
import com.example.ssenjoymusicapp.Adapter.ChuDeAdapter;
import com.example.ssenjoymusicapp.Model.Album;
import com.example.ssenjoymusicapp.Model.ChuDe;
import com.example.ssenjoymusicapp.R;
import com.example.ssenjoymusicapp.Service.APIService;
import com.example.ssenjoymusicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Chu_De extends Fragment {
    View view;
    RecyclerView recyclerViewChuDe;
    ChuDeAdapter chuDeAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chu_de, container, false);
        recyclerViewChuDe = view.findViewById(R.id.recyclerviewChuDe);
        GetData();
        return view;
    }

    private void GetData() {
        //Khởi tạo retrofit và gửi cấu hình retrofit cách tương tác dữ liệu.
        DataService dataService = APIService.getService();
        //Gọi lại function call.
        Call<List<ChuDe>> callback = dataService.GetDataChuDe();
        //Sự kiện lắng nghe của dữ liệu trả về.
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                //Lấy dữ liệu ra.
                ArrayList<ChuDe> chuDesArrayList = (ArrayList<ChuDe>) response.body();
                chuDeAdapter = new ChuDeAdapter(getActivity(), chuDesArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewChuDe.setLayoutManager(linearLayoutManager);
                recyclerViewChuDe.setAdapter(chuDeAdapter);
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }
}