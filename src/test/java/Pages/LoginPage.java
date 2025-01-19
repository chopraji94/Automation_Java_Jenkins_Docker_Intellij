package Pages;

import Common.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    By userNameField = By.xpath(Locators.LoginPage.usernameFieldXpath);
    By passwordField = By.xpath(Locators.LoginPage.passwordFieldXpath);
    By loginButton = By.xpath(Locators.LoginPage.loginButtonXpath);

    public void login(String username,String password) throws InterruptedException {
        driver.findElement(userNameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
        Thread.sleep(Duration.ofSeconds(10));
    }
}
