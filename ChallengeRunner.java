package com.yourname.bajajchallenge;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.json.JSONObject;

@SpringBootApplication
public class ChallengeRunner implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ChallengeRunner.class, args);
    }

    @Override
    public void run(String... args) {
        RestTemplate restTemplate = new RestTemplate();

        String regNo = "PES1UG22EC176";
        String url = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject requestJson = new JSONObject();
        requestJson.put("name", "Nikhil Shiraguppi"); 
        requestJson.put("regNo", regNo);
        requestJson.put("email", "nikhilshiraguppi@gmail.com"); 

        HttpEntity<String> entity = new HttpEntity<>(requestJson.toString(), headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        JSONObject respJson = new JSONObject(response.getBody());
        String webhookUrl = respJson.getString("webhookUrl");
        String accessToken = respJson.getString("accessToken");

        HttpHeaders submitHeaders = new HttpHeaders();
        submitHeaders.setContentType(MediaType.APPLICATION_JSON);
        submitHeaders.setBearerAuth(accessToken);

        String sql = "SELECT e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME, " +
                     "COUNT(e2.EMP_ID) AS YOUNGER_EMPLOYEES_COUNT " +
                     "FROM EMPLOYEE e1 " +
                     "JOIN DEPARTMENT d ON e1.DEPARTMENT = d.DEPARTMENT_ID " +
                     "LEFT JOIN EMPLOYEE e2 ON e2.DEPARTMENT = e1.DEPARTMENT AND e2.DOB > e1.DOB " +
                     "GROUP BY e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME " +
                     "ORDER BY e1.EMP_ID DESC;";

        JSONObject answerJson = new JSONObject();
        answerJson.put("finalQuery", sql);

        HttpEntity<String> answerEntity = new HttpEntity<>(answerJson.toString(), submitHeaders);
        ResponseEntity<String> submitResponse = restTemplate.postForEntity(webhookUrl, answerEntity, String.class);
        System.out.println(submitResponse.getBody());
    }
}
