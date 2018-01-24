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
    private BaseTests mainTest;

    @BeforeClass
    public void setUp() throws Exception {
        String osName = System.getProperty("os.name");
        String userName = System.getProperty("user.name");
        System.out.println("OS NAME: "+ osName + " userName " + userName );
        printProperties();
        printEnvVars();
        boolean localTest = isUserInLocalTest(userName);
        if(localTest){
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
        mainTest = new BaseTests(webdriver, localTest);
        setUpContexts();
    }

    @AfterClass
    public void tearDown() throws Exception {
        driverQuit();
    }

    @Test(description="browses the page the title")
    public void baseTest() throws InterruptedException {
        mainTest.browsePages();
    }
}