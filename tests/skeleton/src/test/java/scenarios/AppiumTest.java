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

    }

    @Test
    public void loginPageTest() throws InterruptedException {
        new LoginPage(driver).loginPage();
    }

}