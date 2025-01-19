package Pages.Common;

import Common.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class Header {

    WebDriver driver;

    public Header(WebDriver driver){
        this.driver = driver;
    }

    By userDropDown = By.xpath(Locators.MainPage.userDropDownXpath);

    By userNameText = By.xpath(Locators.MainPage.userNameTextXpath);

    public boolean checkIfUserIsOnSelctedPage(String pageName){
        return driver.findElement(By.xpath(Locators.MainPage.pageTitleHeaderXpath(pageName))).isDisplayed();
    }

    public void logout() throws InterruptedException {
        driver.findElement(userDropDown).click();
        driver.findElement(By.xpath(Locators.MainPage.userDropDownOptionXpath("Logout"))).click();
        Thread.sleep(Duration.ofSeconds(10));
    }

    public String getLoggedInUsername(){
        return driver.findElement(userNameText).getText();
    }
}
