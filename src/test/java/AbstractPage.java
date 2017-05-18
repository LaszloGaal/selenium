import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by laszlogaal on 2017. 05. 16..
 */
public abstract class AbstractPage {
    protected final WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver () {
        return this.driver;
    }

    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    public WebElement findElement (By locator) {
        return getDriver().findElement(locator);
    }

    public void clickButton (By buttonToClick){
        new Actions(driver).click(driver.findElement(buttonToClick)).build().perform();
    }

    public void typeText (By targetField, String text){
        new Actions(driver).sendKeys(driver.findElement(targetField), text).build().perform();
    }
}
