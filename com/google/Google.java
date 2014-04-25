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
import java.util.StringTokenizer;


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
     	// try to print creds by to string here 

     return creds;
 }
 
 
  @BeforeTest
 public void setUp() throws Exception {  
  driver = new ChromeDriver();

 }

  
  @Test(dataProvider = "keywords", description = "Google_Test")
  public void search(String search_keyword1, String search_keyword2) throws Exception {
  	
	
	
	
	 StringTokenizer stKey1 = new StringTokenizer(search_keyword1, ",");
      StringTokenizer stKey2 = new StringTokenizer(search_keyword2, ",");

      driver.get("http://www.google.co.in");

      while (stKey1.hasMoreElements()) {

          //  search google via keyword 1
          driver.findElement(By.name("q")).sendKeys();
          driver.findElement(By.name("q")).submit();
          Thread.sleep(4000);
          Boolean a = driver.getTitle().contains("Prashanth Sams"); 
          System.out.println(a);
                    
          while (stKey1.hasMoreElements()) {
              //  search google via keyword 2      
              driver.findElement(By.name("q")).clear();
              driver.findElement(By.name("q")).sendKeys(stKey2.nextElement());
              Thread.sleep(4000);
              Boolean b = driver.getTitle().contains("seleniumworks");
              System.out.println(b);
      }
	
	  
      }
	 
	
	/*// print both search_keyword1 and search_keyword2, i m sure the search_keyword2 will be empty or null

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
      System.out.println(b);*/
      
  }

  @AfterTest
 public void tearDown() throws Exception {
  driver.quit();
 }
    
}
