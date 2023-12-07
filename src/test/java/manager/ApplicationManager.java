package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import utils.ConfigProperties;

import java.time.Duration;
import java.util.Properties;

public class ApplicationManager {
    WebDriver driver;

    UserHelper userHelper;

    static String browser;

    public ApplicationManager(){
        browser = System.getProperty("browser", Browser.CHROME.browserName());
    }

    public void init() {
        if(browser.equals(Browser.CHROME.browserName())){
            ChromeOptions options = new ChromeOptions();
            WebDriver original = new ChromeDriver(options);
            WebDriverListener wdListener = new WebDriverListener() {};
            driver = new EventFiringDecorator(wdListener).decorate(original);
        }else if(browser.equals(Browser.FIREFOX.browserName())){
            /*
            FirefoxOptions options = new FirefoxOptions();
            FirefoxBinary binary = new FirefoxBinary(new File("/opt/firefox/firefox"));
            FirefoxProfile profile = new FirefoxProfile();
            options.setProfile(profile);
            driver = new FirefoxDriver(options);
            logger.warn(browser);
             */

        }

        driver.navigate().to(ConfigProperties.getProperty("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(ConfigProperties.getProperty("implicitlyWait"))));

        userHelper = new UserHelper(driver);

    }
    public void navigateToMainPage() {

        driver.navigate().to(ConfigProperties.getProperty("url"));
    }

    public UserHelper getUserHelper() {
        return userHelper;
    }

    public void tearDown() {
        driver.quit();
    }


}