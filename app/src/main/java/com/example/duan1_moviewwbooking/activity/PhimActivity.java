package com.example.duan1_moviewwbooking.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.preference.PreferenceManager;

import com.example.duan1_moviewwbooking.R;
import com.example.duan1_moviewwbooking.dao.PhimDao;
import com.example.duan1_moviewwbooking.model.Phim;
import com.squareup.picasso.Picasso;

public class PhimActivity extends AppCompatActivity {

    private VideoView videoTrailer;
    private ImageView imgAnhDaiDien, imgTrailer, imgBack;
    private TextView tvTenPhim, tvNgayPhatHanh, tvThoiLuong, tvDoTuoi, tvMota;
    private AppCompatButton btnDatVe;
    private PhimDao phimDao;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phim);

        // Initialize UI components
        imgBack = findViewById(R.id.img_back_atv_phim);
        videoTrailer = findViewById(R.id.video_trailer);
        imgTrailer = findViewById(R.id.img_trailer);
        imgAnhDaiDien = findViewById(R.id.img_anhdaidien);
        tvTenPhim = findViewById(R.id.tv_tenphim);
        tvNgayPhatHanh = findViewById(R.id.tv_ngayphathanh);
        tvThoiLuong = findViewById(R.id.tv_thoiluong);
        tvDoTuoi = findViewById(R.id.tv_dotuoi);
        tvMota = findViewById(R.id.tv_mota);
        btnDatVe = findViewById(R.id.btn_datve_atv_phim);

        // Back button functionality
        imgBack.setOnClickListener(view -> finish());

        // Retrieve movie ID from Intent and fetch movie data
        int phimId = getIntent().getIntExtra("phim_id", -1);
        phimDao = new PhimDao(this);

        Phim phim = phimDao.getPhimById(phimId);

        if (phim != null) {
            // Load images and set text fields
            Picasso.get().load(phim.getAnhDaiDien()).into(imgAnhDaiDien);
            Picasso.get().load(phim.getAnhDaiDien()).into(imgTrailer);
            tvTenPhim.setText(phim.getTenPhim());
            tvNgayPhatHanh.setText(phim.getNgayPhatHanh());
            tvThoiLuong.setText(String.format("%d phút", phim.getThoiLuong()));
            tvDoTuoi.setText(String.format("%d +", phim.getDoTuoiXem()));
            tvMota.setText(phim.getMoTa());

            // Handle video trailer
            final String trailerUrl = phim.getTrailer();
            if (trailerUrl != null && !trailerUrl.isEmpty()) {
                videoTrailer.setVisibility(View.VISIBLE);
                videoTrailer.setOnClickListener(view -> {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl));
                    startActivity(intent);
                });
            } else {
                videoTrailer.setVisibility(View.GONE);
            }
        } else {
            // Handle the case where phim is null
            Toast.makeText(this, "Thông tin phim không hợp lệ", Toast.LENGTH_SHORT).show();
            finish(); // Close activity if phim is not found
        }

        // Handle booking button visibility and click
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sharedPreferences.getString("username", "");

        if ("admin".equals(username)) {
            btnDatVe.setVisibility(View.GONE);
        } else {
            btnDatVe.setVisibility(View.VISIBLE);
            btnDatVe.setOnClickListener(view -> {
                if (username.isEmpty()) {
                    // Hiển thị thông báo "Vui lòng đăng nhập"
                    Toast.makeText(PhimActivity.this, "Vui lòng đăng nhập", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(PhimActivity.this, com.example.duan1_movieticketbooking.activity.RapChieuActivity.class);
                    intent.putExtra("phim_id", phim.getId());
                    startActivity(intent);
                }
            });
        }
    }
}
