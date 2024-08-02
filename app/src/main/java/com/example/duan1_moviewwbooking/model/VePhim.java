package com.example.duan1_moviewwbooking.model;

public class VePhim {
    private int id;
    private int idXuatChieu;
    private int idTaiKhoan;
    private int tongGiaTri;
    private String ngayDatVe;

    public VePhim() {
    }

    public VePhim(int id, int idXuatChieu, int idTaiKhoan, int tongGiaTri, String ngayDatVe) {
        this.id = id;
        this.idXuatChieu = idXuatChieu;
        this.idTaiKhoan = idTaiKhoan;
        this.tongGiaTri = tongGiaTri;
        this.ngayDatVe = ngayDatVe;
    }

    public VePhim(int idXuatChieu, int idTaiKhoan, int tongGiaTri, String ngayDatVe) {
        this.idXuatChieu = idXuatChieu;
        this.idTaiKhoan = idTaiKhoan;
        this.tongGiaTri = tongGiaTri;
        this.ngayDatVe = ngayDatVe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdXuatChieu() {
        return idXuatChieu;
    }

    public void setIdXuatChieu(int idXuatChieu) {
        this.idXuatChieu = idXuatChieu;
    }

    public int getIdTaiKhoan() {
        return idTaiKhoan;
    }

    public void setIdTaiKhoan(int idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
    }

    public int getTongGiaTri() {
        return tongGiaTri;
    }

    public void setTongGiaTri(int tongGiaTri) {
        this.tongGiaTri = tongGiaTri;
    }

    public String getNgayDatVe() {
        return ngayDatVe;
    }

    public void setNgayDatVe(String ngayDatVe) {
        this.ngayDatVe = ngayDatVe;
    }
}
