package TestBase;

import Pages.Common.Header;
import Pages.Common.SideMenu;
import Pages.LoginPage;
import Pages.PIMPage;
import com.github.javafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {

    public WebDriver driver;
    public Faker fake;
    public Properties properties;
    public static final Logger logger = LogManager.getLogger(BaseClass.class);

    public LoginPage loginpage;
    public Header header;
    public SideMenu sideMenu;
    public PIMPage pimPage;

    @BeforeClass(groups = {"smoke"})
    @Parameters({"browser"})
    public void LaunchBrowser(String browser) throws IOException {

        FileReader file = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
        properties = new Properties();
        properties.load(file);

        if(browser.equals("chrome"))
            driver = new ChromeDriver();
        else if(browser.equals("edge")) {
            driver = new EdgeDriver();
        }


        driver.get(properties.getProperty("base_url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        InitializePages();

        fake = new Faker();
    }

    private void InitializePages(){
        if(loginpage==null){
            loginpage = new LoginPage(driver);
        }

        if(header==null){
            header = new Header(driver);
        }

        if(sideMenu==null){
            sideMenu = new SideMenu(driver);
        }

        if(pimPage==null){
            pimPage = new PIMPage(driver);
        }
    }

    public void ScrollToBottomOfpage() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(Duration.ofSeconds(10));
    }

    @AfterClass(groups = {"smoke"})
    public void CloseBrowser(){
        driver.quit();
    }
}
