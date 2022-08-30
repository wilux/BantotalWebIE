package Task;

import Action.*;
import Page.EjecutarPage;
import Page.SimulacionProductosPage;
import Tools.Frame;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Simulacion extends SimulacionProductosPage {
    WebDriver driver;


    public Simulacion(WebDriver driver) {
        super ( driver );
        this.driver = driver;
    }

    public void Paquete(String paquete) {

        Choose choose = new Choose ( driver );
        choose.byText ( SelectPaquete, paquete );

    }

    //    value "309203"+003 -> 309/203
    public void Linea(String ejemplo_309203) throws InterruptedException {
        Thread.sleep ( 3000 );
        Choose choose = new Choose ( driver );
        choose.byValue ( SelectLineaPrestamo, ejemplo_309203 + "003" );
        Thread.sleep ( 1000 );

    }


    public void DestinoFondos(String value) {

        Choose choose = new Choose ( driver );
        choose.byValue ( SelectDestinoFondos, value );

    }

    public void Plazo(String plazo) {

        Write write = new Write ( driver );
        write.On ( InputPlazo, plazo );


    }

    public void Monto(String monto) throws InterruptedException {

        Thread.sleep ( 3000 );
        Write write = new Write ( driver );
        write.Js ( InputMontoSolicitado, monto );
        Thread.sleep ( 3000 );


    }

    public boolean Existe() {

        Get get = new Get ( driver );

        return get.Visible ( SelectPaquete );

    }

    public String Tna() {

        Get get = new Get ( driver );

        return get.TextOnTag ( Span_Tna );
//        return get.ValueOnInput ( input_Tna );

    }

    public void Simular() throws InterruptedException {

        Click click = new Click ( driver );
        click.On ( BTNOPSIMULAR );
        Thread.sleep ( 3000 );

    }

    public void Tarjetas() {

        Click click = new Click ( driver );
        CheckBox checkBox = new CheckBox ( driver );
        checkBox.Check ( CheckTC1 );
        checkBox.Check ( CheckTC2 );

        click.On ( BTNOPPAQUETIZAR );


    }

    public void Descartar() {

        Click click = new Click ( driver );
        click.On ( BTNOPDESCARTAR );


    }

    public void Confirmar() throws InterruptedException {

        Click click = new Click ( driver );
        Thread.sleep ( 4000 );
        click.On ( BTNOPSIMULAR );
        click.On ( BTNOPCONFIRMAR );
        Thread.sleep ( 4000 );
//        click.On ( BTN_SI );


    }


    public void ConfirmarPlanPago() throws InterruptedException {

        Frame frame = new Frame ( driver );
        Thread.sleep ( 4000 );
        frame.BuscarFrame ( BTNOPCOMISION );
        driver.findElement ( BTNOPCONFIRMAR ).click ();
    }

    public void ConfirmarSimulacion() throws InterruptedException {
        Click click = new Click ( driver );
        Frame frame = new Frame ( driver );
        Thread.sleep ( 4000 );
        frame.BuscarFrame ( BTNOPCARTASIMULADOR );
        driver.findElement ( BTNOPCONFIRMAR ).click ();
        Thread.sleep ( 4000 );
        click.On ( BTN_SI );
    }

    public void Cerrar() {

        Click click = new Click ( driver );
        click.On ( BTNOPCERRAR );


    }
}
