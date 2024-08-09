package com.example.duan1_moviewwbooking.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_moviewwbooking.R;
import com.example.duan1_moviewwbooking.adapter.RapChieuAdapter;
import com.example.duan1_moviewwbooking.dao.PhimDao;
import com.example.duan1_moviewwbooking.dao.RapChieuDao;
import com.example.duan1_moviewwbooking.model.Phim;
import com.example.duan1_moviewwbooking.model.RapChieu;
import com.harrywhewell.scrolldatepicker.DayScrollDatePicker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RapChieuActivity extends AppCompatActivity {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Context mContext;
    private TextView tvTenPhim;
    private ImageView imgBack;
    private DayScrollDatePicker mPicker;
    private RecyclerView rcvDanhSachRap;
    private RapChieuAdapter rapChieuAdapter;
    private RapChieuDao rapChieuDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rap_chieu);

        initializeViews();
        setupBackButton();
        retrieveAndDisplayMovieDetails();
        setupDatePicker();
        setupRecyclerView();
    }

    private void initializeViews() {
        mContext = this;
        tvTenPhim = findViewById(R.id.tv_tenphim_atv_rapchieu);
        imgBack = findViewById(R.id.img_back_atv_rapchieu);
        mPicker = findViewById(R.id.calendar_lichchieu);
        rcvDanhSachRap = findViewById(R.id.rcv_danhsachrap);
    }

    private void setupBackButton() {
        imgBack.setOnClickListener(view -> finish());
    }

    private void retrieveAndDisplayMovieDetails() {
        int phimId = getIntent().getIntExtra("phim_id", 0);
        PhimDao phimDao = new PhimDao(this);
        Phim phim = phimDao.getPhimById(phimId);
        if (phim != null) {
            tvTenPhim.setText(phim.getTenPhim());
        } else {
            tvTenPhim.setText("Phim không tìm thấy");
        }
    }

    private void setupDatePicker() {
        LocalDate currentDate = LocalDate.now();
        LocalDate startDate = currentDate.minusDays(5);
        LocalDate endDate = currentDate.plusDays(5);

        mPicker.setStartDate(startDate.getDayOfMonth(), startDate.getMonthValue(), startDate.getYear());
        mPicker.setEndDate(endDate.getDayOfMonth(), endDate.getMonthValue(), endDate.getYear());

        // Correct the method name here:
//        mPicker.setOnScrollChangedListener((view, year, month, day) -> {
//            LocalDate selectedDate = LocalDate.of(year, month + 1, day); // month is 0-based
//            executorService.submit(() -> {
//                List<RapChieu> danhSachRapChieu = loadRapChieu((selectedDate));
//                runOnUiThread(() -> rapChieuAdapter.updateData(danhSachRapChieu));
//            });
//        });
    }

    private void setupRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        rcvDanhSachRap.setLayoutManager(layoutManager);

        rapChieuDao = new RapChieuDao(mContext);
        // Ensure initialRapChieuList is a List<RapChieu>
        List<RapChieu> initialRapChieuList = rapChieuDao.getAllRapChieu();
        rapChieuAdapter = new RapChieuAdapter(mContext, initialRapChieuList);
        rcvDanhSachRap.setAdapter(rapChieuAdapter);
    }

    private List<RapChieu> loadRapChieu(LocalDate date) {
        String ngayChieu = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate ngayChieuLocalDate = LocalDate.parse(ngayChieu, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        return rapChieuDao.getRapChieuTheoNgay(ngayChieuLocalDate);
    }
}
