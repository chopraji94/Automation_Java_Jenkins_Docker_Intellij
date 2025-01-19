package TestCases;

import Common.Constants;
import Common.Locators;
import TestBase.BaseClass;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class CheckSidemenuSelection extends BaseClass {

    @Test(priority = 1,groups = {"smoke","regression"})
    public void login() throws InterruptedException {
        logger.info("----------------------login-----------------------");
        loginpage.login(properties.getProperty("admin_login"),properties.getProperty("admin_password"));
    }

    @Test(priority = 2,dependsOnMethods = "login",groups = {"smoke","regression"})
    public void checkEachMenuSelctionWithPageHeader() throws InterruptedException {
        logger.info("----------------------checkEachMenuSelctionWithPageHeader-----------------------");
        List<String> expctedList = Constants.MainPage.sideMenuOptions;
        for(String option : expctedList){
            if(option != "Maintenance"){
                driver.findElement(By.xpath(Locators.MainPage.sideMenuOptionXpath(option))).click();
                Thread.sleep(Duration.ofSeconds(5));
                if(option =="My Info")
                    option = "PIM";
                boolean exist = header.checkIfUserIsOnSelctedPage(option);
                Assert.assertTrue(exist);
            }
        }
    }
}
