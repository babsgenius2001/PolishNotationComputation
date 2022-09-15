package com.sinchapi.api;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void should_Evaluate_ListOfPolishNotations_Successfully() {
        String expression = "+ + 0.5 1.5 * 4 10\n- 2e3 - 700 + 7 * 2 15\n- -1.5 * 3.1415 / -7 -2\n100500\n1 2\n+ 1\n";
        String outcome = "42.00\n1337.00\n-12.50\n100500.00\nerror\nerror";

        ResponseEntity<?> evaluationResponse = restTemplate.postForEntity(getRootUrl() + "/api/evaluate", expression, String.class);

        Assert.assertNotNull(evaluationResponse);
        Assert.assertNotNull(evaluationResponse.getBody());
        Assert.assertEquals(evaluationResponse.getBody(), outcome);
    }

    @Test
    public void should_Evaluate_ListOfPolishNotations_Successfully_With_StatusCode_200() {
        String expression = "+ + 0.5 1.5 * 4 10\n- 2e3 - 700 + 7 * 2 15\n- -1.5 * 3.1415 / -7 -2\n100500\n1 2\n+ 1\n";

        ResponseEntity<?> evaluationResponse = restTemplate.postForEntity(getRootUrl() + "/api/evaluate", expression, String.class);

        Assert.assertEquals(evaluationResponse.getStatusCodeValue(), 200);

    }

    @Test
    public void should_return_with_400_when_empty_expression_is_provided() {
        String expression = "";

        ResponseEntity<?> evaluationResponse = restTemplate.postForEntity(getRootUrl() + "/api/evaluate", expression, String.class);
        Assert.assertEquals(evaluationResponse.getStatusCodeValue(), 400);

    }

    @Test
    public void should_return_with_error_when_expression_is_invalid_no_operator() {
        String expression = "1 2";

        ResponseEntity<?> evaluationResponse = restTemplate.postForEntity(getRootUrl() + "/api/evaluate", expression, String.class);
        Assert.assertEquals(evaluationResponse.getBody(), "error");

    }

    @Test
    public void should_return_with_error_when_expression_is_invalid_one_operand() {
        String expression = "+ 4";

        ResponseEntity<?> evaluationResponse = restTemplate.postForEntity(getRootUrl() + "/api/evaluate", expression, String.class);
        Assert.assertEquals(evaluationResponse.getBody(), "error");

    }

}
