package ru.geekbrains.homeworklesson02calc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class SettingsActivity extends AppCompatActivity {

    private int selectedTheme = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        RadioButton radioButtonMyTheme = findViewById(R.id.radioButtonMyTheme);
        radioButtonMyTheme.setOnClickListener(radioButtonClickListener);

        RadioButton radioButtonMyThemeDark = findViewById(R.id.radioButtonMyThemeDark);
        radioButtonMyThemeDark.setOnClickListener(radioButtonClickListener);

        Button buttonSelect = findViewById(R.id.buttonSelectTheme);
        buttonSelect.setOnClickListener(view -> {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("selectedTheme", selectedTheme);
            setResult(RESULT_OK, returnIntent);
            finish();
        });
    }

    View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RadioButton radioButton = (RadioButton) view;
            switch (view.getId()) {
                case R.id.radioButtonMyTheme:
                    selectedTheme = 0;
                    break;
                case R.id.radioButtonMyThemeDark:
                    selectedTheme = 1;
                    break;
            }
        }
    };
}