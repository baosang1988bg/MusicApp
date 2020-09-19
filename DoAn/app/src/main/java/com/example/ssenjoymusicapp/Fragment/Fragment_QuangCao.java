package com.example.ssenjoymusicapp.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ssenjoymusicapp.Adapter.QuangCaoAdapter;
import com.example.ssenjoymusicapp.Model.QuangCao;
import com.example.ssenjoymusicapp.R;
import com.example.ssenjoymusicapp.Service.APIService;
import com.example.ssenjoymusicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_QuangCao extends Fragment {
    View view;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    QuangCaoAdapter quangCaoAdapter;
    Runnable runnable;
    Handler handler;
    int current;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_quang_cao, container, false);
        anhXa();
        getData();

        return view;

    }

    private void anhXa() {
        viewPager = view.findViewById(R.id.viewPager);
        circleIndicator = view.findViewById(R.id.indicatordefualt);

    }

    private void getData() {
        //Khởi tạo retrofit và cách tương tác dữ liệu.
        DataService dataService = APIService.getService();
        //Gọi lại function call.
        Call<List<QuangCao>> callback = dataService.GetDataQuangcao();
        //Sự kiện lắng nghe của dữ liệu trả về.
        callback.enqueue(new Callback<List<QuangCao>>() {
            @Override
            public void onResponse(Call<List<QuangCao>> call, Response<List<QuangCao>> response) {
                //Lấy dữ liệu ra.
                ArrayList<QuangCao> quangCaos = (ArrayList<QuangCao>) response.body();
                quangCaoAdapter = new QuangCaoAdapter(getActivity(),quangCaos);
                viewPager.setAdapter(quangCaoAdapter);
                circleIndicator.setViewPager(viewPager);
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        current = viewPager.getCurrentItem();
                        current++;
                        if(current >= viewPager.getAdapter().getCount())
                            current = 0;
                        viewPager.setCurrentItem(current,true);
                        handler.postDelayed(runnable, 4500);
                    }
                };
                handler.postDelayed(runnable,4500);
            }

            @Override
            public void onFailure(Call<List<QuangCao>> call, Throwable t) {

            }
        });
    }
}
