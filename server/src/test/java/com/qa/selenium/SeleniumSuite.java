package com.qa.selenium;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.qa.selenium.homepage.HomePageCreateTest;
import com.qa.selenium.homepage.HomePageDeleteTest;
import com.qa.selenium.homepage.HomePageUpdateTest;

@RunWith(Suite.class)
@SuiteClasses({HomePageCreateTest.class, HomePageUpdateTest.class, HomePageDeleteTest.class})
public class SeleniumSuite {

}
