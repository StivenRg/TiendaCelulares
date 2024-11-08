package co.edu.uptc.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class VentanaPrincipal extends JFrame {

	private PanelInventario info;
	private PanelVentas infoVentas;
	private PanelBotones botones;
	private PanelPersona persona;
	
	public VentanaPrincipal(){
		setTitle("Mi Tienda");
		setSize(1100,600);
		
		Evento evento= new Evento(this);
		info= new PanelInventario(evento);
		infoVentas= new PanelVentas(evento);
		botones = new PanelBotones(evento);
		persona = new PanelPersona(evento);
		
		setLayout(new BorderLayout());
		add(info,BorderLayout.WEST);
		add(persona,BorderLayout.CENTER);
		add(infoVentas,BorderLayout.EAST);
		add(botones,BorderLayout.SOUTH);
		
		
	}
	public static void main(String[] args) {
		VentanaPrincipal nueva= new VentanaPrincipal();
		nueva.setVisible(true);
	}
	
	public void cargarInfoInventario() {
		JOptionPane.showMessageDialog(this, "Cargar contenido de inventario" );
		//TODO implementar logica para separa información
		// trae lo que hay en textArea en String
		info.obtenerDatos();
	}
	
	public void cargarInfoVentas() {
		//TODO implementar logica para separa información
		// trae lo que hay en textArea en String
		infoVentas.obtenerDatos();
	}
	
	public void generarInformeInventario() {
		JOptionPane.showMessageDialog(this, "Crear infome de inventario" );
		DialogoLista nuevo= new DialogoLista();
		String encabezado="# celulares | Total Precio base |"
				+ "Total Precio de venta |"
				+ "Total de Impuestos a pagar |"
				+ "Total comisiones de venta |"
				+ "Total ganancias ";
		nuevo.agregrarTexto(encabezado);		
		nuevo.setVisible(true);
	}
	public void salir() {
		//TODO investigar como cerrar un JFRAME
	}
}
