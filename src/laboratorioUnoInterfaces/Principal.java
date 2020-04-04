/*
 * Archivo: Principal.java
 * Fecha creacion: 11 de febrereo de 2020
 * Fecha última modificacion: 11 de febrero de 2020
 * Autores: Jaimen Aza     
 *              Juan Camilo Quintero          
 */

package laboratorioUnoInterfaces;

import java.awt.EventQueue;
import javax.swing.UIManager;

/**
 * The Class Principal.
 * Clase principal para ejectuar el programa
 * Relación: tiene relación con la clase GUILaboratorio
 */
public class Principal {
	
	/**
	 * The main method.
	 * Método principal que ejecuta todo el programa.
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		//try {
        //    String className = UIManager.getCrossPlatformLookAndFeelClassName();
        //    UIManager.setLookAndFeel(className);
        //} catch (Exception e) {}
		//
		//EventQueue.invokeLater(new Runnable(){
        //public void run() {
        //    	GUILaboratorio gui = new GUILaboratorio();
        //    }
		//});
		String url = "jdbc:mysql://localhost/jjat00_labTresInterfaces";
		//BaseDeDatos db = new BaseDeDatos(url);
		//db.connect("jjat00", "jaiime.,,");
		//int result = db.update("insert into configurarParametros set tiempoMuestreo="+4+", idSenalAnaloga="+7+", idSenalDigital="+4+", idSenalSalida="+ 8);
		ImplementacionConfiguracionParametrosDB configuracion = new ImplementacionConfiguracionParametrosDB(url);
		int result = configuracion.insertConfiguracion(150, 2, 3, 1);
		System.out.println(result);
		//ConfiguracionParametrosDB config = configuracion.getConfiguracion(1);
		

	}
}
