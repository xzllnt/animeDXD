package com.animedxd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mainLayout = findViewById(R.id.main);
        menuButton = findViewById(R.id.menuButton);
        closeDropdown = findViewById(R.id.closeDropdown);
        dropdownMenu = findViewById(R.id.dropdownMenu);
        logoutText = findViewById(R.id.logoutText);
        greetingText = findViewById(R.id.greetingText);

        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "User");
        greetingText.setText("Welcome, " + username + "!");

        menuButton.setOnClickListener(v -> {
            dropdownMenu.setVisibility(View.VISIBLE);
        });

        closeDropdown.setOnClickListener(v -> {
            dropdownMenu.setVisibility(View.GONE);
        });

        logoutText.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        mainLayout.setOnTouchListener((v, event) -> {
            if (dropdownMenu.getVisibility() == View.VISIBLE) {
                dropdownMenu.setVisibility(View.GONE);
                return true;
            }
            return false;
        });

//        ImageView homeIcon = findViewById(R.id.homeIcon);
//        ImageView bookIcon = findViewById(R.id.bookIcon);
//        ImageView infoIcon = findViewById(R.id.infoIcon);
//
//// Atur default: home aktif
//        setActiveNav(homeIcon);
//        setInactiveNav(bookIcon);
//        setInactiveNav(infoIcon);
//
//        homeIcon.setOnClickListener(v -> {
//            setActiveNav(homeIcon);
//            setInactiveNav(bookIcon);
//            setInactiveNav(infoIcon);
//            // pindah halaman jika perlu
//        });
//
//        bookIcon.setOnClickListener(v -> {
//            setActiveNav(bookIcon);
//            setInactiveNav(homeIcon);
//            setInactiveNav(infoIcon);
//            startActivity(new Intent(HomeActivity.this, List.class));
//        });
//
//        infoIcon.setOnClickListener(v -> {
//            setActiveNav(infoIcon);
//            setInactiveNav(homeIcon);
//            setInactiveNav(bookIcon);
//            startActivity(new Intent(HomeActivity.this, About.class));
//        });


//        Navbar
//        ImageView homeIcon = findViewById(R.id.homeIcon);
//        ImageView bookIcon = findViewById(R.id.bookIcon);
//        ImageView infoIcon = findViewById(R.id.infoIcon);
//
//        homeIcon.setOnClickListener(v -> {
//            if (!(this instanceof HomeActivity)) {
//                startActivity(new Intent(this, HomeActivity.class));
//                finish();
//            }
//        });
//        bookIcon.setOnClickListener(v -> {
//            if (!(this instanceof ListActivity)) {
//                startActivity(new Intent(this, ListActivity.class));
//                finish();
//            }
//        });
//        infoIcon.setOnClickListener(v -> {
//            if (!(this instanceof AboutActivity)) {
//                startActivity(new Intent(this, AboutActivity.class));
//                finish();
//            }
//        });

    }


}
