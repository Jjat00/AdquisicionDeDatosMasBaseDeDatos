/*
 * Archivo: Serial.java
 * Fecha creacion: 17 de febrereo de 2020
 * Fecha última modificacion: 18 de febrero de 2020
 * Autores: Jaimen Aza           
 * 				Juan Camilo Quintero    
 */

package laboratorioUnoInterfaces;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

// TODO: Auto-generated Javadoc
/**
 * The Class Serial.
 */
public class Serial {

    /** The port id. */
    private static CommPortIdentifier portId;
    
    /** The serial port. */
    private static SerialPort serialPort;
    
    /** The input stream. */
    private InputStream inputStream;
    
    /** The output stream. */
    private static OutputStream outputStream;
    
    /** The thread. */
    private Thread thread;
    
    /** The escucha serial. */
    private EscuchaPuertoSerial escuchaSerial;
    
    /** The header. */
    private byte header;
    
    /** The estado fsm. */
    private int estadoFsm;
    
    /** The contador FSM. */
    private int contadorFSM;
    
    /** The buffer FSM. */
    private byte[] bufferFSM;

    /**
     * Abrir puerto serial.
     */
    public void abrirPuertoSerial() {
        // crear escucha a serialPort
        escuchaSerial = new EscuchaPuertoSerial();
        header = 0x55;
        estadoFsm = 0;
        contadorFSM = 0;
        bufferFSM = new byte[6];

        try {
            portId = CommPortIdentifier.getPortIdentifier("COM1"); // ttyUSB0
        } catch (NoSuchPortException e) {
        }

        try {
            serialPort = (SerialPort) portId.open("SimpleReadApp", 1000);
        } catch (PortInUseException e) {
        }
    }

    /**
     * Configurar parametros serial.
     */
    public void configurarParametrosSerial() {
        try {
            serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        } catch (Exception e) {
        }

    }

    /**
     * Leer datos.
     */
    public void leerDatos() {
        try {
            inputStream = serialPort.getInputStream();
        } catch (IOException e) {
        }
        try {
            serialPort.addEventListener(escuchaSerial);
        } catch (TooManyListenersException e) {
        }
        serialPort.notifyOnDataAvailable(true);
    }

    /**
     * Escribir dato.
     *
     * @param cadena the cadena
     */
    public void escribirDato(String cadena) {
        try {
            outputStream = serialPort.getOutputStream();
        } catch (IOException e) {
        }
        try {
            serialPort.addEventListener(escuchaSerial);
        } catch (TooManyListenersException e) {
        }
        serialPort.notifyOnDataAvailable(true);
        try {
            outputStream.write(cadena.getBytes());
            System.out.print("numero enviado: ");
            System.out.println(cadena);
        } catch (IOException e) {
        }
    }

    /**
     * Fsm.
     *
     * @param trama the trama
     */
    public void fsm(byte trama) {
        switch (estadoFsm) {
            case 0:
                if (trama == header) {
                    estadoFsm = 1;
                } else {
                    estadoFsm = 0;
                }
                break;
            case 1:
                if (trama == header) {
                    estadoFsm = 2;
                } else {
                    estadoFsm = 0;
                }
                break;
            case 2:
                if (trama == header) {
                    estadoFsm = 3;
                } else {
                    estadoFsm = 0;
                }
                break;
            case 3:
                if (contadorFSM < 5) {
                    bufferFSM[contadorFSM] = trama;
                    contadorFSM++;
                }
                if (contadorFSM == 5) {
                    String str = new String(bufferFSM);
                    String senalAuxiliar = str.substring(0, 1);
                    Grafica.senalDigital = Integer.parseInt(senalAuxiliar);
                    System.out.println(Integer.parseInt(senalAuxiliar));
                   String senalAuxiliar1 = str.substring(1, 5);
                    System.out.println(senalAuxiliar1);
                    Grafica.senalAnaloga = Integer.parseInt(senalAuxiliar1);
                    System.out.println(Integer.parseInt(senalAuxiliar1));
                    estadoFsm = 0;
                    contadorFSM = 0;
                }
                break;
        }
    }

    /**
     * Delay.
     *
     * @param tiempo the tiempo
     */
    public void delay(int tiempo) {
        thread = new Thread() {
            public void run() {
                try {
                    while (true) {
                        sleep(tiempo);
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        thread.start();
    }

    /**
     * The Class EscuchaPuertoSerial.
     */
    private class EscuchaPuertoSerial implements SerialPortEventListener {
        
        /**
         * Serial event.
         *
         * @param event the event
         */
        public void serialEvent(SerialPortEvent event) {
            switch (event.getEventType()) {
                case SerialPortEvent.BI:
                case SerialPortEvent.OE:
                case SerialPortEvent.FE:
                case SerialPortEvent.PE:
                case SerialPortEvent.CD:
                case SerialPortEvent.CTS:
                case SerialPortEvent.DSR:
                case SerialPortEvent.RI:
                case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                    break;
                case SerialPortEvent.DATA_AVAILABLE:
                    byte[] readBuffer = new byte[20];
                    try {
                        int numeroDatos = 0;
                        while (inputStream.available() > 0) {
                            numeroDatos = inputStream.read(readBuffer);
                        }
                        int count = 0;
                        while (count < numeroDatos) {
                            fsm(readBuffer[count]);
                            count++;
                        }
                    } catch (Exception e) {
                    }
                    break;
            }
        }
    }
}
