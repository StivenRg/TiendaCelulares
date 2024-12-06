package co.edu.uptc.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Arrays;

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

	public void rellenarTabla (ArrayList <Object> paramArrayList){
		for (Object paramObjeto : paramArrayList){
			agregarFila(paramObjeto.toString().split(","));
		}
	}

	private void agregarFila (Object[] datos){
		if (datos.length != numeroColumnas){
			new Log().registrar("No se pueden agregar los datos: " + Arrays.toString(datos));
			return;
		}
		dtm.addRow(datos);
	}

	public void mostrarTabla (){
		JScrollPane locJScrollPane = new JScrollPane(tabla);
		getContentPane().add(locJScrollPane);
		setVisible(true);
	}
}
