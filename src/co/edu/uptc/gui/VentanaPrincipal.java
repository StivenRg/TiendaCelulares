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

	public VentanaPrincipal (){
		setTitle("Mi Tienda");
		setSize(1100, 600);

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
		add(botones, BorderLayout.SOUTH);
	}

	public static void main (String[] args){
		VentanaPrincipal ventana = new VentanaPrincipal();
		ventana.setVisible(true);
	}

	public void cargarInfoInventario (){
		String[] datosProductos = info.obtenerDatos();
		tienda.agregarProductos(datosProductos);
		JOptionPane.showMessageDialog(null, "Guardando Inventario...");
	}

	public void cargarInfoPersona (){
		String[] datosPersonas = persona.obtenerDatos();
		tienda.agregarVendedor(datosPersonas);
		JOptionPane.showMessageDialog(this, "Agregando Vendedores...");
	}

	public void cargarInfoVentas (){
		String[] datosVentas = infoVentas.obtenerDatos();
		tienda.agregarVenta(datosVentas);
		JOptionPane.showMessageDialog(this, "Efectuando ventas...");
	}

	public void generarInformeInventario (){
		JOptionPane.showMessageDialog(this, "Cargando Inventario...");
		ReporteStockDTO reporteStock = new ReporteStockDTO();
		JTextArea       textArea     = new JTextArea(reporteStock.obtenerTablaInventario(tienda.getProductos()));
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textArea.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(textArea);

		JOptionPane.showMessageDialog(null, scrollPane, "Inventario", JOptionPane.PLAIN_MESSAGE);
	}

	public void generarInformeImpuestos (){
		JOptionPane.showMessageDialog(this, "Cargando Informe de Impuestos...");
		ReporteImpuestosDTO locReporteImpuestosDTO = new ReporteImpuestosDTO(tienda.getVentas(), tienda.getProductos());
		JTextArea           textArea               = new JTextArea(locReporteImpuestosDTO.obtenerTablaImpuestos());
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textArea.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(textArea);

		JOptionPane.showMessageDialog(null, scrollPane, "Impuestos", JOptionPane.PLAIN_MESSAGE);
	}

	public void generarInformeVentas (){
		JOptionPane.showMessageDialog(this, "Cargando Informe de Ventas...");
		ReporteVentasDTO locReporteVentasDTO = new ReporteVentasDTO(tienda.getProductos());
		JTextArea        textArea            = new JTextArea(locReporteVentasDTO.obtenerTablaVentas(tienda.getVendedores()));
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textArea.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(textArea);

		JOptionPane.showMessageDialog(null, scrollPane, "Ventas", JOptionPane.PLAIN_MESSAGE);
	}

	public void generarInformeMasVendidos (){
		JOptionPane.showMessageDialog(this, "Cargando Informe de Productos Mas Vendidos...");
		ReporteMasVendidosDTO locReporteMasVendidosDTO = new ReporteMasVendidosDTO(tienda.getVentas(), tienda.getProductos());

		//Por Valor
		//JTextArea textArea = new JTextArea(locReporteMasVendidosDTO.obtenerTablaMasVendidosValor());

		//Por Cantidad
		JTextArea textArea = new JTextArea(locReporteMasVendidosDTO.obtenerTablaMasVendidosCantidad());
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textArea.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(textArea);
		JOptionPane.showMessageDialog(null, scrollPane, "Mas ventas", JOptionPane.PLAIN_MESSAGE);
	}

	public void salir (){
		System.exit(0);
	}
}