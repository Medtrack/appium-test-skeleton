package scenarios;

//import com.sun.xml.internal.rngom.parse.xml.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.BaseTests;

import java.net.MalformedURLException;

public class AppiumTest extends TestSetup {
   // final String ENVIRONMENT = ( System.getProperty("os.name").toLowerCase().indexOf("mac") != -1 ) ? "IOS" : "ANDROID";
   public String platform = "android";

    @BeforeClass
    public void setUp() throws Exception {
        String osName = System.getProperty("os.name");
        String userName = System.getProperty("user.name");
        System.out.println("OS NAME: "+ osName + " userName " + userName );
        printProperties();
        printEnvVars();
        if(isUserInLocalTest(userName)){
            platform = ( osName.toLowerCase().indexOf("mac") != -1 ) ? "ios" : "android";
            System.out.println("Local test, choosing driver for platform " + platform );

            if (platform != "ios"){
                prepareAndroidForAppium();
            } else {
                prepareIosForAppium();
            }
        } else {
            System.out.println("Trying to execute simple Appium Driver" );
            try {
                prepareDriver();
            }catch (MalformedURLException m){
                System.out.println(m.getMessage());
            }
        }

        /**/

        setUpContexts();
    }

    @AfterClass
    public void tearDown() throws Exception {
        driverQuit();
    }

    @Test(priority=1, description="Counts from one to five")
    public void loginPageTest() throws InterruptedException {
        getBaseTests().countOneToFive();
    }

    @Test(priority=2, description="Reads the title", dependsOnMethods="loginPageTest")
    public void loginPage2Test() throws InterruptedException {
        getBaseTests().readTitle();
    }

    @Test(priority=3, description="Counts from six to ten", dependsOnMethods="loginPage2Test")
    public void loginPage3Test() throws InterruptedException {
        getBaseTests().countsSixToTen();
    }

}