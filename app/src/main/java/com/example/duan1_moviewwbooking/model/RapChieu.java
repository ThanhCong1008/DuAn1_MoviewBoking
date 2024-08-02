package com.example.duan1_moviewwbooking.model;


public class RapChieu {
    private int id;
    private String tenRap;
    private String diaChi;
    private String dienThoai;
    private String ngayChieu;

    public RapChieu() {
    }

    public RapChieu(int id, String tenRap, String diaChi, String dienThoai, String ngayChieu) {
        this.id = id;
        this.tenRap = tenRap;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
        this.ngayChieu = ngayChieu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenRap() {
        return tenRap;
    }

    public void setTenRap(String tenRap) {
        this.tenRap = tenRap;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getNgayChieu() {
        return ngayChieu;
    }

    public void setNgayChieu(String ngayChieu) {
        this.ngayChieu = ngayChieu;
    }
}
