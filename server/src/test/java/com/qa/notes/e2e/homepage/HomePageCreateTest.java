package com.qa.notes.e2e.homepage;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.notes.e2e.SeleniumConst;
import com.qa.notes.e2e.homepage.HomePagePOM;

public class HomePageCreateTest {

	private WebDriver driver;
	private HomePagePOM homepage;

	@Before
	public void setup() {
		System.setProperty(SeleniumConst.DRIVER_KEY, SeleniumConst.DRIVER_LOCATION);
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless");
		driver = new ChromeDriver(chromeOptions);
		driver.get(SeleniumConst.HOMEPAGE_URL);
		homepage = new HomePagePOM(driver);		
	}
	
	@Test
	public void addNoteTest() {
		new WebDriverWait(driver, 4).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-notes")));
		int before = homepage.noteLength();
		homepage.addNote(SeleniumConst.OLD_TEXT);
		assertEquals(before + 1, homepage.noteLength());
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
}
