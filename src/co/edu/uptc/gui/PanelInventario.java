package co.edu.uptc.gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PanelInventario extends JPanel{
	private final JTextArea txInformacion;

	public PanelInventario (Evento evento){
		setBorder(new TitledBorder("Linea Texto de Inventario:"));
		txInformacion = new JTextArea(60, 30);

		JButton accion1 = new JButton(Evento.CARGAR_INVENTARIO);
		accion1.addActionListener(evento);
		accion1.setActionCommand(Evento.CARGAR_INVENTARIO);
		setLayout(new BorderLayout());

		add(txInformacion, BorderLayout.CENTER);
		add(accion1, BorderLayout.SOUTH);
	}

	/// Metodo encargado de obtener los datos del Inventario
	///
	/// @return String[]: Datos del Inventario separados por \n
	public String[] obtenerDatos (){
		final String separadorLinea = "\n";
		String       lineas         = txInformacion.getText();
		return lineas.split(separadorLinea);
	}
}