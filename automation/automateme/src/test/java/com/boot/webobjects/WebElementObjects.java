package com.boot.webobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WebElementObjects {
	Select dropSelector;
	
	public WebElement getHeader(WebDriver driver) {
		return driver.findElement(By.xpath("//*[@id=\"label-first\"]/b"));
	}
	
	/**
	 * List of todo
	 */
	public WebElement formFetch(WebDriver driver, String task) {
		return driver.findElement(By.xpath("//*[@id=\"todos-content\"]/form/ul/li[last()]/input/following-sibling::text()"));
	}
	
	/**
	 * Controls
	 */
	public WebElement todoRemove(WebDriver driver) {
		return driver.findElement(By.xpath("//div[@class=\"controls\"]/input[@value=\"Remove\"]"));
	}
	
	public WebElement todoComplete(WebDriver driver) {
		return driver.findElement(By.xpath("//div[@class=\"controls\"]/input[@value=\"Complete\"]"));
	}
	
	public WebElement todoToggleAll(WebDriver driver) {
		return driver.findElement(By.xpath("//div[@class=\"controls\"]/input[@name=\"allbox\"]"));
	}
	
	/**
	 * Advanced Controls - Data [Add To Do task]
	 */
	public WebElement setTodoData(WebDriver driver) {
		return driver.findElement(By.xpath("//div[@class=\"advance-controls\"]/input[@name=\"data\"]"));
	}
	
	public WebElement todoSubmit(WebDriver driver) {
		return driver.findElement(By.xpath("//div[@class=\"advance-controls\"]/input[@value=\"Add\"]"));
	}
	
	public void setNewTodoCategoryColor(WebDriver driver, String category) {
		dropSelector = new Select(
				driver.findElement(By.xpath("//div[@class=\"advance-controls\"]//select[@name=\"category\"]")));
		dropSelector.selectByVisibleText(category);
	}
	
	public void setNewTodoDueDay(WebDriver driver) {
		dropSelector = new Select(
				driver.findElement(By.xpath("//div[@class=\"advance-controls\"]//select[@name=\"due_day\"]")));
		dropSelector.selectByVisibleText("None");
	}
	
	public void setNewTodoDueDay(WebDriver driver, Integer day) {
		dropSelector = new Select(
				driver.findElement(By.xpath("//div[@class=\"advance-controls\"]//select[@name=\"due_day\"]")));
		if (day > 0 & day <= 31) {
			dropSelector.selectByVisibleText(day.toString());
		}
		else {
			dropSelector.selectByVisibleText("None");
		}
	}
	
	public void setNewTodoDueMonth(WebDriver driver) {
		dropSelector = new Select(
				driver.findElement(By.xpath("//div[@class=\"advance-controls\"]//select[@name=\"due_month\"]")));
		dropSelector.selectByVisibleText("None");
	}
	
	public void setNewTodoDueMonth(WebDriver driver, String month) {
		// TODO - check month value
		dropSelector = new Select(
				driver.findElement(By.xpath("//div[@class=\"advance-controls\"]//select[@name=\"due_month\"]")));
		dropSelector.selectByVisibleText(month);
	}
	
	public void setNewTodoDueYear(WebDriver driver) {
		dropSelector = new Select(
				driver.findElement(By.xpath("//div[@class=\"advance-controls\"]//select[@name=\"due_year\"]")));
		dropSelector.selectByVisibleText("None");
	}
	
	public void setNewTodoDueYear(WebDriver driver, Integer year) {
		// TODO - check year value
		dropSelector = new Select(
				driver.findElement(By.xpath("//div[@class=\"advance-controls\"]//select[@name=\"due_year\"]")));
		dropSelector.selectByVisibleText(year.toString());
	}
	
	/**
	 * Advanced Controls - Category [Add New Category]
	 */
	public WebElement todoNewCategoryData(WebDriver driver) {
		return driver.findElement(By.xpath("//div[@class=\"advance-controls\"]/span[@id=\"extra\"]/input[@name=\"categorydata\"]"));
	}
	
	// Change input value tag from Add category to add-category
	public WebElement todoNewCategorySubmit(WebDriver driver) {
		return driver.findElement(By.xpath("//div[@class=\"advance-controls\"]//input[@value=\"Add category\"]"));
	}
	
	public void setNewCategoryColor(WebDriver driver, String color) {
		dropSelector = new Select(
				driver.findElement(By.xpath("//div[@class=\"advance-controls\"]//select[@name=\"colour\"]")));
		dropSelector.selectByVisibleText(color);
	}
	
	/**
	 * To Do already exists
	 */
	public boolean todoAlreadyExists(WebDriver driver) {
		return (driver.findElements(By.xpath("/html/body/a/preceding-sibling::text()[0]")).size() > 0) ? true : false;
	}
	
	public void todoAlreadyExistsGoBack(WebDriver driver) {
		driver.findElement(By.xpath("//body/a[.='Back']")).click();;
	}
}
