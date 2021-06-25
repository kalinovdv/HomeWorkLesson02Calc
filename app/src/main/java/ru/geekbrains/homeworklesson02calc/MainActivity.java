package ru.geekbrains.homeworklesson02calc;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initButtons();
    }

    private void initView(){
        textView = findViewById(R.id.TextView);
    }

    private void initButtons(){
        Button button0 = findViewById(R.id.button0);
        button0.setOnClickListener(this);

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(this);

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(this);

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(this);

        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(this);

        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(this);

        Button button7 = findViewById(R.id.button7);
        button7.setOnClickListener(this);

        Button button8 = findViewById(R.id.button8);
        button8.setOnClickListener(this);

        Button button9 = findViewById(R.id.button9);
        button9.setOnClickListener(this);

        Button buttonAddition = findViewById(R.id.buttonAddition);
        buttonAddition.setOnClickListener(this);

        Button buttonSubtraction = findViewById(R.id.buttonSubtraction);
        buttonSubtraction.setOnClickListener(this);

        Button buttonMultiplication = findViewById(R.id.buttonМultiplication);
        buttonMultiplication.setOnClickListener(this);

        Button buttonDivision = findViewById(R.id.buttonDivision);
        buttonDivision.setOnClickListener(this);

        Button buttonEqually = findViewById(R.id.buttonEqually);
        buttonEqually.setOnClickListener(this);

        Button buttonDot = findViewById(R.id.buttonDot);
        buttonDot.setOnClickListener(this);

        Button buttonClear = findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(this);
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
                textView.setText("Нажата кнопка C");
                break;
        }
    }
}