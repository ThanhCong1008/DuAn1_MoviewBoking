package com.example.duan1_moviewwbooking.model;

public class Phim {
    private int id;
    private String tenPhim;
    private String moTa;
    private int thoiLuong;
    private String anhDaiDien;
    private String trailer;
    private int doTuoiXem;
    private String ngayPhatHanh;

    public Phim() {
    }

    public Phim(int id, String tenPhim, String moTa, int thoiLuong, String anhDaiDien, String trailer, int doTuoiXem, String ngayPhatHanh) {
        this.id = id;
        this.tenPhim = tenPhim;
        this.moTa = moTa;
        this.thoiLuong = thoiLuong;
        this.anhDaiDien = anhDaiDien;
        this.trailer = trailer;
        this.doTuoiXem = doTuoiXem;
        this.ngayPhatHanh = ngayPhatHanh;
    }

    public Phim(String tenPhim, String moTa, int thoiLuong, String anhDaiDien, int doTuoiXem, String ngayPhatHanh) {
        this.tenPhim = tenPhim;
        this.moTa = moTa;
        this.thoiLuong = thoiLuong;
        this.anhDaiDien = anhDaiDien;
        this.doTuoiXem = doTuoiXem;
        this.ngayPhatHanh = ngayPhatHanh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(int thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public String getAnhDaiDien() {
        return anhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public int getDoTuoiXem() {
        return doTuoiXem;
    }

    public void setDoTuoiXem(int doTuoiXem) {
        this.doTuoiXem = doTuoiXem;
    }

    public String getNgayPhatHanh() {
        return ngayPhatHanh;
    }

    public void setNgayPhatHanh(String ngayPhatHanh) {
        this.ngayPhatHanh = ngayPhatHanh;
    }
}
