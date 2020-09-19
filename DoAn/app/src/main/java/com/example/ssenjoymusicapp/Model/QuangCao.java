package com.example.ssenjoymusicapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class QuangCao implements Serializable {

@SerializedName("idQuangCao")
@Expose
private String idQuangCao;
@SerializedName("HinhAnhQuangCao")
@Expose
private String hinhAnhQuangCao;
@SerializedName("NoiDungQuangCao")
@Expose
private String noiDungQuangCao;
@SerializedName("idBaiHat")
@Expose
private String idBaiHat;
@SerializedName("TenBaiHat")
@Expose
private String tenBaiHat;
@SerializedName("HinhBaiHat")
@Expose
private String hinhBaiHat;

public String getIdQuangCao() {
return idQuangCao;
}

public void setIdQuangCao(String idQuangCao) {
this.idQuangCao = idQuangCao;
}

public String getHinhAnhQuangCao() {
return hinhAnhQuangCao;
}

public void setHinhAnhQuangCao(String hinhAnhQuangCao) {
this.hinhAnhQuangCao = hinhAnhQuangCao;
}

public String getNoiDungQuangCao() {
return noiDungQuangCao;
}

public void setNoiDungQuangCao(String noiDungQuangCao) {
this.noiDungQuangCao = noiDungQuangCao;
}

public String getIdBaiHat() {
return idBaiHat;
}

public void setIdBaiHat(String idBaiHat) {
this.idBaiHat = idBaiHat;
}

public String getTenBaiHat() {
return tenBaiHat;
}

public void setTenBaiHat(String tenBaiHat) {
this.tenBaiHat = tenBaiHat;
}

public String getHinhBaiHat() {
return hinhBaiHat;
}

public void setHinhBaiHat(String hinhBaiHat) {
this.hinhBaiHat = hinhBaiHat;
}

}