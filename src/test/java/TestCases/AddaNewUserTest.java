package TestCases;

import Common.Constants;
import Common.Locators;
import Models.EmployeeModel;
import TestBase.BaseClass;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AddaNewUserTest extends BaseClass {

    EmployeeModel employee;

    @Test(priority = 1,groups = {"smoke","regression"})
    public void login() throws InterruptedException {
        logger.info("----------------------login-----------------------");
        loginpage.login(properties.getProperty("admin_login"),properties.getProperty("admin_password"));
    }

    @Test(priority = 2,dependsOnMethods = {"login"},groups = {"smoke","regression"})
    public void addNewEmployee() throws InterruptedException {
        logger.info("----------------------testReloginSuccesfully-----------------------");
        driver.findElement(By.xpath(Locators.MainPage.sideMenuOptionXpath("PIM"))).click();
        String name = fake.name().fullName();
        employee = pimPage.addAnEmployee(name,name,fake.name().lastName(),fake.number().digits(4),name,"qwerty@1234");
    }

    @Test(priority = 3,dependsOnMethods = {"addNewEmployee"},groups = {"smoke","regression"})
    public void checkNewEmployeeAdded() throws InterruptedException {
        logger.info("----------------------checkNewEmployeeAdded-----------------------");
        driver.findElement(By.xpath(Locators.MainPage.sideMenuOptionXpath("PIM"))).click();
        pimPage.searchEmployee(employee.getFirstname(),employee.getEmployeeId());
        Assert.assertTrue(pimPage.getColumnValues(Constants.PIMPage.idColumnText).contains(employee.getEmployeeId()));
        Assert.assertTrue(pimPage.getColumnValues(Constants.PIMPage.FirstMiddleName).contains(employee.getFirstname()+" "+employee.getMidleName()));
    }
}
