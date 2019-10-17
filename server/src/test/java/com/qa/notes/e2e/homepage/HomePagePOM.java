package com.qa.notes.e2e.homepage;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class HomePagePOM {

	private WebDriver driver;
	
	@FindBy(id = "textInput")
	private WebElement textInput;
	
	@FindBy(id = "submitInput")
	private WebElement submitInput;
	
	@FindBy(id = "notes")
	private WebElement noteList;
	
	@FindBys ({
		@FindBy(id = "notes"),
		@FindBy(tagName = "li")
	})
	private List<WebElement> listItems;
	
	public HomePagePOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void addNote(String text) {
		textInput.sendKeys(text);
		textInput.submit();
	}
	
	public int noteLength() {
		return listItems.size();
	}
	
	public void deleteNote(String searchText) {
		findNote(searchText).findElement(By.tagName("button")).click();
	}
	
	public void updateNote(String searchText, String replaceText) throws InterruptedException {
		WebElement element = findNote(searchText);
		
		element.findElement(By.tagName("p")).click();
		WebElement input = element.findElement(By.tagName("input"));
		input.clear();
		input.sendKeys(replaceText);
		input.submit();
//		return findNote(replaceText).findElement(By.tagName("p")).getText();
	}
	
	private WebElement findNote(String text) {
		for(WebElement element: listItems) {
			if(element.findElement(By.tagName("p")).getText().equals(text)) {
				return element;
			}
		}
		throw new RuntimeException("WebElement not found");
		
	}
	
}
