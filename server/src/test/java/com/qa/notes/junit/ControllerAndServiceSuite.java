package com.qa.notes.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.qa.notes.junit.controller.NoteControllerTest;
import com.qa.notes.junit.service.NoteServiceTest;

@RunWith(Suite.class)
@SuiteClasses({NoteControllerTest.class, NoteServiceTest.class})
public class ControllerAndServiceSuite {

}
