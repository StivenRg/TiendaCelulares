package co.edu.uptc.negocio;

import co.edu.uptc.modelo.Producto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Inventario{
	private static final ArrayList <Producto> productos        = new ArrayList <>();
	private static       int                  totalCantidad    = 0;
	private static       double               totalPrecioBase  = 0;
	private static       double               totalPrecioVenta = 0;
	private static       double               totalImpuesto    = 0;
	private static       double               totalComision    = 0;
	private static       double               totalGanancia    = 0;

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
			double impuestos = 0;
			if (producto.getPrecio() > 600000){
				impuestos = producto.getPrecio() * 19 / 100;
			} else{
				impuestos = producto.getPrecio() * 5 / 100;
			}
			totalImpuesto += impuestos * producto.getCantidad();
		}
	}

	private static void calcularTotalComision (){
		totalComision = 0;
		for (Producto producto : productos){
			double precioBase = producto.getPrecio() * producto.getCantidad();
			totalComision += precioBase * 0.05;
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
		String columna0 = "Codigo";
		String columna1 = "Cantidad";
		String columna2 = "Precio base";
		String columna3 = "Precio venta";
		String columna4 = "Impuestos";
		String columna5 = "Comisiones";
		String columna6 = "Ganancia";
		return String.format("###| %-18s | %-18s | %-18s  | %-18s  | %-18s  | %-18s  | %-18s %n", columna0, columna1, columna2, columna3, columna4, columna5, columna6);
	}

	private static String obtenerTablaInventario (){
		StringBuilder tablaInventario = new StringBuilder(generarCabecerasTablaInventario());
		int           i               = 1;
		for (Producto locProducto : productos){
			String codigoProducto = locProducto.getCodigo();
			int    cantidad       = locProducto.getCantidad();
			double precioBase     = locProducto.getPrecio() * cantidad;
			double impuesto       = calcularImpuesto(locProducto.getPrecio()) * cantidad;
			double precioVenta    = precioBase + impuesto;
			double comision       = precioBase * 0.05;
			double ganancia       = precioBase - comision;

			tablaInventario.append(String.format("%3d| %-18s | %,-18d | $%,18.1f | $%,18.1f | $%,18.1f | $%,18.1f | $%,18.1f%n",
			                                     i,
			                                     codigoProducto,
			                                     cantidad,
			                                     precioBase,
			                                     precioVenta,
			                                     impuesto,
			                                     comision,
			                                     ganancia));
			i++;
		}
		tablaInventario.append(generarTotalesTablaInventario());
		return tablaInventario.toString();
	}

	private static String generarTotalesTablaInventario (){
		StringBuilder tablaInventario = new StringBuilder();
		calcularTotalCantidad();
		calcularTotalPrecioBase();
		calcularTotalImpuesto();
		calcularTotalPrecioVenta();
		calcularTotalComision();
		calcularTotalGanancia();
		tablaInventario.append(String.format("%3s| %-18s | %,-18d | $%,18.1f | $%,18.1f | $%,18.1f | $%,18.1f | $%,18.1f%n",
		                                     "-",
		                                     "Totales",
		                                     totalCantidad,
		                                     totalPrecioBase,
		                                     totalPrecioVenta,
		                                     totalImpuesto,
		                                     totalComision,
		                                     totalGanancia));
		return tablaInventario.toString();
	}

	//Este metodo fue generado con IA, puesto que deseaba que la tabla se viera bien alineada.
	public static void mostrarTablaInventario (){
		JTextArea textArea = new JTextArea(obtenerTablaInventario());
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textArea.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(textArea);

		JOptionPane.showMessageDialog(null, scrollPane, "Inventario", JOptionPane.PLAIN_MESSAGE);
	}

	public static ArrayList <Producto> getProductos (){
		return productos;
	}
}