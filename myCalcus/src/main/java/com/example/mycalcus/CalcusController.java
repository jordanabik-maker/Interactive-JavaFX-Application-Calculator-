package com.example.mycalc;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalcusController {
    @FXML
    private Label resultLabel, expressionLabel;

    String currentText = "";
    Boolean isOperandSingle = Boolean.FALSE;

    @FXML
    protected void onZeroButtonClick() {
        currentText = resultLabel.getText();
        resultLabel.setText(currentText + "0");
    }

    @FXML
    protected void onOneButtonClick() {
        currentText = resultLabel.getText();
        resultLabel.setText(currentText + "1");
    }

    @FXML
    protected void onTwoButtonClick() {
        currentText = resultLabel.getText();
        resultLabel.setText(currentText + "2");
    }

    @FXML
    protected void onThreeButtonClick() {
        currentText = resultLabel.getText();
        resultLabel.setText(currentText + "3");
    }

    @FXML
    protected void onFourButtonClick() {
        currentText = resultLabel.getText();
        resultLabel.setText(currentText + "4");
    }

    @FXML
    protected void onFiveButtonClick() {
        currentText = resultLabel.getText();
        resultLabel.setText(currentText + "5");
    }

    @FXML
    protected void onSixButtonClick() {
        currentText = resultLabel.getText();
        resultLabel.setText(currentText + "6");
    }

    @FXML
    protected void onSevenButtonClick() {
        currentText = resultLabel.getText();
        resultLabel.setText(currentText + "7");
    }

    @FXML
    protected void onEightButtonClick() {
        currentText = resultLabel.getText();
        resultLabel.setText(currentText + "8");
    }

    @FXML
    protected void onNineButtonClick() {
        currentText = resultLabel.getText();
        resultLabel.setText(currentText + "9");
    }

    @FXML
    protected void onAddButtonClick() {
        currentText = resultLabel.getText();
        resultLabel.setText(currentText + "+");
    }

    @FXML
    protected void onSubtractButtonClick() {
        currentText = resultLabel.getText();
        resultLabel.setText(currentText + "-");
    }

    @FXML
    protected void onDotButtonClick() {
        currentText = resultLabel.getText();
        resultLabel.setText(currentText + ".");
    }

    @FXML
    protected void onBackspaceButtonClick() {
        currentText = resultLabel.getText();

        if (currentText != null && !currentText.isEmpty()) {
            // Remove the last character
            resultLabel.setText(currentText.substring(0, currentText.length() - 1));
        }
    }


    @FXML
    protected void onSqrtButtonClick() {
        if (resultLabel.getText().isEmpty()) return;

        double value = Double.parseDouble(resultLabel.getText());
        resultLabel.setText(String.valueOf(Math.sqrt(value)));
    }

    @FXML
    protected void onMultiplyButtonClick() {
        currentText = resultLabel.getText();
        resultLabel.setText(currentText + "x");
    }

    @FXML
    protected void onDivideButtonClick() {
        currentText = resultLabel.getText();
        resultLabel.setText(currentText + "÷");
    }

    @FXML
    protected void onPercentButtonClick() {
        if (resultLabel.getText().isEmpty()) return;

        double value = Double.parseDouble(resultLabel.getText());
        resultLabel.setText(String.valueOf(value / 100));
    }

    @FXML
    protected void onClearButtonClick() {
        resultLabel.setText("");
    }

    @FXML
    protected void onClearAllButtonClick() {
        resultLabel.setText("");
        expressionLabel.setText("");
    }

    @FXML
    protected void onInverseButtonClick() {
        if (resultLabel.getText().isEmpty()) return;

        double value = Double.parseDouble(resultLabel.getText());
        resultLabel.setText(String.valueOf(1 / value));
    }

    @FXML
    protected void onFactorialButtonClick() {
        if (resultLabel.getText().isEmpty()) return;

        int num = (int) Double.parseDouble(resultLabel.getText());
        int result = 1;

        for (int i = 1; i <= num; i++) {
            result *= i;
        }

        resultLabel.setText(String.valueOf(result));
    }

    @FXML
    protected void onPlusMinusButtonClick() {
        if (resultLabel.getText().isEmpty()) return;

        double value = Double.parseDouble(resultLabel.getText());
        resultLabel.setText(String.valueOf(-value));
    }

    private Boolean expressionEvaluator(String expression){

        //String expressionRegEx = "\\d+\\s*[x*÷+-]\\s*\\d+";
        String expressionRegEx = "\\d*\\.?\\d+[+\\-x÷]\\d*\\.?\\d+?|√\\d+|\\d+!";

        // Compile the regex into a Pattern object
        Pattern pattern = Pattern.compile(expressionRegEx);

        //System.out.println(expression);
        // Create a matcher for the expression string
        Matcher matcher = pattern.matcher(expression);

        if (matcher.matches())
        {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    @FXML
    protected void onEqualButtonClick() {
        String theExpression = resultLabel.getText();

        Boolean evaluationResult = expressionEvaluator(theExpression);

        if(evaluationResult) {
            expressionLabel.setText(theExpression);
            String result = doTheMath(theExpression);
            resultLabel.setText(result);
        }else{
            resultLabel.setText("Wrong expression");
        }
    }

    private String doTheMath (String expression)
    {
        //expression: 3 + 4
        char theOperator = ' ';
        String[] parts = expression.split("[\\+\\-x÷%%!√]");

        if (expression.contains("+")) {
            theOperator = '+';
        } else if (expression.contains("-")) {
            theOperator = '-';
        } else if (expression.contains("x")) {
            theOperator = 'x';
        } else if (expression.contains("÷")) {
            theOperator = '÷';
        } else if (expression.contains("√")) {
            theOperator = '√';
            isOperandSingle = Boolean.TRUE;
        } else if (expression.contains("!")) {
            theOperator = '!';
            isOperandSingle = Boolean.TRUE;
        }

        //cast the String into Integer
        double operand1 = 0;
        double operand2 = 0;
        double operand3 = 0;

        if (!isOperandSingle) {
            operand1 = Double.parseDouble(parts[0]);
            operand2 = Double.parseDouble(parts[1]);
        }else{
            operand3 = (theOperator == '!') ? Double.parseDouble(parts[0]) : Double.parseDouble(parts[1]);
        }

        //perform th operation based on the operator
        double result = 0;

        switch (theOperator)
        {
            //multiple operands are handled below
            case '+':
                result = operand1 + operand2;
                break;
            case '-':
                result = operand1 - operand2;
                break;

            case 'x':
                result = operand1 * operand2;
                break;

            case '÷':
                if (operand2 == 0) {
                    return "Error";
                }
                result = operand1 / operand2;
                break;
            //single operands are handled below
            case '√':
                //System.out.println(operand3);
                result = Math.sqrt(operand3);
                isOperandSingle = Boolean.FALSE;
                break;
            case '!':
                int fact = 1;
                for (int i = 1; i <= (int) operand3; i++) {
                    fact *= i;
                }
                result = fact;
                isOperandSingle = Boolean.FALSE;
                break;
        }

        return String.valueOf(result);
    }

}
