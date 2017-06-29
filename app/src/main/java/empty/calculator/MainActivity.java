package empty.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private static final String OPERATION_TEXT = "OperationText";
    private static final String OPERAND1_S = "Operand1";
    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;
    //variables to hold operands and type of calculations
    private Double operand1 = null;

    private String pendingOperation = "=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (EditText) findViewById(R.id.result);
        newNumber = (EditText) findViewById(R.id.newNumber);
        displayOperation = (TextView) findViewById(R.id.operation);

        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button buttonDot = (Button) findViewById(R.id.buttonDot);

        Button buttonNEG = (Button) findViewById(R.id.buttonNEG);

        Button buttonCLEAR = (Button) findViewById(R.id.buttonCLEAR);
        Button buttonEquals = (Button) findViewById(R.id.buttonEquals);
        Button buttonDivide = (Button) findViewById(R.id.buttonDivide);
        Button buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        Button buttonMinus = (Button) findViewById(R.id.buttonMinus);
        Button buttonPlus = (Button) findViewById(R.id.buttonPlus);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                newNumber.append(b.getText().toString());
            }
        };

        //could actually add the buttons to an array, and looped through it to set the listener
        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonDot.setOnClickListener(listener);


        // video lesson - you can instead put the try catch block in the if else statement
        View.OnClickListener negListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = newNumber.getText().toString();

                try {
                    Double num = Double.parseDouble(newNumber.getText().toString());
                    newNumber.setText((num * -1) + "");
                } catch (NumberFormatException e) {
                    if (n.isEmpty()) {
                        newNumber.setText("-");
                    } else {
                        newNumber.setText("");
                    }
                }


            }
        };

        buttonNEG.setOnClickListener(negListener);

        View.OnClickListener opListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                String op = b.getText().toString();
                String value = newNumber.getText().toString();
                try {
                    Double doubleValue = Double.valueOf(value);
                    performOperation(doubleValue, op);

                } catch (NumberFormatException e) {
                    newNumber.setText("");
                }

                pendingOperation = op;
                displayOperation.setText(pendingOperation);
            }


        };

        buttonEquals.setOnClickListener(opListener);
        buttonDivide.setOnClickListener(opListener);
        buttonMultiply.setOnClickListener(opListener);
        buttonMinus.setOnClickListener(opListener);
        buttonPlus.setOnClickListener(opListener);


        View.OnClickListener clearListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayOperation.setText("");
                newNumber.setText("");
                result.setText("");
                operand1 = null;

            }
        };
        buttonCLEAR.setOnClickListener(clearListener);


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(OPERATION_TEXT, pendingOperation);
        if (operand1 != null) {
            outState.putDouble(OPERAND1_S, operand1);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pendingOperation = savedInstanceState.getString(OPERATION_TEXT);
        operand1 = savedInstanceState.getDouble(OPERAND1_S);
        displayOperation.setText(pendingOperation);
    }

    private void performOperation(Double value, String operation) {
        if (operand1 == null) {
            operand1 = value;
        } else {


            if (pendingOperation.equals("=")) {
                pendingOperation = operation;
            }

            switch (pendingOperation) {
                case "=":
                    operand1 = value;
                    break;
                case "/":
                    if (value == 0) {
                        operand1 = 0.0;
                    } else {
                        operand1 /= value;
                    }
                    break;
                case "*":
                    operand1 *= value;
                    break;
                case "-":
                    operand1 -= value;
                    break;
                case "+":
                    operand1 += value;
                    break;
            }
        }


        result.setText(operand1.toString());
        newNumber.setText("");

    }
}
