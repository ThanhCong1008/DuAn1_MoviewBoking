package com.example.duan1_moviewwbooking.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.duan1_moviewwbooking.R;
import com.example.duan1_moviewwbooking.SliderItem;
import com.example.duan1_moviewwbooking.activity.DanhSachPhimActivity;
import com.example.duan1_moviewwbooking.activity.PhimActivity;
import com.example.duan1_moviewwbooking.adapter.PhimAdapter;
import com.example.duan1_moviewwbooking.adapter.SliderAdapter;
import com.example.duan1_moviewwbooking.dao.PhimDao;
import com.example.duan1_moviewwbooking.model.Phim;

import java.util.ArrayList;
import java.util.List;

public class TrangChuFragment extends Fragment {
    private ViewPager2 viewPager2;
    private Handler sliderHandler = new Handler(Looper.getMainLooper());
    SliderAdapter sliderAdapter;
    RecyclerView rcv_phim;
    LayoutInflater inflater;
    Context mContext;

    ArrayList<Phim> arrPhim = new ArrayList<>();
    PhimDao phimDao;
    PhimAdapter phimAdapter;
    TextView tvXemThem, tvXinChaoUser;
    SharedPreferences sharedPreferences;
    ImageView img_iconuser;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trang_chu, container, false);
        viewPager2 = view.findViewById(R.id.banner);
        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.banner, "https://www.cgv.vn/"));
        sliderItems.add(new SliderItem(R.drawable.banner01, "https://www.cgv.vn/"));
        sliderItems.add(new SliderItem(R.drawable.banner02, "https://www.cgv.vn/"));
        sliderAdapter = new SliderAdapter(sliderItems, viewPager2);
        viewPager2.setAdapter(sliderAdapter);

        sliderAdapter.setCallBack(new SliderAdapter.OncallBack() {
            @Override
            public void onclick(String url) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(50));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 3000);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tvXinChaoUser = view.findViewById(R.id.tv_xinchao_user);
        img_iconuser = view.findViewById(R.id.img_iconuser);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String username = sharedPreferences.getString("username", "");

        if (username.isEmpty()) {
            tvXinChaoUser.setText("MovieTicketBooking");
            img_iconuser.setVisibility(View.GONE);
        } else {
            tvXinChaoUser.setText("Xin chào, " + username);
        }

        rcv_phim = view.findViewById(R.id.rcv_phim);
        inflater = getLayoutInflater();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        rcv_phim.setLayoutManager(layoutManager);
        phimDao = new PhimDao(mContext);
        arrPhim = (ArrayList<Phim>) phimDao.getAllPhim();
        phimAdapter = new PhimAdapter(mContext, arrPhim);
        rcv_phim.setAdapter(phimAdapter);

        phimAdapter.setOnItemClickListener(new PhimAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Phim phim = arrPhim.get(position);
                Intent intent = new Intent(getContext(), PhimActivity.class);
                intent.putExtra("phim_id", phim.getId());
                startActivity(intent);
            }
        });

        tvXemThem = view.findViewById(R.id.tv_xemthem);

        tvXemThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DanhSachPhimActivity.class);
                startActivity(intent);
            }
        });
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 3000);
    }
}
