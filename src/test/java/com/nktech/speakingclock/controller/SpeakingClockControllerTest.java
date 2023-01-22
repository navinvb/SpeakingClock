package com.nktech.speakingclock.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class SpeakingClockControllerTest {

    private RestTemplate restTemplate;

    @BeforeEach
    public void beforeTest() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void testSpeakingClockWithValidInput() {
        String uri = "http://localhost:8080/currentTime/11:23";
        HttpEntity httpEntity = new HttpEntity(new HttpHeaders());
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
        String resultString = result.getBody();
        assert resultString != null;
        Assertions.assertTrue(resultString.contains("It's  Eleven Twenty  Three"));
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testSpeakingClockWithInValidInput() {
        String uri = "http://localhost:8080/currentTime/some:text";
        HttpEntity httpEntity = new HttpEntity(new HttpHeaders());
        HttpClientErrorException result =
                Assertions.assertThrows(HttpClientErrorException.class, () -> restTemplate.exchange(uri, HttpMethod.GET, httpEntity, Object.class));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }


}
