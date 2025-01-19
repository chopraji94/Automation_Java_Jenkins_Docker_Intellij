package Common;

public class Locators {

    public static class MainPage{

        //Xpath
        public static String userDropDownXpath = "//div[@class='oxd-topbar-header']//span[@class='oxd-userdropdown-tab']";
        public static String userNameTextXpath = "//p[@class='oxd-userdropdown-name']";
        public static String userDropDownOptionXpath(String option){ return String.format("//div[@class='oxd-topbar-header']//ul[@role='menu']//li/a[text()='%s']",option); }
        public static String sideMenuOptionsListXpath = "//div[@class='oxd-sidepanel-body']//li//span";
        public static String sideMenuOptionXpath(String option) { return String.format("//div[@class='oxd-sidepanel-body']//li//span[text()='%s']",option); }
        public static String pageTitleHeaderXpath(String pageName) { return String.format("//div[@class='oxd-topbar-header-title']//h6[text()='%s']",pageName); }
    }

    public static class LoginPage{

        //Xpath
        public static String usernameFieldXpath = "//input[@name='username']";
        public static String passwordFieldXpath = "//input[@name='password']";
        public static String loginButtonXpath = "//button[text()=' Login ']";
    }

    public static class PIMPage{

        //Xpath
        public static String menuOptionsXpath(String option) { return String.format("//div/nav[@aria-label='Topbar Menu']//li//a[text()='%s']",option); }
        public static String firstNameTextFieldXpath = "//div[@class='orangehrm-card-container']//input[@name='firstName']";
        public static String middleNameTextFieldXpath = "//div[@class='orangehrm-card-container']//input[@name='middleName']";
        public static String lastNameTextFieldXpath = "//div[@class='orangehrm-card-container']//input[@name='lastName']";
        public static String usernameTextFieldXpath = "//label[text()='Username']/parent::div//following-sibling::div/input";
        public static String passwordTextFieldXpath = "//label[text()='Password']/parent::div//following-sibling::div/input";
        public static String confirmPasswordTextFieXpath = "//label[text()='Confirm Password']/parent::div//following-sibling::div/input";
        public static String createLoginDetailCheckBoxXpath = "//div[@class='oxd-switch-wrapper']//span";
        public static String saveButtonXpath = "//button[text()=' Save ']";
        public static String employeeIdTextFieldXpath = "//label[text()='Employee Id']/parent::div//following-sibling::div/input";

        //PersonalDetail
        public static String personalDetailHeaderXpath = "//div[@class='orangehrm-edit-employee-content']//h6[text()='Personal Details']";
        public static String personalDetailsEmployeeNameTextFieldXpath = "//label[text()='Employee Name']/parent::div//following-sibling::div//input";
        public static String personalDetailsemployeeIdTextFieldXpath = "//label[text()='Employee Id']/parent::div//following-sibling::div/input";
        public static String searchButtonXpath = "//button[text()=' Search ']";
        public static String addButtonXpath = "//h6[text()='Attachments']/following-sibling::button";
        public static String fileUploadFieldXpath = "//label[text()='Select File']/parent::div//following-sibling::div/div";
        public static String saveAddAttachementButtonXpath = "//h6[text()='Add Attachment']/parent::div//button[text()=' Save ']";

        //EmployeeInformation
        public static String recordTableHeader = "//div[@role='table']//div[@class='oxd-table-header']//div[@role='columnheader']";
        public static String recordTableColumnValues(int columnIndex)  {return String.format("//div[@role='table']//div[@class='oxd-table-body']//div[contains(@class,'oxd-table-row')]//div[position()=%d]/div",columnIndex);}
    }
}
