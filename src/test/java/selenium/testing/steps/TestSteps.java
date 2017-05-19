package selenium.testing.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import selenium.testing.pageobjects.AbstractPage;
import selenium.testing.pageobjects.HomePage;
import selenium.testing.pageobjects.RoutePage;

import java.io.IOException;

/**
 * Common steps of test executions
 *
 * Created by laszlogaal on 2017. 05. 19..
 */
public class TestSteps {
    private WebDriver driver;
    private HomePage homePage;
    private RoutePage routePage;
    private String screenshotDir;

    public TestSteps(WebDriver driver, String screenshotDir){
        this.driver = driver;
        this.homePage = new HomePage(driver);
        this.routePage = new RoutePage(driver);
        this.screenshotDir = screenshotDir;
    }

    public void openBrowser() {
        homePage.openHomePage();
    }

    public void planTrip(String from, String to) {
        routePage = homePage.planTripWithActions(from, to);
    }

    public void verifyResultPage() {
        Assert.assertTrue(routePage.isElementPresent(By.className("jarat-utvonal")), "Direction not found");
        Assert.assertFalse(routePage.getTravelTime().equals(""));
    }

    public void saveRouteDetailsAndPrintDuration() throws IOException {
        routePage.highlightElement(By.className("jarat-utvonal"));
        routePage.createScreenshot(screenshotDir);
        System.out.println(routePage.getTravelTime());
    }

    public void closeBrowser() {
        driver.quit();
    }
}
