package co.edu.uptc.dto;

import co.edu.uptc.modelo.Producto;

import java.util.ArrayList;

public class ReporteStockDTO{
	private int    totalCelulares;
	private double totalPrecioBase;
	private double totalPrecioVenta;
	private double totalImpuesto;
	private double totalComision;
	private double totalGanancia;

	public ReporteStockDTO (){
	}

	public String generarCabecerasTablaInventario (){
		String columna0 = "Codigo";
		String columna1 = "Cantidad";
		String columna2 = "Precio base";
		String columna3 = "Precio venta";
		String columna4 = "Impuestos";
		String columna5 = "Comisiones";
		String columna6 = "Ganancia";
		return String.format("###| %-18s | %-18s | %-18s  | %-18s  | %-18s  | %-18s  | %-18s %n", columna0, columna1, columna2, columna3, columna4, columna5, columna6);
	}

	public String obtenerTablaInventario (ArrayList <Producto> productos){
		StringBuilder tablaInventario = new StringBuilder(generarCabecerasTablaInventario());
		int           i               = 1;

		totalCelulares  = 0;
		totalPrecioBase = totalPrecioVenta = totalImpuesto = totalComision = totalGanancia = 0;

		for (Producto locProducto : productos){
			String codigoProducto = locProducto.getCodigo();
			int    cantidad       = locProducto.getCantidad();
			double precioBase     = locProducto.getPrecio() * cantidad;
			double impuesto       = calcularPorcentajeImpuesto(locProducto.getPrecio()) * cantidad;
			double precioVenta    = precioBase + impuesto;
			double comision       = precioBase * 0.05;
			double ganancia       = precioBase * 0.35;

			totalCelulares += cantidad;
			totalPrecioBase += precioBase;
			totalPrecioVenta += precioVenta;
			totalImpuesto += impuesto;
			totalComision += comision;
			totalGanancia += ganancia;

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

	public double calcularPorcentajeImpuesto (double paramPrecio){
		if (paramPrecio > 600000){
			return paramPrecio * 19 / 100;
		}
		return paramPrecio * 5 / 100;
	}

	public String generarTotalesTablaInventario (){
		StringBuilder tablaInventario = new StringBuilder();
		tablaInventario.append(String.format("%3s| %-18s | %,-18d | $%,18.1f | $%,18.1f | $%,18.1f | $%,18.1f | $%,18.1f%n",
		                                     "-",
		                                     "Totales",
		                                     totalCelulares,
		                                     totalPrecioBase,
		                                     totalPrecioVenta,
		                                     totalImpuesto,
		                                     totalComision,
		                                     totalGanancia));
		return tablaInventario.toString();
	}
}