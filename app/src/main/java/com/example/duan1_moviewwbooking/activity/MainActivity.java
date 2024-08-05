package com.example.duan1_moviewwbooking.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.duan1_moviewwbooking.R;
import com.example.duan1_moviewwbooking.databinding.ActivityMainBinding;
import com.example.duan1_moviewwbooking.fragment.TaiKhoanFragment;
import com.example.duan1_moviewwbooking.fragment.TrangChuFragment;
import com.example.duan1_moviewwbooking.fragment.VePhimFragment;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new TrangChuFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                replaceFragment(new TrangChuFragment());
            } else if (item.getItemId() == R.id.my_ticket) {
                replaceFragment(new VePhimFragment());
            } else if (item.getItemId() == R.id.account) {
                replaceFragment(new TaiKhoanFragment());
            }
            return true;
        });

        // Initialize executor service
        executorService = Executors.newSingleThreadExecutor();

        // Execute heavy task
        executorService.execute(new HeavyTask());
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    private class HeavyTask implements Runnable {
        @Override
        public void run() {
            // Perform heavy task here
            try {
                // Simulate a heavy task
                for (int i = 0; i <= 100; i += 10) {
                    final int progress = i;
                    runOnUiThread(() -> {
                        // Update UI with progress
                        // Example: progressBar.setProgress(progress);
                    });
                    Thread.sleep(500); // Simulate work
                }

                // Update UI after task is completed
                runOnUiThread(() -> {
                    // Final UI update code here
                });
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                // Handle the interruption
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}
