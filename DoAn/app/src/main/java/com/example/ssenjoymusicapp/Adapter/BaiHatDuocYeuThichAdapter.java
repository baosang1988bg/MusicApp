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

import com.example.ssenjoymusicapp.Activity.DanhSachBaiHatActivity;
import com.example.ssenjoymusicapp.Activity.PlayNhacActivity;
import com.example.ssenjoymusicapp.Model.Album;
import com.example.ssenjoymusicapp.Model.Baihat;
import com.example.ssenjoymusicapp.R;
import com.example.ssenjoymusicapp.Service.APIService;
import com.example.ssenjoymusicapp.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaiHatDuocYeuThichAdapter extends  RecyclerView.Adapter<BaiHatDuocYeuThichAdapter.ViewHolder> {

    Context context;
    ArrayList<Baihat> mangBaihats;

    public BaiHatDuocYeuThichAdapter(Context context, ArrayList<Baihat> mangBaihats) {
        this.context = context;
        this.mangBaihats = mangBaihats;
    }

    @NonNull
    @Override
    public BaiHatDuocYeuThichAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_bai_hat_duoc_yeu_thich,viewGroup,false);

        return new BaiHatDuocYeuThichAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaiHatDuocYeuThichAdapter.ViewHolder viewHolder, int i) {
        Baihat baihat = mangBaihats.get(i);
        viewHolder.txtCaSi.setText(baihat.getCaSi());
        viewHolder.txtTen.setText(baihat.getTenBaiHat());
        Picasso.with(context).load(baihat.getHinhBaiHat()).into(viewHolder.imgHinh);

    }

    @Override
    public int getItemCount() {
        return mangBaihats.size();
    }

    //Cung cấp lại các view bên phần danh sáchđể cho tương tác là gắn dữ liệu lại
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTen,txtCaSi;
        ImageView imgHinh, imgLuotThich;
        public ViewHolder(View itemview){
            super(itemview);
            txtCaSi = itemview.findViewById(R.id.textviewTenCaSiBaiHatDuocYeuThich);
            txtTen = itemview.findViewById(R.id.textviewTenBaiHatDuocYeuThich);
            imgHinh = itemview.findViewById(R.id.imageviewBaiHatDuocYeuThich);
            imgLuotThich = itemview.findViewById(R.id.imageviewLuotThich);
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
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc",mangBaihats.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
