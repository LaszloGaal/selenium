import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by laszlogaal on 2017. 05. 15..
 */

public class HomeworkTest {
    private final BrowserType BROWSER = BrowserType.CHROME;
    private final DriverFactory driverFactory = new DriverFactory();
    private WebDriver driver;


    @BeforeClass(description = "Start browser")
    public void startBrowser() {
        driver = driverFactory.getDriver(BROWSER);
    }


//    @AfterClass(description = "Stop Browser")
//    public void stopBrowser() {
//        driver.quit();
//    }



    @Test(description = "homework 1")
    public void homework_1() {

        //Test data
        final String    START_PAGE  = "http://futar.bkk.hu/";
        final Dimension WINDOW_SIZE = new Dimension(1280, 800);
        final String    FROM        = "Vaskapu utca 3";
        final String    TO          = "Futo utca 47";

        //Test steps
        driver.get(START_PAGE);
        driver.manage().window().setSize(WINDOW_SIZE);
        driver.findElement(By.className("planner-from")).sendKeys(FROM);
        driver.findElement(By.className("planner-to")).sendKeys(TO);
        driver.findElement(By.className("planner-plan")).click();

        //Verify the result
        By utvonal = By.className("jarat-utvonal");
        Assert.assertTrue(isElementPresent(utvonal), "Direction not found");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        //Wait for the page to fuly load
        wait.until(ExpectedConditions.titleIs("Útvonalterv - BKK FUTÁR Utazástervező"));
        //Print travel time
        System.out.print("homework_1 - ");
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
        Assert.assertTrue(isElementPresent(By.className("jarat-utvonal")), "Direction not found");
        Assert.assertFalse(routePage.getTravelTime().equals(""));
        System.out.print("homework_2 - ");
        System.out.println(routePage.getTravelTime());
    }


    private boolean isElementPresent(By by) {
        // Custom implementation for is ElementPresent
        return !driver.findElements(by).isEmpty();
    }

}
