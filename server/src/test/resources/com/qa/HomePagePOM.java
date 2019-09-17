package com.qa;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class HomePagePOM {

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
}
