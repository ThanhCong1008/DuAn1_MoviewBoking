package com.example.duan1_moviewwbooking.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_moviewwbooking.database.DbHelper;
import com.example.duan1_moviewwbooking.model.TaiKhoan;

import java.util.ArrayList;
import java.util.List;


public
class TaiKhoanDao {
    DbHelper database;
    SQLiteDatabase sqLiteDatabase;
    public static  final  String TABLE_NAME_TAIKHOAN = DbHelper.TABLE_NAME_TAIKHOAN;

    public void openDatabase() {
        sqLiteDatabase = database.getWritableDatabase();
    }
    public TaiKhoanDao(Context context) {
        database = new DbHelper ( context);
        sqLiteDatabase = database.getWritableDatabase();
    }

    public
    boolean checkdangnhap(String tenTaiKhoan, String matKhau) {
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase ();
        Cursor cursor = sqLiteDatabase.rawQuery ("select * from taikhoan where tenTaiKhoan=? and matKhau=?  ", new String[]{tenTaiKhoan, matKhau});
        if (cursor.getCount () != 0) {
            return true;
        } else {
            return false;
        }
    }
    public void registerTaiKhoan(String tenTaiKhoan, String hoTen, String matKhau) {
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        sqLiteDatabase.execSQL("INSERT INTO taikhoan (tenTaiKhoan, hoTen, matKhau) VALUES (?, ?, ?)", new String[]{tenTaiKhoan, hoTen, matKhau});
        sqLiteDatabase.close();
    }

    public boolean checkTaiKhoanTonTai(String tenTaiKhoan) {
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME_TAIKHOAN + " WHERE tenTaiKhoan=?", new String[]{tenTaiKhoan});
        boolean taiKhoanTonTai = cursor.getCount() > 0;
        cursor.close();
        return taiKhoanTonTai;
    }

    @SuppressLint("Range")
    public int getUserIdByUsername(String username) {
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT id FROM " + TABLE_NAME_TAIKHOAN + " WHERE tenTaiKhoan=?", new String[]{username});
        int userId = -1; // Giá trị mặc định nếu không tìm thấy người dùng
        if (cursor.moveToFirst()) {
            userId = cursor.getInt(cursor.getColumnIndex("id"));
        }
        cursor.close();
        return userId;
    }

    public Cursor getUserById(int userId) {
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME_TAIKHOAN + " WHERE id=?", new String[]{String.valueOf(userId)});
        return cursor;
    }

    public List<TaiKhoan> getAllNguoiDung() {
        String sql = "SELECT * FROM " + TABLE_NAME_TAIKHOAN;
        return getData(sql);
    }

    public int deleteTaiKhoan(int id) {
        openDatabase();
        return sqLiteDatabase.delete(TABLE_NAME_TAIKHOAN, "id=?", new String[]{String.valueOf(id)});
    }

    @SuppressLint("Range")
    private List<TaiKhoan> getData(String sql, String... selectionArgs) {
        List<TaiKhoan> listNguoiDung = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            TaiKhoan taiKhoan = new TaiKhoan();
            taiKhoan.setId(cursor.getInt(cursor.getColumnIndex("id")));
            taiKhoan.setTenTaiKhoan(cursor.getString(cursor.getColumnIndex("tenTaiKhoan")));
            taiKhoan.setHoTen(cursor.getString(cursor.getColumnIndex("hoTen")));
            listNguoiDung.add(taiKhoan);
        }
        cursor.close();
        return listNguoiDung;
    }
}
