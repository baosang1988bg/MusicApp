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
import android.widget.Toast;

import com.example.ssenjoymusicapp.Activity.PlayNhacActivity;
import com.example.ssenjoymusicapp.Model.Baihat;
import com.example.ssenjoymusicapp.R;
import com.example.ssenjoymusicapp.Service.APIService;
import com.example.ssenjoymusicapp.Service.DataService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaihatAdapter extends  RecyclerView.Adapter<DanhSachBaihatAdapter.ViewHolder> {

    Context context;
    ArrayList<Baihat> mangBaihats;

    public DanhSachBaihatAdapter(Context context, ArrayList<Baihat> mangBaihats) {
        this.context = context;
        this.mangBaihats = mangBaihats;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_bai_hat,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Baihat baihat = mangBaihats.get(i);
        viewHolder.txtCaSi.setText(baihat.getCaSi());
        viewHolder.txtTenBaiHat.setText(baihat.getTenBaiHat());
        viewHolder.txtindex.setText(i + 1 + "");

    }

    @Override
    public int getItemCount() {
        return mangBaihats.size();
    }

    //Cung cấp lại các view bên phần danh sáchđể cho tương tác là gắn dữ liệu lại
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtindex, txtTenBaiHat,txtCaSi;
        ImageView imgLuotThich;
        public ViewHolder(View itemview){
            super(itemview);
            txtCaSi = itemview.findViewById(R.id.textviewtencasi);
            txtindex = itemview.findViewById(R.id.textviewdanhsachindex);
            txtTenBaiHat = itemview.findViewById(R.id.textviewtencakhuc);
            imgLuotThich = itemview.findViewById(R.id.imageviewluotthichdanhsachbaihat);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc", mangBaihats.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            imgLuotThich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgLuotThich.setImageResource(R.drawable.iconloved);
                    DataService dataService  = APIService.getService();
                    Call<String> callback = dataService.UpdateLuotThich("1",mangBaihats.get(getPosition()).getIdBaiHat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if(ketqua.equals("Success")){
                                Toast.makeText(context, "Đã thích.", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(context, "Lỗi !", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgLuotThich.setEnabled(false);
                }
            });
        }
    }
}
