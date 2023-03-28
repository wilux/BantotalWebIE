package Action;

import Tools.Frame;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Write {
    WebDriver driver;


    public Write(WebDriver driver) {

        this.driver = driver;

    }

    public void On(By locator, String text) {
        Frame frame = new Frame ( driver );

        if ( frame.BuscarFrame ( locator ) ) {
            try {
                frame.BuscarFrame ( locator );
                driver.findElement ( locator ).clear ();
                driver.findElement ( locator ).sendKeys ( text );
                Thread.sleep ( 200 );


            } catch (Exception e) {
            }
        }
    }

    public void Js(By locator, String text) {
        Frame frame = new Frame ( driver );

        frame.BuscarFrame ( locator );
        WebElement i = driver.findElement ( locator );
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript ( "arguments[0].value='" + text + "';", i );

    }

    public void Clear(By locator) {
        Frame frame = new Frame ( driver );

        if ( frame.BuscarFrame ( locator ) ) {
            try {
                driver.findElement ( locator ).clear ();

            } catch (Exception e) {

            }
        }
    }

}


