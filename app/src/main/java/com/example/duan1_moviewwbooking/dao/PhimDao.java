package com.example.duan1_moviewwbooking.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_moviewwbooking.database.DbHelper;
import com.example.duan1_moviewwbooking.model.Phim;

import java.util.ArrayList;
import java.util.List;

public class PhimDao {
    SQLiteDatabase sqLiteDatabase;
    Context context;
    public static  final  String TABLE_NAME_PHIM = DbHelper.TABLE_NAME_PHIM;
    public PhimDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        this.context = context;
    }

    public Phim getPhimById(int phimId) {
        String sql = "SELECT * FROM " + TABLE_NAME_PHIM + " WHERE id = ?";
        String[] selectionArgs = {String.valueOf(phimId)};
        List<Phim> listPhim = getData(sql, selectionArgs);
        if (!listPhim.isEmpty()) {
            return listPhim.get(0);
        }
        return null;
    }

    public List<Phim> searchPhimByTenPhim(String tenPhim) {
        String sql = "SELECT * FROM " + TABLE_NAME_PHIM + " WHERE tenPhim LIKE ?";
        String[] selectionArgs = {"%" + tenPhim + "%"};
        return getData(sql, selectionArgs);
    }

    public List<Phim> getAllPhim() {
        String sql = "SELECT * FROM " + TABLE_NAME_PHIM;
        return getData(sql);
    }

    public long insertPhim (Phim phim) {
        ContentValues values = new ContentValues();
        values.put("tenPhim", phim.getTenPhim());
        values.put("moTa", phim.getMoTa());
        values.put("thoiLuong", String.valueOf(phim.getThoiLuong()));
        values.put("anhDaiDien", phim.getAnhDaiDien());
        values.put("trailer", phim.getTrailer());
        values.put("doTuoiXem", String.valueOf(phim.getDoTuoiXem()));
        values.put("ngayPhatHanh", String.valueOf(phim.getNgayPhatHanh()));
        return sqLiteDatabase.insert(TABLE_NAME_PHIM, null, values);
    }

    public int deletePhim (int id) {
        return  sqLiteDatabase.delete(TABLE_NAME_PHIM, "id=?", new String[]{String.valueOf(id)});
    }


    @SuppressLint("Range")
    private List<Phim> getData(String sql, String... selectionArgs) {
        List<Phim> listPhim = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            Phim phim = new Phim();
            phim.setId(cursor.getInt(cursor.getColumnIndex("id")));
            phim.setTenPhim(cursor.getString(cursor.getColumnIndex("tenPhim")));
            phim.setMoTa(cursor.getString(cursor.getColumnIndex("moTa")));
            phim.setThoiLuong(cursor.getInt(cursor.getColumnIndex("thoiLuong")));
            phim.setAnhDaiDien(cursor.getString(cursor.getColumnIndex("anhDaiDien")));
            phim.setTrailer(cursor.getString(cursor.getColumnIndex("trailer")));
            phim.setDoTuoiXem(cursor.getInt(cursor.getColumnIndex("doTuoiXem")));
            phim.setNgayPhatHanh(cursor.getString(cursor.getColumnIndex("ngayPhatHanh")));
            listPhim.add(phim);
        }
        cursor.close();
        return listPhim;
    }
}
