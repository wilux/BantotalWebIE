package Tools;


import Config.Credenciales;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;

public class SQLDatabaseConnection {


    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public void select(String sql) {


        String connectionUrl = "jdbc:sqlserver://arcncd07;databaseName=BPN_WEB_QA;encrypt=true;integratedSecurity=true;authenticationScheme=nativeAuthentication;trustServerCertificate=true;trustStore= C:\\Program Files\\Java\\jdk1.8.0_251\\lib\\security\\cacert;trustStorePassword=changeit";
        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection ( connectionUrl );
             Statement statement = connection.createStatement ();) {

            // Create and execute a SELECT SQL statement.
            resultSet = statement.executeQuery ( sql );


            // Print results from select statement
            while (resultSet.next ()) {
                System.out.println ( resultSet.getString ( 2 ) + " " + resultSet.getString ( 3 ) );
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    public void wc(String cuil) throws ParseException {
        Credenciales credenciales = new Credenciales ();
        String db = "BPN_WEB_QA";
        String usuario = getValue ( "select J055XZUsr from J055XZ where J055XZUad='" + credenciales.username + "'", db );
        String sucursal = getValue ( "select bnqfx06suc from bnqfx06 where BNQFX06Usu = '" + usuario + "'", db );

        //Fecha BT
        String fechaBT = getValue ( "select Pgfape from fst017", db );
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern ( "HH:mm:ss" );
        LocalDateTime now = LocalDateTime.now ();
        String horaLocal = dtf.format ( now );


        // Note, MM is months, not mm
        DateFormat outputFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.US );
        DateFormat inputFormat = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.US );

        Date date1 = inputFormat.parse ( fechaBT );

        String fecha = outputFormat.format ( date1 );


        String date = fecha + " " + horaLocal;

        //Inserto consulta falsa // Sumar tiempo (sacar hora real)
        String sql_InsertBridgerInsight =
                "insert into BPNC37 select '" + usuario + "'," + sucursal + ",'10.10.6.53','" + date + "',penom,pendoc,'R','N' from " +
                        "fsd001 where pendoc = '" + cuil + "'";


        update ( sql_InsertBridgerInsight, db );


    }

    public String getValue(String sql, String db) {


        String connectionUrl = "jdbc:sqlserver://arcncd07;databaseName=" + db + ";encrypt=true;integratedSecurity=true;" +
                "authenticationScheme=nativeAuthentication;trustServerCertificate=true;trustStore= C:\\Program Files\\Java\\jdk1.8.0_251\\lib\\security\\cacert;trustStorePassword=changeit";
        ResultSet resultSet = null;
        String value = "";

        try (Connection connection = DriverManager.getConnection ( connectionUrl );
             Statement statement = connection.createStatement ();) {

            // Create and execute a SELECT SQL statement.
            resultSet = statement.executeQuery ( sql );


            // Print results from select statement
            while (resultSet.next ()) {
                value = resultSet.getString ( 1 );
            }
        } catch (SQLException e) {
//            e.printStackTrace ();
            System.out.println ( "No hay registros en la consulta" );
        }
        System.out.println ( value );
        return value;
    }

    public List<String> getValues(String sql, String db) {


        String connectionUrl = "jdbc:sqlserver://arcncd07;databaseName=" + db + ";encrypt=true;integratedSecurity=true;" +
                "authenticationScheme=nativeAuthentication;trustServerCertificate=true;trustStore= C:\\Program Files\\Java\\jdk1.8.0_251\\lib\\security\\cacert;trustStorePassword=changeit";
        ResultSet resultSet = null;
        List value = new ArrayList<> ();

        try (Connection connection = DriverManager.getConnection ( connectionUrl );
             Statement statement = connection.createStatement ();) {

            // Create and execute a SELECT SQL statement.
            resultSet = statement.executeQuery ( sql );
            ResultSetMetaData rsmd = resultSet.getMetaData ();
            int columnCount = rsmd.getColumnCount ();

            value = new ArrayList<> ( columnCount );

            // Print results from select statement
            while (resultSet.next ()) {

                int i = 1;
                while (i <= columnCount) {
                    value.add ( resultSet.getString ( i++ ) );
                }
            }
        } catch (SQLException ex) {
//            ex.printStackTrace ();
            System.out.println ( "No hay registros en la consulta" );
        }
        return value;
    }

