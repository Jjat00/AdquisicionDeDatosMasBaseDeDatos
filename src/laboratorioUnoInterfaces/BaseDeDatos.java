package laboratorioUnoInterfaces;

import java.sql.DriverManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class BaseDeDatos {
    private String urlDataBase;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSelect;
    private int resultUpdate;

    public BaseDeDatos(String url){
        urlDataBase = url;
        connection = null;
        try {
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver).newInstance();
        } catch (Exception e) {
            System.out.println("No se  puede cargar el Driver de MYSQL");
            e.printStackTrace();
            return;
        }
    }

    public void connect(String user, String password) {
        try {
            connection =  DriverManager.getConnection(urlDataBase, user, password);
            statement = connection.createStatement();
        } catch (Exception e) {
            System.out.println("No se pudo conectar a la base de  datos");
        }
    }

    public ResultSet select(String sql) {
        try {
            resultSelect = (ResultSet) statement.executeQuery(sql);
        } catch (Exception e) {
            System.out.println("El sql no arrojó resultados");
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                System.out.println("conexion cerrada");
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return resultSelect;
    }

    public int update(String sql) {
        try {
            resultUpdate =  statement.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("No se insertó el nuevo dato");
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                System.out.println("conexion cerrada");
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return resultUpdate;
    }


}
