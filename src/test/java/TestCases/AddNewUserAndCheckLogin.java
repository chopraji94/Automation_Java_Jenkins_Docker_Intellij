package TestCases;

import Common.Locators;
import Models.EmployeeModel;
import TestBase.BaseClass;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddNewUserAndCheckLogin extends BaseClass {

    EmployeeModel employee;

    @Test(priority = 1,groups = {"regression"})
    public void login() throws InterruptedException {
        logger.info("----------------------login-----------------------");
        loginpage.login(properties.getProperty("admin_login"),properties.getProperty("admin_password"));
    }

    @Test(priority = 2,dependsOnMethods = {"login"},groups = {"regression"})
    public void addNewEmployee() throws InterruptedException {
        logger.info("----------------------addNewEmployee-----------------------");
        driver.findElement(By.xpath(Locators.MainPage.sideMenuOptionXpath("PIM"))).click();
        String name = fake.name().fullName();
        employee = pimPage.addAnEmployee(name,name,fake.name().lastName(),fake.number().digits(4),name,"qwerty@1234");
    }

    @Test(priority = 3,dependsOnMethods = {"addNewEmployee"},groups = {"regression"})
    public void reLogin() throws InterruptedException {
        logger.info("----------------------reLogin-----------------------");
        header.logout();
        loginpage.login(employee.getUserName(),employee.getPassword());
    }

    @Test(priority = 4,dependsOnMethods = {"reLogin"})
    public void testReloginSuccesfully(){
        logger.info("----------------------testReloginSuccesfully-----------------------");
        String text = header.getLoggedInUsername();
        String expectedText = employee.getFirstname()+" "+employee.getLastName();
        Assert.assertEquals(text,expectedText);
    }
}
