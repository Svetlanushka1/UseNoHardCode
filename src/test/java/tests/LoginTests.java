package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import static io.qameta.allure.SeverityLevel.*;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.TmsLink;
import dto.UserDTO;
import dto.UserDTOWith;
import dto.UserDtoLombok;
import utils.ConfigProperties;
import utils.DataProviderLogin;;

public class LoginTests extends BaseTest {
    @BeforeMethod(alwaysRun = true)
    public void preconditionsLogin() {
        logoutIflogin();
    }
    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"testqa20@gmail.com", "123456Aa$"},
                {"testqa201@gmail.com", "123456Aa$"},
                {"testqa202@gmail.com", "123456Aa$"}
        };
    }

    @AfterMethod(alwaysRun = true)
    public void postconditionsLogin() {
        app.getUserHelper().clickOkPopUpSuccessLogin();
    }

    @Test
    public void positiveLoginFromConfigProperties() {//get login from prodConfig file
        UserDTO userPropertyFile = new UserDTO(ConfigProperties.getProperty("email"), ConfigProperties.getProperty("password"));
        logger.info("take login from PROPERTIES file");
        BaseTest.app.getUserHelper().login(userPropertyFile);
        Assert.assertTrue(BaseTest.app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
        logger.info("login using production PROPERTIES file");
    }

    // @Test(dataProvider = "datalogin.csv",dataProviderClass = DataProviderLogin.class)
    @Test(groups = {"smoke"}, dataProvider = "loginCSV", dataProviderClass = DataProviderLogin.class)
    public void positiveLoginProvider(UserDtoLombok user) {//get login form csv file
        logger.info("User: " + user.toString());
        app.getUserHelper().loginUserDtoLombok(user);
        flagLogin = true;
        flagPopUp = true;
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
        logger.info("2 login using CSV file");

    }

    @Test(dataProvider = "negativeLoginCSV", dataProviderClass = DataProviderLogin.class)
    public void negativeDataLoginCSV(UserDtoLombok user) {
        app.getUserHelper().loginUserDtoLombok(user);
        flagPopUp = true;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }

//TODO fix waiter Jira



    @Test(groups = {"smoke", "regression"}, dataProvider = "loginData")
    public void positiveLoginUserDTO(String user, String password) {
        UserDTO userDTO = new UserDTO(user, password);
        BaseTest.app.getUserHelper().login(userDTO);
        Assert.assertTrue(BaseTest.app.getUserHelper().validatePopUpMessageSuccessAfterLogin());

    }

    ////////////////////////////////////////////////////////////////////////

    @Test(description = "positiveLoginUserDTOWith", groups = {"smoke", "regression"})
    @Description("This test attempts to log into the website using a login and a password. Fails if any error happens.\n\nNote that this test does not test 2-Factor Authentication.")
    @Severity(CRITICAL)
    @Owner("John Doe")
    @Link(name = "Website", url = "https://dev.example.com/")
    @Issue("AUTH-123")
    @TmsLink("TMS-456")



////////////////////////////////////////////////////////////////////////


    @Test(priority = 1, groups = {"smoke", "regression"}, dataProvider = "loginData")
    public void wrongLoginWithDataProvider(String user, String password) {
        UserDTO userDTO = new UserDTO(user, password);
        BaseTest.app.getUserHelper().login(userDTO);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }

    /*
    @Test
    public void positiveLogin() {
        UserDtoLombok userDtoLombok = UserDtoLombok.builder()
                .email("testqa20@gmail.com")
                .password("123456Aa$")
                .build();
        app.getUserHelper().loginUserDtoLombok(userDtoLombok);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
    }
       public void positiveLoginUserDTOWith() {
        UserDTOWith userDTOWith = new UserDTOWith()
                .withEmail("testqa20@gmail.com")
                .withPassword("123456Aa$");
        BaseTest.app.getUserHelper().login(userDTOWith);
        Assert.assertTrue(BaseTest.app.getUserHelper().validatePopUpMessageSuccessAfterLogin());

    }

    @Test(groups = {"smoke", "regression"})
    public void positiveLogin1() {
        app.getUserHelper().loginUserDtoLombok(userDtoLombok);
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            logger.error(String.valueOf(e));
            throw new RuntimeException(e);
        }
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
        logger.info("Test Finished");
    }



    @Test(priority = 1)
    public void negativePasswordWithoutSymbol() {
        UserDtoLombok userDtoLombok = UserDtoLombok.builder()
                .email("testqa20@gmail.com")
                .password("123456Aaa")
                .build();
        logger.info("testqa20@gmail.com");
        app.getUserHelper().loginUserDtoLombok(userDtoLombok);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }

    @Test(priority = 1)
    public void negativePasswordWithoutNumbers() {
        UserDtoLombok userDtoLombok = UserDtoLombok.builder()
                .email("testqa20@gmail.com")
                .password("ddsdhjAa$")
                .build();
        logger.warn("userDtoLombok initialization finished");
        app.getUserHelper().loginUserDtoLombok(userDtoLombok);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect(), "Fail");
        logger.info("Pass");

    }

    @Test(priority = 1)
    public void negativePasswordWithoutLetters() {
        UserDtoLombok userDtoLombok = UserDtoLombok.builder()
                .email("testqa20@gmail.com")
                .password("12345678$")
                .build();
        logger.warn("userDtoLombok initialization finished again with warn");
        app.getUserHelper().loginUserDtoLombok(userDtoLombok);

        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }
     */
}