import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;

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

    public WebElement highlightElement(By by) {
        WebElement elem = driver.findElement(by);
        // draw a border around the found element
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", elem);
        }
        return elem;
    }

    public void createScreenshot (String saveToDirectory) throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(saveToDirectory));
    }
}
