import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
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
}
