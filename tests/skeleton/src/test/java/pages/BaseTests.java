package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import scenarios.TestSetup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BaseTests extends BasePage {
    By userId = By.id("mobileNo");
    By password = By.id("et_password");
    By login_Button = By.id("btn_mlogin");
    By existingUser_login = By.id("btn_mlogin");
    private AppiumDriver<WebElement> webdriver;
    private AndroidDriver androidDriver;
    private String titleText = "Initial title text ";
    private int i = 0;
    private boolean isLocalTest  = true;

    public BaseTests(AppiumDriver driver) {
        super(driver);
        webdriver = (AppiumDriver<WebElement>) driver;
    }

    public BaseTests(AppiumDriver driver, boolean inLocal) {
        super(driver);
        webdriver = (AppiumDriver<WebElement>) driver;
         isLocalTest = inLocal;
    }

    public BaseTests countOneToFive() throws InterruptedException {
        try {
            i = 0;
            while (i < 5){
                Thread.sleep(1000);
                i++;
                System.out.println("waiting " + i);
            }
            titleText += " appended text on countOneToFive";
            System.out.println(titleText);
            boolean a = true;
            Assert.assertTrue(a);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new BaseTests(driver);
    }

    public void browsePages() throws InterruptedException {
        try {
            Set<String> contextNames = webdriver.getContextHandles();
            System.out.println(contextNames);
            System.out.println("-->" + TestSetup.Contexts.NATIVE + " "+ TestSetup.Contexts.WEBVIEW);
            System.out.println("packagestr " + TestSetup.Contexts.WEBVIEW);
            webdriver.context(TestSetup.Contexts.WEBVIEW);
            System.out.println("switched to " + TestSetup.Contexts.WEBVIEW);

            //Get text of home page
            WebElement homeTitle = webdriver.findElement(By.id("home_title"));
            String homeTitleText = homeTitle.getText();
            System.out.println("button text: " + homeTitleText );
            takeScreenshot("./", "home.png");

            //Move to about page
            WebElement aboutButton = webdriver.findElement(By.id("tab-t0-1"));
            aboutButton.click();
            Thread.sleep(1000);
            WebElement aboutTitle = webdriver.findElement(By.id("about"));
            String aboutTitleText = aboutTitle.getText();
            System.out.println("aboutTitleText: " + aboutTitleText );
            takeScreenshot("./", "about.png");
            Thread.sleep(2000);

            //Move to contact page
            WebElement contactButton = webdriver.findElement(By.id("tab-t0-2"));
            contactButton.click();
            Thread.sleep(1000);
            ArrayList<WebElement> contactTitles = (ArrayList) webdriver.findElements(By.className("label"));
            String contactText = null;
            for(WebElement title: contactTitles){
                System.out.println("title: " + title.getText() );
            }
            if(contactTitles.size() >= 2){
                contactText = contactTitles.get(1).getText();
            }
            takeScreenshot("./", "contact.png");
            Thread.sleep(2000);

            System.out.println("making three assertions");
            Assert.assertEquals(homeTitleText, "Demo Appium test!");
            Assert.assertEquals(aboutTitleText, "This is the about page");
            Assert.assertEquals(contactText, "@ionicframework");

        }catch (Exception ex){
            ex.printStackTrace();
        }
        //return new BaseTests(driver);
    }

    public BaseTests countsSixToTen() throws InterruptedException {
        try {
            //int i = 5;
            while (i < 10){
                Thread.sleep(1000);
                i++;
                System.out.println("waiting " + i);
            }
            titleText += " appended text on countsSixToTen ";
            System.out.println(titleText);
            boolean a = true;
            Assert.assertEquals(i, 10);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new BaseTests(driver);
    }

    public void takeScreenshot(String path_screenshot, String filename) throws IOException {
        if(isLocalTest) {
            String imgPath = takeScreenshotLocal(path_screenshot, filename);
            System.out.println("screenshot path: "+imgPath);
        } else {
            takeScreenshotAws(filename);
        }
    }

    private String takeScreenshotLocal(String path_screenshot, String filename) throws IOException {
        String currentContext = webdriver.getContext();
        System.out.println("current context: " + currentContext + " switching to " + TestSetup.Contexts.NATIVE);
        webdriver.context(TestSetup.Contexts.NATIVE);
        File srcFile=webdriver.getScreenshotAs(OutputType.FILE);
        File targetFile=new File(path_screenshot + filename);
        FileUtils.copyFile(srcFile,targetFile);
        webdriver.context(currentContext);
        System.out.println("current context: " + webdriver.getContext());
        return targetFile.getAbsolutePath();
        //return targetFile.exists();
    }

    private boolean takeScreenshotAws(final String name) {
        String screenshotDirectory = System.getProperty("appium.screenshots.dir", System.getProperty("java.io.tmpdir", ""));
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        System.out.println("before rename " + screenshot.getAbsolutePath());
        boolean rname = screenshot.renameTo(new File(screenshotDirectory, String.format("%s.png", name)));
        System.out.println("after rename " + screenshot.getAbsolutePath());
        return rname;
    }
}