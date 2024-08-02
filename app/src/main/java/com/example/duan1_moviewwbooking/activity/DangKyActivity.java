package com.example.duan1_moviewwbooking.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.duan1_moviewwbooking.R;
import com.example.duan1_moviewwbooking.dao.TaiKhoanDao;


public
class DangKyActivity extends AppCompatActivity {

    private EditText edTenTaiKhoan, edHoTen, edMatKhau, edMatKhauNhapLai;
    private AppCompatButton btnDangKy;
    TaiKhoanDao taiKhoanDao;
    TextView tvDangNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        edTenTaiKhoan = findViewById(R.id.ed_tentaikhoan_atv_dangky);
        edHoTen = findViewById(R.id.ed_hoten);
        edMatKhau = findViewById(R.id.ed_matkhau_atv_dangky);
        edMatKhauNhapLai = findViewById(R.id.ed_matkhaunhaplai);
        btnDangKy = findViewById(R.id.btn_dangky);
        tvDangNhap = findViewById(R.id.tv_dangnhap);
        taiKhoanDao = new TaiKhoanDao(this);

        tvDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangKyActivity.this, DangNhapActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoTen  = edHoTen.getText().toString();
                String matKhau = edMatKhau.getText().toString();
                String matKhauNhapLai = edMatKhauNhapLai.getText().toString();
                String tenTaiKhoan = edTenTaiKhoan.getText().toString();
                boolean taiKhoanDaTonTai = taiKhoanDao.checkTaiKhoanTonTai(tenTaiKhoan);

                if (taiKhoanDaTonTai) {
                    Toast.makeText(DangKyActivity.this, "Tên tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();
                } else {
                    // Tiếp tục quá trình đăng ký
                    if (matKhau.equals(matKhauNhapLai)) {
                        taiKhoanDao.registerTaiKhoan(tenTaiKhoan, hoTen, matKhau);
                        Toast.makeText(DangKyActivity.this, "Tạo tài khoản thành công!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DangKyActivity.this, DangNhapActivity.class));
                    } else {
                        Toast.makeText(DangKyActivity.this, "Tạo tài khoản thất bại!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}