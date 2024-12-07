package co.edu.uptc.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Tabla extends JFrame{
	private DefaultTableModel dtm;
	private JTable            tabla;
	private String            titulo;
	private int               numeroColumnas;
	private int               numeroFilas;

	/// Metodo Constructor encargado de crear una nueva tabla
	///
	/// @param paramTitulo: String con el título de la tabla
	/// @param paramNumeroFilas: int con el número de filas de la tabla
	/// @param paramNumeroColumnas: int con el número de columnas de la tabla
	public Tabla (String paramTitulo, int paramNumeroFilas, int paramNumeroColumnas){
		this.titulo         = paramTitulo;
		this.numeroFilas    = paramNumeroFilas;
		this.numeroColumnas = paramNumeroColumnas;

		setTitle(titulo);
		setSize(750, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		dtm = new DefaultTableModel(numeroFilas, numeroColumnas);
	}

	/// Metodo encargado de generar la cabecera de la tabla
	///
	/// @param nombreColumnas: Arreglo de tipo String con los nombres de las columnas de la tabla
	public void generarCabecera (String[] nombreColumnas){
		dtm.setColumnIdentifiers(nombreColumnas);
		tabla = new JTable(dtm);
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/// Metodo encargado de rellenar la tabla con los datos
	///
	/// @param paramDatos: String con los datos a rellenar la tabla
	public void rellenarTabla (String paramDatos){
		String[] datosSeparados = paramDatos.split("\n");
		for (String linea : datosSeparados){
			agregarFila(linea);
		}
	}

	/// Metodo encargado de agregar una fila a la tabla
	///
	/// @param dato: String con los datos de la fila
	private void agregarFila (String dato){
		String[] datoSeparado = dato.split("\\|");
		if (datoSeparado.length != numeroColumnas){
			Log.registrar("Error: " + dato);
			return;
		}
		dtm.addRow(datoSeparado);
	}

	/// Metodo encargado de mostrar la tabla
	public void mostrarTabla (){
		JScrollPane scrollPane = new JScrollPane(tabla);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		setVisible(true);
	}
}
