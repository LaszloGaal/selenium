package selenium.testing.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Route page for BKK UTVONAL
 *
 * Created by laszlogaal on 2017. 05. 16..
 */
public class RoutePage extends AbstractPage {
    public RoutePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public String getTravelTime(){
        //Print travel time
        return driver.findElement(By.className("jarat-utvonal")).getText();
    }

    public boolean hasRoute(){
        return isElementPresent(By.className("jarat-utvonal"));
    }
}
