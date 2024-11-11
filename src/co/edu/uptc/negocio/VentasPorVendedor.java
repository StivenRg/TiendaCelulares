package co.edu.uptc.negocio;

import co.edu.uptc.modelo.Vendedor;
import co.edu.uptc.modelo.Venta;

import javax.swing.*;
import java.util.ArrayList;

public class VentasPorVendedor{
	private static final ArrayList <Vendedor> listaVendedores = new ArrayList <>();

	public static void agregarVendedor (Vendedor... paramVendedor){
		if (paramVendedor.length > 0){
			listaVendedores.add(paramVendedor[0]);
			return;
		}

		Vendedor locVendedor = obtenerVendedor();
		if (locVendedor == null){
			return;
		}
		for (Vendedor vendedor : listaVendedores){
			if (locVendedor.getNumeroID() == (vendedor.getNumeroID())){
				return;
			}
		}
		listaVendedores.add(obtenerVendedor());
	}

	private static Vendedor obtenerVendedor (){
		String nombre          = JOptionPane.showInputDialog("Ingrese el nombre completo del vendedor:");
		String sNumeroTelefono = JOptionPane.showInputDialog("Ingrese el numero de telefono:");
		String tipoID          = JOptionPane.showInputDialog("Ingrese el tipo de ID:");
		String sNumeroID       = JOptionPane.showInputDialog("Ingrese el numero de ID:");
		String tipoCuenta      = JOptionPane.showInputDialog("Ingrese el tipo de cuenta:");
		String sNumeroCuenta   = JOptionPane.showInputDialog("Ingrese el numero de cuenta:");

		try{
			long numeroTelefono = Integer.parseInt(sNumeroTelefono);
			long numeroID       = Integer.parseInt(sNumeroID);
			long numeroCuenta   = Integer.parseInt(sNumeroCuenta);
			return new Vendedor(nombre, numeroTelefono, numeroID, tipoID, numeroCuenta, tipoCuenta);
		} catch (NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Error en los campos numericos", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	private static int obtenerCantidadVentasVendedor (Vendedor paramVendedor){
		int cantidadVentasVendedor = 0;
		for (Venta locVenta : paramVendedor.getVentasVendedor()){
			cantidadVentasVendedor += locVenta.getCantidad();
		}
		return cantidadVentasVendedor;
	}

	private static double calcularTotalComisionVendedor (Vendedor paramVendedor){
		double totalComisionVendedor = 0;
		for (Venta locVenta : paramVendedor.getVentasVendedor()){
			totalComisionVendedor += locVenta.getProducto().getPrecio() * locVenta.getCantidad() * 0.05;
		}
		return totalComisionVendedor;
	}

	private static String generarCabecerasTablaVentasPorVendedor (){
		StringBuilder tablaVentasPorVendedor = new StringBuilder();
		String        columna0               = "Tipo ID y #";
		String        columna1               = "Nombre";
		String        columna2               = "Total de comision";
		String        columna3               = "# de cuenta";
		String        columna4               = "Tipo de cuenta";
		String        columna5               = "# de ventas";
		tablaVentasPorVendedor.append(String.format("####|%-13s|%-12s|%-12s|%-16s|%-10s|%-4s%n", columna0, columna1, columna2, columna3, columna4, columna5));
		return tablaVentasPorVendedor.toString();
	}

	public static void mostarTablaVentasPorVendedor (){
		StringBuilder tablaVentasPorVendedor = new StringBuilder(generarCabecerasTablaVentasPorVendedor());
		for (Vendedor locVendedor : listaVendedores){
			int    i              = 1;
			String ID             = locVendedor.getTipoID() + locVendedor.getNumeroID();
			String nombre         = locVendedor.getNombre();
			double totalComision  = calcularTotalComisionVendedor(locVendedor);
			long   numeroCuenta   = locVendedor.getNumeroCuenta();
			String tipoCuenta     = locVendedor.getTipoCuenta();
			int    cantidadVentas = obtenerCantidadVentasVendedor(locVendedor);
			tablaVentasPorVendedor.append(String.format("%,3d|%-13s|%-12s|$%,10.2f|%,16d|%-10s|%,4d%n", i, ID, nombre, totalComision, numeroCuenta, tipoCuenta, cantidadVentas));
		}
		JOptionPane.showMessageDialog(null, tablaVentasPorVendedor.toString(), "Ventas por vendedor", JOptionPane.PLAIN_MESSAGE);
	}

	public static ArrayList <Venta> getListaVentas (){
		ArrayList <Venta> listaVentas = new ArrayList <>();
		for (Vendedor locVendedor : listaVendedores){
			listaVentas.addAll(locVendedor.getVentasVendedor());
		}
		return listaVentas;
	}

	public static ArrayList <Vendedor> getListaVendedores (){
		return listaVendedores;
	}
}