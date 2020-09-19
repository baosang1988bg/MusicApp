package com.example.ssenjoymusicapp.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Class khởi tạo retrofir để tương tác với server.
public class APIRetrofitClient {

    //cấp phát bộ nhớ trước (nhằm trách lỗi).
    private static Retrofit retrofit  = null;

    //function trả về cấu hình khi thực hiện xong cho retrofit.
    public static Retrofit getClient(String base_url){

        //Kiểm tra cấu hình giao thức tương tác với phần mạng phía server.
        OkHttpClient okHttpClient  = new OkHttpClient.Builder().readTimeout(10000, TimeUnit.MILLISECONDS)
                .writeTimeout(10000,TimeUnit.MILLISECONDS)
                .connectTimeout(10000,TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .protocols(Arrays.asList(Protocol.HTTP_1_1)).build();

        //Convert API thành java nhờ Gson.
        Gson gson = new GsonBuilder().setLenient().create();

        //Gắn cấu hình và Gson vào retrofit.
        retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return  retrofit;
    }
}
