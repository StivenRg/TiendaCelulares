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
			final double porcentajeConGanancia = 1.25;
			final double porcentajeComision    = 0.05;

			String codigoProducto = locProducto.getCodigo();
			int    cantidad       = locProducto.getCantidad();
			double invertido      = locProducto.getPrecio() * cantidad;

			double precioBaseConGanancia = locProducto.getPrecio() * porcentajeConGanancia * cantidad;
			double impuesto              = calcularPorcentajeImpuesto(locProducto.getPrecio() * porcentajeConGanancia) * cantidad;
			double precioVenta           = (precioBaseConGanancia + impuesto);
			double comision              = locProducto.getPrecio() * porcentajeComision * cantidad;
			double ganancia              = precioBaseConGanancia - invertido - comision;

			totalCelulares += cantidad;
			totalPrecioBase += invertido;
			totalPrecioVenta += precioVenta;
			totalImpuesto += impuesto;
			totalComision += comision;
			totalGanancia += ganancia;

			tablaInventario.append(String.format("%3d| %-18s | %,-18d | $%,18.1f | $%,18.1f | $%,18.1f | $%,18.1f | $%,18.1f%n",
			                                     i,
			                                     codigoProducto,
			                                     cantidad,
			                                     invertido,
			                                     precioVenta,
			                                     impuesto,
			                                     comision,
			                                     ganancia));
			i++;
		}
		tablaInventario.append(generarTotalesTablaInventario());
		return tablaInventario.toString();
	}

	public double calcularPorcentajeImpuesto (double paramPrecioConGanancia){
		if (paramPrecioConGanancia > 600000){
			return paramPrecioConGanancia * 0.19;
		}
		return paramPrecioConGanancia * 0.05;
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