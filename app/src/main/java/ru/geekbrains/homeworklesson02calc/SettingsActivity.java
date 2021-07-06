package ru.geekbrains.homeworklesson02calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button buttonSelect = findViewById(R.id.buttonSelectTheme);
        buttonSelect.setOnClickListener(view -> {
            finish();
        });
    }
}