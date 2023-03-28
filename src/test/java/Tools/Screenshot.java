package Tools;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Screenshot {

    WebDriver driver;

    public Screenshot(WebDriver driver) {
        this.driver = driver;
    }

    public void takeScreenshot(String pathname) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs ( OutputType.FILE );
        FileUtils.copyFile ( src, new File ( pathname ) );
    }
}
