package co.edu.uptc.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Evento implements ActionListener {

	public static final String VENTAS="VENTAS";
	
	public static final String STOCK="STOCK";
	
	public static final String MAS_VENDIDO="+ VENDIDO";
	
	public static final String IMPUESTOS="IMPUESTOS";
	
	public static final String CARGAR="Cargar Inverntario";
	
	public static final String CARGAR_PERSONAS="Cargar Persona";
	
	public static final String CARGAR_VENTAS="Cargar Ventas";
	
	public static final String SALIR="SALIR";
	
	
	private VentanaPrincipal ventana;
	
	public Evento(VentanaPrincipal ventanaPrincipal) {
		this.ventana= ventanaPrincipal;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String evento= e.getActionCommand();
		
		//TODO reto del dia llamar a los demas metodos
		if(evento.equals(CARGAR)) {
			ventana.cargarInfoInventario();;
		}else if(evento.equals(CARGAR_VENTAS)) {
			ventana.cargarInfoInventario();;
		}else if(evento.equals(STOCK)) {
			ventana.generarInformeInventario();
		}		
		else if(evento.equals(SALIR)) {
			ventana.salir();
		}
			
		
	}

}
