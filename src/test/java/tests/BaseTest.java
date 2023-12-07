package tests;

import manager.ApplicationManager;
import org.testng.annotations.*;

public class BaseTest {
    static ApplicationManager app = new ApplicationManager();
    @BeforeSuite
    public void setup(){
        app.init();
    }
    @AfterSuite
    public void stop(){
        app.tearDown();
    }
    public void logoutIflogin() {
        if (app.getUserHelper().btnLogoutExist()) {
            app.getUserHelper().logout();
        }
    }
}
