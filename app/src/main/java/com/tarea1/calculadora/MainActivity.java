package com.tarea1.calculadora;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView historial;
    private TextView display;
    private double firstValue = 0;
    private double secondValue = 0;
    private String operator = "";
    private boolean isNewOperation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.txtDisplay);
        historial = findViewById(R.id.txtHistorial);
        // Números
        setNumberButtonListener(R.id.btn0, "0");
        setNumberButtonListener(R.id.btn1, "1");
        setNumberButtonListener(R.id.btn2, "2");
        setNumberButtonListener(R.id.btn3, "3");
        setNumberButtonListener(R.id.btn4, "4");
        setNumberButtonListener(R.id.btn5, "5");
        setNumberButtonListener(R.id.btn6, "6");
        setNumberButtonListener(R.id.btn7, "7");
        setNumberButtonListener(R.id.btn8, "8");
        setNumberButtonListener(R.id.btn9, "9");

        // Operadores
        setOperatorButtonListener(R.id.btnAdd, "+");
        setOperatorButtonListener(R.id.btnSub, "-");
        setOperatorButtonListener(R.id.btnMul, "*");
        setOperatorButtonListener(R.id.btnDiv, "/");

        // Igual
        findViewById(R.id.btnEquals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondValue = Double.parseDouble(display.getText().toString());

                double result = 0;
                switch (operator) {
                    case "+":
                        result = firstValue + secondValue;
                        break;
                    case "-":
                        result = firstValue - secondValue;
                        break;
                    case "*":
                        result = firstValue * secondValue;
                        break;
                    case "/":
                        result = secondValue != 0 ? firstValue / secondValue : 0;
                        break;
                }

                display.setText(String.valueOf(result));
                historial.setText(historial.getText().toString() + " " + secondValue + " =");
                isNewOperation = true;
            }
        });

        // Limpiar pantalla
        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText("0");
                historial.setText("");
                firstValue = 0;
                secondValue = 0;
                operator = "";
                isNewOperation = true;
            }
        });
    }

    // Configurar los botones de número
    private void setNumberButtonListener(int id, final String number) {
        findViewById(id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNewOperation) {
                    display.setText(number);
                    isNewOperation = false;
                } else {
                    display.append(number);
                }
            }
        });
    }

    // Configurar los botones de operador
    private void setOperatorButtonListener(int id, final String operatorSymbol) {
        findViewById(id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstValue = Double.parseDouble(display.getText().toString());
                operator = operatorSymbol;
                historial.setText(firstValue + " " + operator);
                isNewOperation = true;
            }
        });
    }
}
