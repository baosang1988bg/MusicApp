package com.example.ssenjoymusicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ssenjoymusicapp.Activity.DanhSachBaiHatActivity;
import com.example.ssenjoymusicapp.Model.ChuDe;
import com.example.ssenjoymusicapp.Model.Playlist;
import com.example.ssenjoymusicapp.Model.TheLoai;
import com.example.ssenjoymusicapp.R;
import com.example.ssenjoymusicapp.Service.APIService;
import com.example.ssenjoymusicapp.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChuDeAdapter extends  RecyclerView.Adapter<ChuDeAdapter.ViewHolder> {

    Context context;
    ArrayList<ChuDe> mangChuDe;
    TheLoaiAdapter theLoaiAdapter;
    public ChuDeAdapter(Context context, ArrayList<ChuDe> mangChuDe) {
        this.context = context;
        this.mangChuDe = mangChuDe;
    }

    @NonNull
    @Override
    public ChuDeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_chu_de,viewGroup,false);

        return new ChuDeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChuDeAdapter.ViewHolder viewHolder, int i) {
        ChuDe chuDe = mangChuDe.get(i);
        viewHolder.txtTenChuDe.setText(chuDe.getTenChuDe());
            //Khởi tạo retrofit và gửi cấu hình retrofit cách tương tác dữ liệu.
            DataService dataService = APIService.getService();
            //Gọi lại function call.
            Call<List<TheLoai>> callback = dataService.GetDataTheLoai(mangChuDe.get(i).getIdChuDe());
            //Sự kiện lắng nghe của dữ liệu trả về.
            callback.enqueue(new Callback<List<TheLoai>>() {
                @Override
                public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                    //Lấy dữ liệu ra.
                    ArrayList<TheLoai> theLoaisArrayList = (ArrayList<TheLoai>) response.body();
                    theLoaiAdapter = new TheLoaiAdapter(context, theLoaisArrayList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                    linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    viewHolder.recyclerViewChuDeCon.setLayoutManager(linearLayoutManager);
                    viewHolder.recyclerViewChuDeCon.setAdapter(theLoaiAdapter);
                }

                @Override
                public void onFailure(Call<List<TheLoai>> call, Throwable t) {

                }
            });

    }

    @Override
    public int getItemCount() {
        return mangChuDe.size();
    }

    //Cung cấp lại các view bên phần danh sáchđể cho tương tác là gắn dữ liệu lại
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenChuDe;
        RecyclerView recyclerViewChuDeCon;
        public ViewHolder(View itemview){
            super(itemview);
            txtTenChuDe = itemview.findViewById(R.id.textviewTenChuDe);
            recyclerViewChuDeCon = itemview.findViewById(R.id.recyclerviewChuDeCon);

        }
    }
}
