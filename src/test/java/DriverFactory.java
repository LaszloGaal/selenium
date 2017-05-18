import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * Created by laszlogaal on 2017. 05. 17..
 */
public class DriverFactory {
    public WebDriver getDriver(BrowserType browser){

        if (browser == BrowserType.CHROME) {
            System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
            return new ChromeDriver();
        }
        else if (browser == BrowserType.FIREFOX) {
            System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
            return new FirefoxDriver();
        }
        else if (browser == BrowserType.SAFARI) {
            //System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
            return new SafariDriver();
        }

        return null;
    }
}
