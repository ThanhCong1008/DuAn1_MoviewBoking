package com.example.duan1_moviewwbooking.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_moviewwbooking.R;
import com.example.duan1_moviewwbooking.dao.TaiKhoanDao;
import com.example.duan1_moviewwbooking.model.TaiKhoan;

import java.util.ArrayList;

public class QuanLyNguoiDungAdapter extends RecyclerView.Adapter<QuanLyNguoiDungAdapter.QuanLyNguoiDungViewHolder> {
    ArrayList<TaiKhoan> arrNguoiDung = new ArrayList<>();
    Context context;

    public QuanLyNguoiDungAdapter(Context context, ArrayList<TaiKhoan> arrNguoiDung) {
        this.context = context;
        this.arrNguoiDung = arrNguoiDung;
    }


    @NonNull
    @Override
    public QuanLyNguoiDungViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nguoi_dung, parent, false);
        return new QuanLyNguoiDungViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull QuanLyNguoiDungViewHolder holder, @SuppressLint("RecyclerView") int position) {
        TaiKhoan nguoidung = arrNguoiDung.get(position);
        holder.tvTenNguoiDung.setText(nguoidung.getTenTaiKhoan());
        TaiKhoanDao taiKhoanDao = new TaiKhoanDao(context);
        holder.imgDeleteNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa vé này?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TaiKhoan taiKhoanToDelete = arrNguoiDung.get(position);
                        int nguoiDungID = taiKhoanToDelete.getId();
                        taiKhoanDao.deleteTaiKhoan(nguoiDungID);
                        arrNguoiDung.remove(position);
                        Toast.makeText(context, "Xoá thành công!", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Hủy", null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }


    public class QuanLyNguoiDungViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTenNguoiDung;
        private ImageView imgDeleteNguoiDung;

        public QuanLyNguoiDungViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenNguoiDung = itemView.findViewById(R.id.tv_tennguoidung);
            imgDeleteNguoiDung = itemView.findViewById(R.id.img_delete_user);


        }

    }
    @Override
    public int getItemCount() {
        return arrNguoiDung.size();
    }
}
