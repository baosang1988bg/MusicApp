package com.example.ssenjoymusicapp.Fragment;

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

import com.example.ssenjoymusicapp.Adapter.AlbumAdapter;
import com.example.ssenjoymusicapp.Adapter.BaiHatDuocYeuThichAdapter;
import com.example.ssenjoymusicapp.Model.Album;
import com.example.ssenjoymusicapp.Model.Baihat;
import com.example.ssenjoymusicapp.R;
import com.example.ssenjoymusicapp.Service.APIService;
import com.example.ssenjoymusicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_BaiHatDuocYeuThich extends Fragment {
    View view;
    RecyclerView recyclerViewBaiHatDuocYeuThich;
    BaiHatDuocYeuThichAdapter baiHatDuocYeuThichAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bai_hat_duoc_yeu_thich, container, false);
        recyclerViewBaiHatDuocYeuThich = view.findViewById(R.id.recylerviewBaiHatDuocYeuThich);
        GetData();
        return view;
    }

    private void GetData() {
        //Khởi tạo retrofit và gửi cấu hình retrofit cách tương tác dữ liệu.
        DataService dataService = APIService.getService();
        //Gọi lại function call.
        Call<List<Baihat>> callback = dataService.GetDataBaiHatDuocYeuThich();
        //Sự kiện lắng nghe của dữ liệu trả về.
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                //Lấy dữ liệu ra.
                ArrayList<Baihat> baihatArrayList = (ArrayList<Baihat>) response.body();
                baiHatDuocYeuThichAdapter = new BaiHatDuocYeuThichAdapter(getActivity(), baihatArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewBaiHatDuocYeuThich.setLayoutManager(linearLayoutManager);
                recyclerViewBaiHatDuocYeuThich.setAdapter(baiHatDuocYeuThichAdapter);
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }


}
