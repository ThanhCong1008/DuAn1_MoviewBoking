package com.example.duan1_moviewwbooking.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duan1_moviewwbooking.R;
import com.example.duan1_moviewwbooking.dao.RapChieuDao;
import com.example.duan1_moviewwbooking.dao.TaiKhoanDao;
import com.example.duan1_moviewwbooking.dao.VePhimDao;
import com.example.duan1_moviewwbooking.dao.XuatChieuDao;
import com.example.duan1_moviewwbooking.model.RapChieu;
import com.example.duan1_moviewwbooking.model.TaiKhoan;
import com.example.duan1_moviewwbooking.model.VePhim;
import com.example.duan1_moviewwbooking.model.XuatChieu;

public class ThongTinVeActivity extends AppCompatActivity {
    ImageView imgBack;
    VePhimDao vePhimDao;
    XuatChieuDao xuatChieuDao;
    RapChieuDao rapChieuDao;
    TaiKhoanDao taiKhoanDao;
    TextView tvNgayChieu, tvGioChieu, tvRapChieu, tvNguoiDat, tvMaVe, tvTongTien;
    SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ve);

        imgBack = findViewById(R.id.img_back_atv_thongtinve);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvNgayChieu = findViewById(R.id.tv_ngaychieu_atv_thongtinve);
        tvGioChieu = findViewById(R.id.tv_giochieu_atv_thongtinve);
        tvRapChieu = findViewById(R.id.tv_tenrap_atv_thongtinve);
        tvNguoiDat = findViewById(R.id.tv_nguoidat_atv_thongtinve);
        tvMaVe = findViewById(R.id.tv_mave);
        tvTongTien = findViewById(R.id.tv_tonggia_atv_thongtinve);

        int vePhimID = getIntent().getIntExtra("vephim_id", -1);
        vePhimDao = new VePhimDao(this);

        // Lấy thông tin ve phim từ VePhimDao dựa trên ID
        VePhim vePhim = vePhimDao.getVePhimById(vePhimID);

        // Lay id xuat chieu hien tai
        int xuatChieuID = vePhim.getIdXuatChieu();
        // khoi tao xuat chieu dao
        xuatChieuDao = new XuatChieuDao(this);
        // lay xuat chieu theo id xuat chieu vua lay o tren
        XuatChieu xuatChieu = xuatChieuDao.getXuatChieuById(xuatChieuID);
        // lay id rap chieu cua xuat chieu hien tai
        int rapChieuID = xuatChieu.getIdRapChieu();
        // khoi tao rap chieu dao
        rapChieuDao = new RapChieuDao(this);
        // lay thong tin rap chieu theo id rap vua lay
        RapChieu rapChieu = rapChieuDao.getRapChieuById(rapChieuID);

        // set cac thuoc tinh cua xuat chieu va rap chieu len thong tin ve
        tvNgayChieu.setText(rapChieu.getNgayChieu() + " / ");
        tvRapChieu.setText(rapChieu.getTenRap());
        tvGioChieu.setText(xuatChieu.getThoiGianBatDau());
        tvMaVe.setText(vePhim.getId() + "-TICKET");
        tvTongTien.setText(vePhim.getTongGiaTri() + " VND");

        int idUser = vePhim.getIdTaiKhoan();
        taiKhoanDao = new TaiKhoanDao(this);
        Cursor cursor = taiKhoanDao.getUserById(idUser);

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range")
            String tenTaiKhoan = cursor.getString(cursor.getColumnIndex("tenTaiKhoan"));
            // Tạo một đối tượng TaiKhoan từ dữ liệu của cursor
            TaiKhoan taiKhoan = new TaiKhoan();
            taiKhoan.setTenTaiKhoan(tenTaiKhoan);
            tvNguoiDat.setText(taiKhoan.getTenTaiKhoan());
            cursor.close();
        }
    }
}
