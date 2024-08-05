package com.example.duan1_moviewwbooking.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_moviewwbooking.R;
import com.example.duan1_moviewwbooking.adapter.VePhimAdapter;
import com.example.duan1_moviewwbooking.dao.TaiKhoanDao;
import com.example.duan1_moviewwbooking.dao.VePhimDao;
import com.example.duan1_moviewwbooking.model.VePhim;

import java.util.ArrayList;

public class VePhimFragment extends Fragment {
    RecyclerView rcvVePhim;
    VePhimAdapter vePhimAdapter;

    VePhimDao vePhimDao;
    ArrayList<VePhim> arrVePhim = new ArrayList<>();
    Context mContext;
    LayoutInflater inflater;
    SharedPreferences sharedPreferences;
    TaiKhoanDao taiKhoanDao;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ve_phim, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rcvVePhim = view.findViewById(R.id.rcv_vephim);

        inflater = getLayoutInflater();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rcvVePhim.setLayoutManager(layoutManager);
        vePhimDao = new VePhimDao(mContext);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext()); // Updated usage
        String username = sharedPreferences.getString("username", "");

        if (username.equals("admin")) {
            arrVePhim = (ArrayList<VePhim>) vePhimDao.getAllVePhim();
        } else {
            taiKhoanDao = new TaiKhoanDao(getContext());
            int userId = taiKhoanDao.getUserIdByUsername(username);
            arrVePhim = (ArrayList<VePhim>) vePhimDao.getVePhimByIdUser(userId);
        }

        vePhimAdapter = new VePhimAdapter(mContext, arrVePhim);
        rcvVePhim.setAdapter(vePhimAdapter);
    }
}
