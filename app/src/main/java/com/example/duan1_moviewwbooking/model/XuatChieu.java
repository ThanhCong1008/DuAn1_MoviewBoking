package com.example.duan1_moviewwbooking.model;

public class XuatChieu {
    private int id;
    private int idRapChieu;
    private String thoiGianBatDau;
    private int giaVe;

    public XuatChieu() {

    }

    public XuatChieu(int id, int idRapChieu, String thoiGianBatDau, int giaVe) {
        this.id = id;
        this.idRapChieu = idRapChieu;
        this.thoiGianBatDau = thoiGianBatDau;
        this.giaVe = giaVe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRapChieu() {
        return idRapChieu;
    }

    public void setIdRapChieu(int idRapChieu) {
        this.idRapChieu = idRapChieu;
    }

    public String getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(String thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public int getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(int giaVe) {
        this.giaVe = giaVe;
    }
}
