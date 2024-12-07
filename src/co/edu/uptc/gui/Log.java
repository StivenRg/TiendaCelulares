package co.edu.uptc.gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class Log extends JPanel{
	private static JTextArea txLog;

	/// Metodo Constructor encargado de crear un nuevo registro de log
	public Log (){
		setBorder(new TitledBorder("Registro de Log"));
		txLog = new JTextArea(10, 50);
		txLog.setEditable(false);
		setLayout(new BorderLayout());
		add(txLog, BorderLayout.CENTER);
	}

	/// Metodo encargado de registrar un mensaje en el registro de log
	///
	/// @param mensaje: String con el mensaje a registrar
	public static void registrar (String mensaje){
		txLog.append(mensaje);
		txLog.append("\n");
		txLog.setCaretPosition(txLog.getDocument().getLength());
	}

	/// Metodo encargado de limpiar el registro de log
	public static void limpiar (){
		txLog.setText("");
	}
}
