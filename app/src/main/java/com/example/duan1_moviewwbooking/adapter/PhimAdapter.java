package com.example.duan1_moviewwbooking.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_moviewwbooking.R;
import com.example.duan1_moviewwbooking.model.Phim;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhimAdapter extends RecyclerView.Adapter<PhimAdapter.PhimViewHolder> {
    ArrayList<Phim> arrPhim = new ArrayList<>();
    Context context;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public PhimAdapter(Context context, ArrayList<Phim> arrPhim) {
        this.context = context;
        this.arrPhim = arrPhim;
    }

    @NonNull
    @Override
    public PhimViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phim_avt_trangchu, parent, false);
        return new PhimViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull PhimViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Phim phim = arrPhim.get(position);
        holder.tvTenPhim.setText(phim.getTenPhim());

        Picasso.get().load(phim.getAnhDaiDien()).into(holder.avtPhim);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClick(position);
                }
            }
        });
    }



    public class PhimViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTenPhim;
        private ImageView avtPhim;

        public PhimViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenPhim = itemView.findViewById(R.id.tv_tenphim);
            avtPhim = itemView.findViewById(R.id.avt_phim);
        }

    }
    @Override
    public int getItemCount() {
        return arrPhim.size();
    }
}
