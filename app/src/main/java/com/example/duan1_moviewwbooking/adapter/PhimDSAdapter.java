package com.example.duan1_moviewwbooking.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_moviewwbooking.R;
import com.example.duan1_moviewwbooking.activity.PhimActivity;
import com.example.duan1_moviewwbooking.dao.PhimDao;
import com.example.duan1_moviewwbooking.model.Phim;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PhimDSAdapter extends RecyclerView.Adapter<PhimDSAdapter.PhimViewHolder> {
    private final ArrayList<Phim> arrPhim;
    private final Context context;

    public PhimDSAdapter(Context context, ArrayList<Phim> arrPhim) {
        this.context = context;
        this.arrPhim = arrPhim;
    }

    public void setFilter(List<Phim> filteredList) {
        arrPhim.clear();
        arrPhim.addAll(filteredList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhimViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_phim_atv_danhsachphim, parent, false);
        return new PhimViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhimViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Phim phim = arrPhim.get(position);
        holder.tvTenPhim.setText(phim.getTenPhim());
        holder.tvDoTuoi.setText("P-G " + phim.getDoTuoiXem() + "+");
        holder.tvThoiLuong.setText(phim.getThoiLuong() + " min");

        Picasso.get().load(phim.getAnhDaiDien())
                .into(holder.avtPhim); // Removed placeholder and error image

        holder.tvTenPhim.setOnClickListener(v -> openPhimActivity(phim));
        holder.avtPhim.setOnClickListener(v -> openPhimActivity(phim));
        holder.btnDatVe.setOnClickListener(v -> openPhimActivity(phim));

        holder.imgDeletePhim.setOnClickListener(v -> showDeleteConfirmationDialog(position));
    }

    private void openPhimActivity(Phim phim) {
        Intent intent = new Intent(context, PhimActivity.class);
        intent.putExtra("phim_id", phim.getId());
        context.startActivity(intent);
    }

    private void showDeleteConfirmationDialog(int position) {
        new AlertDialog.Builder(context)
                .setTitle("Xác nhận xóa")
                .setMessage("Bạn có chắc chắn muốn xóa phim này?")
                .setPositiveButton("OK", (dialog, which) -> {
                    Phim phimToDelete = arrPhim.get(position);
                    PhimDao phimDao = new PhimDao(context);
                    phimDao.deletePhim(phimToDelete.getId());
                    arrPhim.remove(position);
                    notifyItemRemoved(position); // Better to use notifyItemRemoved for specific position
                    Toast.makeText(context, "Xoá thành công!", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Hủy", null)
                .create()
                .show();
    }

    @Override
    public int getItemCount() {
        return arrPhim.size();
    }

    public static class PhimViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTenPhim, tvDoTuoi, tvThoiLuong;
        private final ImageView avtPhim, imgDeletePhim;
        private final AppCompatButton btnDatVe;
        private final SharedPreferences sharedPreferences;

        public PhimViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenPhim = itemView.findViewById(R.id.tv_tenphim_atv_danhsachphim);
            tvDoTuoi = itemView.findViewById(R.id.tv_dotuoi_atv_danhsachphim);
            tvThoiLuong = itemView.findViewById(R.id.tv_thoiluong_atv_danhsachphim);
            avtPhim = itemView.findViewById(R.id.img_anhdaidien_atv_danhsachphim);
            btnDatVe = itemView.findViewById(R.id.btn_datve_atv_danhsachphim);
            imgDeletePhim = itemView.findViewById(R.id.img_delete_phim);

            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(itemView.getContext());
            String username = sharedPreferences.getString("username", "");
            if (!"admin".equals(username)) {
                imgDeletePhim.setVisibility(View.GONE);
            } else {
                btnDatVe.setVisibility(View.GONE);
            }
        }
    }
}
