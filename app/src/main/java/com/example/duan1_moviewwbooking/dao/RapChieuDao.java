package com.example.duan1_moviewwbooking.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_movieticketbooking.database.DbHelper;
import com.example.duan1_movieticketbooking.model.RapChieu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RapChieuDao {
    SQLiteDatabase sqLiteDatabase;
    Context context;
    public static  final  String TABLE_NAME_RAPCHIEU = DbHelper.TABLE_NAME_RAPCHIEU;
    public RapChieuDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        this.context = context;
    }

    public RapChieu getRapChieuById(int rapChieuId) {
        String sql = "SELECT * FROM " + TABLE_NAME_RAPCHIEU + " WHERE id = ?";
        String[] selectionArgs = {String.valueOf(rapChieuId)};
        List<RapChieu> listRapChieu = getData(sql, selectionArgs);
        if (!listRapChieu.isEmpty()) {
            return listRapChieu.get(0);
        }
        return null;
    }


    public List<RapChieu> getAllRapChieu() {
        String sql = "SELECT * FROM " + TABLE_NAME_RAPCHIEU;
        return getData(sql);
    }

    public List<RapChieu> getRapChieuTheoNgay(LocalDate ngayChieu) {
        String sql = "SELECT * FROM " + TABLE_NAME_RAPCHIEU + " WHERE ngayChieu = ?";
        String[] selectionArgs = {ngayChieu.toString()};
        return getData(sql, selectionArgs);
    }

    @SuppressLint("Range")
    private List<RapChieu> getData(String sql, String... selectionArgs) {
        List<RapChieu> listRapChieu = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            RapChieu rapChieu = new RapChieu();
            rapChieu.setId(cursor.getInt(cursor.getColumnIndex("id")));
            rapChieu.setTenRap(cursor.getString(cursor.getColumnIndex("tenRap")));
            rapChieu.setDienThoai(cursor.getString(cursor.getColumnIndex("dienThoai")));
            rapChieu.setNgayChieu(cursor.getString(cursor.getColumnIndex("ngayChieu")));

            listRapChieu.add(rapChieu);
        }
        cursor.close();
        return listRapChieu;
    }
}
