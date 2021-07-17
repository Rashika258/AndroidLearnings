package com.example.prg21;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button zero, one, two, three, four, five, six, seven, eight, nine, div, mul, add, sub, eql, dot, del, clr;
    TextView res;
    char operator;
    int op_flag = 0;
    float op1, op2, result = 0;
    int error = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zero = (Button) findViewById(R.id.btn0);
        one = (Button) findViewById(R.id.btn1);
        two = (Button) findViewById(R.id.btn2);
        three = (Button) findViewById(R.id.btn3);
        four = (Button) findViewById(R.id.btn4);
        five = (Button) findViewById(R.id.btn5);
        six = (Button) findViewById(R.id.btn6);
        seven = (Button) findViewById(R.id.btn7);
        eight = (Button) findViewById(R.id.btn8);
        nine = (Button) findViewById(R.id.btn9);
        div = (Button) findViewById(R.id.btnDiv);
        mul = (Button) findViewById(R.id.btnMul);
        add = (Button) findViewById(R.id.btnAdd);
        sub = (Button) findViewById(R.id.btnSub);
        eql = (Button) findViewById(R.id.btnEql);
        dot = (Button) findViewById(R.id.btnPnt);
        del = (Button) findViewById(R.id.btnDel);
        clr = (Button) findViewById(R.id.btnClr);
        res = (TextView) findViewById(R.id.TVresult);


        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (operator == '=') {
                    clear_result();
                    res.setText("0");
                }
                else
                    res.setText(res.getText() + "0");
            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operator == '=') {
                    clear_result();
                    res.setText("1");
                }
                else
                    res.setText(res.getText()+"1");
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operator == '=') {
                    clear_result();
                    res.setText("2");
                }
                else
                    res.setText(res.getText()+"2");
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operator == '=') {
                    clear_result();
                    res.setText("3");
                }
                else
                    res.setText(res.getText()+"3");
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operator == '=') {
                    clear_result();
                    res.setText("4");
                }
                else
                    res.setText(res.getText()+"4");
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operator == '=') {
                    clear_result();
                    res.setText("5");
                }
                else
                    res.setText(res.getText()+"5");
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operator == '=') {
                    clear_result();
                    res.setText("6");
                }
                else
                    res.setText(res.getText()+"6");
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operator == '=') {
                    clear_result();
                    res.setText("7");
                }
                else
                    res.setText(res.getText()+"7");
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operator == '=') {
                    clear_result();
                    res.setText("8");
                }
                else
                    res.setText(res.getText()+"8");
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operator == '=') {
                    clear_result();
                    res.setText("9");
                }
                else
                    res.setText(res.getText()+"9");
            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                append_operator('/');
            }
        });

        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                append_operator('x');
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                append_operator('+');
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                append_operator('-');
            }
        });

        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (op_flag != -1)
                    res.setText(res.getText()+".");
                else
                    res.setText(".");
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(res.getText().length() > 0 || res.getText().charAt(0) != '=')
                    res.setText(res.getText().subSequence(0,res.getText().length()-1));
            }
        });

        clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_result();
                res.setText("");
            }
        });

        eql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result = calculateResult();

                op_flag = -1;
                operator = '=';

                if(error == -1)
                    res.setText("= "+result);
                else {
                    switch (error) {
                        case 0:
                            res.setText("Zero Error");
                            break;
                        case 1:
                            res.setText("Invalid exp");
                            break;
                    }
                }
            }
        });
    }

    private void append_operator(char op) {
        if((res.length() != 0)) {
            if((op_flag == 1) && (res.getText().charAt(res.length() - 1) != op) &&
                    (res.getText().charAt(res.length() - 1) == '/' ||
                            res.getText().charAt(res.length() - 1) == 'x' ||
                            res.getText().charAt(res.length() - 1) == '+' ||
                            res.getText().charAt(res.length() - 1) == '-'))
            {
                res.setText(res.getText().subSequence(0,res.getText().length()-1) + Character.toString(op));
            }
            else if (res.getText().charAt(res.length() - 1) != op) {
                if (op_flag == 0) {
                    op1 = Float.parseFloat(String.valueOf(res.getText()));
                    res.setText(res.getText() + Character.toString(op));
                } else if (op_flag == -1) {
                    op1 = result;
                    res.setText(result + Character.toString(op));
                } else {
                    op1 = calculateResult();
                    res.setText(op1 + Character.toString(op));
                }
                operator = op;
                op_flag = 1;
            }
        }
    }

    private void clear_result() {
        result = 0;
        op1 = 0;
        op2 = 0;
        op_flag = 0;
        error = -1;
        operator = ' ';
    }

    private float calculateResult() {
        if(op_flag == 1 && res.getText().toString().indexOf(operator) < res.getText().length()-1)
        {

            op2 = Float.parseFloat(String.valueOf(res.getText().subSequence(res.getText().toString().indexOf(operator)+1, res.getText().length())));

            switch (operator)
            {
                case '+':
                    return op1+op2;

                case '-':
                    return op1-op2;

                case '/':
                    try {
                        if(op2 == 0)
                            error = 0;
                        return op1/op2;
                    }catch (ArithmeticException e) {
                        error = 0;
                    }

                case 'x':
                    return op1*op2;
            }
        }
        error = 1;
        return Integer.MIN_VALUE;
    }
}