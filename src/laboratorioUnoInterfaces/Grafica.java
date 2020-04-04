/*
 * Archivo: Grafica.java
 * Fecha creacion: 11 de febrereo de 2020
 * Fecha última modificacion: 17 de febrero de 2020
 * Autores: Jaimen Aza           
 * 				Juan Camilo Quintero    
 */

package laboratorioUnoInterfaces;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

// TODO: Auto-generated Javadoc
/**
 * The Class Grafica.
 * Crea una grafica tipo freeChart y puede graficar, guardar y limpiar
 * los datos obtenidos. 
 * Relacion: tiene relacion con la clase Archivos.
 */
public class Grafica {
	
	/** The series. */
	private XYSeries series;
	
	/** The dataset. */
	private XYSeriesCollection dataset;
	
	/** The chart. */
	private JFreeChart chart;
	
	/** The plot. */
	private XYPlot plot;
	
	/** The grafica. */
	private ChartPanel grafica;
	
	/** The renderer. */
	private XYLineAndShapeRenderer renderer;
	
	/** The tiempo. */
	private double[] tiempo;
	
	/** The datos senal analoga. */
	private double[] datosSenalAnaloga;
	
	/** The datos senal digital. */
	private int[] datosSenalDigital;
	
	/** The manejar archivos. */
	private Archivos manejarArchivos;
	
	/** The numoro datos. */
	private int numeroDatos;

	/** The  para graficar los datos. */
	Thread thread;
	
	/** The senal analoga. */
	public static int senalAnaloga;
	
	/** The senal digital. */
	public static int senalDigital;
	
	/** The serial. */
	private Serial serial;
	
	/** The trama datos. */
	String tramaDatos;

	/**
	 * Instantiates a new grafica.
	 * Setea los atributos iniciales de la gráfica.
	 * @param tituloGrafica the titulo grafica
	 */
	public Grafica(String tituloGrafica){
		serial = new Serial();
		//crear gráfico lineal
		series = new XYSeries("Datos Serial");
		numeroDatos = 200;
		//crear dataset de datos XY
		dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		//generar el grafico tipo XYLineChart
		chart = ChartFactory.createXYLineChart(tituloGrafica, "tiempo (s)", "datos f(t)", dataset, PlotOrientation.VERTICAL, false, false, false);
		chart.setBackgroundPaint(new Color(0, 0, 0));
		chart.getTitle().setPaint(new Color(243, 227, 52));
		//personalizar labels de la gráfica
		plot = (XYPlot) chart.getPlot();
		plot.setDomainGridlinePaint(new Color(100,0,0));
		plot.setRangeGridlinePaint(new Color(100, 0, 0));
		plot.getDomainAxis().setTickLabelPaint(new Color(47, 167, 209));
		plot.getDomainAxis().setAutoRange(true);
		plot.getDomainAxis().setTickLabelFont(new Font("Calibri Bold Caps", Font.BOLD, 12));
		plot.getDomainAxis().setLabelPaint(new Color(208, 81, 81));
		plot.getRangeAxis().setTickLabelPaint(new Color(47, 167, 209));
		plot.getRangeAxis().setTickLabelFont(new Font("Calibri Bold Caps", Font.BOLD, 12));
		plot.getRangeAxis().setLabelPaint(new Color(208, 81, 81));
		plot.getRangeAxis().setAutoRangeMinimumSize(10);
		plot.getRangeAxis().setAutoRange(false);
		plot.setBackgroundPaint(new Color(0, 0, 0));

		renderer = (XYLineAndShapeRenderer) plot.getRenderer();
		renderer.setSeriesShapesVisible(0, false);
		renderer.setSeriesPaint(0, new Color(52, 243, 167));
	}

	/**
	 * Grafica inicial.
	 * Retorna la gráfica inicial para ser agregada a la GUI 
	 * @return the chart panel
	 */
	public ChartPanel graficaInicial() {
		grafica = new ChartPanel(chart);
		grafica.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		grafica.setBackground(Color.BLACK);
		return grafica;
	}

	/**
	 * Inits the serial, abre y configura el puerto serial.
	 */
	public void initSerial() {
		serial.abrirPuertoSerial();
		serial.configurarParametrosSerial();
	}

