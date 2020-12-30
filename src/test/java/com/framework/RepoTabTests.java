package com.framework;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepoTabTests extends BaseTestClass{


    @Test
    void repoCountIsCorrect() {

        //act

        driver.get("https://github.com/Nesreenmoh?tab=repositories");


        List<WebElement> repoLink = driver.findElements(By.xpath("//div[@id='user-repositories-list']//li"));

        //assert
        assertEquals(17, repoLink.size());

    }
}
