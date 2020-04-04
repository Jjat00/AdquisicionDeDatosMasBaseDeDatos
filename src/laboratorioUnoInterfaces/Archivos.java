/*
 * Archivo: Archivos.java
 * Fecha creacion: 11 de febrereo de 2020
 * Fecha ultima modificacion: 17 de febrero de 2020
 * @Author: Jaimen Aza               
 *          Juan Camilo Quintero
 */

package laboratorioUnoInterfaces;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import javax.swing.JFileChooser;

/**
 * The Class Archivos. 
 * Contiene los métodos para abrir un directorio, leer y escribir un archivo.
 * Relación: No tiene 
 */
public class Archivos {
    
    /** The file read. */
    private FileReader fileRead;
    
    /** The input. */
    private BufferedReader input;
    
    /** The file writer. */
    private FileWriter fileWriter;
    
    /** The output. */
    private BufferedWriter output;
    
    /** The chooser. */
    private JFileChooser chooser;
    
    /** The ruta archivo. */
    private File rutaArchivo;

    /**
     * Seleccionar directorio.
     * Abre una ventana en donde el usuario escoge la ruta para guardar
     * un archivo.
     */
    public void seleccionarDirectorio() {
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Escoger ruta para guardar archivo");    
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
            rutaArchivo = chooser.getSelectedFile();          
            System.out.println("getSelectedFile() : " + rutaArchivo);
        } else {
            System.out.println("No Selection ");
        }
    }

    /**
     * Leer archivo.
     * lee un archivo guardado en la ruta especificada.
     * @return the string
     */
    public String leerArchivo(String pathFile){
        String texto = "";
        try {
            fileRead = new FileReader(pathFile);
            input = new BufferedReader(fileRead);
            String linea  = input.readLine();
            while (linea != null) {
                linea = input.readLine();
                texto += linea + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        return texto;
    }

    /**
     * Escribir archivo.
     * Crea un archivo de texto y escribe una linea en la ruta escogida
     * por el usuario.
     * @param linea the linea
     */
    public void escribirArchivo(String linea) {
        try {
			fileWriter = new FileWriter(rutaArchivo, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
        output = new BufferedWriter(fileWriter);
        try {
			output.write(linea);
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			output.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
            try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }
}
