package com.qa;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.qa.controller.NoteControllerTest;
import com.qa.service.NoteServiceTest;

@RunWith(Suite.class)
@SuiteClasses({NoteControllerTest.class, NoteServiceTest.class})
public class ControllerAndServiceSuite {

}
