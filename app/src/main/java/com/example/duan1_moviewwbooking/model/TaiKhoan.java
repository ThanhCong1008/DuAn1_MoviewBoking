package com.example.duan1_moviewwbooking.model;

public class TaiKhoan {
    private int id;
    private String tenTaiKhoan;
    private String hoTen;
    private String matKhau;

    public TaiKhoan() {
    }

    public TaiKhoan(int id, String tenTaiKhoan, String hoTen, String matKhau) {
        this.id = id;
        this.tenTaiKhoan = tenTaiKhoan;
        this.hoTen = hoTen;
        this.matKhau = matKhau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
