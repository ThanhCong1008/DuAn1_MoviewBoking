package com.example.duan1_moviewwbooking.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_moviewwbooking.database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class ThongKeDao {
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public ThongKeDao(Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }


    //thong ke doanh thu
    @SuppressLint("Range")
    public double getDoanhThu(String tuNgay, String denNgay) {
        String sqlDoanhThu = "SELECT SUM(tongGiaTri) as doanhThu FROM vephim WHERE ngayDatVe >= ? AND ngayDatVe <= ?";
        List<Integer> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sqlDoanhThu, new String[]{tuNgay, denNgay});

        while (cursor.moveToNext()) {
            try {
                list.add(cursor.getInt(cursor.getColumnIndex("doanhThu")));
            } catch (Exception e) {
                list.add(0);
            }
        }

        if (list.isEmpty()) {
            return 0.0;
        } else {
            return list.get(0);
        }
    }



}
