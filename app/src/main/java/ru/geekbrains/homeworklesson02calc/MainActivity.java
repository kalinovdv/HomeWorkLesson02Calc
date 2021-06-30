package ru.geekbrains.homeworklesson02calc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.material.radiobutton.MaterialRadioButton;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private static final String KEY = "CALKULATION";

    private static final String NAME_SHARED_PREFERENCE = "MYCALC";
    private static final String THEME = "MYTHEME";

    private static final int MY_THEME = 0;
    private static final int MY_THEME_DARK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences(NAME_SHARED_PREFERENCE, MODE_PRIVATE);
        int code = sharedPreferences.getInt(THEME, MY_THEME);
        switch (code){
            case MY_THEME:
                setTheme(R.style.MyTheme);
                break;
            case MY_THEME_DARK:
                setTheme(R.style.MyThemeDark);
                break;
        }

        setContentView(R.layout.activity_main);

        initView();
        initButtons();

        MaterialRadioButton radioButtonMyTheme = findViewById(R.id.radioButtonMyTheme);
        radioButtonMyTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(NAME_SHARED_PREFERENCE, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(THEME, MY_THEME);
                editor.apply();
                recreate();
            }
        });

        MaterialRadioButton radioButtonMyThemeDark = findViewById(R.id.radioButtonMyThemeDark);
        radioButtonMyThemeDark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(NAME_SHARED_PREFERENCE, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(THEME, MY_THEME_DARK);
                editor.apply();
                recreate();
            }
        });
    }

    private void initView(){
        textView = findViewById(R.id.TextView);
    }

    private void initButtons(){

        ConstraintLayout constraintLayout = findViewById(R.id.myLayout);

        for (int i = 0; i < constraintLayout.getChildCount(); i++) {
            View view = constraintLayout.getChildAt(i);
            if (view instanceof Button){
                view.setOnClickListener(this);
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button0:
                textView.setText("Нажата кнопка 0");
                break;
            case R.id.button1:
                textView.setText("Нажата кнопка 1");
                break;
            case R.id.button2:
                textView.setText("Нажата кнопка 2");
                break;
            case R.id.button3:
                textView.setText("Нажата кнопка 3");
                break;
            case R.id.button4:
                textView.setText("Нажата кнопка 4");
                break;
            case R.id.button5:
                textView.setText("Нажата кнопка 5");
                break;
            case R.id.button6:
                textView.setText("Нажата кнопка 6");
                break;
            case R.id.button7:
                textView.setText("Нажата кнопка 7");
                break;
            case R.id.button8:
                textView.setText("Нажата кнопка 8");
                break;
            case R.id.button9:
                textView.setText("Нажата кнопка 9");
                break;
            case R.id.buttonAddition:
                textView.setText("Нажата кнопка +");
                break;
            case R.id.buttonSubtraction:
                textView.setText("Нажата кнопка -");
                break;
            case R.id.buttonМultiplication:
                textView.setText("Нажата кнопка *");
                break;
            case R.id.buttonDivision:
                textView.setText("Нажата кнопка /");
                break;
            case R.id.buttonEqually:
                textView.setText("Нажата кнопка =");
                break;
            case R.id.buttonDot:
                textView.setText("Нажата кнопка .");
                break;
            case R.id.buttonClear:
                textView.setText("0");
                break;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putString(KEY, (String) textView.getText());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        String s = (String) instanceState.getString(KEY);
        textView.setText(s);
    }


}