package co.edu.uptc.gui;

import co.edu.uptc.modelo.Vendedor;
import co.edu.uptc.negocio.VentasPorVendedor;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PanelPersona extends JPanel{
	private JTextArea txInformacion;

	public PanelPersona (Evento evento){
		setBorder(new TitledBorder("Linea Texto de Personas:"));
		txInformacion = new JTextArea(60, 30);

		JButton accion1 = new JButton(Evento.CARGAR_PERSONAS);
		accion1.addActionListener(evento);
		accion1.setActionCommand(Evento.CARGAR_PERSONAS);
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
			String   nombre, sNumeroTelefono, tipoID, sNumeroID, tipoCuenta, sNumeroCuenta;
			try{
				nombre          = locDato[0].strip();
				sNumeroTelefono = locDato[1].strip();
				sNumeroID       = locDato[2].strip();
				tipoID          = locDato[3].strip();
				sNumeroCuenta   = locDato[4].strip();
				tipoCuenta      = locDato[5].strip();
			} catch (ArrayIndexOutOfBoundsException e){
				System.err.println("Persona no válida: " + locLinea);
				continue;
			}

			try{
				long     numeroTelefono = Long.parseLong(sNumeroTelefono);
				long     numeroID       = Long.parseLong(sNumeroID);
				long     numeroCuenta   = Long.parseLong(sNumeroCuenta);
				Vendedor vendedor       = new Vendedor(nombre, numeroTelefono, numeroID, tipoID, numeroCuenta, tipoCuenta);
				VentasPorVendedor.agregarVendedor(vendedor);
			} catch (NumberFormatException e){
				System.err.println("Error en dato numerico: " + e.getMessage());
			}
		}
	}
}