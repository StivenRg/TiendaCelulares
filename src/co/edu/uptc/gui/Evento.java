package co.edu.uptc.gui;

import co.edu.uptc.negocio.Impuestos;
import co.edu.uptc.negocio.MasVendidos;
import co.edu.uptc.negocio.VentasPorVendedor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Evento implements ActionListener{
	public static final String           VENTAS            = "VENTAS";
	public static final String           STOCK             = "STOCK";
	public static final String           MAS_VENDIDO       = "+ VENDIDO";
	public static final String           IMPUESTOS         = "IMPUESTOS";
	public static final String           CARGAR_INVENTARIO = "Cargar Inventario";
	public static final String           CARGAR_PERSONAS   = "Cargar Persona";
	public static final String           CARGAR_VENTAS     = "Cargar Ventas";
	public static final String           SALIR             = "SALIR";
	private             VentanaPrincipal ventana;

	public Evento (VentanaPrincipal ventanaPrincipal){
		this.ventana = ventanaPrincipal;
	}

	@Override public void actionPerformed (ActionEvent e){
		String evento = e.getActionCommand();

		//TODO reto del dia llamar a los demas metodos
		switch (evento){
			case CARGAR_INVENTARIO -> ventana.cargarInfoInventario();
			case CARGAR_VENTAS -> ventana.cargarInfoVentas();
			case CARGAR_PERSONAS -> ventana.cargarInfoPersona();
			case SALIR -> ventana.salir();
			case STOCK -> ventana.generarInformeInventario();
			case IMPUESTOS -> Impuestos.mostarTablaImpuestos();
			case VENTAS -> VentasPorVendedor.mostarTablaVentasPorVendedor();
			case MAS_VENDIDO -> MasVendidos.mostarTablaMasVendidos();
		}
	}
}
