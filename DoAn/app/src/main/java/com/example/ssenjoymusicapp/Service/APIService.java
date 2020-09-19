package com.example.ssenjoymusicapp.Service;

//là class kết hợp DataService và APIRetrofitClient.
public class APIService {

    //Đường dẫn url để tương tác về phía của server (cấu hình retrofit để tương tác với server).
    private static String base_url = "https://ssenjoymusic.000webhostapp.com/Server/";

    //Tạo function để tương tác kết hợp DataService và APIRetrofitClient.
    public static DataService getService(){

        //Gửi cấu hình, khởi tạo phương thức DataServeice đẻ gửi lên.
        return APIRetrofitClient.getClient(base_url).create(DataService.class);

    }
}
