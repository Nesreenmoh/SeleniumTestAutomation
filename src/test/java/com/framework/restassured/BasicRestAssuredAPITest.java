package com.framework.restassured;

import io.restassured.RestAssured;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class BasicRestAssuredAPITest {


    @Test
    public void getStatusCodeIs200(){
        RestAssured.get("https://api.github.com")
                .then()
                .statusCode(200);
    }

    @Test
    public void handlersContainCorrectValues(){

        RestAssured.get("https://api.github.com")
                .then()
                .assertThat()
                .header("content-type","application/json; charset=utf-8")
                .header("X-Ratelimit-Limit","60");

    }

    @Test
    public void bodyContainsCorrectValues(){
        RestAssured.get("https://api.github.com/users/Nesreenmoh")
                .then()
                .assertThat()
                .body("login",equalTo("Nesreenmoh"))
                .body("type",equalTo("User"));

    }

    @Test
    public void postFails(){
        RestAssured.post("https://api.github.com/user/repos")
                .then()
                .assertThat()
                .statusCode(401)
                .body("message", equalTo("Requires authentication"))
                .body(Matchers.containsString("Requires authentication"));



    }
}
