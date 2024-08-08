package com.example.duan1_moviewwbooking.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_moviewwbooking.R;
import com.example.duan1_moviewwbooking.activity.ThongTinVeActivity;
import com.example.duan1_moviewwbooking.dao.RapChieuDao;
import com.example.duan1_moviewwbooking.dao.VePhimDao;
import com.example.duan1_moviewwbooking.dao.XuatChieuDao;
import com.example.duan1_moviewwbooking.model.RapChieu;
import com.example.duan1_moviewwbooking.model.VePhim;
import com.example.duan1_moviewwbooking.model.XuatChieu;

import java.util.ArrayList;

public class VePhimAdapter extends RecyclerView.Adapter<VePhimAdapter.VePhimViewHolder> {
    ArrayList<VePhim> arrVePhim = new ArrayList<>();
    Context context;

    public VePhimAdapter(Context context, ArrayList<VePhim> arrVePhim) {
        this.context = context;
        this.arrVePhim = arrVePhim;
    }

    @NonNull
    @Override
    public VePhimViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vephim, parent, false);
        return new VePhimViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VePhimViewHolder holder, @SuppressLint("RecyclerView") int position) {
        VePhim vePhim = arrVePhim.get(position);
        holder.tvMaVe.setText(vePhim.getId() + "-TICKET");

        int xuatChieuID = vePhim.getIdXuatChieu();
        holder.xuatChieuDao = new XuatChieuDao(context);
        XuatChieu xuatChieu = holder.xuatChieuDao.getXuatChieuById(xuatChieuID);
        int rapChieuID = xuatChieu.getIdRapChieu();
        holder.rapChieuDao = new RapChieuDao(context);
        RapChieu rapChieu = holder.rapChieuDao.getRapChieuById(rapChieuID);
        holder.tvNgayChieu.setText(rapChieu.getNgayChieu());
        VePhimDao vePhimDao = new VePhimDao(context);

        holder.lnlVePhim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VePhim vePhim1 = arrVePhim.get(position);
                Intent intent = new Intent(context, ThongTinVeActivity.class);
                intent.putExtra("vephim_id", vePhim1.getId());
                context.startActivity(intent);
            }
        });

        holder.imgDeleteVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa vé này?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        VePhim vePhimToDelete = arrVePhim.get(position);
                        int vePhimId = vePhimToDelete.getId();
                        vePhimDao.deleteVePhim(vePhimId);
                        arrVePhim.remove(position);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Hủy", null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public class VePhimViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMaVe, tvNgayChieu, tvTongGia;
        private ImageView imgDeleteVe;
        private LinearLayout lnlVePhim;
        private RapChieuDao rapChieuDao;
        private XuatChieuDao xuatChieuDao;
        private SharedPreferences sharedPreferences;

        public VePhimViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaVe = itemView.findViewById(R.id.tv_mave);
            tvNgayChieu = itemView.findViewById(R.id.tv_ngaychieu_atv_ve);
            tvTongGia = itemView.findViewById(R.id.tv_tonggia_atv_quanlyve);
            lnlVePhim = itemView.findViewById(R.id.lnl_vephim);
            imgDeleteVe = itemView.findViewById(R.id.img_delete_ve);

            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context); // Updated usage
            String username = sharedPreferences.getString("username", "");
            if (!"admin".equals(username)) {
                imgDeleteVe.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return arrVePhim.size();
    }
}
