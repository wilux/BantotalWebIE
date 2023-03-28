package Config;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
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
        System.setProperty ( "webdriver.ie.driver", "webdriver/IEDriverServer.exe" );
        driver = new InternetExplorerDriver ();
    }


    @AfterSuite
    public void after() {
        if ( driver != null ) {
            driver.quit ();
        }

    }


}