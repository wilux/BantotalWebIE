package Action;

import Tools.Frame;
import com.google.common.base.Stopwatch;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Wait {
    WebDriver driver;


    public Wait(WebDriver driver) {

        this.driver = driver;

    }



    public void Refresh() {
        By locator = By.xpath("//img[contains(@src, 'wait.gif')]");
        Frame frame = new Frame ( driver );
        frame.BuscarFrame ( locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));

    }
}

