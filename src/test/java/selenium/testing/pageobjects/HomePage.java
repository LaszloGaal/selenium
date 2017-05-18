package selenium.testing.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Home page for BKK UTVONAL
 *
 * Created by laszlogaal on 2017. 05. 16..
 */
public class HomePage extends AbstractPage {
    private final String URL = "http://futar.bkk.hu/";
    private final By fromLocator = By.className("planner-from");
    private final By toLocator = By.className("planner-to");
    private final By planLocator = By.className("planner-plan");
    private final String routePageTitle = "Útvonalterv - BKK FUTÁR Utazástervező";

    public HomePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void openHomePage(){
        getDriver().get(URL);
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.elementToBeClickable(fromLocator));
    }
    public RoutePage planTrip(String from, String to){
        findElement(fromLocator).sendKeys(from);
        findElement(toLocator).sendKeys(to);
        findElement(planLocator).click();
        //Wait for the page to fuly load
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.titleIs(routePageTitle));
        return new RoutePage(driver);
    }

    public RoutePage planTripWithActions(String from, String to){
        typeText(fromLocator, from);
        typeText(toLocator, to);
        clickButton(planLocator);
        //Wait for the page to fuly load
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.titleIs(routePageTitle));
        return new RoutePage(driver);
    }
}