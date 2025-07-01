package com.animedxd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    public static String globalUsername = "";

    private TextInputEditText usernameInput, passwordInput;
    private TextInputLayout textInputLayout, passwordLayout;
    private TextView errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi UI
        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);
        textInputLayout = findViewById(R.id.textInputLayout);
        passwordLayout = findViewById(R.id.passwordLayout);
        errorMessage = findViewById(R.id.errorMessage);
        MaterialButton loginButton = findViewById(R.id.button);

        setupHintBehavior(usernameInput, textInputLayout, "Your Username");
        setupHintBehavior(passwordInput, passwordLayout, "Your Password");

        loginButton.setOnClickListener(v -> {
            String username = usernameInput.getText() != null ? usernameInput.getText().toString().trim() : "";
            String password = passwordInput.getText() != null ? passwordInput.getText().toString().trim() : "";

            StringBuilder errorBuilder = new StringBuilder();

            if (username.isEmpty()) {
                errorBuilder.append("Username must be filled\n");
            } else if (username.length() < 5 || username.length() > 10) {
                errorBuilder.append("Username must be 5 - 10 characters\n");
            }

            if (password.isEmpty()) {
                errorBuilder.append("Password must be filled");
            }

            if (errorBuilder.length() > 0) {
                errorMessage.setText(errorBuilder.toString().trim());
                errorMessage.setVisibility(View.VISIBLE);
            } else {
                errorMessage.setVisibility(View.GONE);

                // Simpan username ke global & SharedPreferences
                globalUsername = username;

                SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", username);
                editor.apply();

                // Pindah ke HomeActivity
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setupHintBehavior(TextInputEditText input, TextInputLayout layout, String hint) {
        input.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus || !input.getText().toString().isEmpty()) {
                layout.setHint(null);
            } else {
                layout.setHint(hint);
            }
        });

        input.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (input.hasFocus() || !s.toString().isEmpty()) {
                    layout.setHint(null);
                } else {
                    layout.setHint(hint);
                }
            }
            @Override public void afterTextChanged(Editable s) {}
        });
    }
}
