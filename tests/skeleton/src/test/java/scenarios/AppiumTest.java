package scenarios;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;

public class AppiumTest extends TestSetup {

    @BeforeClass
    public void setUp() throws Exception {
        prepareAndroidForAppium();
        setUpContexts();
    }

    @AfterClass
    public void tearDown() throws Exception {
        //driver.quit();
        driverQuit();
    }

    @Test(priority=1, description="Counts from one to five")
    public void loginPageTest() throws InterruptedException {
        new LoginPage(driver).countOneToFive();
    }

    @Test(priority=2, description="Reads the title")
    public void loginPage2Test() throws InterruptedException {
        new LoginPage(driver).readTitle();
    }

    @Test(priority=3, description="Counts from six to teb")
    public void loginPage3Test() throws InterruptedException {
        new LoginPage(driver).countsSixToTen();
    }

}