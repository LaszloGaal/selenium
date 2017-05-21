package selenium.testing.pageobjects;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Parent page for BKK UTVONAL
 *
 * Created by laszlogaal on 2017. 05. 16..
 */
public abstract class AbstractPage {
    final WebDriver driver;

    AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver getDriver () {
        return this.driver;
    }

    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    WebElement findElement (By locator) {
        return getDriver().findElement(locator);
    }

    void clickButton (By buttonToClick){
        new Actions(driver).click(driver.findElement(buttonToClick)).build().perform();
    }

    void typeText (By targetField, String text){
        new Actions(driver).sendKeys(driver.findElement(targetField), text).build().perform();
    }

    public void highlightElement(By by) {
        // draw a border around the found element
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor)driver)
                    .executeScript("arguments[0].style.border='3px solid red'", driver.findElement(by));
        }
    }

    public void createScreenshot (String saveToDirectory) throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,
                new File(saveToDirectory +
                        new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".png"));
    }
}
