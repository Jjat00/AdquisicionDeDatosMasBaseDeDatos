package laboratorioUnoInterfaces;

public interface ConfiguracionParametrosDBDAO {
    public ConfiguracionParametrosDB getConfiguracion(int idConfiguracion);
    public int insertConfiguracion(int tiempoMuestreo, int idSenalAnaloga, int idSenalDigital, int idSenalSalida);
    public int updateConfiguracion(int idConfiguracion);
    public int deleteConfiguracion(int idConfiguracion);
}
