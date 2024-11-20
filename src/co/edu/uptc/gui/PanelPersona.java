package co.edu.uptc.gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PanelPersona extends JPanel{
	private JTextArea txInformacion;

	public PanelPersona (Evento evento){
		setBorder(new TitledBorder("Línea Texto de Personas:"));
		txInformacion = new JTextArea(60, 30);

		JButton accion1 = new JButton(Evento.CARGAR_PERSONAS);
		accion1.addActionListener(evento);
		accion1.setActionCommand(Evento.CARGAR_PERSONAS);
		setLayout(new BorderLayout());

		add(txInformacion, BorderLayout.CENTER);
		add(accion1, BorderLayout.SOUTH);
	}

	public String[] obtenerDatos (){
		final String separadorLinea = "\n";
		String       lineas         = txInformacion.getText();
		return lineas.split(separadorLinea);
	}
}