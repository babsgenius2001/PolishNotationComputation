package com.codeproblems;

import com.codeproblems.service.PolishNotationService;
import com.codeproblems.service.PolishNotationServiceImpl;

import java.util.*;

/***Create a program that evaluates arithmetic expressions written in Polish notation.
 Expressions can contain double-precision floating point numbers and the following operations: addition, subtraction, division and multiplication.
 ***/

public class Problem2 {
    private static String expressionToEvaluate;

    public static void main(String[] args) throws EmptyStackException {
        Scanner stdin = new Scanner(System.in);
        List<String> listOfItems = new ArrayList<>();

        while (stdin.hasNextLine()) {
            expressionToEvaluate = stdin.nextLine();
            if (expressionToEvaluate.isEmpty()) {
                break;
            }
            listOfItems.add(expressionToEvaluate);
        }

        PolishNotationService polishNotationService = new PolishNotationServiceImpl();
        polishNotationService.evaluateExpressions(listOfItems).forEach(System.out::println);
    }

}
