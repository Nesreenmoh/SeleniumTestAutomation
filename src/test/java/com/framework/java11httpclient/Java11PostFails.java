package com.framework.java11httpclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Java11PostFails {


    public static final String BaseURL = "https://api.github.com/";

    // define the client
    HttpClient httpClient = HttpClient.newBuilder().build();

    HttpResponse<Void> response;


    @BeforeEach
    void sendGetToBaseEndPoint() throws IOException, InterruptedException {

        // define the request as a post
        HttpRequest post = HttpRequest.newBuilder(URI.create(BaseURL+"user/repos"))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();


        //act
        response = httpClient.send(post, HttpResponse.BodyHandlers.discarding());
    }

    @Test
    void postWithoutAuthorizationPost(){

        int actualCode= response.statusCode();

        Assertions.assertEquals(401,actualCode);
    }
}
