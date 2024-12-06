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

	public Tabla (String paramTitulo, int paramNumeroFilas, int paramNumeroColumnas){
		this.titulo         = paramTitulo;
		this.numeroFilas    = paramNumeroFilas;
		this.numeroColumnas = paramNumeroColumnas;
		setTitle(titulo);
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dtm = new DefaultTableModel(numeroFilas, numeroColumnas);
	}

	public void generarCabecera (String[] nombreColumnas){
		dtm.setColumnIdentifiers(nombreColumnas);
		tabla = new JTable(dtm);
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	}

	public void rellenarTabla (String paramDatos){
		String[] datosSeparados = paramDatos.split("\n");
		for (String linea : datosSeparados){
			agregarFila(linea);
		}
	}

	private void agregarFila (String dato){
		String[] datoSeparado = dato.split("\\|");
		if (datoSeparado.length != numeroColumnas){
			Log.registrar("Error: " + dato);
			return;
		}
		dtm.addRow(datoSeparado);
	}

	public void mostrarTabla (){
		JScrollPane locJScrollPane = new JScrollPane(tabla);
		getContentPane().add(locJScrollPane, BorderLayout.CENTER);
		setVisible(true);
		locJScrollPane.exit ;
	}
}
