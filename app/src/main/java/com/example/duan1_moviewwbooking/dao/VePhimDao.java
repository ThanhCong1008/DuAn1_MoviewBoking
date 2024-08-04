package com.example.duan1_moviewwbooking.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_moviewwbooking.database.DbHelper;
import com.example.duan1_moviewwbooking.model.VePhim;

import java.util.ArrayList;
import java.util.List;

public class VePhimDao {
    SQLiteDatabase sqLiteDatabase;
    Context context;
    public static  final  String TABLE_NAME_VEPHIM = DbHelper.TABLE_NAME_VEPHIM;
    public VePhimDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        this.context = context;
    }

    public List<VePhim> getAllVePhim() {
        String sql = "SELECT * FROM " + TABLE_NAME_VEPHIM;
        return getData(sql);
    }

    public List<VePhim> getVePhimByIdUser(int userId) {
        String sql = "SELECT * FROM " + TABLE_NAME_VEPHIM + " WHERE idTaiKhoan = ?";
        String[] selectionArgs = {String.valueOf(userId)};
        return getData(sql, selectionArgs);
    }

    public int deleteVePhim (int id) {
        return  sqLiteDatabase.delete(TABLE_NAME_VEPHIM, "id=?", new String[]{String.valueOf(id)});
    }


    public long insertVePhim(VePhim vePhim) {
        ContentValues values = new ContentValues();
        values.put("idXuatChieu", vePhim.getIdXuatChieu());
        values.put("idTaiKhoan", vePhim.getIdTaiKhoan());
        values.put("tongGiaTri", vePhim.getTongGiaTri());
        values.put("ngayDatVe", vePhim.getNgayDatVe());
        return sqLiteDatabase.insert(TABLE_NAME_VEPHIM, null, values);
    }

    public VePhim getVePhimById(int vePhimID) {
        String sql = "SELECT * FROM " + TABLE_NAME_VEPHIM + " WHERE id = ?";
        String[] selectionArgs = {String.valueOf(vePhimID)};
        List<VePhim> listVePhim = getData(sql, selectionArgs);
        if (!listVePhim.isEmpty()) {
            return listVePhim.get(0);
        }
        return null;
    }


    @SuppressLint("Range")
    private List<VePhim> getData(String sql, String... selectionArgs) {
        List<VePhim> listVePhim = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            VePhim vePhim = new VePhim();
            vePhim.setId(cursor.getInt(cursor.getColumnIndex("id")));
            vePhim.setIdXuatChieu(cursor.getInt(cursor.getColumnIndex("idXuatChieu")));
            vePhim.setIdTaiKhoan(cursor.getInt(cursor.getColumnIndex("idTaiKhoan")));
            vePhim.setTongGiaTri(cursor.getInt(cursor.getColumnIndex("tongGiaTri")));
            vePhim.setNgayDatVe(cursor.getString(cursor.getColumnIndex("ngayDatVe")));
            listVePhim.add(vePhim);
        }
        cursor.close();
        return listVePhim;
    }
}
