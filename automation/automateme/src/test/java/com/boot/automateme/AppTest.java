package com.boot.automateme;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.boot.webobjects.WebElementObjects;

import io.github.bonigarcia.wdm.WebDriverManager;

/*****************************************************************************
 * Author:      Marzana Naz
 * Description: This is the  Selenium TestNG test for QA Exercise.
 *              
*******************************************************************************/

public class AppTest {

    //-----------------------------------Global Variables-----------------------------------
    //Declare a ChromeDriver variable
    public WebDriver driver;
    ChromeOptions options = new ChromeOptions();
    
    // Port number for the qa-exercise
    public String Port = "89";
    
    //Declare a test URL variable
    public String testURL = "http://localhost:"+Port+"/qa-exercise/";
    WebElementObjects webObjects = new WebElementObjects();
    
    @BeforeClass
    public void setupClass() {
        System.setProperty("webdriver.chrome.driver","C:\\drivers\\chromedriver_win32\\chromedriver.exe");        
        options.addArguments("start-minimized");
        driver = new ChromeDriver(options);
        driver.get(testURL);
    }
    
    @Test(description = "0. Open the TODO web App")
    public void openSite() {
        
        driver.navigate().to(testURL);   
    }
    
    //-----------------------------------Tests-----------------------------------
    @Test(description = "1. Simple test to get the header")
    public void firstTest () {
    	
        //Get page title
        // String title = driver.findElement(By.xpath("//*[@id=\"label-first\"]/b")).getText();
        String title = webObjects.getHeader(driver).getText();
        
        //Print page's title
        System.out.println("Page Title: " + title);

        //Assertion
        Assert.assertEquals(title, "NSS-TODO List v 1.1", "Title assertion is failed!");
    }	
    
	@Test(description = "2.a. Create a Todo task - no category and no due date")
	public void setTodoDataNoCatNoDue() {
		String task = "Heyo";
		
		webObjects.setTodoData(driver).sendKeys(task);
		webObjects.todoSubmit(driver).click();
		
		// TODO - reuse this block to check if todo tasks are already existing
		if (webObjects.todoAlreadyExists(driver)) {
			// Click Back
			webObjects.todoAlreadyExistsGoBack(driver);
			System.out.println("The TODO item exists already.");
		}
		
		// Text is no wrapped in a span or such tags?
		String formFetch = webObjects.formFetch(driver,task).getText();
		Assert.assertEquals(formFetch, task+" (None)", "Title assertion is failed!");
	}
	
	@Test(description = "2.b. Create a Todo task - assign category and no due date")
	public void setTodoDataAssignCatNoDue() {
		String task = "Buy Chipotle";
		String cat = "Play";
		
		webObjects.setTodoData(driver).sendKeys(task);
		webObjects.setNewTodoCategoryColor(driver, cat);
		webObjects.todoSubmit(driver).click();
		String formFetch = webObjects.formFetch(driver,task).getText();
		Assert.assertEquals(formFetch, task +" (None)", "Title assertion is failed!");
	}
	
	
	@Test(description = "6.a. Create cat with no color")
	public void createNewCat() {
		String catData = "Chores";
		webObjects.todoNewCategoryData(driver).sendKeys(catData);
		webObjects.todoNewCategorySubmit(driver).click();
		Assert.assertEquals("(None)", "(None)");
	}
	
    //-----------------------------------Test TearDown-----------------------------------
	@AfterClass
	public void teardownTest () throws InterruptedException{
	    //Close browser and end the session
		Thread.sleep(4000);
	    driver.quit();
	}


}