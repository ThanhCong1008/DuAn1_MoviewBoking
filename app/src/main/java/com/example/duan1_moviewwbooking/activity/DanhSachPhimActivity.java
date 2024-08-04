package com.example.duan1_moviewwbooking.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_moviewwbooking.R;
import com.example.duan1_moviewwbooking.adapter.PhimDSAdapter;
import com.example.duan1_moviewwbooking.dao.PhimDao;
import com.example.duan1_moviewwbooking.databinding.ActivityDanhSachPhimBinding;
import com.example.duan1_moviewwbooking.databinding.DialogThemPhimBinding;
import com.example.duan1_moviewwbooking.model.Phim;

import java.util.ArrayList;
import java.util.Calendar;

public class DanhSachPhimActivity extends AppCompatActivity {

    private ActivityDanhSachPhimBinding binding;
    private PhimDSAdapter phimDSAdapter;
    private PhimDao phimDao;
    private ArrayList<Phim> arrPhim = new ArrayList<>();
    private Context mContext;
    private LayoutInflater inflater;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDanhSachPhimBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mContext = this;

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sharedPreferences.getString("username", "");
        if (!"admin".equals(username)) {
            binding.floatBtnAddPhim.setVisibility(View.GONE);
        }

        binding.imgBackAtvDanhsachphim.setOnClickListener(view -> finish());

        int searchTextColor = Color.WHITE;
        EditText searchEditText = binding.searchPhim.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(searchTextColor);
        binding.searchPhim.setQueryHint(getString(R.string.search_phim_hint));

        inflater = getLayoutInflater();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        binding.rcvDanhsachphim.setLayoutManager(layoutManager);

        phimDao = new PhimDao(mContext);
        arrPhim = (ArrayList<Phim>) phimDao.getAllPhim();
        phimDSAdapter = new PhimDSAdapter(mContext, arrPhim);
        binding.rcvDanhsachphim.setAdapter(phimDSAdapter);

        binding.searchPhim.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Phim> filteredList = (ArrayList<Phim>) phimDao.searchPhimByTenPhim(newText);
                phimDSAdapter.setFilter(filteredList);
                return true;
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(DanhSachPhimActivity.this);
        DialogThemPhimBinding dialogBinding = DialogThemPhimBinding.inflate(getLayoutInflater());
        builder.setView(dialogBinding.getRoot());
        AlertDialog dialogAddPhim = builder.create();

        binding.floatBtnAddPhim.setOnClickListener(view -> {
            dialogBinding.edNgayphathanh.setOnClickListener(v -> {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, (datePicker, year, month, day) ->
                        dialogBinding.edNgayphathanh.setText(String.format("%d/%d/%d", day, month + 1, year)),
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            });

            dialogAddPhim.show();

            dialogBinding.btnDialogCancleAddPhim.setOnClickListener(v -> dialogAddPhim.dismiss());

            dialogBinding.btnDialogAddPhim.setOnClickListener(v -> {
                int check = 1;
                if (dialogBinding.edTenphim.getText().toString().isEmpty()) {
                    dialogBinding.inputTenphim.setError(getString(R.string.error_ten_phim));
                    check = -1;
                } else {
                    dialogBinding.inputTenphim.setError("");
                }
                if (dialogBinding.edMota.getText().toString().isEmpty()) {
                    dialogBinding.inputMota.setError(getString(R.string.error_mo_ta));
                    check = -1;
                } else {
                    dialogBinding.inputMota.setError("");
                }
                String thoiLuongText = dialogBinding.edThoiluong.getText().toString();
                if (TextUtils.isEmpty(thoiLuongText)) {
                    dialogBinding.inputThoiluong.setError(getString(R.string.error_thoi_luong));
                    check = -1;
                } else if (!TextUtils.isDigitsOnly(thoiLuongText)) {
                    dialogBinding.inputThoiluong.setError(getString(R.string.error_thoi_luong_number));
                    check = -1;
                } else {
                    dialogBinding.inputThoiluong.setError("");
                }
                if (dialogBinding.edAnhdaidien.getText().toString().isEmpty()) {
                    dialogBinding.inputAnhdaidien.setError(getString(R.string.error_anh_dai_dien));
                    check = -1;
                } else {
                    dialogBinding.inputAnhdaidien.setError("");
                }
                if (dialogBinding.edTrailer.getText().toString().isEmpty()) {
                    dialogBinding.inputTrailer.setError(getString(R.string.error_trailer));
                    check = -1;
                } else {
                    dialogBinding.inputTrailer.setError("");
                }
                String doTuoiText = dialogBinding.edDotuoi.getText().toString();
                if (TextUtils.isEmpty(doTuoiText)) {
                    dialogBinding.inputDotuoi.setError(getString(R.string.error_do_tuoi));
                    check = -1;
                } else if (!TextUtils.isDigitsOnly(doTuoiText)) {
                    dialogBinding.inputDotuoi.setError(getString(R.string.error_do_tuoi_number));
                    check = -1;
                } else {
                    dialogBinding.inputDotuoi.setError("");
                }
                if (dialogBinding.edNgayphathanh.getText().toString().isEmpty()) {
                    dialogBinding.inputNgayphathanh.setError(getString(R.string.error_ngay_phat_hanh));
                    check = -1;
                } else {
                    dialogBinding.inputNgayphathanh.setError("");
                }

                if (check > 0) {
                    Phim phim = new Phim();
                    phim.setTenPhim(dialogBinding.edTenphim.getText().toString());
                    phim.setMoTa(dialogBinding.edMota.getText().toString());
                    phim.setThoiLuong(Integer.parseInt(dialogBinding.edThoiluong.getText().toString()));
                    phim.setAnhDaiDien(dialogBinding.edAnhdaidien.getText().toString());
                    phim.setTrailer(dialogBinding.edTrailer.getText().toString());
                    phim.setDoTuoiXem(Integer.parseInt(dialogBinding.edDotuoi.getText().toString()));
                    phim.setNgayPhatHanh(dialogBinding.edNgayphathanh.getText().toString());
                    phimDao.insertPhim(phim);

                    arrPhim.clear();
                    arrPhim.addAll(phimDao.getAllPhim());
                    phimDSAdapter.notifyDataSetChanged();
                    Toast.makeText(DanhSachPhimActivity.this, getString(R.string.add_phim_success), Toast.LENGTH_SHORT).show();
                    dialogAddPhim.dismiss();

                    dialogBinding.edTenphim.setText("");
                    dialogBinding.edMota.setText("");
                    dialogBinding.edThoiluong.setText("");
                    dialogBinding.edAnhdaidien.setText("");
                    dialogBinding.edTrailer.setText("");
                    dialogBinding.edDotuoi.setText("");
                    dialogBinding.edNgayphathanh.setText("");
                    dialogBinding.inputTenphim.setError("");
                    dialogBinding.inputMota.setError("");
                    dialogBinding.inputThoiluong.setError("");
                    dialogBinding.inputAnhdaidien.setError("");
                    dialogBinding.inputTrailer.setError("");
                    dialogBinding.inputDotuoi.setError("");
                    dialogBinding.inputNgayphathanh.setError("");
                }
            });
        });
    }
}
