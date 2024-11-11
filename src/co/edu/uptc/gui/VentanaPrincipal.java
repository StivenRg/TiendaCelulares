package co.edu.uptc.gui;

import co.edu.uptc.negocio.Impuestos;
import co.edu.uptc.negocio.Inventario;
import co.edu.uptc.negocio.MasVendidos;
import co.edu.uptc.negocio.VentasPorVendedor;

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
		info.obtenerDatos();
		JOptionPane.showMessageDialog(this, "Guardando Inventario...");
	}

	public void cargarInfoPersona (){
		persona.obtenerDatos();
		JOptionPane.showMessageDialog(this, "Agregando Vendedores...");
	}

	public void cargarInfoVentas (){
		infoVentas.obtenerDatos();
		JOptionPane.showMessageDialog(this, "Generando Ventas...");
	}

	public void generarInformeInventario (){
		JOptionPane.showMessageDialog(this, "Cargando Inventario...");
		Inventario.mostrarTablaInventario();
	}

	public void generarInformeImpuestos (){
		JOptionPane.showMessageDialog(this, "Cargando Informe de Impuestos...");
		Impuestos.mostarTablaImpuestos();
	}

	public void generarInformeVentas (){
		JOptionPane.showMessageDialog(this, "Cargando Informe de Ventas...");
		VentasPorVendedor.mostrarTablaVentasPorVendedor();
	}

	public void generarInformeMasVendidos (){
		JOptionPane.showMessageDialog(this, "Cargando Informe de Productos Mas Vendidos...");
		MasVendidos.mostarTablaMasVendidos();
	}

	public void salir (){
		System.exit(0);
	}
}