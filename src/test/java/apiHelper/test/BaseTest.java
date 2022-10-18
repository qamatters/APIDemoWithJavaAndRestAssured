package apiHelper.test;

import apiHelper.util.logs.Log;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    @BeforeClass
    public void classLevelSetup() {
        Log.info("Tests is starting!");
    }

    @BeforeMethod
    public void methodLevelSetup() {
        Log.info("Method execution starts");
    }

    @AfterClass
    public void teardown() {
        Log.info("Tests are ending!");
    }
}