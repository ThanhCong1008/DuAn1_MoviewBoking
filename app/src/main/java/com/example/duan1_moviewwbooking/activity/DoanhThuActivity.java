package com.example.duan1_moviewwbooking.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.duan1_moviewwbooking.R;
import com.example.duan1_moviewwbooking.dao.ThongKeDao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DoanhThuActivity extends AppCompatActivity {
    ImageView imgBack;
    AppCompatButton btn_doanh_thu;
    EditText ed_doanh_thu_tu_ngay, ed_doanh_thu_den_ngay;
    TextView tv_doanh_thu;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doanh_thu);

        imgBack = findViewById(R.id.img_back_atv_doanh_thu);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ed_doanh_thu_tu_ngay = findViewById(R.id.ed_doanh_thu_tu_ngay);
        ed_doanh_thu_den_ngay = findViewById(R.id.ed_doanh_thu_den_ngay);
        tv_doanh_thu = findViewById(R.id.tv_doanhthu);
        btn_doanh_thu = findViewById(R.id.btn_doanhthu);

        DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mYear = year;
                mMonth = month;
                mDay = dayOfMonth;
                GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
                ed_doanh_thu_tu_ngay.setText(sdf.format(c.getTime()));
            }
        };
        DatePickerDialog.OnDateSetListener mDateDenNgay = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mYear = year;
                mMonth = month;
                mDay = dayOfMonth;
                GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
                ed_doanh_thu_den_ngay.setText(sdf.format(c.getTime()));
            }
        };
        ed_doanh_thu_tu_ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(DoanhThuActivity.this,
                        0, mDateTuNgay, mYear, mMonth, mDay);
                d.show();
            }
        });

        ed_doanh_thu_den_ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(DoanhThuActivity.this,
                        0, mDateDenNgay, mYear, mMonth, mDay);
                d.show();
            }
        });
        btn_doanh_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tuNgay = ed_doanh_thu_tu_ngay.getText().toString();
                String denNgay = ed_doanh_thu_den_ngay.getText().toString();
                if(tuNgay.isEmpty() || denNgay.isEmpty()) {
                    Toast.makeText(DoanhThuActivity.this, "Vui lòng chọn ngày", Toast.LENGTH_SHORT).show();
                } else {
                    ThongKeDao thongKeDao = new ThongKeDao(DoanhThuActivity.this);
                    tv_doanh_thu.setText(thongKeDao.getDoanhThu(tuNgay, denNgay) + " VND");
                }

            }
        });
    }
}