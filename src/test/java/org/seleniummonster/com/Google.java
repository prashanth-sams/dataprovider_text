package org.seleniummonster.com;

import java.io.FileInputStream;
import java.util.Arrays;
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

	 System.out.println("The file is @ "+System.getProperty("user.dir")+"/config.properties");
     FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"/config.properties");
     Properties prop = new Properties();
     prop.load(fs);
        
     Object[][] creds = new Object[1][2];
     creds[0][0] = prop.getProperty("search1");
     creds[0][1] = prop.getProperty("search2");

System.out.println(":::::::::::::::::::::::::::::: The creds"+Arrays.toString(creds));
     return creds;
 }
 
 
  @BeforeTest
 public void setUp() throws Exception {  
  driver = new FirefoxDriver();

 }

  
  @Test(dataProvider = "keywords", description = "Google_Test")
  public void search(String search_keyword1, String search_keyword2) throws Exception {
	  
	  System.out.println(":::::::::::::::::::::::::Keyword 1,"+search_keyword1);
	  System.out.println(":::::::::::::::::::::::::Keyword 2,"+search_keyword2);
	  
      StringTokenizer stKey1 = new StringTokenizer(search_keyword1, ",");
      StringTokenizer stKey2 = new StringTokenizer(search_keyword2, ",");

      driver.get("http://www.google.co.in");
      
      while (stKey1.hasMoreElements()&& stKey2.hasMoreElements()) {

			// search google via keyword 1
			driver.findElement(By.name("q")).clear();
			driver.findElement(By.name("q")).sendKeys("" + stKey1.nextElement());
			driver.findElement(By.name("q")).submit();
			Thread.sleep(4000);
			System.out.println(driver.getTitle().contains("Prashanth Sams"));
			// search google via keyword 2
			driver.findElement(By.name("q")).clear();
			driver.findElement(By.name("q")).sendKeys("" + stKey2.nextElement());
			driver.findElement(By.name("q")).submit();
			Thread.sleep(4000);
			System.out.println(driver.getTitle().contains("seleniumworks"));
      }
  }

  @AfterTest
 public void tearDown() throws Exception {
  driver.quit();
 }
    
}
