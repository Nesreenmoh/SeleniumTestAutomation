package com.framework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OverviewTabTests extends BaseTestClass {


    String user = "Nesreenmoh";

    @BeforeEach
    void overviewSetup(){
        // open the browser in this page https://github.com/Nesreenmoh
        driver.get(BaseURL + user);
    }

    @Test
    void userNameIsCorrectOnOverviewTab() {


        //act
        String actualUserName = driver.findElement(By.className("p-nickname")).getText();
        //assert

        assertEquals(user, actualUserName);

    }

    @Test
    void repoClickGoesToCorrectRepo() {

        //act

        String repo = "Restaurant-Java-JavaScript";
        WebElement repoLink = driver.findElement(By.linkText(repo));
        repoLink.click();
        String actualURL = driver.getCurrentUrl();

        //assert
        assertEquals(BaseURL+"Nesreenmoh/" + repo, actualURL);

    }



}
