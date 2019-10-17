package com.qa.notes.integration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({PostIT.class, GetIT.class, DeleteIT.class})
public class IntegrationSuite {

}
