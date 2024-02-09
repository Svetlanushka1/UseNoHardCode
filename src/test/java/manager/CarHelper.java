package manager;

import dto.AddCarDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;
import utils.CarConfigReader;

import java.time.Duration;

public class CarHelper extends BaseHelper{
    public CarHelper(WebDriver driver) {
        super(driver);
    }

    By inputLocation = By.id(CarConfigReader.getProperty("inputLocationId"));
    By inputLocationXPath = By.id(CarConfigReader.getProperty("inputLocationXPath"));
    By btnAddNewCarMenu = By.xpath(CarConfigReader.getProperty("btnAddNewCarMenu"));
    By inputManifacture = By.xpath(CarConfigReader.getProperty("inputManifacturexPath"));
    By inputModel = By.xpath(CarConfigReader.getProperty("inputModelxPath"));
    By inputYear = By.id(CarConfigReader.getProperty("inputYearId"));
    By selectFuel = By.id(CarConfigReader.getProperty("selectFuelId"));
    By inputSeats = By.id(CarConfigReader.getProperty("inputSeatsId"));
    By inputClass = By.id(CarConfigReader.getProperty("inputClassId"));
    By inputSerialNumber = By.id(CarConfigReader.getProperty("inputSerialNumberId"));
    By inputPrice = By.id(CarConfigReader.getProperty("inputPriceId"));
    By btnSubmitNewCar = By.xpath(CarConfigReader.getProperty("btnSubmitNewCar"));
    By textPopUpTitle = By.xpath(CarConfigReader.getProperty("textPopUpTitle"));
    By btnAddAnotherCarPopUp = By.xpath(CarConfigReader.getProperty("btnAddAnotherCarPopUp"));
    By containerCities = By.xpath(CarConfigReader.getProperty("containerCities"));

    By btnCarAddingFailedPopUp = By.xpath("//button[contains(@class, 'positive-button')]");

    By inputAbout = By.id(CarConfigReader.getProperty("inputAboutId"));
    By labelPhoto = By.id(CarConfigReader.getProperty("labelPhotoId"));
    public By getOptionFuel(String str) {
        return By.xpath(String.format("//option[@value='%s']", str));
    }
    private By locationInputLocator = By.xpath("//input[@id='pickUpPlace']");

    public void clickAddNewCar() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        clickBase(btnAddNewCarMenu);
    }

    public void clickAddNewCarPopUp() {
        clickBase(btnAddAnotherCarPopUp);
    }

    public void clickCarAddingFailedPopUp() {
        clickBase(btnCarAddingFailedPopUp);
    }

    public void fillFormNewCar(AddCarDTO car) {
        enterLocationWithAutocomplete(car.getCity());
        typeTextBase(inputManifacture, car.getManufacture());
        typeTextBase(inputModel, car.getModel());
        typeTextBase(inputYear, String.valueOf(car.getYear()));
        clickBase(selectFuel);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        clickBase(getOptionFuel(car.getFuel()));
        typeTextBase(inputSeats, String.valueOf(car.getSeats()));
        typeTextBase(inputClass, car.getCarClass());
        typeTextBase(inputSerialNumber, car.getSerialNumber());
        typeTextBase(inputPrice, String.valueOf(car.getPricePerDay()));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        clickBase(btnSubmitNewCar);
        // location
    }

    public boolean validateMessagePopUp() {
        return isTextEqual(textPopUpTitle, "Car added".trim().toUpperCase());
    }

    private void enterLocationWithAutocomplete(String location) {
        typeTextBase(locationInputLocator, location);
        selectFirstOptionFromGooglePlacesDropdown();
    }

    private void selectFirstOptionFromGooglePlacesDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        WebElement autoCompleteResult = wait.until(ExpectedConditions.visibilityOfElementLocated(containerCities));
        Actions builder = new Actions(driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        builder.moveToElement(autoCompleteResult).perform();
        builder.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
    }
}
