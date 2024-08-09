package com.example.duan1_moviewwbooking.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_moviewwbooking.R;
import com.example.duan1_moviewwbooking.activity.GheNgoiActivity;
import com.example.duan1_moviewwbooking.model.RapChieu;
import com.example.duan1_moviewwbooking.model.XuatChieu;

import java.util.ArrayList;
import java.util.List;

public class XuatChieuAdapter extends RecyclerView.Adapter<XuatChieuAdapter.XuatChieuViewHolder> {
    ArrayList<XuatChieu> arrXuatChieu = new ArrayList<>();
    List<RapChieu> arrRapChieu = new ArrayList<>();

    Context context;



    public XuatChieuAdapter(Context context, ArrayList<XuatChieu> arrXuatChieu, List<RapChieu> arrRapChieu) {
        this.context = context;
        this.arrXuatChieu = arrXuatChieu;
        this.arrRapChieu = arrRapChieu;
    }



    public void updateData(List<XuatChieu> danhSachXuatChieuMoi) {
        arrXuatChieu.clear();
        arrXuatChieu.addAll(danhSachXuatChieuMoi);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public XuatChieuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_xuatchieu, parent, false);
        return new XuatChieuViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull XuatChieuViewHolder holder, @SuppressLint("RecyclerView") int position) {
        XuatChieu xuatChieu = arrXuatChieu.get(position);
        holder.tvThoiGianBatDau.setText(xuatChieu.getThoiGianBatDau());
        holder.tvThoiGianBatDau.setOnClickListener(new View.OnClickListener() {
            private static final int DOUBLE_CLICK_DELAY = 300; // Đặt thời gian chờ giữa các lần nhấp đúp
            private long lastClickTime = 0;


            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onXuatChieuClick(xuatChieu);

                }

                long clickTime = System.currentTimeMillis();
                if (clickTime - lastClickTime < DOUBLE_CLICK_DELAY) {
                    // Chuyển sang màn hình ghe ngoi và truyền dữ liệu xuat chieu
                    XuatChieu xuatChieu1 = arrXuatChieu.get(position);
                    RapChieu rapChieu = arrRapChieu.get(position);

                    Intent intent = new Intent(context, GheNgoiActivity.class);
                    intent.putExtra("xuatchieu_id", xuatChieu1.getId());
                    intent.putExtra("rapchieu_id", rapChieu.getId());

                    context.startActivity(intent);
                }
                lastClickTime = clickTime;
            }
        });

    }


    public static class XuatChieuViewHolder extends RecyclerView.ViewHolder {
        TextView tvThoiGianBatDau;

        public XuatChieuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvThoiGianBatDau = itemView.findViewById(R.id.tv_thoigianbatdau);

        }
    }

    public interface OnXuatChieuClickListener {
        void onXuatChieuClick(XuatChieu xuatChieu);
    }

    private OnXuatChieuClickListener clickListener;

    public void setOnXuatChieuClickListener(OnXuatChieuClickListener clickListener) {
        this.clickListener = clickListener;
    }
    @Override
    public int getItemCount() {
        return arrXuatChieu.size();
    }
}
