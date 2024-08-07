package com.example.duan1_moviewwbooking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_moviewwbooking.R;
import com.example.duan1_movietvbooking.model.GheNgoi;

import java.util.ArrayList;

public class GheNgoiAdapter extends RecyclerView.Adapter<GheNgoiAdapter.GheNgoiViewHolder> {
    private final ArrayList<GheNgoi> arrGheNgoi;
    private final Context context;
    private OnGheNgoiClickListener clickListener;

    public GheNgoiAdapter(Context context, ArrayList<GheNgoi> arrGheNgoi) {
        this.context = context;
        this.arrGheNgoi = arrGheNgoi;
    }

    @NonNull
    @Override
    public GheNgoiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ghe_ngoi, parent, false);
        return new GheNgoiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GheNgoiViewHolder holder, int position) {
        GheNgoi gheNgoi = arrGheNgoi.get(position);
        holder.tvSoGhe.setText(gheNgoi.getSoGhe());

        final int[] color = new int[1];

        switch (gheNgoi.getTrangThai()) {
            case 2:
                color[0] = ContextCompat.getColor(context, R.color.daDat);
                holder.tvSoGhe.setBackgroundColor(color[0]);
                break;
            case 0:
                color[0] = ContextCompat.getColor(context, R.color.chuaDat);
                holder.tvSoGhe.setBackgroundColor(color[0]);
                break;
            case 1:
                color[0] = ContextCompat.getColor(context, R.color.dangChon);
                holder.tvSoGhe.setBackgroundColor(color[0]);
                break;
            default:
                color[0] = ContextCompat.getColor(context, R.color.white);
                holder.tvSoGhe.setBackgroundColor(color[0]);
                break;
        }

        holder.tvSoGhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.onGheNgoiClick(gheNgoi);
                }

                switch (gheNgoi.getTrangThai()) {
                    case 0: // Ghế trống
                        color[0] = ContextCompat.getColor(context, R.color.dangChon);
                        gheNgoi.setTrangThai(1);
                        break;
                    case 1: // Ghế đang chọn
                        color[0] = ContextCompat.getColor(context, R.color.chuaDat);
                        gheNgoi.setTrangThai(0);
                        break;
                    case 2: // Ghế đã đặt
                        return;
                    default:
                        color[0] = ContextCompat.getColor(context, R.color.white);
                        break;
                }
                holder.tvSoGhe.setBackgroundColor(color[0]);
            }
        });
    }

    public class GheNgoiViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvSoGhe;

        public GheNgoiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSoGhe = itemView.findViewById(R.id.tv_soghe);
        }
    }

    public interface OnGheNgoiClickListener {
        void onGheNgoiClick(GheNgoi gheNgoi);
    }

    public void setOnGheNgoiClickListener(OnGheNgoiClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return arrGheNgoi.size();
    }
}
