package TestCase;


import Config.Acciones;
import Config.BaseTest;
import Task.Simulacion;
import Tools.SQLDatabaseConnection;
import org.testng.Assert;
import org.testng.annotations.Test;
import Page.SimulacionProductosPage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SimularTest extends BaseTest {


    @Test
    public void Simular() throws InterruptedException, SQLException {


        //Instanciamos clases que usaremos
        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
        Acciones acciones = new Acciones ( driver );

//        bd.CambiarUsuario ( "SERPILLOE" );

        //Logueamos
        acciones.login ().Ingresar ( "QA" );

        //Menu Ejecutar
        acciones.menu ().Ejecutar ();
        //Abrir BandejaTareas
        acciones.ejecutar ().Programa ( "hxwf900" );

        //Ejecutar entrevista
        acciones.bandejaTareas().ejecutarEntrevista("1609526");

        //Si llegamos hasta la pantalla de simulacion la prueba fue exitosa.
        Assert.assertTrue ( acciones.simulacion ().Existe () );


        // recorrer todas las lineas y guardar valores a cotejar con nuevo panel
        List<Simulacion.Valores> listaA;
        List<Simulacion.Valores> listaB;
        List<String> list = acciones.simulacion().Lineas();

        // Primer pantalla de prestamo obtengo valores
        listaA = acciones.simulacion().getValues(list);

        for (Simulacion.Valores valores : listaA) {
            System.out.println("Panel Viejo -> "+ valores.toString());

        }

        //Abro nuevo panel de prestamos
        acciones.simulacion().Prestamos();

        // Nueva pantalla de prestamo obtengo valores
        listaB = acciones.simulacion().getValues(list);

        for (Simulacion.Valores valores : listaB) {
            System.out.println("Panel Nuevo -> "+ valores.toString());
        }

        // Obtengo diferencias
        List<Simulacion.Valores> listaDiferencias = new ArrayList<>();
        for (Simulacion.Valores valores : listaA) {
            if (!listaB.contains(valores)) {
                listaDiferencias.add(valores);
            }
        }

        for (Simulacion.Valores valores : listaDiferencias) {
            System.out.println("Diferencias -> "+ valores.toString());
        }

        // Valido que sean iguales ambas listas
        assert listaA.equals(listaB);


    }

}

