package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

public class UserHelper extends BaseHelper{

    public UserHelper(WebDriver driver) {
        super(driver);
    }
    By btnLogout = By.xpath(ConfigReader.getProperty("btnLogout"));
    By tabLetTheCarWork = By.xpath(ConfigReader.getProperty("tabLetTheCar"));
    By locationInputContainer = By.xpath(ConfigReader.getProperty("locationInputContainer"));

    //////////////////
    By btnLoginNavigatorMenu = By.xpath("//a[contains(@href, '/login')]");
    By inputEmailLoginForm = By.xpath("//input[@id='email']");
    By inputPasswordLoginForm = By.xpath("//input[@id='password']");
    By btnYallaLoginForm = By.xpath("//button[@type='submit']");
    By textSuccessLoginPopUp = By.xpath("//h2[@class='message']");

    //By btnLoginNavigatorMenu = By.xpath(ConfigReader.getProperty("btnLoginNavigatorMenu"));

;
    By btnOpenRegForm = By.xpath("//a[contains(@href, '/registration')]");
    By inputNameReg = By.xpath("//input[@id='name']");
    By inputLastNameReg = By.xpath("//input[@id='lastName']");
    By inputEmailReg = By.xpath("//input[@id='email']");
    By inputPasswordReg = By.xpath("//input[@id='password']");
    String btnRegNewUser = "document.querySelector('#terms-of-use').click();";
    //    By checkBoxReg = By.xpath("//label[@for='name']");
    String btnOkPopUpStr = "document.querySelector(`[type='button']`).click();";
    By checkBoxReg = By.xpath("//label[@for='terms-of-use']");
    By btnUallaReg = By.xpath("//button[@type='submit']");
    By textPopUpSuccessRegH1 = By.xpath("//div[@class='dialog-container']//h1[@class='title']");

    //By btnLogout = By.xpath("//a[contains(@href, 'logout')]");

    By btnOkPopUp = By.xpath("//button[@type='button']");
    By errorMessageWrongEmailReg = By.xpath("//input[@autocomplete='email']/..//div//div");
    By errorMessageIncorrectPasswordReg = By.xpath("//input[@autocomplete='new-password']/..//div//div");



    public boolean btnLogoutExist() {
        return isElementExist(btnLogout);
    }

    public void logout() {
        clickBase(btnLogout);
    }

    public void letTheCarWork() {
        clickBase(tabLetTheCarWork);
    }

}
