package org.seleniummonster.com;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.StringTokenizer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Google {
    private WebDriver driver;

    @DataProvider(name="keywords")
    public Object[][] data() throws Exception {
        HashMap<String,String[]> dataSet= new Text2TestData(System.getProperty("user.dir")+"\\config.properties").getData();
        //Let us assume username and password dataset
        int size =2;
        Object[][] creds = new Object[size][size];
        String usernameStrings[]=dataSet.get("username");
        String passwordStrings[]=dataSet.get("password");
        for(int i=0;i<size;i++)
        {
            creds[i][0]=usernameStrings[i];
            creds[i][1]=passwordStrings[i];
        }
        return creds;
    }


    @BeforeTest
    public void setUp() throws Exception {
        driver = new FirefoxDriver();

    }


    @Test(dataProvider = "keywords", description = "Google_Test")
    public void search(String username, String password) throws Exception {

        driver.get("http://www.google.co.in");

        // search google via keyword 1
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("" + username);
        driver.findElement(By.name("q")).submit();
        Thread.sleep(4000);


        // search google via keyword 1
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("" + password);
        driver.findElement(By.name("q")).submit();
        Thread.sleep(4000);

    }

    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }

}
