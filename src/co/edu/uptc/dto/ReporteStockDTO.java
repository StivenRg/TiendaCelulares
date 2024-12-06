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

	/// Metodo encargado de generar la cabecera de la tabla de Inventario
	///
	/// @return String: Cabecera de la tabla de Inventario
	public String[] obtenerCabecerasTablaInventario (){
		String columna0 = "# ID";
		String columna1 = "Codigo";
		String columna2 = "Cantidad";
		String columna3 = "Precio base";
		String columna4 = "Precio venta";
		String columna5 = "Impuestos";
		String columna6 = "Comisiones";
		String columna7 = "Ganancia";
		return new String[] {columna0, columna1, columna2, columna3, columna4, columna5, columna6, columna7};
	}

	/// Metodo encargado de obtener la tabla de Inventario
	///
	/// @param productos: Lista de productos
	///
	/// @return String: Tabla de Inventario
	public String obtenerDatosTablaInventario (ArrayList <Producto> productos){
		StringBuilder tablaInventario = new StringBuilder();
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
			double impuesto              = calcularImpuesto(locProducto.getPrecio() * porcentajeConGanancia) * cantidad;
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

	/// Metodo encargado de calcular el valor de los impuestos
	///
	/// @param paramPrecioConGanancia: Precio con ganancia
	///
	/// @return double: Valor de impuestos (5% o 19%)
	public double calcularImpuesto (double paramPrecioConGanancia){
		if (paramPrecioConGanancia > 600000){
			return paramPrecioConGanancia * 0.19;
		}
		return paramPrecioConGanancia * 0.05;
	}

	/// Metodo encargado de generar la cabecera de la tabla de Totales
	///
	/// @return String: Cabecera de la tabla de Totales
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