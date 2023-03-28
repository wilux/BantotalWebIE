package Task;


import Action.CheckBox;
import Action.Click;
import Action.Get;
import Page.PlanPagosAmortizablesPage;
import com.google.common.base.Stopwatch;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class PlanPagosAmortizables extends PlanPagosAmortizablesPage {

    WebDriver driver;

    public PlanPagosAmortizables(WebDriver driver) {

        super ( driver );
        this.driver = driver;

    }

    public void Confirmar() {
        Click click = new Click ( driver );
        Get get = new Get ( driver );

        if ( get.Habilitado ( BTNOPCOMISION ) ) {
            click.On ( BTNOPCONFIRMAR );
        }


    }

    public void VinculaGarantia() {
        Click click = new Click ( driver );
        Get get = new Get ( driver );
        final Stopwatch stopwatch = Stopwatch.createStarted ();
        while ((stopwatch.elapsed ( TimeUnit.SECONDS ) < 10)) {
            if ( get.Existe ( checkPartida ) ) {
                CheckBox checkBox = new CheckBox ( driver );
                checkBox.Check ( checkPartida );
                break;
            }
            else {
                continue;
            }
        }
        click.On ( BTNOPRESUMEN );
    }


}


