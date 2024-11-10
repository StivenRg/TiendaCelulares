package co.edu.uptc.negocio;

import co.edu.uptc.modelo.Vendedor;
import co.edu.uptc.modelo.Venta;

import javax.swing.*;
import java.util.ArrayList;

public class VentasPorVendedor{
	private static final ArrayList <Vendedor> listaVendedores = new ArrayList <>();

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
		tablaVentasPorVendedor.append(String.format("#### | %-12s | %-12s | %-12s | %-16s | %-10s | %-4s%n", columna0, columna1, columna2, columna3, columna4, columna5));
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
			tablaVentasPorVendedor.append(String.format("%-3d | %-12s | %-12s | $%-10.2f | $-16s | %-10d | %-4s%n", i, tipoID, nombre, totalComision, numeroCuenta, tipoCuenta, cantidadVentas));
		}
		JOptionPane.showMessageDialog(null, tablaVentasPorVendedor.toString(), "Ventas por vendedor", JOptionPane.PLAIN_MESSAGE);
	}
}