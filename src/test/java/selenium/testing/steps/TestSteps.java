package selenium.testing.steps;

import org.hamcrest.Matcher;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import selenium.testing.pageobjects.HomePage;
import selenium.testing.pageobjects.RoutePage;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

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
        assertThat("Direction not found", routePage.hasRoute(), is(true));
    }

    public void printRouteOptions() throws IOException {
        routePage.printTravelTimes();
    }

    public void createScreenshot(String testName) throws IOException {
        routePage.createScreenshot(screenshotDir, testName);
    }
}