    public int update(String sql, String db) {


        String connectionUrl = "jdbc:sqlserver://arcncd07;databaseName=" + db + ";encrypt=true;integratedSecurity=true;authenticationScheme=nativeAuthentication;trustServerCertificate=true;trustStore= C:\\Program Files\\Java\\jdk1.8.0_251\\lib\\security\\cacert;trustStorePassword=changeit";
        int result = 0;
        try (Connection connection = DriverManager.getConnection ( connectionUrl );
             Statement statement = connection.createStatement ();) {
            result = statement.executeUpdate ( sql );

        } catch (SQLException e) {
            e.printStackTrace ();
        }
        return result;
    }

    public void CambiarUsuario(String usuario) {

        Credenciales credenciales = new Credenciales ();
        String db = "BPN_WEB_QA";
        String sql = "UPDATE J055XZ SET J055XZUsr='" + usuario + "'" + " WHERE J055XZUad='" + credenciales.username + "'";
        if ( update ( sql, db ) < 0 ) {
            System.out.println ( "Cambio no efectuado" );
        }
        else {
//            System.out.println ( "Cambio realizado" );
        }
    }


    public boolean hasColumn(String tabla, String columnName) throws SQLException {


        String connectionUrl = "jdbc:sqlserver://arcncd07;databaseName=BPN_WEB_QA;encrypt=true;integratedSecurity=true;authenticationScheme=nativeAuthentication;trustServerCertificate=true;trustStore= C:\\Program Files\\Java\\jdk1.8.0_251\\lib\\security\\cacert;trustStorePassword=changeit";
        ResultSet resultSet = null;

        Connection connection = DriverManager.getConnection ( connectionUrl );
        Statement statement = connection.createStatement ();

        // Create and execute a SELECT SQL statement.
        resultSet = statement.executeQuery ( "select top 1 * from " + tabla );

        ResultSetMetaData rsmd = resultSet.getMetaData ();
        int columns = rsmd.getColumnCount ();

        for (int x = 1; x <= columns; x++) {
            if ( columnName.equals ( rsmd.getColumnName ( x ) ) ) {
                return true;
            }
        }
        return false;
    }


    public void completarLD(String cuil) throws SQLException {
        String db_LegajoDigital = "LegajoDigital_QA";
        String db_Firma = "FirmaGrafometrica_QA";

        //Obtengo idPersona
        String sql_idPersona = "select idPersona from PERSONA where CuitCuil = '" + cuil + "'";
        String idPersona = getValue ( sql_idPersona, db_LegajoDigital );

        //Obtengo lista con idTramite
        String sql_idTramite = "select idTramite from TRAMITE where idPersona = '" + idPersona + "' and estado = 1"; //1
        List<String> idTramites = getValues ( sql_idTramite, db_LegajoDigital );


        //Obtengo Tramites para una lista de idTramites
        String tramites = String.join ( ",", idTramites );

        //Aprobar Tramites
        String sql_UpdateTramite = "UPDATE Tramite set estado = 2 where  idTramite in (" + tramites + ")";
        update ( sql_UpdateTramite, db_LegajoDigital );

        //Aprobar Documentos
        String sql_updateVersionDocumento =
                "update VERSIONDOCUMENTO set estado=2 where idVersionDocumento in (select idVersionDocumento from " +
                        "TRAMITEVERSIONDOCUMENTO where idTramite in (" + tramites + "))";
        update ( sql_updateVersionDocumento, db_LegajoDigital );

        //FirmaDigital
        String sql_updateFirma = "update Tramite set activo = 0 where CuitCuil = '" + cuil + "'";
        update ( sql_updateFirma, db_Firma );
    }

    public boolean esperarFormularios(String cuil) throws SQLException {
        String db_Firma = "FirmaGrafometrica_QA";

        //FirmaDigital
        String sql_updateFirma = "select *  from TRAMITE where CuitCuil = '" + cuil + "' and activo = 1";
        return (getValue ( sql_updateFirma, db_Firma ).equals ( "" ));
    }

    public boolean estadoEntrevistaWf(String valor, String entrevista) throws SQLException {
        String db = "BPN_WEB_QA";

        //FirmaDigital
        String sql = "select top 1 b.WFTaskName from wfwrkitems a (nolock)\n" +
                "inner join wftask b (nolock) on a.WFTaskCod=b.WFTaskCod and b.WFPrcId in(6,9,10,13) --and b.WFTaskCod=70\n" +
                "inner join wfattsvalues c (nolock) on a.WFInsPrcId=c.WFInsPrcId and c.WFAttSId='ENTREVISTA' and c" +
                ".WFAttSVal in('" + entrevista + ".0000') --775731\n" +
                "order by WFItemId desc";
        return (getValue ( sql, db ).contains ( valor ));
    }


}

