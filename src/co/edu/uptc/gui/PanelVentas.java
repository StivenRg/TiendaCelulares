package co.edu.uptc.gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PanelVentas extends JPanel{
	private JTextArea txInformacion;

	public PanelVentas (Evento evento){
		setBorder(new TitledBorder("Linea Texto de Ventas:"));
		txInformacion = new JTextArea(60, 30);

		JButton accion1 = new JButton(Evento.CARGAR_VENTAS);
		accion1.addActionListener(evento);
		accion1.setActionCommand(Evento.CARGAR_VENTAS);
		setLayout(new BorderLayout());

		add(txInformacion, BorderLayout.CENTER);
		add(accion1, BorderLayout.SOUTH);
	}

	/// Metodo encargado de obtener los datos de la venta
	///
	/// @return String[]: Datos de la venta separados por \n
	public String[] obtenerDatos (){
		final String separadorLinea = "\n";
		String       lineas         = txInformacion.getText();
		return lineas.split(separadorLinea);
	}
}