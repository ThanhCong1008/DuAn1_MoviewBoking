package com.example.duan1_moviewwbooking.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_moviewwbooking.database.DbHelper;
import com.example.duan1_moviewwbooking.model.GheNgoi;

import java.util.ArrayList;
import java.util.List;

public class GheNgoiDao {
    SQLiteDatabase sqLiteDatabase;
    Context context;
    public static  final  String TABLE_NAME_GHENGOI = DbHelper.TABLE_NAME_GHENGOI;
    public GheNgoiDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        this.context = context;
    }


    public List<GheNgoi> getGheNgoiByIdXuatChieu(int xuatChieuId) {
        String sql = "SELECT * FROM " + TABLE_NAME_GHENGOI + " WHERE idXuatChieu = ?";
        String[] selectionArgs = {String.valueOf(xuatChieuId)};
        return getData(sql, selectionArgs);
    }



    public void setGheTrangThai(int gheNgoiId, int trangThai) {
        ContentValues values = new ContentValues();
        values.put("trangThai", trangThai);
        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(gheNgoiId)};
        sqLiteDatabase.update(TABLE_NAME_GHENGOI, values, whereClause, whereArgs);
    }

    @SuppressLint("Range")
    private List<GheNgoi> getData(String sql, String... selectionArgs) {
        List<GheNgoi> listGheNgoi = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            GheNgoi gheNgoi = new GheNgoi();
            gheNgoi.setId(cursor.getInt(cursor.getColumnIndex("id")));
            gheNgoi.setIdXuatChieu(cursor.getInt(cursor.getColumnIndex("idXuatChieu")));
            gheNgoi.setSoGhe(cursor.getString(cursor.getColumnIndex("soGhe")));
            gheNgoi.setTrangThai(cursor.getInt(cursor.getColumnIndex("trangThai")));
            listGheNgoi.add(gheNgoi);
        }
        cursor.close();
        return listGheNgoi;
    }
}
