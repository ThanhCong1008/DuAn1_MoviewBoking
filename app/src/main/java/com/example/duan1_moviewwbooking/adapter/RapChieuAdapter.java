package com.example.duan1_moviewwbooking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_movieticketbooking.R;
import com.example.duan1_movieticketbooking.dao.XuatChieuDao;
import com.example.duan1_movieticketbooking.model.RapChieu;
import com.example.duan1_movieticketbooking.model.XuatChieu;

import java.util.ArrayList;
import java.util.List;

public class RapChieuAdapter extends RecyclerView.Adapter<RapChieuAdapter.RapChieuViewHolder> {
    List<RapChieu> arrRapChieu = new ArrayList<>();
    Context context;
    ArrayList<XuatChieu> arrXuatChieu = new ArrayList<>();


    public RapChieuAdapter(Context context, List<RapChieu> arrRapChieu) {
        this.context = context;
        this.arrRapChieu = arrRapChieu;
    }


    @NonNull
    @Override
    public RapChieuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rapchieu, parent, false);
        return new RapChieuViewHolder(view);
    }

    @NonNull

    @Override
    public void onBindViewHolder(@NonNull RapChieuViewHolder holder, int position) {
        RapChieu rapChieu = arrRapChieu.get(position);
        holder.tvTenRap.setText(rapChieu.getTenRap());

        holder.xuatChieuAdapter = new XuatChieuAdapter(holder.mContext, arrXuatChieu, arrRapChieu);
        holder.rcvXuatChieu.setAdapter(holder.xuatChieuAdapter);
        holder.xuatChieuAdapter.setOnXuatChieuClickListener(new XuatChieuAdapter.OnXuatChieuClickListener() {
            @Override
            public void onXuatChieuClick(XuatChieu xuatChieu) {
                holder.tvGiaVe.setText(xuatChieu.getGiaVe() + " VND");
            }
        });

        // Lấy danh sách xuất chiếu dựa trên idRapChieu
        XuatChieuDao xuatChieuDao = new XuatChieuDao(context);
        List<XuatChieu> listXuatChieu = xuatChieuDao.getXuatChieuByIdRapChieu(rapChieu.getId());
        holder.xuatChieuAdapter.updateData(listXuatChieu);
    }


    public class RapChieuViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTenRap, tvGiaVe;
        private LinearLayout lnlItemRap;
        private ImageView imgDropdowRapChieu;
        private boolean isItemVisible;
        private RecyclerView rcvXuatChieu;
        private XuatChieuAdapter xuatChieuAdapter;

        private XuatChieuDao xuatChieuDao;
        private LayoutInflater inflater;
        private Context mContext;


        public RapChieuViewHolder(@NonNull View itemView) {
            super(itemView);

            mContext = context;
            tvTenRap = itemView.findViewById(R.id.tv_tenrap);
            tvGiaVe = itemView.findViewById(R.id.tv_giave);
            lnlItemRap = itemView.findViewById(R.id.lnl_xuatchieu);
            imgDropdowRapChieu = itemView.findViewById(R.id.img_dropdow_rapchieu);
            rcvXuatChieu = itemView.findViewById(R.id.rcv_xuatchieu);

            lnlItemRap.setVisibility(View.GONE);
            isItemVisible = false;
            imgDropdowRapChieu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isItemVisible) {
                        // Ẩn lnlItemRap nếu đang hiển thị
                        lnlItemRap.setVisibility(View.GONE);
                        isItemVisible = false;
                    } else {
                        // Hiển thị lnlItemRap nếu đang ẩn
                        lnlItemRap.setVisibility(View.VISIBLE);
                        isItemVisible = true;
                    }
                }
            });

            inflater = LayoutInflater.from(mContext);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            rcvXuatChieu.setLayoutManager(layoutManager);
            xuatChieuDao = new XuatChieuDao(mContext);
            arrXuatChieu = (ArrayList<XuatChieu>) xuatChieuDao.getAllXuatChieu();


        }

    }


    public void updateData(List<RapChieu> danhSachRapMoi) {
        arrRapChieu.clear();
        arrRapChieu.addAll(danhSachRapMoi);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return arrRapChieu.size();
    }
}