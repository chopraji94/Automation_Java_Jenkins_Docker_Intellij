package TestBase;

import Pages.Common.Header;
import Pages.Common.SideMenu;
import Pages.LoginPage;
import Pages.PIMPage;
import com.github.javafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
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

        if(properties.getProperty("run_platform").equals("remote") ){
            DesiredCapabilities cap = new DesiredCapabilities();
            if(properties.getProperty("run_through").equals("docker"))
                cap.setPlatform(Platform.LINUX); // Enable this if you want to run through docker as docker by default has linux installed

            if(browser.equals("chrome")){
                cap.setBrowserName("chrome");
                ChromeOptions coptions = new ChromeOptions();
                coptions.addArguments("--disable-dev-shm-usage"); // required to run through docker
                coptions.addArguments("--no-sandbox"); // required to run through docker
                coptions.merge(cap);
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),coptions);
            }
            else if(browser.equals("edge")) {
                cap.setBrowserName("MicrosoftEdge");
                EdgeOptions eoptions = new EdgeOptions();
                eoptions.addArguments("--disable-dev-shm-usage"); // required to run through docker
                eoptions.addArguments("--no-sandbox"); // required to run through docker
                eoptions.merge(cap);
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),eoptions);
            }
        }
        else{
            if(browser.equals("chrome"))
                driver = new ChromeDriver();
            else if(browser.equals("edge")) {
                driver = new EdgeDriver();
            }
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
