package com.animedxd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    private ConstraintLayout mainLayout;
    private ImageButton menuButton;
    private ImageView closeDropdown;
    private ConstraintLayout dropdownMenu;
    private TextView logoutText;
    private TextView greetingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        // Window insets untuk edge-to-edge layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi komponen UI
        mainLayout = findViewById(R.id.main);
        menuButton = findViewById(R.id.menuButton);
        closeDropdown = findViewById(R.id.closeDropdown);
        dropdownMenu = findViewById(R.id.dropdownMenu);
        logoutText = findViewById(R.id.logoutText);
        greetingText = findViewById(R.id.greetingText);

        // Ambil username dari SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "User");
        greetingText.setText("Welcome, " + username + "!");

        // Tampilkan dropdown menu saat menuButton diklik
        menuButton.setOnClickListener(v -> {
            dropdownMenu.setVisibility(View.VISIBLE);
        });

        // Sembunyikan dropdown saat tombol X ditekan
        closeDropdown.setOnClickListener(v -> {
            dropdownMenu.setVisibility(View.GONE);
        });

        // Logout ke halaman login
        logoutText.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        // Sembunyikan dropdown saat area luar diklik
        mainLayout.setOnTouchListener((v, event) -> {
            if (dropdownMenu.getVisibility() == View.VISIBLE) {
                dropdownMenu.setVisibility(View.GONE);
                return true;
            }
            return false;
        });
    }
}
