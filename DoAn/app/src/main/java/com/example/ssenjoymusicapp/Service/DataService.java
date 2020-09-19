package com.example.ssenjoymusicapp.Service;

import com.example.ssenjoymusicapp.Model.Album;
import com.example.ssenjoymusicapp.Model.Baihat;
import com.example.ssenjoymusicapp.Model.ChuDe;
import com.example.ssenjoymusicapp.Model.Playlist;
import com.example.ssenjoymusicapp.Model.QuangCao;
import com.example.ssenjoymusicapp.Model.TheLoai;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

//Class dùng để gửi lên những phương thức tương tác với server, sau khi đã kết nối sẽ nhận dữ liệu về.
public interface DataService {
    //Tương tác với đường link
    @GET("songbanner.php")
    //Nhận dữ liệu
    Call<List<QuangCao>> GetDataQuangcao();

    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> GetDataPlaylistToday();

    @GET("album.php")
    Call<List<Album>> GetDataALbumHot();

    @GET("BaiHatDuocYeuThich.php")
    Call<List<Baihat>> GetDataBaiHatDuocYeuThich();

    @GET("Playlist.php")
    Call<List<Playlist>> GetDataDanhSachPLaylist();

    @GET("TatCaAlbum.php")
    Call<List<Album>> GetDataTatCaAlbum();

    @GET("ChuDe.php")
    Call<List<ChuDe>> GetDataChuDe();

    //Sử dụng phương thức POST để gửi dữ liệu lên server và nhận về
    @FormUrlEncoded
    @POST("DanhSachBaiHat.php")
    Call<List<Baihat>> GetDanhSachBaihatTheoQuangCao(@Field("idQuangCao") String idQuangCao);

    @FormUrlEncoded
    @POST("DanhSachBaiHat.php")
    Call<List<Baihat>> GetDanhSachBaihatTheoPLaylist(@Field("idPlaylist") String idPlaylist);

    @FormUrlEncoded
    @POST("DanhSachBaiHat.php")
    Call<List<Baihat>> GetDanhSachBaihatTheoAlbum(@Field("idAlbum") String idAlbum);

    @FormUrlEncoded
    @POST("DanhSachBaiHat.php")
    Call<List<Baihat>> GetDanhSachBaihatTheoTheLoai(@Field("idTheLoai") String idTheLoai);

    @FormUrlEncoded
    @POST("TimKiem.php")
    Call<List<Baihat>> GetTimKiemBaiHat(@Field("tukhoa") String tukhoa);

    @FormUrlEncoded
    @POST("UpdateLuotThich.php")
    Call<String> UpdateLuotThich(@Field("LuotThich") String luotthich,@Field("idBaiHat") String idBaihat);

    @FormUrlEncoded
    @POST("TheLoai.php")
    Call<List<TheLoai>> GetDataTheLoai(@Field("idChuDe") String idChuDe);
}
