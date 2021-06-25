package ru.geekbrains.homeworklesson02calc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button0;
    private Button buttonAddition;
    private Button buttonSubtraction;
    private Button buttonMultiplication;
    private Button buttonDivision;
    private Button buttonEqually;
    private Button buttonDot;
    private Button buttonClear;

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
        button0 = findViewById(R.id.button0);
        button0.setOnClickListener(this);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(this);

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(this);

        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(this);

        button5 = findViewById(R.id.button5);
        button5.setOnClickListener(this);

        button6 = findViewById(R.id.button6);
        button6.setOnClickListener(this);

        button7 = findViewById(R.id.button7);
        button7.setOnClickListener(this);

        button8 = findViewById(R.id.button8);
        button8.setOnClickListener(this);

        button9 = findViewById(R.id.button9);
        button9.setOnClickListener(this);

        buttonAddition = findViewById(R.id.buttonAddition);
        buttonAddition.setOnClickListener(this);

        buttonSubtraction = findViewById(R.id.buttonSubtraction);
        buttonSubtraction.setOnClickListener(this);

        buttonMultiplication = findViewById(R.id.buttonМultiplication);
        buttonMultiplication.setOnClickListener(this);

        buttonDivision = findViewById(R.id.buttonDivision);
        buttonDivision.setOnClickListener(this);

        buttonEqually = findViewById(R.id.buttonEqually);
        buttonEqually.setOnClickListener(this);

        buttonDot = findViewById(R.id.buttonDot);
        buttonDot.setOnClickListener(this);

        buttonClear = findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}