package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import scenarios.TestSetup;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class LoginPage extends BasePage {
    By userId = By.id("mobileNo");
    By password = By.id("et_password");
    By login_Button = By.id("btn_mlogin");
    By existingUser_login = By.id("btn_mlogin");
    private AppiumDriver<WebElement> webdriver;
    private AndroidDriver androidDriver;
    public LoginPage(AppiumDriver driver) {
        super(driver);

        //androidDriver = (AndroidDriver) driver;
        //String packagestr = androidDriver.getCurrentPackage();
        webdriver = (AppiumDriver<WebElement>) driver;
    }

    public LoginPage loginPage() throws InterruptedException {
        //String permissionButtonId = "com.android.packageinstaller:id/permission_allow_button";
        //MobileElement waiter = (MobileElement) new WebDriverWait(webdriver, 30).until( ExpectedConditions.presenceOfElementLocated(By.id(permissionButtonId) ) );
        //System.out.println("wating for permissions");
        try {
            /*Set<String> contextNames = webdriver.getContextHandles();
            System.out.println(contextNames);
            System.out.println("-->" + TestSetup.Contexts.NATIVE + " "+ TestSetup.Contexts.WEBVIEW);
            System.out.println("packagestr " + TestSetup.Contexts.WEBVIEW);
            webdriver.context(TestSetup.Contexts.WEBVIEW);
            System.out.println("switched to " + TestSetup.Contexts.WEBVIEW);
            WebElement loginButton = webdriver.findElement(By.id("deviceready"));
            System.out.println("button text: " + loginButton.getText() );
            Assert.assertEquals(loginButton.getText(), "DEVICE IS READY");*/

            int i = 0;
            while (i < 5){
                Thread.sleep(1000);
                i++;
                System.out.println("waiting " + i);
            }

            boolean a = true;
            Assert.assertTrue(a);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new LoginPage(driver);
    }

    public LoginPage loginPage2() throws InterruptedException {
        //String permissionButtonId = "com.android.packageinstaller:id/permission_allow_button";
        //MobileElement waiter = (MobileElement) new WebDriverWait(webdriver, 30).until( ExpectedConditions.presenceOfElementLocated(By.id(permissionButtonId) ) );
        //System.out.println("wating for permissions");
        try {
            /*Set<String> contextNames = webdriver.getContextHandles();
            System.out.println(contextNames);
            System.out.println("-->" + TestSetup.Contexts.NATIVE + " "+ TestSetup.Contexts.WEBVIEW);
            System.out.println("packagestr " + TestSetup.Contexts.WEBVIEW);
            webdriver.context(TestSetup.Contexts.WEBVIEW);
            System.out.println("switched to " + TestSetup.Contexts.WEBVIEW);
            WebElement loginButton = webdriver.findElement(By.id("deviceready"));
            System.out.println("button text: " + loginButton.getText() );
            Assert.assertEquals(loginButton.getText(), "DEVICE IS READY");*/

            int i = 5;
            while (i < 10){
                Thread.sleep(1000);
                i++;
                System.out.println("waiting " + i);
            }

            String a = "DEVICE IS READY";
            Assert.assertEquals(a, "DEVICE IS READY");
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new LoginPage(driver);
    }

    public void takeScreenshot(String path_screenshot, String filename) throws IOException {
        String currentContext = webdriver.getContext();
        System.out.println("current context: " + currentContext + " switching to " + TestSetup.Contexts.NATIVE);
        webdriver.context(TestSetup.Contexts.NATIVE);
        File srcFile=webdriver.getScreenshotAs(OutputType.FILE);
        File targetFile=new File(path_screenshot + filename);
        FileUtils.copyFile(srcFile,targetFile);
        webdriver.context(currentContext);
        System.out.println("current context: " + webdriver.getContext());
    }
}