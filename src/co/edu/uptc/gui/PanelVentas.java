package co.edu.uptc.gui;

import co.edu.uptc.modelo.Vendedor;
import co.edu.uptc.negocio.VentasPorVendedor;

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

	public void obtenerDatos (){
		final String separadorDato   = "\\|";
		final String separadorLinea  = "\n";
		String       lineas          = txInformacion.getText();
		String[]     lineasSeparadas = lineas.split(separadorLinea);
		for (String locLinea : lineasSeparadas){
			String[] locDato = locLinea.strip().split(separadorDato);
			try{
				locDato[0] = locDato[0].strip();
				locDato[1] = locDato[1].strip();
				locDato[2] = locDato[2].strip();
			} catch (ArrayIndexOutOfBoundsException e){
				System.err.println("Venta no válida: " + locLinea);
				continue;
			}

			for (Vendedor locVendedor : VentasPorVendedor.getListaVendedores()){
				if (locVendedor.getCodigoVendedor().equals(locDato[0])){
					locVendedor.agregarVenta(locDato[1], Integer.parseInt(locDato[2]));
					break;
				}
			}
		}
	}
}