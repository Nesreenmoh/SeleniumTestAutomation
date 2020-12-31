package com.framework.java11httpclient;

import com.testframework.entities.User;
import com.testframework.handlers.JsonBodyHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Java11BodyTest {


    public static final String BaseURL = "https://api.github.com/";

    // define the client
    HttpClient httpClient = HttpClient.newBuilder().build();

    HttpResponse<String> response;
    HttpRequest get;

    @BeforeEach
    void sendGetToBaseEndPoint() throws IOException, InterruptedException {

        // define the request as a post
        get = HttpRequest.newBuilder(URI.create(BaseURL+"users/Nesreenmoh"))
                .setHeader("User-Agent","Java 11 HttpClient")
                .build();

        response = httpClient.send(get, HttpResponse.BodyHandlers.ofString());

    }

    @Test
    void bodyContainsCurrentUserUrl(){

        //act

        String body= response.body();

        //assert
        Assertions.assertTrue(body.contains("\"login\":\"Nesreenmoh\""));


    }

    // this is the right way of making a body test/ jason return data test from website
    // creating a User object
    // create a handler to map between the data and the object
    @Test
    void handleJsonBody() throws IOException, InterruptedException {
        //arrange create response

        get = HttpRequest.newBuilder(URI.create(BaseURL+"users/Nesreenmoh"))
                .setHeader("User-Agent","Java 11 HttpClient")
                .build();

       HttpResponse<User> response =
               httpClient.send(get, JsonBodyHandler.jsonBodyHandler(User.class));

       String actualLogin = response.body().getLogin();

       //Assertion
        Assertions.assertEquals("Nesreenmoh",actualLogin);

    }

}
