package tests;

import org.testng.annotations.*;

public class LaunchTests extends BaseTest {
    @BeforeClass(alwaysRun = true)
    public void preconditionsLogin() {
        logoutIflogin();
    }

   @Test
    public void openLetTheCarWorkTab() {
        BaseTest.app.getUserHelper().letTheCarWork();
        //Assert.assertTrue(app.getUserHelper().isElementExist(("locationInputContainer")));

        //Assert.assertEquals(BaseHelper.isTextActualEqualToExpected("","Enter your address"));
       // Assert.assertTrue(loginHelper.validateTextBoardsExist());
   }
   //@AfterClass(alwaysRun = true)



}
