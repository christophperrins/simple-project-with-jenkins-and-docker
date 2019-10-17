package com.qa.notes.e2e;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.qa.notes.e2e.homepage.HomePageCreateTest;
import com.qa.notes.e2e.homepage.HomePageDeleteTest;
import com.qa.notes.e2e.homepage.HomePageUpdateTest;

@RunWith(Suite.class)
@SuiteClasses({HomePageCreateTest.class, HomePageUpdateTest.class, HomePageDeleteTest.class})
public class SeleniumSuite {

	
}
