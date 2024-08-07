package com.example.duan1_moviewwbooking.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.duan1_moviewwbooking.R;
import com.example.duan1_moviewwbooking.SliderItem;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder>{

    private List<SliderItem> sliderItem;
    private ViewPager2 viewPager2;

    OncallBack oncallBack;
    public SliderAdapter(List<SliderItem> sliderItem, ViewPager2 viewPager2) {
        this.sliderItem = sliderItem;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.slider_item_container,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        SliderItem sliderItem1 = sliderItem.get(position);
        holder.setImage(sliderItem.get(position));
        if (position == sliderItem.size() - 2) {
            viewPager2.post(runnable);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oncallBack.onclick(sliderItem1.getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return sliderItem.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder{
        private RoundedImageView imageView;

        SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlider);
        }

        void  setImage(SliderItem sliderItem) {
            imageView.setImageResource(sliderItem.getImage());
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            sliderItem.addAll(sliderItem);
            notifyDataSetChanged();
        }
    };
    public interface OncallBack{
        void onclick(String url);
    }
    public  void setCallBack(OncallBack oncallBack) {
        this.oncallBack = oncallBack;
    }
}
