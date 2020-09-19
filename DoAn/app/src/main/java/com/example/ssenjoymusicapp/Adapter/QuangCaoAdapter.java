package com.example.ssenjoymusicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ssenjoymusicapp.Activity.DanhSachBaiHatActivity;
import com.example.ssenjoymusicapp.Model.QuangCao;
import com.example.ssenjoymusicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class QuangCaoAdapter extends PagerAdapter {

    Context context;
    ArrayList<QuangCao> arrayListQuangCao;

    public QuangCaoAdapter(Context context, ArrayList<QuangCao> arrayListQuangCao) {
        this.context = context;
        this.arrayListQuangCao = arrayListQuangCao;
    }

    @Override
    public int getCount() {
        return arrayListQuangCao.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_quang_cao, null);

        ImageView imgbackgroundquangcao= view.findViewById(R.id.imageviewbackgroundquangcao);
        ImageView imgbaihatquangcao = view.findViewById(R.id.imagequangcao);
        TextView txttitlebaihatquangcao = view.findViewById(R.id.textviewtitlequangcaobaihat);
        TextView txtnoidung = view.findViewById(R.id.textviewnoidung);

        Picasso.with(context).load(arrayListQuangCao.get(position).getHinhAnhQuangCao()).into(imgbackgroundquangcao);
        Picasso.with(context).load(arrayListQuangCao.get(position).getHinhBaiHat()).into(imgbaihatquangcao);
        txttitlebaihatquangcao.setText(arrayListQuangCao.get(position).getTenBaiHat());
        txtnoidung.setText(arrayListQuangCao.get(position).getNoiDungQuangCao());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                intent.putExtra("QuangCao",arrayListQuangCao.get(position));
                context.startActivity(intent);
            }
        });

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
