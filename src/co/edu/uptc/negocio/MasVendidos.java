package co.edu.uptc.negocio;

import co.edu.uptc.modelo.Producto;
import co.edu.uptc.modelo.Venta;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MasVendidos{
	private static final ArrayList <Venta> listaVentas = VentasPorVendedor.getListaVentas();

	private static HashMap <String, Integer> obtenerVentasPorMarca (){
		if (listaVentas.isEmpty()) return null;
		HashMap <String, Integer> ventasPorMarca = new HashMap <>();
		for (Venta locVenta : listaVentas){
			Producto locProducto = locVenta.getProducto();
			if (locProducto.getCantidad() <= 0) continue;
			if (ventasPorMarca.containsKey(locProducto.getMarca())){
				int cantidad = ventasPorMarca.get(locProducto.getMarca());
				cantidad += locProducto.getCantidad();
				ventasPorMarca.put(locProducto.getMarca(), cantidad);
				continue;
			}
			ventasPorMarca.put(locProducto.getMarca(), locProducto.getCantidad());
		}
		return ventasPorMarca;
	}

	private static String[] obtenerMarcaMasVendida (){
		HashMap <String, Integer> ventasPorMarca = obtenerVentasPorMarca();
		if (ventasPorMarca == null) return null;

		String nombreMarcaMasVendida   = "";
		int    cantidadMarcaMasVendida = 0;
		for (HashMap.Entry <String, Integer> locMarca : ventasPorMarca.entrySet()){
			if (locMarca.getValue() <= 0) continue;
			if (locMarca.getValue() > cantidadMarcaMasVendida){
				nombreMarcaMasVendida   = locMarca.getKey();
				cantidadMarcaMasVendida = locMarca.getValue();
			}
		}
		return new String[] {nombreMarcaMasVendida, String.valueOf(cantidadMarcaMasVendida)};
	}

	private static HashMap <String, Integer> obtenerVentasPorLinea (){
		if (listaVentas.isEmpty()) return null;
		HashMap <String, Integer> ventasPorLinea = new HashMap <>();
		for (Venta locVenta : listaVentas){
			Producto locProducto = locVenta.getProducto();
			if (locProducto.getCantidad() <= 0) continue;
			if (ventasPorLinea.containsKey(locProducto.getLinea())){
				int cantidad = ventasPorLinea.get(locProducto.getLinea());
				cantidad += locProducto.getCantidad();
				ventasPorLinea.put(locProducto.getLinea(), cantidad);
				continue;
			}
			ventasPorLinea.put(locProducto.getLinea(), locProducto.getCantidad());
		}
		return ventasPorLinea;
	}

	private static String[] obtenerLineaMasVendida (){
		HashMap <String, Integer> ventasPorLinea = obtenerVentasPorLinea();
		if (ventasPorLinea == null) return null;

		String nombreLineaMasVendida   = "";
		int    cantidadLineaMasVendida = 0;
		for (HashMap.Entry <String, Integer> locLinea : ventasPorLinea.entrySet()){
			if (locLinea.getValue() <= 0) continue;
			if (locLinea.getValue() > cantidadLineaMasVendida){
				nombreLineaMasVendida   = locLinea.getKey();
				cantidadLineaMasVendida = locLinea.getValue();
			}
		}
		return new String[] {nombreLineaMasVendida, String.valueOf(cantidadLineaMasVendida)};
	}

	public static void mostarTablaMasVendidos (){
		String[] marcaMasVendida = obtenerMarcaMasVendida();
		String[] lineaMasVendida = obtenerLineaMasVendida();
		if (marcaMasVendida == null || lineaMasVendida == null){
			JOptionPane.showMessageDialog(null, "No hay ventas para mostrar.", "No hay ventas", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		StringBuilder tablaMasVendidos = new StringBuilder();
		String        columna0         = "Concepto";
		String        columna1         = "Valor";
		tablaMasVendidos.append(String.format("%-30s | %-15s%n", columna0, columna1));

		String fila1 = "Marca +Vendida";
		String fila2 = "Cantidad ventas Marca +Vendida";
		tablaMasVendidos.append(String.format("%-30s | %-15s%n", fila1, marcaMasVendida[0]));
		tablaMasVendidos.append(String.format("%-30s | %-15s%n", fila2, marcaMasVendida[1]));

		String fila3 = "Linea +Vendida";
		String fila4 = "Cantidad ventas Línea +Vendida";
		tablaMasVendidos.append(String.format("%-30s | %-15s%n", fila3, lineaMasVendida[0]));
		tablaMasVendidos.append(String.format("%-30s | %-15s%n", fila4, lineaMasVendida[1]));

		JOptionPane.showMessageDialog(null, tablaMasVendidos.toString(), "Mas ventas", JOptionPane.PLAIN_MESSAGE);
	}
}