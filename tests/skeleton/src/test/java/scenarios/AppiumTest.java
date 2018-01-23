package scenarios;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.net.MalformedURLException;

public class AppiumTest extends TestSetup {
   // final String ENVIRONMENT = ( System.getProperty("os.name").toLowerCase().indexOf("mac") != -1 ) ? "IOS" : "ANDROID";
   public String platform = "android";

    @BeforeClass
    public void setUp() throws Exception {
        //String osName = System.geqtProperty("os.name");
        //platform = isiOS() ? "ios" : "android";
        //System.out.println("OS NAME: "+ platform + " " + platform + " " + osName );
        //printProperties();
        //printEnvVars();

        /*if (platform != "ios"){
            prepareAndroidForAppium();
        } else {
            prepareIosForAppium();
        }*/
        System.out.println("Trying to execute simple Appium Driver" );
        try {
            prepareDriver();
        }catch (MalformedURLException m){
            System.out.println(m.getMessage());
        }

        setUpContexts();
    }

    @AfterClass
    public void tearDown() throws Exception {
        //dri ver.quit();
        driverQuit();
    }

    @Test(priority=1, description="Counts from one to five")
    public void loginPageTest() throws InterruptedException {
        new LoginPage(webdriver).countOneToFive();
    }

/*
    @Test(priority=2, description="Reads the title")
    public void loginPage2Test() throws InterruptedException {
        new LoginPage(webdriver).readTitle();
    }

    @Test(priority=3, description="Counts from six to teb")
    public void loginPage3Test() throws InterruptedException {
        new LoginPage(webdriver).countsSixToTen();
    }
    */
}