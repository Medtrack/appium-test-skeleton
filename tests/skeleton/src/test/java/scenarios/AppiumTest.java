package scenarios;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;

public class AppiumTest extends TestSetup {

    @BeforeClass
    public void setUp() throws Exception {
        //prepareAndroidForAppium();
        setUpContexts();
    }

    @AfterClass
    public void tearDown() throws Exception {
        //driver.quit();
        driverQuit();
    }

    @Test(priority=1, description="Launches the test 1")
    public void loginPageTest() throws InterruptedException {
        new LoginPage(driver).loginPage();
    }

    @Test(priority=2, description="Launches the test 2")
    public void loginPage2Test() throws InterruptedException {
        new LoginPage(driver).loginPage2();
    }

}