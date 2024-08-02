package com.example.duan1_moviewwbooking.model;

public class GheNgoi {
    private int id;
    private int idXuatChieu;
    private String soGhe;
    private int trangThai;

    public GheNgoi() {
    }

    public GheNgoi(int id, int idXuatChieu, String soGhe, int trangThai) {
        this.id = id;
        this.idXuatChieu = idXuatChieu;
        this.soGhe = soGhe;
        this.trangThai = trangThai;
    }

    public int getIdXuatChieu() {
        return idXuatChieu;
    }

    public void setIdXuatChieu(int idXuatChieu) {
        this.idXuatChieu = idXuatChieu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(String soGhe) {
        this.soGhe = soGhe;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
