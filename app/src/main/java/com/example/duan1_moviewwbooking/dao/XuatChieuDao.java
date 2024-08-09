package com.example.duan1_moviewwbooking.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_moviewwbooking.database.DbHelper;
import com.example.duan1_moviewwbooking.model.XuatChieu;

import java.util.ArrayList;
import java.util.List;

public class XuatChieuDao {
    SQLiteDatabase sqLiteDatabase;
    Context context;
    public static  final  String TABLE_NAME_XUATCHIEU = DbHelper.TABLE_NAME_XUATCHIEU;
    public XuatChieuDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        this.context = context;
    }


    public List<XuatChieu> getAllXuatChieu() {
        String sql = "SELECT * FROM " + TABLE_NAME_XUATCHIEU;
        return getData(sql);
    }

    public List<XuatChieu> getXuatChieuByIdRapChieu(int rapChieuId) {
        String sql = "SELECT * FROM " + TABLE_NAME_XUATCHIEU + " WHERE idRapChieu = ?";
        String[] selectionArgs = {String.valueOf(rapChieuId)};
        return getData(sql, selectionArgs);
    }

    public XuatChieu getXuatChieuById(int xuatChieuId) {
        String sql = "SELECT * FROM " + TABLE_NAME_XUATCHIEU + " WHERE id = ?";
        String[] selectionArgs = {String.valueOf(xuatChieuId)};
        List<XuatChieu> listXuatChieu = getData(sql, selectionArgs);
        if (!listXuatChieu.isEmpty()) {
            return listXuatChieu.get(0);
        }
        return null;
    }


    @SuppressLint("Range")
    private List<XuatChieu> getData(String sql, String... selectionArgs) {
        List<XuatChieu> listXuatChieu = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            XuatChieu xuatChieu = new XuatChieu();
            xuatChieu.setId(cursor.getInt(cursor.getColumnIndex("id")));
            xuatChieu.setIdRapChieu(cursor.getInt(cursor.getColumnIndex("idRapChieu")));
            xuatChieu.setThoiGianBatDau(cursor.getString(cursor.getColumnIndex("thoiGianBatDau")));
            xuatChieu.setGiaVe(cursor.getInt(cursor.getColumnIndex("giaVe")));
            listXuatChieu.add(xuatChieu);
        }
        cursor.close();
        return listXuatChieu;
    }
}
