package TestCases;

import Common.Constants;
import TestBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CheckSideMenuOptionsTest extends BaseClass {

    @Test(priority = 1,groups = {"smoke"})
    public void login() throws InterruptedException {
        logger.info("----------------------login-----------------------");
        loginpage.login(properties.getProperty("admin_login"),properties.getProperty("admin_password"));
    }

    @Test(priority = 2,dependsOnMethods = {"login"},groups = {"smoke"})
    public void checkSideMenuOptions(){
        logger.info("----------------------checkSideMenuOptions-----------------------");
        List<String> uiList = sideMenu.getSideMenuOptionsList();
        Assert.assertEquals(uiList,Constants.MainPage.sideMenuOptions);
    }
}
