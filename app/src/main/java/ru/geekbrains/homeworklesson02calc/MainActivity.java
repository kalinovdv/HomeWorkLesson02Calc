package ru.geekbrains.homeworklesson02calc;

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

    // перменнеая
    private TextView textView;

    // переменная для хранения последней арифметической операции (+, -, *, /)
    private String operation = "";

    // переменная для хранения типа нажатой клавиши (цифра, операция, равно)
    private String inputType = "";

    // переменная для хранения операнда арифметической операции
    private Double memoryCell = 0.0;

    // константы для операций сохранения состояния
    private static final String KEY_TEXT_VIEW = "KEYTEXTVIEW";
    private static final String KEY_OPERATION = "KEYOPERATION";
    private static final String KEY_INPUTTYPE = "KEYINPUTTYPE";
    private static final String KEY_MEMORYCELL = "KEYMEMORYCELL";
    private static final String NAME_SHARED_PREFERENCE = "MYCALC";
    private static final String THEME = "MYTHEME";

    // переменная для формата выводимого результата
    private final DecimalFormat decimalFormat = new DecimalFormat("#.#####");

    // константы для стилей приложения
    private static final int MY_THEME = 0;
    private static final int MY_THEME_DARK = 1;

    // константы арифметических операций
    private static final String OPERATION_ADDITION = "+";
    private static final String OPERATION_SUBTRACTION = "-";
    private static final String OPERATION_MULTIPLIKATION = "*";
    private static final String OPERATION_DIVISION = "/";

    // константы типов вводимых данных
    private static final String INPUTTYPE_ADDITION = "+";
    private static final String INPUTTYPE_SUBTRACTION = "-";
    private static final String INPUTTYPE_MULTIPLIKATION = "*";
    private static final String INPUTTYPE_DIVISION = "/";
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

                } else if (inputType.equals(INPUTTYPE_ADDITION) || inputType.equals(INPUTTYPE_SUBTRACTION)
                        || inputType.equals(INPUTTYPE_EQUALLY) || inputType.equals(INPUTTYPE_DIVISION)) {
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
                //обработка нажатия клавиши "+"
                if (inputType.equals(INPUTTYPE_ADDITION)) {
                    // если клавиша "+" нажата после клавиши "+",
                    // то ничего не делаем: 2++ никакого результата не дает
                    inputType = INPUTTYPE_ADDITION;
                    operation = OPERATION_ADDITION;

                } else if (inputType.equals(INPUTTYPE_EQUALLY)) {
                    // если клавиша "+" нажата после клавиши "=",
                    // то сохраняем значение на экране калькулятора в память
                    result = Double.parseDouble(textView.getText().toString());
                    memoryCell = result;
                    inputType = INPUTTYPE_ADDITION;
                    operation = OPERATION_ADDITION;

                } else if (inputType.equals(INPUTTYPE_DIGITS)) {
                    // если клавиша "+" нажата после цифровой клавиши,
                    result = Double.parseDouble(textView.getText().toString());
                    // вычисляем в памяти значение арифметической операции
                    if (operation.equals(OPERATION_ADDITION)) {
                        memoryCell += result;
                    } else if (operation.equals(OPERATION_SUBTRACTION)) {
                        memoryCell -= result;
                    } else if (operation.equals(OPERATION_MULTIPLIKATION)) {
                        memoryCell *= result;
                    } else if (operation.equals(OPERATION_DIVISION)) {
                        try {
                            memoryCell /= result;
                            if (memoryCell == Double.POSITIVE_INFINITY || memoryCell == Double.NEGATIVE_INFINITY) {
                                throw new ArithmeticException();
                            }
                        } catch (ArithmeticException e) {
                            textView.setText("ERR");
                            inputType = "";
                            operation = "";
                            memoryCell = 0.0;
                            break;
                        }
                    } else {
                        memoryCell = result;
                    }
                    // на экран выводим результат вычислений из памяти
                    textView.setText(decimalFormat.format(memoryCell));
                    operation = OPERATION_ADDITION;
                    inputType = INPUTTYPE_ADDITION;
                }
                break;

            case R.id.buttonSubtraction:
                //обработка нажатия клавиши "-"
                if (inputType.equals(INPUTTYPE_SUBTRACTION)) {
                    // если клавиша "-" нажата после клавиши "-",
                    // то ничего не делаем: 2-- никакого результата
                    inputType = INPUTTYPE_SUBTRACTION;
                    operation = OPERATION_SUBTRACTION;

                } else if (inputType.equals(INPUTTYPE_EQUALLY)) {
                    // если клавиша "-" нажата после клавиши "=",
                    // то сохраняем значение на экране калькулятора в память
                    result = Double.parseDouble(textView.getText().toString());
                    memoryCell = result;
                    inputType = INPUTTYPE_SUBTRACTION;
                    operation = OPERATION_SUBTRACTION;

                } else if (inputType.equals(INPUTTYPE_DIGITS)) {
                    // если клавиша "-" нажата после цифровой клавиши,
                    result = Double.parseDouble(textView.getText().toString());
                    // вычисляем в памяти значение арифметической операции
                    if (operation.equals(OPERATION_ADDITION)) {
                        memoryCell += result;
                    } else if (operation.equals(OPERATION_SUBTRACTION)) {
                        memoryCell -= result;
                    } else if (operation.equals(OPERATION_MULTIPLIKATION)) {
                        memoryCell *= result;
                    } else if (operation.equals(OPERATION_DIVISION)) {
                        try {
                            memoryCell /= result;
                            if (memoryCell == Double.POSITIVE_INFINITY || memoryCell == Double.NEGATIVE_INFINITY) {
                                throw new ArithmeticException();
                            }
                        } catch (ArithmeticException e) {
                            textView.setText("ERR");
                            inputType = "";
                            operation = "";
                            memoryCell = 0.0;
                            break;
                        }
                    } else {
                        memoryCell = result;
                    }
                    // на экран выводим результат вычислений из памяти
                    textView.setText(decimalFormat.format(memoryCell));
                    inputType = INPUTTYPE_SUBTRACTION;
                    operation = OPERATION_SUBTRACTION;
                }
                break;

            case R.id.buttonМultiplication:
                //обработка нажатия клавиши "*"
                if (inputType.equals(INPUTTYPE_MULTIPLIKATION)) {
                    // если клавиша "*" нажата после клавиши "*",
                    // то ничего не делаем: 2-- никакого результата
                    inputType = INPUTTYPE_MULTIPLIKATION;
                    operation = OPERATION_MULTIPLIKATION;

                } else if (inputType.equals(INPUTTYPE_EQUALLY)) {
                    // если клавиша "*" нажата после клавиши "=",
                    // то сохраняем значение на экране калькулятора в память
                    result = Double.parseDouble(textView.getText().toString());
                    memoryCell = result;
                    inputType = INPUTTYPE_MULTIPLIKATION;
                    operation = OPERATION_MULTIPLIKATION;

                } else if (inputType.equals(INPUTTYPE_DIGITS)) {
                    // если клавиша "*" нажата после цифровой клавиши,
                    result = Double.parseDouble(textView.getText().toString());
                    // вычисляем в памяти значение арифметической операции
                    if (operation.equals(OPERATION_ADDITION)) {
                        memoryCell += result;
                    } else if (operation.equals(OPERATION_SUBTRACTION)) {
                        memoryCell -= result;
                    } else if (operation.equals(OPERATION_MULTIPLIKATION)) {
                        memoryCell *= result;
                    } else if (operation.equals(OPERATION_DIVISION)) {
                        try {
                            memoryCell /= result;
                            if (memoryCell == Double.POSITIVE_INFINITY || memoryCell == Double.NEGATIVE_INFINITY) {
                                throw new ArithmeticException();
                            }
                        } catch (ArithmeticException e) {
                            textView.setText("ERR");
                            inputType = "";
                            operation = "";
                            memoryCell = 0.0;
                            break;
                        }
                    } else {
                        memoryCell = result;
                    }
                    // на экран выводим результат вычислений из памяти
                    textView.setText(decimalFormat.format(memoryCell));
                    inputType = INPUTTYPE_MULTIPLIKATION;
                    operation = OPERATION_MULTIPLIKATION;
                }
                break;

            case R.id.buttonDivision:
                //обработка нажатия клавиши "/"
                if (inputType.equals(INPUTTYPE_DIVISION)) {
                    // если клавиша "*" нажата после клавиши "*",
                    // то ничего не делаем: 2-- никакого результата
                    inputType = INPUTTYPE_DIVISION;
                    operation = OPERATION_DIVISION;

                } else if (inputType.equals(INPUTTYPE_EQUALLY)) {
                    // если клавиша "*" нажата после клавиши "=",
                    // то сохраняем значение на экране калькулятора в память
                    result = Double.parseDouble(textView.getText().toString());
                    memoryCell = result;
                    inputType = INPUTTYPE_DIVISION;
                    operation = OPERATION_DIVISION;

                } else if (inputType.equals(INPUTTYPE_DIGITS)) {
                    // если клавиша "*" нажата после цифровой клавиши,
                    result = Double.parseDouble(textView.getText().toString());
                    // вычисляем в памяти значение арифметической операции
                    if (operation.equals(OPERATION_ADDITION)) {
                        memoryCell += result;
                    } else if (operation.equals(OPERATION_SUBTRACTION)) {
                        memoryCell -= result;
                    } else if (operation.equals(OPERATION_MULTIPLIKATION)) {
                        memoryCell *= result;
                    } else if (operation.equals(OPERATION_DIVISION)) {
                        try {
                            memoryCell /= result;
                            if (memoryCell == Double.POSITIVE_INFINITY || memoryCell == Double.NEGATIVE_INFINITY) {
                                throw new ArithmeticException();
                            }
                        } catch (ArithmeticException e) {
                            textView.setText("ERR");
                            inputType = "";
                            operation = "";
                            memoryCell = 0.0;
                            break;
                        }
                    } else {
                        memoryCell = result;
                    }
                    // на экран выводим результат вычислений из памяти
                    textView.setText(decimalFormat.format(memoryCell));
                    inputType = INPUTTYPE_DIVISION;
                    operation = OPERATION_DIVISION;
                }
                break;

            case R.id.buttonEqually:
                //обработка нажатия клавиши "="
                if (inputType.equals(INPUTTYPE_ADDITION) || inputType.equals(INPUTTYPE_SUBTRACTION) ||
                        inputType.equals(INPUTTYPE_MULTIPLIKATION) || inputType.equals(INPUTTYPE_DIVISION)) {
                    // если клавиша "=" нажата после клавиш "+", "-", "*", "/" (2+=, результат - 4)
                    // запомиинаем введенное значение
                    result = Double.parseDouble(textView.getText().toString());
                    // производим вычисление значения в памяти
                    if (operation.equals(OPERATION_ADDITION)) {
                        memoryCell += result;
                    } else if (operation.equals(OPERATION_SUBTRACTION)) {
                        memoryCell -= result;
                    } else if (operation.equals(OPERATION_MULTIPLIKATION)) {
                        memoryCell *= result;
                    } else if (operation.equals(OPERATION_DIVISION)) {
                        try {
                            memoryCell /= result;
                            if (memoryCell == Double.POSITIVE_INFINITY || memoryCell == Double.NEGATIVE_INFINITY) {
                                throw new ArithmeticException();
                            }
                        } catch (ArithmeticException e) {
                            textView.setText("ERR");
                            inputType = "";
                            operation = "";
                            memoryCell = 0.0;
                            break;
                        }
                    }
                    // выводим вычисленное значение в памяти на экран
                    textView.setText(decimalFormat.format(memoryCell));
                    // сохраняем введенное значение в памяти
                    memoryCell = result;
                    inputType = INPUTTYPE_EQUALLY;

                } else if (inputType.equals(INPUTTYPE_EQUALLY)) {
                    // если клавиша "=" нажата после клавиши "=" (2+3===, результат - 11)
                    // запомиинаем введенное значение
                    result = Double.parseDouble(textView.getText().toString());
                    // производим вычисление значения из памяти
                    if (operation.equals(OPERATION_ADDITION)) {
                        result += memoryCell;
                    } else if (operation.equals(OPERATION_SUBTRACTION)) {
                        result -= memoryCell;
                    } else if (operation.equals(OPERATION_MULTIPLIKATION)) {
                        result *= memoryCell;
                    } else if (operation.equals(OPERATION_DIVISION)) {
                        try {
                            memoryCell /= result;
                            if (memoryCell == Double.POSITIVE_INFINITY || memoryCell == Double.NEGATIVE_INFINITY) {
                                throw new ArithmeticException();
                            }
                        } catch (ArithmeticException e) {
                            textView.setText("ERR");
                            inputType = "";
                            operation = "";
                            memoryCell = 0.0;
                            break;
                        }
                    }
                    // выводим результат вычисления на экран
                    textView.setText(decimalFormat.format(result));
                    inputType = INPUTTYPE_EQUALLY;

                } else if (inputType.equals(INPUTTYPE_DIGITS)) {
                    // если клавиша "=" нажата после цифровой клавиши,
                    // запомиинаем введенное значение
                    result = Double.parseDouble(textView.getText().toString());
                    // производим вычисление значения в памяти
                    if (operation.equals(OPERATION_ADDITION)) {
                        memoryCell += result;
                    } else if (operation.equals(OPERATION_SUBTRACTION)) {
                        memoryCell -= result;
                    } else if (operation.equals(OPERATION_MULTIPLIKATION)) {
                        memoryCell *= result;
                    } else if (operation.equals(OPERATION_DIVISION)) {
                        try {
                            memoryCell /= result;
                            if (memoryCell == Double.POSITIVE_INFINITY || memoryCell == Double.NEGATIVE_INFINITY) {
                                throw new ArithmeticException();
                            }
                        } catch (ArithmeticException e) {
                            textView.setText("ERR");
                            inputType = "";
                            operation = "";
                            memoryCell = 0.0;
                            break;
                        }
                    }
                    // выводим вычисленное значение в памяти на экран
                    textView.setText(decimalFormat.format(memoryCell));
                    // сохраняем введенное значение в памяти
                    memoryCell = result;
                    inputType = INPUTTYPE_EQUALLY;
                }
                break;

            case R.id.buttonClear:
                // обработка нажатия клавиши "С"
                // очистка всех парпметров
                textView.setText("0");
                inputType = "";
                operation = "";
                memoryCell = 0.0;
                break;

            default:
                //обработка нажатия цифровых клавиш, кроме "0"
                if (textView.getText().toString().equals("0")) {
                    // если на экране "0", то выводим нажатую цифру
                    textView.setText(str);
                    // меняем тип нажатой клавиши на цифру
                    inputType = INPUTTYPE_DIGITS;

                } else if (inputType.equals(INPUTTYPE_ADDITION) || inputType.equals(INPUTTYPE_SUBTRACTION) ||
                        inputType.equals(INPUTTYPE_MULTIPLIKATION) || inputType.equals(INPUTTYPE_DIVISION)) {
                    // если тип последней нажатой клавиши равен арифметической операции,
                    // то выводим нажатую цифру
                    textView.setText(str);
                    // меняем тип нажатой клавиши на цифру
                    inputType = INPUTTYPE_DIGITS;

                } else if (inputType.equals(INPUTTYPE_EQUALLY)) {
                    // если тип последней нажатой клавиши равен "=",
                    // то выводим нажатую цифру
                    textView.setText(str);
                    // меняем тип нажатой клавиши на цифру
                    inputType = INPUTTYPE_DIGITS;
                    // сбрасываем значение в памяти в ноль (считаем, что начато новое действие)
                    memoryCell = 0.0;

                } else if (inputType.equals(INPUTTYPE_DIGITS)) {
                    // если на экране не "0" и нажата цифровая клавиша,
                    // то добавляем цифру к уже введенным цифрам
                    textView.append(str);
                    // меняем тип нажатой клавиши на цифру
                    inputType = INPUTTYPE_DIGITS;
                }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putString(KEY_TEXT_VIEW, textView.getText().toString());
        instanceState.putString(KEY_OPERATION, operation);
        instanceState.putString(KEY_INPUTTYPE, inputType);
        instanceState.putDouble(KEY_MEMORYCELL, memoryCell);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        textView.setText(instanceState.getString(KEY_TEXT_VIEW));
        operation = instanceState.getString(KEY_OPERATION);
        inputType = instanceState.getString(KEY_INPUTTYPE);
        memoryCell = instanceState.getDouble(KEY_MEMORYCELL);
    }


}