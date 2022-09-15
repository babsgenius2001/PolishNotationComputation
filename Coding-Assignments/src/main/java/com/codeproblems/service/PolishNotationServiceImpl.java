package com.codeproblems.service;

import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

@Service
public class PolishNotationServiceImpl implements PolishNotationService {
    private static String operators = "+-*/";
    private static String expressionOperator;
    private static double operand1;
    private static double operand2;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public List<String> evaluateExpressions(List<String> expressions) {
        List<String> evaluatedItems = new ArrayList<>();
        try {
            for (String item : expressions) {
                String[] expressionCharacters = item.split(" ");

                int expressionCharactersLength = expressionCharacters.length;
                if (expressionCharactersLength > 1 && Arrays.stream(expressionCharacters).noneMatch(x -> x.equals("+") || x.equals("-") || x.equals("*") || x.equals("/"))) {
                    evaluatedItems.add("error");

                } else if (expressionCharactersLength > 1 && operandsCounter(expressionCharacters) <= 1) {
                    evaluatedItems.add("error");
                } else {
                    Stack<Double> expressionToEvaluateItems = new Stack<>();

                    for (int j = expressionCharactersLength - 1; j >= 0; j--) {
                        if (operators.contains(expressionCharacters[j])) {
                            operand1 = expressionToEvaluateItems.pop();
                            operand2 = expressionToEvaluateItems.pop();

                            expressionOperator = expressionCharacters[j];

                            switch (expressionOperator) {
                                case "+":
                                    expressionToEvaluateItems.push(operand1 + operand2);
                                    break;
                                case "-":
                                    expressionToEvaluateItems.push(operand1 - operand2);
                                    break;
                                case "*":
                                    expressionToEvaluateItems.push(operand1 * operand2);
                                    break;
                                case "/":
                                    if (operand1 == 0) {
                                        throw new UnsupportedOperationException("Cannot divide by zero");
                                    }
                                    expressionToEvaluateItems.push(operand1 / operand2);
                                    break;
                            }

                        } else {
                            expressionToEvaluateItems.push(Double.valueOf((expressionCharacters[j])));
                        }
                    }
                    evaluatedItems.add(df.format(expressionToEvaluateItems.pop()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return evaluatedItems;
    }

    public int operandsCounter(String[] expressionCharacters) {
        int count = 0;
        for (String expressionCharacter : expressionCharacters) {
            if (expressionCharacter.matches("\\d+") || expressionCharacter.matches("[+-]?[0-9]+(\\.[0-9]+)?([Ee][+-]?[0-9]+)?")) {
                count++;
            }
        }
        return count;
    }
}
