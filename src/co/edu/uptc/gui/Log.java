package co.edu.uptc.gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class Log extends JPanel{
	private final JTextArea txLog;

	public Log (){
		setBorder(new TitledBorder("Registro de Log"));
		txLog = new JTextArea(10, 50);
		txLog.setEditable(false);
		setLayout(new BorderLayout());
		add(txLog, BorderLayout.CENTER);
	}

	public void registrar (String mensaje){
		txLog.setEditable(true);
		txLog.append(mensaje);
		txLog.append("\n");
		txLog.setCaretPosition(txLog.getDocument().getLength());
		txLog.setEditable(false);
	}
}
