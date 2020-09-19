package com.example.ssenjoymusicapp.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.ssenjoymusicapp.Adapter.TimKiemAdapter;
import com.example.ssenjoymusicapp.Model.Baihat;
import com.example.ssenjoymusicapp.R;
import com.example.ssenjoymusicapp.Service.APIService;
import com.example.ssenjoymusicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_TimKiem extends Fragment {
    View view;
    Toolbar toolbarTimKiem;
    RecyclerView recyclerViewTimKiem;
    TextView txtKhongCoDuLieu;
    TimKiemAdapter timKiemAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tim_kiem, container, false);
        toolbarTimKiem = view.findViewById(R.id.toolbarTimKiemBaiHat);
        recyclerViewTimKiem = view.findViewById(R.id.recyclerviewTimKiemBaiHat);
        txtKhongCoDuLieu = view.findViewById(R.id.textviewKhongCoDuLieu);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarTimKiem);
        toolbarTimKiem.setTitle("");
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.timkiem_view,menu);
        MenuItem menuItem = menu.findItem(R.id.menu_timkiem);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                TimKiemTuKhoaBaiHat(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                TimKiemTuKhoaBaiHat(s);
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
    private void TimKiemTuKhoaBaiHat(String query){
        DataService dataService = APIService.getService();
        Call<List<Baihat>> callback = dataService.GetTimKiemBaiHat(query);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                ArrayList<Baihat> mangBaihat = (ArrayList<Baihat>) response.body();
                Log.d("BBB", mangBaihat.get(0).getTenBaiHat());
                if(mangBaihat.size() > 0)
                {
                    timKiemAdapter = new TimKiemAdapter(getActivity(), mangBaihat);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerViewTimKiem.setLayoutManager(linearLayoutManager);
                    recyclerViewTimKiem.setAdapter(timKiemAdapter);
                    txtKhongCoDuLieu.setVisibility(View.GONE);
                    recyclerViewTimKiem.setVisibility(View.VISIBLE);
                }
                else
                {
                    recyclerViewTimKiem.setVisibility(View.GONE);
                    txtKhongCoDuLieu.setVisibility(View.VISIBLE);
                }
             }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }
}
