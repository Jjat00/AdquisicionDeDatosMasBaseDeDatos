/*
 * Archivo: GUILaboratorio.java
 * Fecha creacion: 11 de febrereo de 2020
 * Fecha última modificacion: 17 de febrero de 2020
 * Autores: Jaimen Aza           
 *              Juan Camilo Quintero    
 */
package laboratorioUnoInterfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;

// TODO: Auto-generated Javadoc
/**
 * The Class GUILaboratorio. 
 * Posee los elementos de la interfaz gráfica de usuario, los botones, las 
 * gráficas y los combobox.
 * Relación: Tiene relación con la clase Gráfica.
 */
public class GUILaboratorio extends JFrame{
	
	/** The panel senales analogicas. */
	private JPanel panelSenalesAnalogicas;
    
    /** The panel graficas. */
    private JPanel panelGraficas;
    
    /** The panel botones. */
    private JPanel panelBotones;
    
    /** The panel senales digitales. */
    private JPanel panelSenalesDigitales;
    
    /** The panel senales salida. */
    private JPanel panelSenalesSalida;
    
    /** The combo box senales analogicas. */
    private JComboBox comboBoxSenalesAnalogicas;
    
    /** The combo box senales digitales. */
    private JComboBox comboBoxSenalesDigitales;
    
    /** The combo box senal salida. */
    private JComboBox comboBoxSenalSalida;

    /** The combo box tiempos muestreo digital. */
    private JComboBox comboBoxTiemposMuestreo;

    /** The boton graficar senales analogicas. */
    private JButton botonGraficarSenalesAnalogicas;

    /** The boton graficar senales analogicas. */
    private JButton botonDetenerSenalesAnalogicas;
    
    /** The boton detener senales digital. */
    private JButton botonDetenerSenalesDigital;
    
    /** The boton guardar senales analogicas. */
    private JButton botonGuardarSenalesAnalogicas;
    
    /** The boton graficar senales digitales. */
    private JButton botonGraficarSenalesDigitales;
    
    /** The boton guardar senales digitales. */
    private JButton botonGuardarSenalesDigitales;
    
    /** The boton activar senale salida. */
    private JButton botonActivarSenaleSalida;
    
    /** The boton desactivar senal salida. */
    private JButton botonDesactivarSenalSalida;
    
    /** The boton tiempo muestreo analogico. */
    private JButton botonTiempoMuestreo;
    
    /** The label senales analogicas. */
    private JLabel labelSenalesAnalogicas;
    
    /** The label senales digitales. */
    private JLabel labelSenalesDigitales;
    
    /** The label senales salida. */
    private JLabel labelSenalesSalida;
    
    /** The label inicial. */
    private JLabel labelInicial;
    
    /** The label tiempo muestreo . */
    private JLabel labelSeleccionarTiempoMuestreo;
    
    /** The label tiempo muestreo. */
    private JLabel labelTiempoMuestreo;

    /** The label tiempo muestreo. */
    private JLabel labelTiempoMuestreoInfo;
    
    /** The label tiempo muestreo actual D. */
    private JLabel labelTiempoMuestreoActual;
    
    /** The escucha botones. */
    private EscuchaBotones escuchaBotones;

    /** The grafica senal analogica. */
    private Grafica graficaSenalAnalogica;
    
    /** The grafica senal digital. */
    private Grafica graficaSenalDigital;

    /** The tiempo muestreo senal digital. */
    private int tiempoMuestreoSenal;

    /** The canal senal anolaga. */
    private int canalSenalAnolaga;
    
    /** The canal senal digital. */
    private int canalSenalDigital;
    
    /** The canal salida digital. */
    private int canalSalidaDigital;
    
    /** The estado senal salida. */
    private int estadoSenalSalida;
    

	/**
	 * Instantiates a new GUI laboratorio.
     * Asigna los valores iniciales a la interfaz de usuario 
	 */
	public GUILaboratorio() {
        super("Laboratio 1 Interfaces");
        initUI();        
        // Set default window configuration
        this.setUndecorated(false);
        this.setBackground(new Color(0, 0, 0));
        this.setPreferredSize(new Dimension(1200,700));
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    /**
     * Inits the UI.
     * Inicializa cada compontente de la interfaz de usuario 
     */
    public void initUI() {
        //set container JFrame 
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());
        container.setBackground(new Color(0, 0, 0));
        //create controler object
        graficaSenalAnalogica = new Grafica("Señal Analógica");
        graficaSenalDigital = new Grafica("Señal Digital");
        //create listener object
        escuchaBotones = new EscuchaBotones();
        //set Jcomponenet configuration
        container.add(zonaGraficas(), BorderLayout.CENTER);
        container.add(zonaJComponents(), BorderLayout.WEST);

    }
    
