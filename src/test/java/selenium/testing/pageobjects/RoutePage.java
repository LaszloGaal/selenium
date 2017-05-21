package selenium.testing.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

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

    public void printTravelTimes(){
        //Print travel time
        System.out.println("Route list:");
        String xpath = "//div[*/text()='Lehetséges útvonalak']/descendant::label/descendant::span/descendant::p/descendant::span";
        List<WebElement> routeList = driver.findElements(By.xpath(xpath));

        for (int index=0; index<routeList.size(); index++){
            WebElement temp = routeList.get(index);
            System.out.println((index+1) + ": " + temp.getText());
        }
    }

    public boolean hasRoute(){
        String xpath = "//div[h2/text()='Lehetséges útvonalak']";
        return isElementPresent(By.xpath(xpath));
    }
}
