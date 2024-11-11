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
			String[] locDato         = locLinea.strip().split(separadorDato);
			String   nombre          = locDato[0].strip();
			String   sNumeroTelefono = locDato[1].strip();
			String   tipoID          = locDato[2].strip();
			String   sNumeroID       = locDato[3].strip();
			String   tipoCuenta      = locDato[4].strip();
			String   sNumeroCuenta   = locDato[5].strip();

			try{
				long     numeroTelefono = Integer.parseInt(sNumeroTelefono);
				long     numeroID       = Integer.parseInt(sNumeroID);
				long     numeroCuenta   = Integer.parseInt(sNumeroCuenta);
				Vendedor vendedor       = new Vendedor(nombre, numeroTelefono, numeroID,tipoID, numeroCuenta, tipoCuenta);
				VentasPorVendedor.agregarVendedor(vendedor);
			} catch (NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Error en los campos numericos", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
