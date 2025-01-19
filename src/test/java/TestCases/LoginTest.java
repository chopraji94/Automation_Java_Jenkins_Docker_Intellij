package TestCases;

import Common.Locators;
import TestBase.BaseClass;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseClass {

    @Test(priority = 1,groups = {"smoke"})
    public void login() throws InterruptedException {
        logger.info("----------------------login-----------------------");
        loginpage.login(properties.getProperty("admin_login"),properties.getProperty("admin_password"));
    }

    @Test(priority = 2,dependsOnMethods = {"login"},groups = {"smoke"})
    public void checkUserOnDashBoardPage(){
        logger.info("----------------------checkUserOnDashBoardPage-----------------------");
        boolean exist = header.checkIfUserIsOnSelctedPage("Dashboard");
        Assert.assertTrue(exist);
    }
}
