package com.google;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Google {
 private WebDriver driver;
 
 @DataProvider(name="keywords")
 public Object[][] data() throws Exception {

     FileInputStream fs = new FileInputStream("C:\\config.properties");         
     Properties prop = new Properties();
     prop.load(fs);
        
     Object[][] creds = new Object[1][2];
     creds[0][0] = prop.getProperty("search1");
     creds[0][1] = prop.getProperty("search2");     
     return creds;
 }
 
 
  @BeforeTest
 public void setUp() throws Exception {  
  driver = new ChromeDriver();

 }

  
  @Test(dataProvider = "keywords", description = "Google_Test")
  public void search(String search_keyword1, String search_keyword2) throws Exception {      

	  driver.get("http://www.google.co.in");
	  //  search google via keyword 1
      driver.findElement(By.name("q")).sendKeys(search_keyword1);
      driver.findElement(By.name("q")).submit();
      Thread.sleep(4000);
      Boolean a = driver.getTitle().contains("Prashanth Sams");
      System.out.println(a);
	  //  search google via keyword 2      
      driver.findElement(By.name("q")).clear();
      driver.findElement(By.name("q")).sendKeys(search_keyword2);
      Thread.sleep(4000);
      Boolean b = driver.getTitle().contains("seleniumworks");
      System.out.println(b);
      
  }

  @AfterTest
 public void tearDown() throws Exception {
  driver.quit();
 }
    
}
