package co.edu.uptc.gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class PanelVentas extends JPanel {

	private JTextArea txInformacion;
	
	public PanelVentas(Evento evento) {
		setBorder(new TitledBorder("Linea Texto de Ventas:"));
		txInformacion= new JTextArea(60,30);
		
		JButton accion1= new JButton(Evento.CARGAR_VENTAS);
		accion1.addActionListener(evento);
		accion1.setActionCommand(Evento.CARGAR_VENTAS);
		setLayout(new BorderLayout());
		
		
		add(txInformacion,BorderLayout.CENTER);
		add(accion1,BorderLayout.SOUTH);
	}
	
	public String obtenerDatos() {
		return txInformacion.getText();
	}
}
