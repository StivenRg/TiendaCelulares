package co.edu.uptc.gui;

import co.edu.uptc.modelo.Producto;
import co.edu.uptc.negocio.Inventario;

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

	public void obtenerDatos (){
		final String separadorDato  = "\\|";
		final String separadorLinea = "\n";
		String       lineas         = txInformacion.getText();
		lineas = lineas.trim();
		String[] lineasSeparadas = lineas.split(separadorLinea);
		for (String locLinea : lineasSeparadas){
			String[] locDato = locLinea.trim().split(separadorDato);
			locDato[0] = locDato[0].trim();
			locDato[1] = locDato[1].trim();
			locDato[2] = locDato[2].trim();
			locDato[3] = locDato[3].trim();
			locDato[4] = locDato[4].trim();

			Producto producto = new Producto(locDato[0], locDato[1], locDato[2], Double.parseDouble(locDato[3]), Integer.parseInt(locDato[4]));
			Inventario.agregarProducto(producto);
		}
	}
}