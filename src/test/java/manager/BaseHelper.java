package manager;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BaseHelper {

    WebDriver driver;
    // Logger logger = LoggerFactory.getLogger(BaseHelper.class);

    public BaseHelper(WebDriver driver) {

        this.driver = driver;
    }

    private WebElement findElementBase(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        return driver.findElement(locator);
    }

    private List<WebElement> findElementsBase(By locator) {
        return driver.findElements(locator);
    }

    public boolean isElementExist(By locator) {
        return findElementsBase(locator).size() > 0;
    }
    public boolean isTextActualEqualToExpected(By locator, String text) {
        if(findElementBase(locator).getText().toUpperCase().trim()
                .equals(text.toUpperCase().trim())) {
            return true;
        } else {
            System.out.println("actual result: " +
                    findElementBase(locator).getText().toUpperCase().trim() +
                    " expected result: " + text.toUpperCase().trim());
           /* logger.info("actual result: " +
                    findElementBase(locator).getText().toUpperCase().trim() +
                    " expected result: " + text.toUpperCase().trim());*/
            return false;
        }
    }

    public void clickBase(By locator) {
        WebElement el = findElementBase(locator);
        el.click();
    }

    public String getTextBase(By locator) {
        WebElement el = findElementBase(locator);
        return el.getText().trim().toUpperCase();
    }

    public void typeTextBase(By locator, String text) {
        WebElement el = findElementBase(locator);
        el.click();
        el.clear();
        el.sendKeys(text);
    }

    public boolean isTextEqual(By locator, String expectedResult) {
        String actualResult = getTextBase(locator);
        expectedResult = expectedResult.toUpperCase();

        if (expectedResult.equals(actualResult)) {
            return true;
        } else {
            System.out.println("expected result: " + expectedResult + "actual result: " + actualResult);
            return false;
        }
    }

    public void jsClickBase(String locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(locator);
    }


    public void clickByXY(By locator, double down, int right) {  // 10  12
        Rectangle rect = findElementBase(locator).getRect();
        int x = rect.getX() + (rect.getWidth() / right);
        int y = (int) (rect.getY() + (rect.getHeight() / down));
        Actions actions = new Actions(driver);
        actions.moveByOffset(x, y).click().perform();
    }




    public void refreshPage() {driver.navigate().refresh();}
}