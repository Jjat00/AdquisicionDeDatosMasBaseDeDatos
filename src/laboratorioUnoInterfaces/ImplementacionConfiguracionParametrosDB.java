package laboratorioUnoInterfaces;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ImplementacionConfiguracionParametrosDB implements ConfiguracionParametrosDBDAO {
    private List<ConfiguracionParametrosDB> configuraciones;
    private BaseDeDatos dataBase;

    public ImplementacionConfiguracionParametrosDB(String url) {
        this.dataBase = new BaseDeDatos(url);
        this.dataBase.connect("jjat00", "jaiime.,,");
        this.configuraciones = new ArrayList<ConfiguracionParametrosDB>();
    }

	@Override
	public ConfiguracionParametrosDB getConfiguracion(int idConfiguracion) {
        ConfiguracionParametrosDB configuracion = null;
        ResultSet result = this.dataBase.select("SELECT id, tiempoMuestreo, idSenalAnaloga, idSenalDigital, idSenalDigital FROM configurarParametros");
        try {
            while (result.next()) {   
                int tiempoMuestreo = result.getInt("tiempoMuestreo");
                int idSenalAnaloga = result.getInt("idSenalAnaloga");
                int idSenalDigital = result.getInt("idSenalDigital");
                int idSenalSalida = result.getInt("idSenalDigital");
                configuracion = new ConfiguracionParametrosDB(tiempoMuestreo, idSenalAnaloga, idSenalDigital, idSenalSalida);
                break;
            }            
        } catch (Exception e) {
            System.out.println("error de sintaxis");
        }
        return configuracion;
	}

	@Override
	public int insertConfiguracion(int tiempoMuestreo, int idSenalAnaloga, int idSenalDigital, int idSenalSalida) {
        //String sqlCommand = "INSERT INTO configurarParametros (id, tiempoMuestreo, idSenalAnaloga, idSenalDigital, idSenalSalida) VALUES (NULL,"+ tiempoMuestreo +","+idSenalAnaloga+","+idSenalDigital+","+idSenalSalida+")";
        String sqlCommand = "insert into configurarParametros set tiempoMuestreo="+tiempoMuestreo+", idSenalAnaloga="+idSenalAnaloga+", idSenalDigital="+idSenalDigital+", idSenalSalida="+ idSenalSalida;
        int result = this.dataBase.update(sqlCommand);
        //int result = this.dataBase.update("insert int configurarParametros set tiempoMuestreo="+tiempoMuestreo+", idSenalAnaloga="+idSenalAnaloga+", idSenalDigital="+idSenalDigital+", idSenalSalida="+ idSenalSalida);
        ConfiguracionParametrosDB nuevaConfiguracion = new ConfiguracionParametrosDB(tiempoMuestreo, idSenalAnaloga, idSenalDigital, idSenalSalida);
        this.configuraciones.add(nuevaConfiguracion);
        return result;
	}

	@Override
	public int updateConfiguracion(int idConfiguracion) {
		return 0;
	}

	@Override
	public int deleteConfiguracion(int idConfiguracion) {
        int result = this.dataBase.update("DELETE FROM configurarParametros WHERE id="+ idConfiguracion);
        return result;
	}

}