	/**
	 * Zona graficas.
	 * Crea los la zonda donde se ubican las gráficas.
	 * @return the j panel
	 */
	public JPanel zonaGraficas() {
		panelGraficas = new JPanel();
        panelGraficas.setLayout(new BoxLayout(panelGraficas, BoxLayout.Y_AXIS));
        panelGraficas.setBackground(new Color(0,0,0));        
        panelGraficas.add(graficaSenalAnalogica.graficaInicial());
    	panelGraficas.add(graficaSenalDigital.graficaInicial());
		return panelGraficas;
	}
	
    /**
     * Zona J components.
     * Crea la zonda donde se añaden todos los JComponent.
     * @return the j panel
     */
    public JPanel zonaJComponents() {
    	panelBotones = new JPanel();
        panelBotones.setLayout(new GridBagLayout());
        panelBotones.setBackground(new Color(0, 0, 0));
    	GridBagConstraints constraints = new GridBagConstraints();

        labelInicial = new JLabel("SELECCIÓN DE SEÑALES");
        labelInicial.setForeground(new Color(150,0,0));
        labelInicial.setFont(labelInicial.getFont().deriveFont(Font.BOLD, 14f));
	    constraints.gridx = 0;
        constraints.gridy = 0;
        panelBotones.add(labelInicial, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
	    panelBotones.add(componentesSenalesAnalogicas(), constraints);

	    constraints.gridx = 0;
	    constraints.gridy = 2;
	    panelBotones.add(componentesSenalesDigitales(), constraints);
	    
	    constraints.gridx = 0;
	    constraints.gridy = 3;
	    panelBotones.add(componentesSenalesSalida(), constraints);
		return panelBotones;
	}
	
	/**
	 * Componentes senales analogicas.
	 * Crea los componentes necesarios para la señal analógica.
	 * @return the j panel
	 */
	public JPanel componentesSenalesAnalogicas() {
		panelSenalesAnalogicas = new JPanel();        
        panelSenalesAnalogicas.setLayout(new GridLayout(0,1));
        panelSenalesAnalogicas.setBackground(new Color(0, 0, 0));

        labelSenalesAnalogicas = new JLabel("Señales Analógicas: ");
        labelSenalesAnalogicas.setForeground(new Color(150, 0, 0));
        
        botonGraficarSenalesAnalogicas = new JButton("Graficar");
        botonGraficarSenalesAnalogicas.addMouseListener(escuchaBotones);
        botonGraficarSenalesAnalogicas.setBackground(new Color(0, 0, 0));
        botonGraficarSenalesAnalogicas.setForeground(new Color(255, 255, 255));
        botonGraficarSenalesAnalogicas.setPreferredSize(new Dimension(90, 40));
        botonGraficarSenalesAnalogicas.setEnabled(false);
        
        botonDetenerSenalesAnalogicas = new JButton("Stop");
        botonDetenerSenalesAnalogicas.addMouseListener(escuchaBotones);
        botonDetenerSenalesAnalogicas.setBackground(new Color(0, 0, 0));
        botonDetenerSenalesAnalogicas.setForeground(new Color(255, 255, 255));
        botonDetenerSenalesAnalogicas.setPreferredSize(new Dimension(90, 40));
        botonDetenerSenalesAnalogicas.setEnabled(false);

        botonGuardarSenalesAnalogicas = new JButton("Guardar");
        botonGuardarSenalesAnalogicas.addMouseListener(escuchaBotones);
        botonGuardarSenalesAnalogicas.setBackground(new Color(0, 0, 0));
        botonGuardarSenalesAnalogicas.setForeground(new Color(255, 255, 255));
        botonGuardarSenalesAnalogicas.setPreferredSize(new Dimension(90, 40));
        botonGuardarSenalesAnalogicas.setEnabled(false);

        String[] senales = { "ADC0", "ADC1", "ADC2", "ADC3", "ADC4", "ADC5"};
        comboBoxSenalesAnalogicas = new JComboBox<>(senales);
        comboBoxSenalesAnalogicas.setPreferredSize(new Dimension(150, 40));
        comboBoxSenalesAnalogicas.setBackground(new Color(0, 0, 0));
        comboBoxSenalesAnalogicas.setForeground(new Color(255, 255, 255));


        labelSeleccionarTiempoMuestreo = new JLabel("Seleccionar t muestreo");
        labelSeleccionarTiempoMuestreo.setForeground(new Color(150, 0, 0));
        labelTiempoMuestreoInfo = new JLabel("Tiempo muestreo(ms)");
        labelTiempoMuestreoInfo.setForeground(new Color(255, 255, 255));
        labelTiempoMuestreoActual = new JLabel("T. Muestreo Actual");
        labelTiempoMuestreoActual.setForeground(new Color(255, 255, 255));
        labelTiempoMuestreo = new JLabel(" ");
        labelTiempoMuestreo.setForeground(new Color(255, 255, 255));

        botonTiempoMuestreo = new JButton("Seleccionar");
        botonTiempoMuestreo.addMouseListener(escuchaBotones);
        botonTiempoMuestreo.setBackground(new Color(0, 0, 0));
        botonTiempoMuestreo.setForeground(new Color(255, 255, 255));
        botonTiempoMuestreo.setPreferredSize(new Dimension(90, 40));

        String[] tiemposMuestreo = { "30", "50", "100", "150", "200", "250" };
        comboBoxTiemposMuestreo = new JComboBox<>(tiemposMuestreo);
        comboBoxTiemposMuestreo.setPreferredSize(new Dimension(150, 40));
        comboBoxTiemposMuestreo.setBackground(new Color(0, 0, 0));
        comboBoxTiemposMuestreo.setForeground(new Color(255, 255, 255));

        panelSenalesAnalogicas.add(labelSeleccionarTiempoMuestreo);
        panelSenalesAnalogicas.add(labelTiempoMuestreoInfo);
        panelSenalesAnalogicas.add(comboBoxTiemposMuestreo);
        panelSenalesAnalogicas.add(botonTiempoMuestreo);
        panelSenalesAnalogicas.add(labelTiempoMuestreoActual);
        panelSenalesAnalogicas.add(labelTiempoMuestreo);

        panelSenalesAnalogicas.add(new JLabel(""));
        panelSenalesAnalogicas.add(labelSenalesAnalogicas);
        panelSenalesAnalogicas.add(comboBoxSenalesAnalogicas);
        panelSenalesAnalogicas.add(botonGraficarSenalesAnalogicas);
        panelSenalesAnalogicas.add(botonDetenerSenalesAnalogicas);
        panelSenalesAnalogicas.add(botonGuardarSenalesAnalogicas);

        return panelSenalesAnalogicas;
    }
    
	/**
	 * Componentes senales digitales.
	 * Crea los componentes necesarios para la señal digital.
	 * @return the j panel
	 */
	public JPanel componentesSenalesDigitales() {
        labelSenalesDigitales = new JLabel("Señales Digitales:");
        labelSenalesDigitales.setForeground(new Color(150, 0, 0));
        
        panelSenalesDigitales = new JPanel();
        panelSenalesDigitales.setLayout(new GridLayout(0,1));
        panelSenalesDigitales.setBackground(new Color(0, 0, 0));
        
        botonGraficarSenalesDigitales = new JButton("Graficar");
        botonGraficarSenalesDigitales.addMouseListener(escuchaBotones);
        botonGraficarSenalesDigitales.setBackground(new Color(0, 0, 0));
        botonGraficarSenalesDigitales.setForeground(new Color(255, 255, 255));
        botonGraficarSenalesDigitales.setPreferredSize(new Dimension(90, 40));
        botonGraficarSenalesDigitales.setEnabled(false);
        
        botonDetenerSenalesDigital = new JButton("Stop");
        botonDetenerSenalesDigital.addMouseListener(escuchaBotones);
        botonDetenerSenalesDigital.setBackground(new Color(0, 0, 0));
        botonDetenerSenalesDigital.setForeground(new Color(255, 255, 255));
        botonDetenerSenalesDigital.setPreferredSize(new Dimension(90, 40));
        botonDetenerSenalesDigital.setEnabled(false);

        botonGuardarSenalesDigitales = new JButton("Guardar");
        botonGuardarSenalesDigitales.addMouseListener(escuchaBotones);
        botonGuardarSenalesDigitales.setBackground(new Color(0, 0, 0));
        botonGuardarSenalesDigitales.setForeground(new Color(255, 255, 255));
        botonGuardarSenalesDigitales.setPreferredSize(new Dimension(90, 40));
        botonGuardarSenalesDigitales.setEnabled(false);

        String[] senales = { "DIG0", "DIG1", "DIG2", "DIG3"};
        comboBoxSenalesDigitales = new JComboBox<>(senales);
        comboBoxSenalesDigitales.setPreferredSize(new Dimension(150, 40));
        comboBoxSenalesDigitales.setBackground(new Color(0, 0, 0));
        comboBoxSenalesDigitales.setForeground(new Color(255, 255, 255));

        panelSenalesDigitales.add(new JLabel(""));
        panelSenalesDigitales.add(labelSenalesDigitales);
        panelSenalesDigitales.add(comboBoxSenalesDigitales);
        panelSenalesDigitales.add(botonGraficarSenalesDigitales);
        panelSenalesDigitales.add(botonDetenerSenalesDigital);
        panelSenalesDigitales.add(botonGuardarSenalesDigitales);

        return panelSenalesDigitales;
	}
	
	/**
	 * Componentes senales salida.
	 * Crea los componentes necesarios para las señales de salida.
	 * @return the j panel
	 */
	public JPanel componentesSenalesSalida() {
        panelSenalesSalida = new JPanel();
        panelSenalesSalida.setLayout(new GridLayout(0,1));
        panelSenalesSalida.setBackground(new Color(0, 0, 0));

        labelSenalesSalida = new JLabel("Señales de Salida:");
        labelSenalesSalida.setForeground(new Color(150, 0, 0));
        
        botonActivarSenaleSalida = new JButton("Activar");
        botonActivarSenaleSalida.addMouseListener(escuchaBotones);
        botonActivarSenaleSalida.setPreferredSize(new Dimension(150, 40));
        botonActivarSenaleSalida.setBackground(new Color(0, 0, 0));
        botonActivarSenaleSalida.setForeground(new Color(255, 255, 255));

        botonDesactivarSenalSalida = new JButton("Desactivar");
        botonDesactivarSenalSalida.setEnabled(false);
        botonDesactivarSenalSalida.addMouseListener(escuchaBotones);
        botonDesactivarSenalSalida.setPreferredSize(new Dimension(150, 40));
        botonDesactivarSenalSalida.setBackground(new Color(0, 0, 0));
        botonDesactivarSenalSalida.setForeground(new Color(255, 255, 255));

        String[] temas = { "SAL0", "SAL1", "SAL2", "SAL3"};
        comboBoxSenalSalida = new JComboBox<>(temas);
        comboBoxSenalSalida.setPreferredSize(new Dimension(150, 40));
        comboBoxSenalSalida.setBackground(new Color(0, 0, 0));
        comboBoxSenalSalida.setForeground(new Color(255, 255, 255));
        
        panelSenalesSalida.add(new JLabel(""));
        panelSenalesSalida.add(labelSenalesSalida);
        panelSenalesSalida.add(comboBoxSenalSalida);
        panelSenalesSalida.add(botonActivarSenaleSalida);
        panelSenalesSalida.add(botonDesactivarSenalSalida);

        return panelSenalesSalida;
	}

    /**
     * The Class EscuchaBotones.
     * Escucha cada unos de los eventos de los botones
     */
    private class EscuchaBotones extends MouseAdapter{
        
        /**
         * Mouse clicked.
         * La intefaz responde según las acciones del usuario.
         * @param event the event
         */
        public void mouseClicked(MouseEvent event) {

            if (event.getSource() == botonGraficarSenalesAnalogicas && botonGraficarSenalesAnalogicas.isEnabled()) {
                System.out.println("botonGraficarSenalesAnalogicas");
                botonDetenerSenalesAnalogicas.setEnabled(true);            
                String senalAnalogicaSeleccionada = comboBoxSenalesAnalogicas.getSelectedItem().toString();
                if (senalAnalogicaSeleccionada == "ADC0") {
                    canalSenalAnolaga = 0;
                } else if (senalAnalogicaSeleccionada == "ADC1") {
                    canalSenalAnolaga = 1;
                } else if (senalAnalogicaSeleccionada == "ADC2") {
                    canalSenalAnolaga = 2;
                } else if (senalAnalogicaSeleccionada == "ADC3") {
                    canalSenalAnolaga = 3;
                } else if (senalAnalogicaSeleccionada == "ADC4") {
                    canalSenalAnolaga = 4;
                } else if (senalAnalogicaSeleccionada == "ADC5") {
                    canalSenalAnolaga = 5;
                }
                graficaSenalAnalogica.obtenerDatos(canalSenalAnolaga, canalSenalDigital, canalSalidaDigital, estadoSenalSalida, tiempoMuestreoSenal);  
                graficaSenalAnalogica.leerDatos();   
                graficaSenalAnalogica.limpiarGrafica();
                graficaSenalAnalogica.graficarDatos(tiempoMuestreoSenal, 0); //cero para grafica analoga y 1 para la digital
            }
            if (event.getSource() == botonDetenerSenalesAnalogicas && botonDetenerSenalesAnalogicas.isEnabled()) {
                System.out.println("botonDetenerSenalesAnalogicas");                
                graficaSenalAnalogica.detenerGrafica();
            }
            if (event.getSource() == botonGuardarSenalesAnalogicas && botonGuardarSenalesAnalogicas.isEnabled()) {
                System.out.println("botonGuardarSenalesAnalogicas");                
                graficaSenalAnalogica.guardarDatos(0); //cero para guardar señal analoa y 1 para guardar digital
            }
            
            if (event.getSource() == botonGraficarSenalesDigitales && botonGraficarSenalesDigitales.isEnabled()) {
                System.out.println("botonGraficarSenalesDigitales");
                botonDetenerSenalesDigital.setEnabled(true);
                String senalDigitalSeleccionada = comboBoxSenalesDigitales.getSelectedItem().toString();
                if (senalDigitalSeleccionada == "DIG0") {
                    canalSenalDigital = 0;
                } else if (senalDigitalSeleccionada == "DIG1") {
                    canalSenalDigital = 1;
                } else if (senalDigitalSeleccionada == "DIG2") {
                    canalSenalDigital = 2;
                } else if (senalDigitalSeleccionada == "DIG3") {
                    canalSenalDigital = 3;
                }
                System.out.println(senalDigitalSeleccionada);
                graficaSenalDigital.obtenerDatos(canalSenalAnolaga, canalSenalDigital, canalSalidaDigital, estadoSenalSalida, tiempoMuestreoSenal);  
                graficaSenalDigital.leerDatos();   
                graficaSenalDigital.limpiarGrafica();
                graficaSenalDigital.graficarDatos(tiempoMuestreoSenal, 1);
            }
            if (event.getSource() == botonDetenerSenalesDigital && botonDetenerSenalesDigital.isEnabled()) {
                System.out.println("botonDetenerSenalesAnalogicas");                
                graficaSenalDigital.detenerGrafica();
            }
            if (event.getSource() == botonGuardarSenalesDigitales && botonGuardarSenalesDigitales.isEnabled()) {
                System.out.println("botonGuardarSenalesDigitales");
                graficaSenalDigital.guardarDatos(1);
            }

            if (event.getSource() == botonActivarSenaleSalida) {
                botonDesactivarSenalSalida.setEnabled(true);
                System.out.println("botonActivarSenaleSalida");
                String senalDeSalidaSeleccionada = comboBoxSenalSalida.getSelectedItem().toString();
                System.out.println("botonActivarSenaleSalida");
                estadoSenalSalida  = 1;
                if (senalDeSalidaSeleccionada == "SAL0") {
                    canalSalidaDigital = 0;
                } else if (senalDeSalidaSeleccionada == "SAL1") {
                    canalSalidaDigital = 1;
                } else if (senalDeSalidaSeleccionada == "SAL2") {
                    canalSalidaDigital = 2;
                } else if (senalDeSalidaSeleccionada == "SAL3") {
                    canalSalidaDigital = 3;
                }
                 graficaSenalAnalogica.obtenerDatos(canalSenalAnolaga, canalSenalDigital, canalSalidaDigital, estadoSenalSalida, tiempoMuestreoSenal);  
            }

            if (event.getSource() == botonDesactivarSenalSalida && botonDesactivarSenalSalida.isEnabled()) {
                String senalDeSalidaSeleccionada = comboBoxSenalSalida.getSelectedItem().toString();
                System.out.println("botonDesactivarSenalSalida");
                System.out.println(senalDeSalidaSeleccionada);
                estadoSenalSalida = 0;
                if (senalDeSalidaSeleccionada == "SAL0") {
                    canalSalidaDigital = 0;
                } else if (senalDeSalidaSeleccionada == "SAL1") {
                    canalSalidaDigital = 1;
                } else if (senalDeSalidaSeleccionada == "SAL2") {
                    canalSalidaDigital = 2;
                } else if (senalDeSalidaSeleccionada == "SAL3") {
                    canalSalidaDigital = 3;
                }
                 graficaSenalAnalogica.obtenerDatos(canalSenalAnolaga, canalSenalDigital, canalSalidaDigital, estadoSenalSalida, tiempoMuestreoSenal);  
            
            }

            if (event.getSource() == botonTiempoMuestreo) {
                graficaSenalAnalogica.initSerial();
                estadoSenalSalida = 0;
                String tiempoMuestreoSeleccionado = comboBoxTiemposMuestreo.getSelectedItem().toString();
                if (tiempoMuestreoSeleccionado=="30") {
                    tiempoMuestreoSenal = 30;
                }
                if (tiempoMuestreoSeleccionado=="50") {
                    tiempoMuestreoSenal = 50;
                }
                if (tiempoMuestreoSeleccionado=="100") {
                    tiempoMuestreoSenal = 100;
                }
                if (tiempoMuestreoSeleccionado=="150") {
                    tiempoMuestreoSenal = 150;
                }
                if (tiempoMuestreoSeleccionado=="200") {
                    tiempoMuestreoSenal = 200;
                }
                if (tiempoMuestreoSeleccionado=="250") {
                    tiempoMuestreoSenal = 250;
                }
	            System.out.println(tiempoMuestreoSeleccionado);
                labelTiempoMuestreo.setText(tiempoMuestreoSeleccionado);

                String senalAnalogicaSeleccionada = comboBoxSenalesAnalogicas.getSelectedItem().toString();
                if (senalAnalogicaSeleccionada == "ADC0"){
                    canalSenalAnolaga = 0;
                } else if(senalAnalogicaSeleccionada == "ADC1"){
                    canalSenalAnolaga = 1;
                } else if(senalAnalogicaSeleccionada == "ADC2"){
                    canalSenalAnolaga = 2;
                } else if(senalAnalogicaSeleccionada == "ADC3"){
                    canalSenalAnolaga = 3;
                } else if(senalAnalogicaSeleccionada == "ADC4"){
                    canalSenalAnolaga = 4;
                } else if(senalAnalogicaSeleccionada == "ADC5"){
                    canalSenalAnolaga = 5;
                }
                System.out.println(senalAnalogicaSeleccionada);

                String senalDigitalSeleccionada = comboBoxSenalesDigitales.getSelectedItem().toString();
                if (senalDigitalSeleccionada == "DIG0") {
                    canalSenalDigital = 0;
                } else if (senalDigitalSeleccionada == "DIG1") {
                    canalSenalDigital = 1;
                } else if (senalDigitalSeleccionada == "DIG2") {
                    canalSenalDigital = 2;
                } else if (senalDigitalSeleccionada == "DIG3") {
                    canalSenalDigital = 3;
                }
                System.out.println(senalDigitalSeleccionada);

                String senalDeSalidaSeleccionada = comboBoxSenalSalida.getSelectedItem().toString();
                System.out.println("botonActivarSenaleSalida");
                if (senalDeSalidaSeleccionada == "SAL0") {
                    canalSalidaDigital = 0;
                } else if (senalDeSalidaSeleccionada == "SAL1") {
                    canalSalidaDigital = 1;
                } else if (senalDeSalidaSeleccionada == "SAL2") {
                    canalSalidaDigital = 2;
                } else if (senalDeSalidaSeleccionada == "SAL3") {
                    canalSalidaDigital = 3;
                }
                System.out.println(senalDeSalidaSeleccionada);

                //graficaSenalAnalogica.obtenerDatos(canalSenalAnolaga, canalSenalDigital, canalSalidaDigital, estadoSenalSalida, tiempoMuestreoSenal);  
                // graficaSenalDigital.obtenerDatosDigitales(canalSenalDigital,tiempoMuestreoSenal);
                botonGraficarSenalesAnalogicas.setEnabled(true);
                botonGuardarSenalesAnalogicas.setEnabled(true);
                botonGraficarSenalesDigitales.setEnabled(true);
                botonGuardarSenalesDigitales.setEnabled(true);
            }

        }
    }
}