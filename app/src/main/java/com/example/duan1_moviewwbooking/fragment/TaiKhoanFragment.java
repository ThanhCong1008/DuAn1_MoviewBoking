package com.example.duan1_moviewwbooking.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.example.duan1_moviewwbooking.R;
import com.example.duan1_moviewwbooking.activity.DangKyActivity;
import com.example.duan1_moviewwbooking.activity.DangNhapActivity;
import com.example.duan1_moviewwbooking.activity.DoanhThuActivity;
import com.example.duan1_moviewwbooking.activity.QuanLyNguoiDungActivity;

public class TaiKhoanFragment extends Fragment {
    LinearLayout lnlDangNhap, lnlDangKy, lnlQuanLyNguoiDung, lnlDoanhThu, lnlDangXuat;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tai_khoan, container, false);
        lnlDangNhap = view.findViewById(R.id.lnl_dangnhap);
        lnlDangKy = view.findViewById(R.id.lnl_dangky);
        lnlQuanLyNguoiDung = view.findViewById(R.id.lnl_quanlynguoidung);
        lnlDoanhThu = view.findViewById(R.id.lnl_doanhthu);
        lnlDangXuat = view.findViewById(R.id.lnl_dangxuat);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext()); // Updated usage
        String username = sharedPreferences.getString("username", "");

        if (username.isEmpty()) {
            lnlDangXuat.setVisibility(View.GONE);
        }
        if (!"admin".equals(username)) {
            lnlQuanLyNguoiDung.setVisibility(View.GONE);
            lnlDoanhThu.setVisibility(View.GONE);
        }

        if (!(username).isEmpty()) {
            lnlDangNhap.setVisibility(View.GONE);
        }

        lnlDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xóa thông tin tài khoản đã lưu trữ
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("username");
                editor.apply();

                Intent intent = new Intent(getActivity(), DangNhapActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        lnlDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xóa thông tin tài khoản đã lưu trữ
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("username");
                editor.apply();

                Intent intent = new Intent(getActivity(), DangKyActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        lnlDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Xác nhận đăng xuất");
                builder.setMessage("Bạn có chắc chắn muốn đăng xuất?");
                builder.setPositiveButton("Đăng xuất", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Xóa thông tin tài khoản đã lưu trữ
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("username");
                        editor.apply();

                        Intent intent = new Intent(getActivity(), DangNhapActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                });
                builder.setNegativeButton("Hủy", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        lnlQuanLyNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), QuanLyNguoiDungActivity.class);
                startActivity(intent);
            }
        });

        lnlDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DoanhThuActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
