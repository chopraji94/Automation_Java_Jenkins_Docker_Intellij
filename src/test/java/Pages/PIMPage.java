package Pages;

import Common.Locators;
import Models.EmployeeModel;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

public class PIMPage {

    WebDriver driver;

    public PIMPage(WebDriver driver){
        this.driver = driver;
    }

    By firstNameTxtField = By.xpath(Locators.PIMPage.firstNameTextFieldXpath);
    By middleNameTxtField = By.xpath(Locators.PIMPage.middleNameTextFieldXpath);
    By lastNameTxtField = By.xpath(Locators.PIMPage.lastNameTextFieldXpath);
    By userNameTxtField = By.xpath(Locators.PIMPage.usernameTextFieldXpath);
    By passwordTxtField = By.xpath(Locators.PIMPage.passwordTextFieldXpath);
    By confirmPasswordTxt = By.xpath(Locators.PIMPage.confirmPasswordTextFieXpath);
    By saveButton = By.xpath(Locators.PIMPage.saveButtonXpath);
    By createLoginDetailSwitchButton = By.xpath(Locators.PIMPage.createLoginDetailCheckBoxXpath);
    By employeeTxtField = By.xpath(Locators.PIMPage.employeeIdTextFieldXpath);
    By personalDetailHeader = By.xpath(Locators.PIMPage.personalDetailHeaderXpath);
    By personalDetailemployeeNameTxtField = By.xpath(Locators.PIMPage.personalDetailsEmployeeNameTextFieldXpath);
    By perosnalDetailemployeeIdTxtField = By.xpath(Locators.PIMPage.personalDetailsemployeeIdTextFieldXpath);
    By searchButton = By.xpath(Locators.PIMPage.searchButtonXpath);
    By recordTableHeaderList = By.xpath(Locators.PIMPage.recordTableHeader);
    By addAttachementButton = By.xpath(Locators.PIMPage.addButtonXpath);
    By fileUploadTextField = By.xpath(Locators.PIMPage.fileUploadFieldXpath);
    By saveAndAddAttachementSaveButton = By.xpath(Locators.PIMPage.saveAddAttachementButtonXpath);

    public EmployeeModel addAnEmployee(String firstname,String middleName,String lastName,String employeeId,String userName,String password) throws InterruptedException {

        driver.findElement(By.xpath(Locators.PIMPage.menuOptionsXpath("Add Employee"))).click();

        WebElement employeeIdField = driver.findElement(employeeTxtField);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        EmployeeModel employeeDetail = new EmployeeModel();
        employeeDetail.setFirstname(firstname);
        employeeDetail.setMidleName(middleName);
        employeeDetail.setLastName(lastName);
        employeeDetail.setUserName(userName);
        employeeDetail.setPassword(password);
        driver.findElement(firstNameTxtField).sendKeys(firstname);
        driver.findElement(middleNameTxtField).sendKeys(middleName);
        driver.findElement(lastNameTxtField).sendKeys(lastName);
        driver.findElement(createLoginDetailSwitchButton).click();
        driver.findElement(employeeTxtField).sendKeys(employeeId);
        driver.findElement(userNameTxtField).sendKeys(userName);
        driver.findElement(passwordTxtField).sendKeys(password);
        driver.findElement(confirmPasswordTxt).sendKeys(password);

        employeeId = (String) js.executeScript("return arguments[0].value;",employeeIdField);
        employeeDetail.setEmployeeId(employeeId);

        driver.findElement(saveButton).click();
        Thread.sleep(Duration.ofSeconds(10));

        return  employeeDetail;
    }

    public boolean checkIfUserOnPersonalDetailsPage(){
        return driver.findElement(personalDetailHeader).isDisplayed();
    }

    public void searchEmployee(String employeeName,String employeeId) throws InterruptedException {
        driver.findElement(personalDetailemployeeNameTxtField).sendKeys(employeeName);
        driver.findElement(perosnalDetailemployeeIdTxtField).click();
        driver.findElement(perosnalDetailemployeeIdTxtField).sendKeys(employeeId);
        driver.findElement(searchButton).click();
        Thread.sleep(Duration.ofSeconds(10));
    }

    public List<String> getRecordsTableHeader(){
        return driver.findElements(recordTableHeaderList).stream().map(WebElement::getText).toList();
    }

    public int getcolumnIndex(String columnName){
        return getRecordsTableHeader().indexOf(columnName)+1;
    }

    public List<String> getColumnValues(String columnName){
        int columnIndex = getcolumnIndex(columnName);
        return driver.findElements(By.xpath(Locators.PIMPage.recordTableColumnValues(columnIndex))).stream().map(WebElement::getText).toList();
    }

    public void addAttachementAndSave(String filePath) throws InterruptedException, AWTException {
        driver.findElement(addAttachementButton).click();
        driver.findElement(fileUploadTextField).click();
        Thread.sleep(Duration.ofSeconds(10));

        // Copy the file path to the clipboard
        StringSelection selection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        Robot robot = new Robot();
        robot.setAutoDelay(1000);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        // Press Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        System.out.println("File uploaded successfully!");

        driver.findElement(saveAndAddAttachementSaveButton).click();
        Thread.sleep(Duration.ofSeconds(10));
    }
}
