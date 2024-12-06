package co.edu.uptc.gui;

import co.edu.uptc.dto.ReporteImpuestosDTO;
import co.edu.uptc.dto.ReporteMasVendidosDTO;
import co.edu.uptc.dto.ReporteStockDTO;
import co.edu.uptc.dto.ReporteVentasDTO;
import co.edu.uptc.modelo.Tienda;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame{
	private PanelInventario info;
	private PanelVentas     infoVentas;
	private PanelBotones    botones;
	private PanelPersona    persona;
	private Tienda          tienda;
	private Tabla           tabla;

	public VentanaPrincipal (){
		setTitle("Mi Tienda");
		setSize(1200, 600);

		Evento evento = new Evento(this);
		info       = new PanelInventario(evento);
		infoVentas = new PanelVentas(evento);
		botones    = new PanelBotones(evento);
		persona    = new PanelPersona(evento);
		tienda     = new Tienda();

		setLayout(new BorderLayout());
		add(info, BorderLayout.WEST);
		add(persona, BorderLayout.CENTER);
		add(infoVentas, BorderLayout.EAST);
		add(botones, BorderLayout.NORTH);
		add(new Log(), BorderLayout.SOUTH);
	}

	public static void main (String[] args){
		VentanaPrincipal ventana = new VentanaPrincipal();
		ventana.setVisible(true);
	}

	/// Metodo encargado de cargar los datos del Inventario
	public void cargarInfoInventario (){
		String[] datosProductos = info.obtenerDatos();
		tienda.agregarProductos(datosProductos);
		Log.registrar("Guardando Inventario...");
	}

	/// Metodo encargado de cargar los datos de las personas
	public void cargarInfoPersona (){
		String[] datosPersonas = persona.obtenerDatos();
		tienda.agregarVendedor(datosPersonas);
		Log.registrar("Agregando Vendedores...");
	}

	/// Metodo encargado de cargar los datos de las ventas
	public void cargarInfoVentas (){
		String[] datosVentas = infoVentas.obtenerDatos();
		tienda.agregarVenta(datosVentas);
		Log.registrar("Efectuando ventas...");
	}

	/// Metodo encargado de generar el informe de Inventario
	public void generarInformeInventario (){
		Log.registrar("Cargando Informe deInventario...");
		ReporteStockDTO reporteStock = new ReporteStockDTO();
		Tabla           tabla        = new Tabla("Reporte de Inventario", 2, 5);
		String[]        cabeceras    = {"Total Celulares", "Total Precio Base", "Total Precio Venta", "Total Impuestos", "Total Comisiones", "Total Ganancia"};
		tabla.generarCabecera(cabeceras);
		tabla.rellenarTabla(reporteStock.obtenerTablaInventario(tienda.getProductos()));
		tabla.mostrarTabla();
		Log.registrar("Informe Cargado Correctamente");
	}

	/// Metodo encargado de generar el informe de Impuestos
	public void generarInformeImpuestos (){
		Log.registrar("Cargando Informe de Impuestos...");
		ReporteImpuestosDTO reporteImpuestos = new ReporteImpuestosDTO(tienda.getVentas(), tienda.getProductos());
		Tabla               tabla            = new Tabla("Reporte de Impuestos", 4, 3);
		String[]            cabeceras        = {"Impuesto", "Total Bases Grabables", "Total Impuesto"};
		tabla.generarCabecera(cabeceras);
		tabla.rellenarTabla(reporteImpuestos.obtenerTablaImpuestos());
		tabla.mostrarTabla();
		Log.registrar("Informe Cargado Correctamente");
	}

	/// Metodo encargado de generar el informe de Ventas
	public void generarInformeVentas (){
		Log.registrar("Cargando Informe de Ventas...");
		ReporteVentasDTO reporteVentas = new ReporteVentasDTO(tienda.getProductos());
		Tabla            tabla         = new Tabla("Reporte de Ventas", 3, 6);
		String[]         cabeceras     = {"Tipo y # ID", "Nombre Vendedor", "Total Comision", "# Cuenta Banco", "Tipo Cuenta Banco", "# Celulares Vendidos"};
		tabla.generarCabecera(cabeceras);
		tabla.rellenarTabla(reporteVentas.obtenerTablaVentas(tienda.getVendedores()));
		tabla.mostrarTabla();
		Log.registrar("Informe Cargado Correctamente");
	}

	/// Metodo encargado de generar el informe de Productos Mas Vendidos
	public void generarInformeMasVendidos (){
		Log.registrar("Cargando Informe de Productos Mas Vendidos...");
		ReporteMasVendidosDTO reporteMasVendidos = new ReporteMasVendidosDTO(tienda.getVentas(), tienda.getProductos());
		Tabla                 tabla              = new Tabla("Reporte de Productos Mas Vendidos", 4, 2);
		String[]              cabeceras          = {"Concepto", "Valor"};
		tabla.generarCabecera(cabeceras);
		tabla.rellenarTabla(reporteMasVendidos.obtenerTablaMasVendidosValor());
		//tabla.rellenarTabla(reporteMasVendidos.obtenerTablaMasVendidosCantidad());
		tabla.mostrarTabla();
		Log.registrar("Informe Cargado Correctamente");
	}

	public void limpiarLog (){
		Log.limpiar();
	}

	/// Metodo encargado de salir de la aplicación
	public void salir (){
		System.exit(0);
	}
}