package Task;

import Action.*;
import Config.Acciones;
import Page.SimulacionProductosPage;
import Tools.Frame;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;


public class Simulacion extends SimulacionProductosPage {
    WebDriver driver;


    public Simulacion(WebDriver driver) {
        super ( driver );
        this.driver = driver;
    }


    public boolean simulacionVisible() {
        Get get = new Get ( driver );
        return get.Existe ( SelectPaquete );
    }

    public void Paquete(String paquete)  {

        Choose choose = new Choose ( driver );
        try {
            choose.byText(SelectPaquete, paquete);
        }catch (Exception e){};

    }


    //    value "309203"+003 -> 309/203
//    public void Linea(String ejemplo_309201) throws InterruptedException {
//        Thread.sleep ( 3000 );
//        Choose choose = new Choose ( driver );
//        choose.byValue ( SelectLineaPrestamo, ejemplo_309201 + "003" );
//        Thread.sleep ( 1000 );
//
//    }

    public void Linea(String value)  {
        try {
            Thread.sleep(3000);
        }catch (Exception e){}
        Choose choose = new Choose ( driver );
        choose.byText ( SelectLineaPrestamo, value );
        try {
            Thread.sleep(1000);
        }catch (Exception e){}

    }

//    public void Linea(String value) {
//        Choose choose = new Choose ( driver );
//        Click click = new Click(driver);
//        click.On(SelectLineaPrestamo);
//        choose.byJSandText ( SelectLineaPrestamo, value );
//        try {
//            Thread.sleep(5000);
//        }catch (Exception e){}
//
//    }
    public List Lineas()   {
        Frame frame = new Frame ( driver );
        ArrayList<String> list = new ArrayList<>();
            if ( frame.BuscarFrame ( SelectLineaPrestamo ) ) {
                WebElement select =  driver.findElement ( SelectLineaPrestamo );
//                select.click();
                List<WebElement> options = select.findElements(By.xpath("//option"));
                for (WebElement option : options) {
                    if (option.getText().contains("309")) {
                        String text = option.getText();
                        list.add(text);
                        System.out.println(option.getText());

                    }
            }
            }
    return list;
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
        write.Clear ( InputMontoSolicitado );
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

    public void Tarjetas(int cantidad) {


        Click click = new Click ( driver );
        CheckBox checkBox = new CheckBox ( driver );
        if ( cantidad == 2 ) {
            checkBox.Check ( CheckTC1 );
            checkBox.Check ( CheckTC2 );
        }
        else if ( cantidad == 1 ) {
            checkBox.Check ( CheckTC1 );
        }

        click.On ( BTNOPPAQUETIZAR );


    }

    public void Tarjetas() {


        Click click = new Click ( driver );
        CheckBox checkBox = new CheckBox ( driver );
        checkBox.Check ( CheckTC1 );
        checkBox.Check ( CheckTC2 );
        checkBox.Check ( CheckTC1 );
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

    public void Prestamos() {
        Click click = new Click ( driver );
        click.On ( BTNOPPRESTAMOS );


    }



    public List<Valores> getValues(List<String> list)  {
        List<Valores> listaX = new ArrayList<>();
        Acciones acciones = new Acciones ( driver );
        SimulacionProductosPage simulacionProductosPage = new SimulacionProductosPage(driver);

        for (String text : list) {
            acciones.choose().selectByText(simulacionProductosPage.SelectLineaPrestamo, text);
            String linea = text;
            String montoSolicitado = acciones.get().ValueOnInput(simulacionProductosPage.InputMontoSolicitado);
            String plazoMeses = acciones.get().ValueOnInput(simulacionProductosPage.InputPlazo);
            String cuotaAprox =acciones.get().TextOnTag(simulacionProductosPage.Span_CuotaAprox);
            String montoOtorgado = acciones.get().TextOnTag(simulacionProductosPage.Span_MontoAprox);
            String seguro = "SIN SEGURO DE VIDA ";
            String tasaSeguro =acciones.get().TextOnTag(simulacionProductosPage.Span_TasaSeguro);
            String comisionLiq =acciones.get().TextOnTag(simulacionProductosPage.Span_ComisLiq);
            String comisionAdm =acciones.get().TextOnTag(simulacionProductosPage.Span_ComisAdmMensual);
            String impuestos =acciones.get().TextOnTag(simulacionProductosPage.Span_Impuestos);
            String ivaIntereses =acciones.get().TextOnTag(simulacionProductosPage.Span_IvaIntereses);
            String ivaComision =acciones.get().TextOnTag(simulacionProductosPage.Span_IvaComision);
            String operacion =acciones.get().TextOnTag(simulacionProductosPage.Span_Operacion);
            String tna =acciones.get().TextOnTag(simulacionProductosPage.Span_Tna);
            String tem =acciones.get().TextOnTag(simulacionProductosPage.Span_Tna);
            String tea =acciones.get().TextOnTag(simulacionProductosPage.Span_Tna);
            Valores valores = new Valores(linea, montoSolicitado, plazoMeses, cuotaAprox, montoOtorgado, seguro, tasaSeguro,
                    comisionLiq, comisionAdm, impuestos, ivaIntereses, ivaComision, operacion, tna, tem, tea);
            listaX.add(valores);
            try{
                Thread.sleep(5000);
            }catch (Exception e){}
        }
        return listaX;
    }
   public class Valores {
        String linea;
        String montoSolicitado;
        String plazoMeses;
        String cuotaAprox;
        String montoOtorgado;
        String seguro;
        String tasaSeguro;
        String comisionLiq;
        String comisionAdm;
        String impuestos;
        String ivaIntereses;
        String ivaComision;
        String operacion;
        String tna;
        String tem;
        String tea;

        public Valores(String linea, String montoSolicitado, String plazoMeses, String cuotaAprox, String montoOtorgado, String seguro, String tasaSeguro,
                       String comisionLiq, String comisionAdm, String impuestos, String ivaIntereses, String ivaComision, String operacion,
                       String tna, String tem, String tea) {
            this.linea = linea;
            this.montoSolicitado = montoSolicitado;
            this.plazoMeses = plazoMeses;
            this.cuotaAprox = cuotaAprox;
            this.montoOtorgado = montoOtorgado;
            this.seguro = seguro;
            this.tasaSeguro = tasaSeguro;
            this.comisionLiq = comisionLiq;
            this.comisionAdm = comisionAdm;
            this.impuestos = impuestos;
            this.ivaIntereses = ivaIntereses;
            this.ivaComision = ivaComision;
            this.operacion = operacion;
            this.tna = tna;
            this.tem = tem;
            this.tea = tea;
        }

        @Override
        public String toString() {
            return "Valores{" +
                    "linea =" + linea +
                    "montoSolicitado=" + montoSolicitado +
                    ", plazoMeses=" + plazoMeses +
                    ", cuotaAprox=" + cuotaAprox +
                    ", montoOtorgado=" + montoOtorgado +
                    ", seguro='" + seguro + '\'' +
                    ", tasaSeguro=" + tasaSeguro +
                    ", comisionLiq=" + comisionLiq +
                    ", comisionAdm=" + comisionAdm +
                    ", impuestos=" + impuestos +
                    ", ivaIntereses=" + ivaIntereses +
                    ", ivaComision=" + ivaComision +
                    ", operacion=" + operacion +
                    ", tna=" + tna +
                    ", tem=" + tem +
                    ", tea=" + tea +
                    '}';
        }

       @Override
       public boolean equals(Object o) {
           if (this == o) return true;
           if (!(o instanceof Valores valores)) return false;

           if (!linea.equals(valores.linea)) return false;
           if (!montoSolicitado.equals(valores.montoSolicitado)) return false;
           if (!plazoMeses.equals(valores.plazoMeses)) return false;
           if (!cuotaAprox.equals(valores.cuotaAprox)) return false;
           if (!montoOtorgado.equals(valores.montoOtorgado)) return false;
           if (!seguro.equals(valores.seguro)) return false;
           if (!tasaSeguro.equals(valores.tasaSeguro)) return false;
           if (!comisionLiq.equals(valores.comisionLiq)) return false;
           if (!comisionAdm.equals(valores.comisionAdm)) return false;
           if (!impuestos.equals(valores.impuestos)) return false;
           if (!ivaIntereses.equals(valores.ivaIntereses)) return false;
           if (!ivaComision.equals(valores.ivaComision)) return false;
           if (!operacion.equals(valores.operacion)) return false;
           if (!tna.equals(valores.tna)) return false;
           if (!tem.equals(valores.tem)) return false;
           return tea.equals(valores.tea);
       }

       @Override
       public int hashCode() {
           int result = linea.hashCode();
           result = 31 * result + montoSolicitado.hashCode();
           result = 31 * result + plazoMeses.hashCode();
           result = 31 * result + cuotaAprox.hashCode();
           result = 31 * result + montoOtorgado.hashCode();
           result = 31 * result + seguro.hashCode();
           result = 31 * result + tasaSeguro.hashCode();
           result = 31 * result + comisionLiq.hashCode();
           result = 31 * result + comisionAdm.hashCode();
           result = 31 * result + impuestos.hashCode();
           result = 31 * result + ivaIntereses.hashCode();
           result = 31 * result + ivaComision.hashCode();
           result = 31 * result + operacion.hashCode();
           result = 31 * result + tna.hashCode();
           result = 31 * result + tem.hashCode();
           result = 31 * result + tea.hashCode();
           return result;
       }
   }
}
