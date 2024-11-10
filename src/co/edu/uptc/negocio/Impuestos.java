package co.edu.uptc.negocio;

import co.edu.uptc.modelo.Venta;

import javax.swing.*;
import java.util.ArrayList;

public class Impuestos{
	private static       double            baseGrabableImpuesto5;
	private static       double            impuesto5;
	private static       double            baseGrabableImpuesto19;
	private static       double            impuesto19;
	private static       double            totalBaseGrabable;
	private static       double            totalImpuesto;
	private static final ArrayList <Venta> listaVentas = VentasPorVendedor.getListaVentas();

	private static void calcularBaseGrabable (){
		baseGrabableImpuesto5  = 0;
		baseGrabableImpuesto19 = 19;
		for (Venta locVenta : listaVentas){
			if (locVenta.getProducto().getPrecio() > 600000){
				baseGrabableImpuesto19 += locVenta.getProducto().getPrecio();
			} else{
				baseGrabableImpuesto5 += locVenta.getProducto().getPrecio();
			}
		}
	}

	private static void calcularImpuesto (){
		impuesto5  = baseGrabableImpuesto5 * 0.05;
		impuesto19 = baseGrabableImpuesto19 * 0.19;
	}

	private static void calcularTotalBaseGrabable (){
		totalBaseGrabable = baseGrabableImpuesto5 + baseGrabableImpuesto19;
	}

	private static void calcularTotalImpuesto (){
		totalImpuesto = impuesto5 + impuesto19;
	}

	private static String generarCabecerasTablaImpuestos (){
		StringBuilder tablaImpuestos = new StringBuilder();
		String        columna0       = "Impuesto";
		String        columna1       = "Total bases grabables";
		String        columna2       = "Total impuesto";
		tablaImpuestos.append(String.format("%-20s|%-20s|%-20s%n", columna0, columna1, columna2));
		return tablaImpuestos.toString();
	}

	public static void mostarTablaImpuestos (){
		StringBuilder tablaImpuestos = new StringBuilder(generarCabecerasTablaImpuestos());
		calcularBaseGrabable();
		calcularImpuesto();
		calcularTotalBaseGrabable();
		calcularTotalImpuesto();

		String fila1 = "Impuesto 5%";
		String fila2 = "Impuesto 19%";
		tablaImpuestos.append(String.format("%-20s|$%-20.2f|$%-20.2f%n", fila1, baseGrabableImpuesto5, impuesto5));
		tablaImpuestos.append(String.format("%-20s|$%-20.2f|$%-20.2f%n", fila2, baseGrabableImpuesto19, impuesto19));

		String fila3 = "Sumatoria de Totales";
		tablaImpuestos.append(String.format("%-20s|$%-20.2f|$%-20.2f", fila3, totalBaseGrabable, totalImpuesto));

		JOptionPane.showMessageDialog(null, tablaImpuestos.toString(), "Impuestos", JOptionPane.PLAIN_MESSAGE);
	}
}