	/**
	 * Activar senal digital.
	 *
	 * @param cual the cual senal
	 * @param activado the activado
	 * @param tiempoMuestreo the tiempo muestreo
	 */
	public void activarSenalDigital(int cual, int activado, int tiempoMuestreo) {
		String dato = "UUU2" + String.valueOf(cual) + String.valueOf(activado)+ igualarLongitudDato(tiempoMuestreo);
		serial.escribirDato(dato);
	}

	/**
	 * Obtener datos.
	 *
	 * @param canalAnalogo the canal analogo
	 * @param canalDigital the canal digital
	 * @param canalSalidaDigital the canal salida digital
	 * @param estadoSalida the estado salida
	 * @param tiempoMuestreo the tiempo muestreo
	 */
	public void obtenerDatos( int canalAnalogo, int canalDigital, int canalSalidaDigital, int estadoSalida, int tiempoMuestreo){
		String buffer = "UUU"+String.valueOf(canalAnalogo)+String.valueOf(canalDigital)+String.valueOf(canalSalidaDigital)+String.valueOf(estadoSalida)+igualarLongitudDato(tiempoMuestreo);
		serial.escribirDato(buffer);
	}

	/**
	 * Leer datos.
	 */
	public void leerDatos(){
		serial.leerDatos();
	}

	/**
	 * Igualar longitud dato.
	 *
	 * @param numero the numero
	 * @return the string
	 */
	public String igualarLongitudDato(int numero) {
		String dato = "";
		if (numero<10) {
			dato = "00"+String.valueOf(numero);
		}else if ( numero>=10 && numero <100) {
			dato = "0"+String.valueOf(numero);
		}else{
			dato = String.valueOf(numero);
		}
		return dato;
	}

	/**
	 * Graficar datos.
	 * Actualiza la grafica inicial con los datos aleatorios creados 
	 *
	 * @param tiempoMuestreo the tiempo muestreo
	 * @param tipoGrafica the tipo grafica
	 */
	public void graficarDatos(int tiempoMuestreo, int tipoGrafica) {
		plot.getDomainAxis().setRange(0, 200 * (tiempoMuestreo * 0.001));
		thread = new Thread() {
			@Override
			public void run() {
					try {
						tiempo = new double[numeroDatos];
						datosSenalAnaloga = new double[numeroDatos];
						datosSenalDigital = new int[numeroDatos];
						int x = 0;
						int count = 0;
						for (;;) {
							sleep(tiempoMuestreo);
							double datoAnalogoAuxiliar =  senalAnaloga * (5.0 / 1023);
							double tiempoAuxiliar = x * (tiempoMuestreo * 0.001);
							if (count < numeroDatos) {
								tiempo[count] = x * (tiempoMuestreo * 0.001);
								datosSenalAnaloga[count] = senalAnaloga * (5.0 / 1023);
								datosSenalDigital[count] = senalDigital * 5;	
							}
							if (tipoGrafica == 0) {
								series.add(tiempoAuxiliar, datoAnalogoAuxiliar);								
							}
							if (tipoGrafica == 1) {
								series.add(tiempoAuxiliar, senalDigital * 5);								
							}
							if (x >= numeroDatos) {
								plot.getDomainAxis().setRange((x- numeroDatos-1) * (tiempoMuestreo * 0.001), tiempoAuxiliar);
								count = 0;
							}
							x++;
							count++;							
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}					
			}
		};
		thread.start();
	}

	/**
	 * Detener grafica.
	 */
	public void detenerGrafica() {
		thread.stop();
		limpiarGrafica();
	}

	/**
	 * Limpiar grafica.
	 */
	public void limpiarGrafica() {
		for (int i = 0; i < dataset.getSeries().size(); i++) {
			series.clear();
		}
	}

	/**
	 * Guardar datos.
	 * guarda los datos en el disco duro del computador
	 *
	 * @param cualSenal the cual senal
	 */
	public void guardarDatos(int cualSenal) {
		manejarArchivos = new Archivos();
		manejarArchivos.seleccionarDirectorio();
		String linea = " ";
		for (int index = 0; index < numeroDatos; index++) {
			if (cualSenal == 0) {
				linea = String.valueOf(tiempo[index]) + "\t" + String.valueOf(datosSenalAnaloga[index]);
			}
			if (cualSenal == 1) {
				linea = String.valueOf(tiempo[index]) + "\t" + String.valueOf(datosSenalDigital[index]);
			}
			manejarArchivos.escribirArchivo(linea);
		}
	}	
}
