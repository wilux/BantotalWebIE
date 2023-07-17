package Config;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public abstract class BaseTest {

    public WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver newDriver) {
        driver = newDriver;
    }


    @BeforeSuite
    public void before() {
        InternetExplorerOptions ieOptions = new InternetExplorerOptions();
        ieOptions.attachToEdgeChrome();
        ieOptions.withEdgeExecutablePath("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");
        WebDriverManager.iedriver().arch32().setup();
        driver = new InternetExplorerDriver(ieOptions);
    }


    @AfterSuite
    public void after() {
        if ( driver != null ) {
            driver.quit ();
        }

    }


}