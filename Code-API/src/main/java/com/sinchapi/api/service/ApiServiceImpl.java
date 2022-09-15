package com.sinchapi.api.service;

import com.codeproblems.service.PolishNotationService;
import com.codeproblems.service.PolishNotationServiceImpl;
import com.google.common.base.Splitter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ApiServiceImpl implements ApiService {
    @Override
    public String evaluateExpression(String expression) {

        PolishNotationService polishNotationService = new PolishNotationServiceImpl();
        List<String> result = polishNotationService.evaluateExpressions(Splitter.on("\n").splitToList(expression));

        return result.stream().collect(Collectors.joining("\n"));
    }
}
