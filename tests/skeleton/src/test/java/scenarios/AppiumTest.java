package scenarios;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.util.Map;

public class AppiumTest extends TestSetup {
    final String ENVIRONMENT1 = ( System.getProperty("os.name").toLowerCase().indexOf("mac") != -1 ) ? "IOS" : "ANDROID";
    //final String ENVIRONMENT = ( System.getProperty("os.name").toLowerCase().indexOf("mac") != -1 ) ? "IOS" : "ANDROID";
    final String ENVIRONMENT = "IOS";

    @BeforeClass
    public void setUp() throws Exception {
        String osName = System.getProperty("os.name");
        System.out.println("OS NAME: "+ ENVIRONMENT + " " + ENVIRONMENT1 );
        if (ENVIRONMENT != "IOS"){
            prepareAndroidForAppium();
        } else {
            prepareIosForAppium();
        }
        setUpContexts();
    }

    @AfterClass
    public void tearDown() throws Exception {
        //driver.quit();
        driverQuit();
    }

    @Test(priority=1, description="Counts from one to five")
    public void loginPageTest() throws InterruptedException {
        new LoginPage(webdriver).countOneToFive();
    }

    @Test(priority=2, description="Reads the title")
    public void loginPage2Test() throws InterruptedException {
        new LoginPage(webdriver).readTitle();
    }

    @Test(priority=3, description="Counts from six to teb")
    public void loginPage3Test() throws InterruptedException {
        new LoginPage(webdriver).countsSixToTen();
    }
}