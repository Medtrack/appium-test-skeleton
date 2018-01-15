package scenarios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by medtrack on 21/12/17.
 */
public class TestSetup {
    protected AndroidDriver driver;
    protected IOSDriver iosdriver;
    protected AppiumDriver<WebElement> webdriver;
    protected Map<String, String> contexts;
    protected void prepareAndroidForAppium() throws MalformedURLException {
        File appDir = new File("apps");
        File app = new File(appDir, "hola-mundo.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device","Android");
        capabilities.setCapability("appActivity",".MainActivity");
        capabilities.setCapability("deviceName","dummy");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("appPackage","me.tararea.hola_mundo");
        capabilities.setCapability("fullReset", true);
        capabilities.setCapability("automationName", "UiAutomator2");
        //other caps
        capabilities.setCapability("app", app.getAbsolutePath());
        driver =  new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        webdriver = (AppiumDriver<WebElement>) driver;
    }
    protected void prepareIosForAppium() throws MalformedURLException {
        File appDir = new File("apps");
        File app = new File(appDir, "hola-mundo.ipa");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        /*capabilities.setCapability("device","Android");
        capabilities.setCapability("appActivity",".MainActivity");
        capabilities.setCapability("deviceName","dummy");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("appPackage","com.hola.mundo");
        capabilities.setCapability("fullReset", true);
        capabilities.setCapability("automationName", "UiAutomator2");*/
        //other caps
        capabilities.setCapability("app", app.getAbsolutePath());
        iosdriver =  new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        //webdriver = (AppiumDriver<WebElement>) driver;
    }



    protected void setUpContexts(){
        try {
            Set<String> contextNames = webdriver.getContextHandles();
            contexts = new HashMap();
            for (String contextName : contextNames) {
                System.out.println(contextName);
                if (contextName.contains("WEBVIEW")){
                    Contexts.WEBVIEW = contextName;
                }
                if(contextName.contains("NATIVE")){
                    Contexts.NATIVE = contextName;
                }
            }
            System.out.println(Contexts.NATIVE+" "+Contexts.WEBVIEW);
            System.out.println("setting up contexts");

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    protected void driverQuit(){
        //driver.quit();
        System.out.println("Quitting driver");
    }

    public static class Contexts {
        public static String WEBVIEW = "";
        public static String NATIVE = "";
    }
}
