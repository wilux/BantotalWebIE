package Task;

import Action.Click;
import Action.Write;
import Config.Acciones;
import Page.BandejaTareasPage;
import Action.Grid;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BandejaTareas extends BandejaTareasPage {
    WebDriver driver;


    public BandejaTareas(WebDriver driver) {
        super ( driver );
        this.driver = driver;
    }

    public void filtrar(String nroEntrevista) {
        //Buscar entrevista
        Click click = new Click ( driver );
        Write write = new Write ( driver );
        write.Js ( InputBuscarAsunto, nroEntrevista );
        click.On ( BTNFILTER_INBOX );

    }

    public void iniciarEntrevista(String tarea) {
        //Iniciar nueva Entrevista
        Grid grid = new Grid ( driver );
        Click click = new Click ( driver );
        click.On ( BTNOPOINICIAR );
        grid.rowSelectbyText ( Proceso_Tareas, tarea );
        click.On ( BTNOPOINICIAR );
    }

    //Tomar
    public void tomarEntrevista(String nroEntrevista) throws InterruptedException {
        //frame.BuscarFrame ( bandejaTareasPage.Grilla_Tareas );
        Click click = new Click ( driver );
        Grid grid = new Grid ( driver );

        //Buscar entrevista
        filtrar ( nroEntrevista );
        //Tomar primer fila

        grid.rowSelectbyFila ( Grilla_Tareas, PrimerTarea );
        //driver.findElement ( BTNOPOTOMAR ).click ();
        click.On ( BTNOPOTOMAR );

    }

    //Ejecutar
    public void ejecutarEntrevista(String nroEntrevista) {
        //frame.BuscarFrame ( bandejaTareasPage.Grilla_Tareas );
        Click click = new Click ( driver );
        Grid grid = new Grid ( driver );

        //Buscar entrevista
        filtrar ( nroEntrevista );
        //Tomar primer fila

        grid.rowSelectbyFila ( Grilla_Tareas, PrimerTarea );
        click.On ( BTNOPOEJECUTAR );
        //driver.findElement ( BTNOPOTOMAR ).click ();
    }

    //Siguiente
    public void siguienteEntrevista(String nroEntrevista) throws InterruptedException {
        //frame.BuscarFrame ( bandejaTareasPage.Grilla_Tareas );
        Click click = new Click ( driver );
        Grid grid = new Grid ( driver );

        //Buscar entrevista
        filtrar ( nroEntrevista );
        //Tomar primer fila

        grid.rowSelectbyFila ( Grilla_Tareas, PrimerTarea );
        click.On ( BTNOPOSIGUIENTE );
        click.On ( BTNCONFIRMATION );
    }


    //Avanzar (decide que accion tomar)
    public void avanzarEntrevista(String nroEntrevista) throws InterruptedException {
        Thread.sleep ( 200 );
        By img = By.id ( "_ZG1_IMGESTADOIMAGE_0001" );
        Click click = new Click ( driver );
        Grid grid = new Grid ( driver );
        Acciones acciones = new Acciones ( driver );
        //Buscar entrevista
        filtrar ( nroEntrevista );
        Thread.sleep ( 200 );
        grid.rowSelectbyFila ( Grilla_Tareas, PrimerTarea );
        Thread.sleep ( 200 );
        if ( acciones.get ().ValueOnInput ( img ).contains ( "icono_mail_inprocess.gif" ) ) {
            click.On ( BTNOPOSIGUIENTE );
            Thread.sleep ( 200 );
            click.On ( BTNCONFIRMATION );
            //Salto mensaje y no se puede Siguiente entonces Ejecutar
            if ( acciones.get ().TextOnTag ( MsgTextArriba ) != "" ) {
                click.On ( BTNOPOEJECUTAR );
            }

        }
        else if ( acciones.get ().ValueOnInput ( img ).contains ( "icono_mail_assigned.gif" ) ) {
            click.On ( BTNOPOEJECUTAR );
        }
        else {
            Thread.sleep ( 200 );
            click.On ( BTNOPOTOMAR );
        }
    }

}
