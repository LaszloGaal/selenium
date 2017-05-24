import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import selenium.testing.pageobjects.HomePage;
import selenium.testing.pageobjects.RoutePage;
import selenium.testing.steps.TestSteps;
import selenium.testing.utility.BrowserType;
import selenium.testing.utility.DriverFactory;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Tests using Selenium WebDriver
 *
 * Created by laszlogaal on 2017. 05. 15..
 */

public class HomeworkTest {
    private final BrowserType BROWSER = BrowserType.CHROME;
    private final DriverFactory driverFactory = new DriverFactory();
    private WebDriver driver;
    private String screenshotDir = "/Users/laszlogaal/Desktop/";
    private TestSteps testSteps;
    private String testName;


    @BeforeClass(description = "Start browser")
    public void startBrowser() {
        driver = driverFactory.getDriver(BROWSER);
        testSteps = new TestSteps(driver, screenshotDir);
    }


    @BeforeMethod
    public void saveTestMethodName(Method method)
    {
        testName = method.getName();
    }

    @AfterClass(description = "Stop Browser")
    public void stopBrowser() {

        driver.quit();
    }

    @AfterMethod
    public void createScreenshotOnFailure(ITestResult result) throws IOException {
        if(result.getStatus() == ITestResult.FAILURE){
            testSteps.createScreenshot(testName + "_FAILURE");
        }
    }



    @Test(description = "homework 1")
    public void homework_1() throws InterruptedException {

        //Test data
        final String    START_PAGE  = "http://futar.bkk.hu/";
        final Dimension WINDOW_SIZE = new Dimension(1280, 800);
        final String    FROM        = "Vaskapu utca 3";
        final String    TO          = "Futo utca 47";
        WebDriverWait wait = new WebDriverWait(driver, 10);

        //Test steps
        driver.get(START_PAGE);
        //Wait for the page to fully load
        wait.until(ExpectedConditions.elementToBeClickable(By.className("planner-from")));
        //driver.manage().window().setSize(WINDOW_SIZE);
        driver.findElement(By.className("planner-from")).sendKeys(FROM);
        driver.findElement(By.className("planner-to")).sendKeys(TO);
        driver.findElement(By.className("planner-plan")).click();

        //Verify the result
        By utvonal = By.className("jarat-utvonal");
        Assert.assertTrue(isElementPresent(utvonal), "Direction not found");
        //Wait for the page to fuly load
        wait.until(ExpectedConditions.titleIs("Útvonalterv - BKK FUTÁR Utazástervező"));
        //Print travel time
        System.out.println(driver.findElement(utvonal).getText());
    }

    @Test(description = "homework 2 - PageObject")
    public void homework_2() {

        //Test data
        final String    from        = "Vaskapu utca 3";
        final String    to          = "Futo utca 47";

        //Test steps
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        RoutePage routePage = homePage.planTrip(from, to);

        //Verify the result
        Assert.assertTrue(routePage.isElementPresent(By.className("jarat-utvonal")), "Direction not found:");

        routePage.printTravelTimes();
    }

    @Test(description = "homework 3 - Actions and Screenshot")
    public void homework_3() throws IOException {

        //Test data
        final String    from        = "Vaskapu utca 3";
        final String    to          = "Futo utca 47";
        String screenshotDir = "/Users/laszlogaal/Desktop/";

        //Test steps
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        RoutePage routePage = homePage.planTripWithActions(from, to);

        //Verify the result
        Assert.assertTrue(routePage.isElementPresent(By.className("jarat-utvonal")), "Direction not found");

        routePage.printTravelTimes();

        //Highlight travel time
        routePage.highlightElement(By.className("jarat-utvonal"));
        routePage.createScreenshot(screenshotDir, testName);
    }

    @Test(description = "homework 4 - Layers")
    public void homework_4() throws IOException {

        //Test data
        final String    from        = "Vaskapu utca 3";
        final String    to          = "Futo utca 47";

        //Test steps
        testSteps.openBrowser();
        testSteps.planTrip(from, to);
        testSteps.verifyResultPage();
        testSteps.printRouteOptions();
    }

    @Test(description = "homework 5 - screenshot on failure")
    public void homework_5() throws IOException {

        //Test data
        final String    from        = "Vaskapu utca 3";
        final String    to          = "New York";

        //Test steps
        testSteps.openBrowser();
        testSteps.planTrip(from, to);
        testSteps.verifyResultPage();
    }

    @Test(description = "homework 6 - multiple routes")
    public void homework_6() throws IOException {

        //Test data
        final String    from        = "Vaskapu utca 3";
        final String    to          = "Liszt Ferenc Airport";

        //Test steps
        testSteps.openBrowser();
        testSteps.planTrip(from, to);
        testSteps.verifyResultPage();
        testSteps.printRouteOptions();
    }


    private boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }
}
