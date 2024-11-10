package co.edu.uptc.negocio;

import co.edu.uptc.modelo.Producto;

import javax.swing.*;
import java.util.ArrayList;

public class Inventario{
	private static       int                  totalCantidad    = 0;
	private static       double               totalPrecioBase  = 0;
	private static       double               totalPrecioVenta = 0;
	private static       double               totalImpuesto    = 0;
	private static       double               totalComision    = 0;
	private static       double               totalGanancia    = 0;
	private static final ArrayList <Producto> productos        = new ArrayList <>();

	public Inventario (int paramTotalCantidad, double paramTotalPrecioBase, double paramTotalPrecioVenta, double paramTotalImpuesto, double paramTotalComision, double paramTotalGanancia){
		totalCantidad    = paramTotalCantidad;
		totalPrecioBase  = paramTotalPrecioBase;
		totalPrecioVenta = paramTotalPrecioVenta;
		totalImpuesto    = paramTotalImpuesto;
		totalComision    = paramTotalComision;
		totalGanancia    = paramTotalGanancia;
	}

	public Inventario (){
	}

	public static void agregarProducto (Producto producto){
		if (producto.getCantidad() <= 0){
			return;
		}
		for (Producto locProducto : productos){
			if (locProducto.getCodigo().equals(producto.getCodigo())){
				locProducto.setCantidad(locProducto.getCantidad() + producto.getCantidad());
				return;
			}
		}
		productos.add(producto);
	}

	public static void descontarProducto (Producto paramProducto, int paramCantidad){
		if (paramCantidad <= 0 || paramCantidad > paramProducto.getCantidad()){
			return;
		}
		paramProducto.setCantidad(paramProducto.getCantidad() - paramCantidad);
	}

	private static void calcularTotalCantidad (){
		totalCantidad = 0;
		for (Producto locProducto : productos){
			if (locProducto.getCantidad() > 0){
				totalCantidad += locProducto.getCantidad();
			}
		}
	}

	private static void calcularTotalPrecioBase (){
		totalPrecioBase = 0;
		for (Producto producto : productos){
			if (producto.getCantidad() > 0){
				totalPrecioBase += (producto.getPrecio() * producto.getCantidad());
			}
		}
	}

	private static void calcularTotalPrecioVenta (){
		totalPrecioVenta = totalPrecioBase + totalImpuesto;
	}

	private static void calcularTotalImpuesto (){
		totalImpuesto = 0;
		for (Producto producto : productos){
			if (producto.getPrecio() > 600000){
				totalImpuesto += producto.getPrecio() * 19 / 100;
			} else{
				totalImpuesto += producto.getPrecio() * 5 / 100;
			}
		}
	}

	private static void calcularTotalComision (){
		totalComision = 0;
		for (Producto producto : productos){
			if (producto.getCantidad() > 0){
				totalComision += calcularImpuesto(producto.getPrecio()) * producto.getCantidad();
			}
		}
	}

	private static double calcularImpuesto (double precioBase){
		if (precioBase > 600000){
			return precioBase * 19 / 100;
		}
		return precioBase * 0.05;
	}

	private static void calcularTotalGanancia (){
		totalGanancia = totalPrecioBase - totalComision;
	}

	private static String generarCabecerasTablaInventario (){
		StringBuilder tablaInventario = new StringBuilder();
		String        columna0        = "Codigo";
		String        columna1        = "Numero total de celulares";
		String        columna2        = "TotalPrecio base";
		String        columna3        = "Total Precio de venta";
		String        columna4        = "Total de Impuestos a pagar";
		String        columna5        = "Total comisiones de venta";
		String        columna6        = "Total ganancias";
		tablaInventario.append(String.format("#### | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s%n%n", columna0, columna1, columna2, columna3, columna4, columna5, columna6));
		return tablaInventario.toString();
	}

	public static void mostarTablaInventario (){
		StringBuilder tablaInventario = new StringBuilder(generarCabecerasTablaInventario());
		for (Producto locProducto : productos){
			int    i              = 1;
			String codigoProducto = locProducto.getCodigo();
			int    cantidad       = locProducto.getCantidad();
			double precioBase     = locProducto.getPrecio() * cantidad;
			double impuesto       = calcularImpuesto(precioBase) * cantidad;
			double precioVenta    = precioBase + impuesto;
			double comision       = precioBase * cantidad * 0.05;
			double ganancia       = precioBase - comision;
			tablaInventario.append(String.format("%-3d | %-10s | %-10d | $%-10.1f | $%-10.2f | $%-10.2f | $%-10.2f | $%-10.2f%n",
			                                     i,
			                                     codigoProducto,
			                                     cantidad,
			                                     precioBase,
			                                     precioVenta,
			                                     impuesto,
			                                     comision,
			                                     ganancia));
		}
		tablaInventario.append(generarTotalesTablaInventario());
		JOptionPane.showMessageDialog(null, tablaInventario.toString(), "Inventario", JOptionPane.PLAIN_MESSAGE);
	}

	private static String generarTotalesTablaInventario (){
		StringBuilder tablaInventario = new StringBuilder();
		calcularTotalCantidad();
		calcularTotalPrecioBase();
		calcularTotalPrecioVenta();
		calcularTotalImpuesto();
		calcularTotalComision();
		calcularTotalGanancia();
		tablaInventario.append(String.format("%-3s | %-10s | $%-10d | $%-10.1f | $%-10.2f | $%-10.2f | $%-10.2f | $%-10.2f%n",
		                                     "---",
		                                     "Totales",
		                                     totalCantidad,
		                                     totalPrecioBase,
		                                     totalPrecioVenta,
		                                     totalImpuesto,
		                                     totalComision,
		                                     totalGanancia));
		return tablaInventario.toString();
	}

	public static ArrayList <Producto> getProductos (){
		return productos;
	}
}