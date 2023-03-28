package Action;

import Tools.Frame;
import com.google.common.base.Stopwatch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class Choose {
    WebDriver driver;


    public Choose(WebDriver driver) {

        this.driver = driver;

    }

    public void byText(By locator, String text) {
        Frame frame = new Frame ( driver );
        final Stopwatch stopwatch = Stopwatch.createStarted ();
        while ((stopwatch.elapsed ( TimeUnit.SECONDS ) < 5)) {

            if ( frame.BuscarFrame ( locator ) ) {
                Select select = new Select ( driver.findElement ( locator ) );
                select.selectByVisibleText ( text );
                break;
            }
            else {
                System.out.println ( "No se encontró " + locator );
                continue;
            }
        }
    }

    public void byValue(By locator, String value) {

        Frame frame = new Frame ( driver );
        final Stopwatch stopwatch = Stopwatch.createStarted ();

        while ((stopwatch.elapsed ( TimeUnit.SECONDS ) < 5)) {
            if ( frame.BuscarFrame ( locator ) ) {
                Select select = new Select ( driver.findElement ( locator ) );
                select.selectByValue ( value );
                System.out.println ( value );
                break;
            }
            else {
                System.out.println ( "No se encontró " + locator );
                continue;
            }
        }

    }

}

