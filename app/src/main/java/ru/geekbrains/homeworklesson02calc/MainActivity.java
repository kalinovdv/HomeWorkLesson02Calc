package ru.geekbrains.homeworklesson02calc;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.radiobutton.MaterialRadioButton;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private String operation = "";
    private String inputType = "";
    private Double memoryCell = 0.0;
    private Boolean operationEqually = false;

    private static final String KEY = "CALKULATION";
    private static final String NAME_SHARED_PREFERENCE = "MYCALC";
    private static final String THEME = "MYTHEME";

    private final DecimalFormat decimalFormat = new DecimalFormat("#.#####");

    private static final int MY_THEME = 0;
    private static final int MY_THEME_DARK = 1;

    private static final String OPERATION_ADDITION = "+";
    private static final String OPERATION_EQUALLY = "=";

    private static final String INPUTTYPE_ADDITION = "+";
    private static final String INPUTTYPE_EQUALLY = "=";
    private static final String INPUTTYPE_DIGITS = "d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences(NAME_SHARED_PREFERENCE, MODE_PRIVATE);
        int code = sharedPreferences.getInt(THEME, MY_THEME);
        switch (code) {
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

    private void initView() {
        textView = findViewById(R.id.TextView);
        textView.setText("0");
    }

    private void initButtons() {

        ConstraintLayout constraintLayout = findViewById(R.id.myLayout);

        for (int i = 0; i < constraintLayout.getChildCount(); i++) {
            View view = constraintLayout.getChildAt(i);
            if (view instanceof Button) {
                view.setOnClickListener(this);
            }
        }
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    public void onClick(View view) {
        String str = ((Button) view).getText().toString();
        int idView = view.getId();
        Double result;

        switch (idView) {
            case R.id.button0:
                //обработка нажатия клавиши "0"
                if (textView.getText().toString().equals("0")) {
                    //если на экране калькулятора "0", то ничего не выводим,
                    // т.к. ноль должен быть нулем, а не цепочкой символов "00000"
                    inputType = INPUTTYPE_DIGITS;

                } else if (inputType.equals(INPUTTYPE_ADDITION) || inputType.equals(INPUTTYPE_EQUALLY)) {
                    //осли клавиша нажата после арифметической операции, то выводим "0"
                    textView.setText(str);
                    inputType = INPUTTYPE_DIGITS;

                } else if (inputType.equals(INPUTTYPE_DIGITS)) {
                    //если клавиша нажата после ввода цифры, то добавляем "0" к введенным цифрам
                    textView.append(str);
                    inputType = INPUTTYPE_DIGITS;
                }
                break;

            case R.id.buttonAddition:
                if (inputType.equals(INPUTTYPE_ADDITION)) {
                    // если клавиша "+" нажата после клавиши "+", то ничего не делаем
                    inputType = INPUTTYPE_ADDITION;
                    operation = OPERATION_ADDITION;

                } else if (inputType.equals(INPUTTYPE_EQUALLY)) {
                    // если клавиша "+" нажата после клавиши "="
                    result = Double.parseDouble(textView.getText().toString());
                    memoryCell = result;
                    //operationEqually = false;
                    inputType = INPUTTYPE_ADDITION;
                    operation = OPERATION_ADDITION;

                } else if (inputType.equals(INPUTTYPE_DIGITS)) {
                    result = Double.parseDouble(textView.getText().toString());
//                    if (operationEqually) {
//                        memoryCell = result;
//                    } else {
//                        result += memoryCell;
//                        memoryCell = result;
//                    }
                    result += memoryCell;
                    memoryCell = result;
                    textView.setText(decimalFormat.format(result));
                    operation = OPERATION_ADDITION;
                    inputType = INPUTTYPE_ADDITION;
                }
                break;

            case R.id.buttonEqually:
                if (inputType.equals(INPUTTYPE_ADDITION)) {
                    // если клавиша "=" нажата после клавиши "+", то выводим результат сложения
                    result = Double.parseDouble(textView.getText().toString());
                    if (operation.equals(OPERATION_ADDITION)) {
                        result += memoryCell;
                    }
                    memoryCell = Double.parseDouble(textView.getText().toString());
                    textView.setText(decimalFormat.format(result));
                    //operationEqually = true;
                    inputType = INPUTTYPE_EQUALLY;

                } else if (inputType.equals(INPUTTYPE_EQUALLY)) {
                    // если клавиша "=" нажата после клавиши "=", то выводим результат сложения
                    result = Double.parseDouble(textView.getText().toString());
                    if (operation.equals(OPERATION_ADDITION)) {
                        result += memoryCell;
                    }
                    textView.setText(decimalFormat.format(result));
                    inputType = INPUTTYPE_EQUALLY;

                } else if (inputType.equals(INPUTTYPE_DIGITS)) {
                    result = Double.parseDouble(textView.getText().toString());
                    if (operation.equals(OPERATION_ADDITION)) {
                        result += memoryCell;
                    }
                    memoryCell = Double.parseDouble(textView.getText().toString());
                    textView.setText(decimalFormat.format(result));
                    //operationEqually = true;
                    inputType = INPUTTYPE_EQUALLY;
                }
                break;

            case R.id.buttonClear:
                textView.setText("0");
                inputType = "";
                operation = "";
                memoryCell = 0.0;
                operationEqually = false;
                break;

            default:
                if (textView.getText().toString().equals("0")) {
                    // если на экране "0", то выводим нажатую цифру
                    textView.setText(str);
                    inputType = INPUTTYPE_DIGITS;

                } else if (inputType.equals(INPUTTYPE_ADDITION)) {
                    textView.setText(str);
                    inputType = INPUTTYPE_DIGITS;

                } else if (inputType.equals(INPUTTYPE_EQUALLY)) {
                    textView.setText(str);
                    inputType = INPUTTYPE_DIGITS;
                    memoryCell = 0.0;
//                    if (operationEqually) {
//                        operationEqually = false;
//                        memoryCell = 0.0;
//                    }

                } else if (inputType.equals(INPUTTYPE_DIGITS)) {
                    // если на экране не "0" и нажата цифровая клавиша, то добавляем цифру
                    textView.append(str);
                    inputType = INPUTTYPE_DIGITS;
                }
        }
//        if (textView.getText().toString().equals("0") && idView == R.id.button0) {
//            inputType = INPUTTYPE_DIGITS;
//
//        } else if ((textView.getText().toString().equals("0") || inputType.equals(INPUTTYPE_OPERATION)) &&
//                (idView == R.id.button1 || idView == R.id.button2 || idView == R.id.button3 |
//                        idView == R.id.button4 || idView == R.id.button5 || idView == R.id.button6 || idView == R.id.button7 ||
//                        idView == R.id.button8 || idView == R.id.button9)) {
//            textView.setText(str);
//            inputType = INPUTTYPE_DIGITS;
//
//        } else if (!textView.getText().toString().equals("0") && (idView == R.id.button1 || idView == R.id.button2 || idView == R.id.button3 |
//                idView == R.id.button4 || idView == R.id.button5 || idView == R.id.button6 || idView == R.id.button7 ||
//                idView == R.id.button8 || idView == R.id.button9 || idView == R.id.button0)) {
//            textView.append(str);
//            inputType = INPUTTYPE_DIGITS;
//
//        } else if (idView == R.id.buttonClear) {
//            textView.setText("0");
//            inputType = null;
//            operation = null;
//            memoryCell = 0.0;
//
//        } else if (idView == R.id.buttonAddition) {
//            inputType = INPUTTYPE_OPERATION;
//            if (!inputType.equals(operation)) {
//                operation = OPERATION_ADDITION;
//                result = Double.parseDouble(textView.getText().toString());
//                result += memoryCell;
//                memoryCell = result;
//                textView.setText(result.toString());
//            }
//
//        } else if (idView == R.id.buttonEqually) {
//            inputType = INPUTTYPE_EQUALLY;
//            switch (operation) {
//                case OPERATION_ADDITION:
//                    break;
//            }
//        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putString(KEY, textView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        String s = instanceState.getString(KEY);
        textView.setText(s);
    }


}