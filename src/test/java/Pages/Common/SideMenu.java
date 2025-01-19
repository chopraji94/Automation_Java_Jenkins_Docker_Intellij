package Pages.Common;

import Common.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SideMenu {

    WebDriver driver;

    public SideMenu(WebDriver driver){
        this.driver = driver;
    }

    By sideMenuOptions = By.xpath(Locators.MainPage.sideMenuOptionsListXpath);

    public List<String> getSideMenuOptionsList(){
        return driver.findElements(sideMenuOptions).stream().map(WebElement::getText).toList();
    }
}
