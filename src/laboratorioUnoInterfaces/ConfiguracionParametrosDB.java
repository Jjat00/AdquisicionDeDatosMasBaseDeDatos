package laboratorioUnoInterfaces;

public class ConfiguracionParametrosDB {
    private int tiempoMuestreo;
    private int idSenalAnaloga;
    private int idSenalDigital;
    private int idSenalSalida;

	public ConfiguracionParametrosDB(int tiempoMuestreo, int idSenalAnaloga, int idSenalDigital, int idSenalSalida){
        this.tiempoMuestreo = tiempoMuestreo;
        this.idSenalAnaloga = idSenalAnaloga;
        this.idSenalDigital = idSenalDigital;
        this.idSenalSalida = idSenalSalida;
    }

    public int getTiempoMuestreo() {
        return this.tiempoMuestreo;
    }

    public void setTiempoMuestreo(int tiempoMuestreo) {
        this.tiempoMuestreo = tiempoMuestreo;
    }

    public int getSenalAnaloga() {
        return this.idSenalAnaloga;
    }

    public void setSenalAnaloga(int idSenalAnaloga) {
        this.idSenalAnaloga = idSenalAnaloga;
    }

    public int getSenalDigital() {
        return this.idSenalDigital;
    }

    public void setSenalDigital(int idSenalDigital) {
        this.idSenalDigital = idSenalDigital;
    }
    public int getidSenalSalida() {
        return this.idSenalSalida;
    }

    public void setidSenalSalida(int idSenalSalida) {
        this.idSenalSalida = idSenalSalida;
    }

}
