package co.edu.uptc.gui;

import co.edu.uptc.negocio.Inventario;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame{
	private PanelInventario info;
	private PanelVentas     infoVentas;
	private PanelBotones    botones;
	private PanelPersona    persona;

	public VentanaPrincipal (){
		setTitle("Mi Tienda");
		setSize(1100, 600);

		Evento evento = new Evento(this);
		info       = new PanelInventario(evento);
		infoVentas = new PanelVentas(evento);
		botones    = new PanelBotones(evento);
		persona    = new PanelPersona(evento);

		setLayout(new BorderLayout());
		add(info, BorderLayout.WEST);
		add(persona, BorderLayout.CENTER);
		add(infoVentas, BorderLayout.EAST);
		add(botones, BorderLayout.SOUTH);
	}

	public static void main (String[] args){
		VentanaPrincipal ventana = new VentanaPrincipal();
		ventana.setVisible(true);
	}

	public void cargarInfoInventario (){
		//TODO implementar logica para separa información
		// trae lo que hay en textArea en String
		info.obtenerDatos();
		JOptionPane.showMessageDialog(this, "Productos Agregados al Stock");
	}

	public void cargarInfoVentas (){
		//TODO implementar logica para separa información
		// trae lo que hay en textArea en String
		infoVentas.obtenerDatos();
	}

	public void generarInformeInventario (){
		JOptionPane.showMessageDialog(this, "Cargando Inventario...");
		Inventario.mostarTablaInventario();
	}

	public void cargarInfoPersona (){
	}

	public void salir (){
		//TODO investigar como cerrar un JFRAME
		System.exit(0);
	}
}
