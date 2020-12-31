package com.framework.java11httpclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Java11GetHeaderTest {

    public static final String BaseURL = "https://api.github.com/users/nesreenmoh";

    // define the client
    HttpClient httpClient = HttpClient.newBuilder().build();

    HttpResponse<Void> response;


    @BeforeAll
    void sendGetToBaseEndPoint() throws IOException, InterruptedException {

        // define the request
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(BaseURL))
                .setHeader("User-Agent", "Java 11 Http bol")
                .build();

        //act
        response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.discarding());
    }

    @Test
    void getReturn200(){

        int statusCode = response.statusCode();

        //Assertion
        Assertions.assertEquals(200,statusCode);
    }

    @Test
    void contentTypeJSON() {

        String contentType = response.headers().firstValue("content-type").get();

        //Assertion
        Assertions.assertEquals("application/json; charset=utf-8",contentType);
    }


    @Test
    void xRateLimitIsPresent(){

        String xRateLimit = response.headers().firstValue("X-Ratelimit-Limit").get();

        //Assertion
        Assertions.assertEquals(60,Integer.parseInt(xRateLimit));
    }


    @Test
    void xRateRemainingIsPresent() {

        String xRateLimitRemaining = response.headers().firstValue("X-Ratelimit-Remaining").get();

        //Assertion
        Assertions.assertEquals(50,Integer.parseInt(xRateLimitRemaining));
    }


    //Refactoring the previous codes and test to passing different parameters

    @ParameterizedTest
    @CsvSource({
            "X-Ratelimit-Limit,60",
            "content-type,application/json; charset=utf-8",
            "X-Ratelimit-Remaining,37"
    })
    void parametrizedTestForHeaders(String header,String expectedValue) {

        String content = response.headers().firstValue(header).get();

        //Assertion
        Assertions.assertEquals(expectedValue,content);


    }
}
