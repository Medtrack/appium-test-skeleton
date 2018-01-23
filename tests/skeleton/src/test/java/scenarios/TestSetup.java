package scenarios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Created by medtrack on 21/12/17.
 */
public class TestSetup {
    protected AndroidDriver androidDriver;
    protected IOSDriver iosdriver;
    protected AppiumDriver<WebElement> webdriver;
    protected Map<String, String> contexts;

    protected void printCapabilities(Capabilities cap){
        Map<String, ?> map =  cap.asMap();
        System.out.println("--------- PRINTING DEFAULT CAPABILITIES ---------");
        for (String envName : map.keySet()) {
            System.out.format("%s=%s%n", envName, map.get(envName));
        }
        System.out.println("-----------------------------------------------");
    }

    protected Capabilities getDefaultCapabilities() throws MalformedURLException {
        AppiumDriver<WebElement> defaultDriver;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        try {
            //defaultDriver = new AppiumDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            //return defaultDriver.getCapabilities();
            return capabilities;
        } catch (Exception ex){
            throw  new MalformedURLException(ex.getMessage());
        }
    }

    protected boolean isiOS(){
        try {
            Capabilities defaultCapabilities = getDefaultCapabilities();
            printCapabilities(defaultCapabilities);
            String platform = defaultCapabilities.getCapability("platformName").toString();
            String app =  defaultCapabilities.getCapability("app").toString().toLowerCase();
            return app.indexOf(".ipa") != -1 || platform.indexOf("mac") != -1 || platform.indexOf("ios") != -1;
        } catch (MalformedURLException m){
            return false;
        }

    }

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
        androidDriver =  new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        //AppiumDriver androidDriver = new AppiumDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        webdriver = (AppiumDriver<WebElement>) androidDriver;
    }

    /*iphone_6: {
            browserName: '',
            'appium-version': '1.6',
            platformName: 'iOS',
            platformVersion: '10.3.2',
            deviceName: 'iPhone 6',
            automationName: "XCUITest",
            xcodeOrgId: 'HNE5T79DFE',//NAFS4KAQKJ
            xcodeSigningId:"iPhone Developer",
            startIWDP: true,
            udid: '180ab6275a376cccae21f7e25c59c43dd8c068a1',
            app: "/Users/medtrack/Desktop/testlogin/MedFit/utilities/mobile-app/apps/ios/MedFit.ipa"
            //app: "/Users/medtrack/Desktop/testlogin/MedFit/utilities/mobile-app/apps/ios/dev/MedFit.ipa",
            //fullReset: false,
            //noReset: true
        },*/

    protected void prepareIosForAppium() throws MalformedURLException {
        File appDir = new File("apps");
        File app = new File(appDir, "hola-mundo.ipa");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device","iOS");
        capabilities.setCapability("deviceName","dummy");
        capabilities.setCapability("xcodeOrgId", "HNE5T79DFE");
        capabilities.setCapability("xcodeSigningId", "iPhone Developer");
        capabilities.setCapability("autoWebview", true);
        capabilities.setCapability("startIWDP", true);
        capabilities.setCapability("platformName","iOS");
        capabilities.setCapability("automationName","XCUITest");
        capabilities.setCapability("udid","180ab6275a376cccae21f7e25c59c43dd8c068a1");
        capabilities.setCapability("app", app.getAbsolutePath());
        iosdriver =  new IOSDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        //AppiumDriver iosdriver = new AppiumDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        webdriver = (AppiumDriver<WebElement>) iosdriver;
        System.out.println("ios prepare" +webdriver.toString() + " "+iosdriver.toString());
    }

    protected void prepareDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        AppiumDriver driver = new AppiumDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        webdriver = driver;
    }

    protected void printProperties() {
        System.out.println("--------- PRINTING SYSTEM PROPERTIES ---------");
        Properties p = System.getProperties();
        Enumeration keys = p.keys();
        while (keys.hasMoreElements()) {
            String key = (String)keys.nextElement();
            String value = (String)p.get(key);
            System.out.println(key + ": " + value);
        }
        System.out.println("-----------------------------------------------");
    }

    protected void printEnvVars() {
        System.out.println("--------- PRINTING ENVIRONMENT VARIABLES ---------");
        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            System.out.format("%s=%s%n", envName, env.get(envName));
        }
        System.out.println("-----------------------------------------------");
    }

    protected void executeCommand(String command) {
        System.out.println("--------- EXECUTING "+command+" ---------");
        StringBuffer output = new StringBuffer();
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }
        } catch (Exception e) {
            //e.printStackTrace();
            output.append(e.getMessage());
        }
        System.out.println(output);
        System.out.println("-----------------------------------------------");

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
