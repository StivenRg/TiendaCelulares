package co.edu.uptc.dto;

import co.edu.uptc.modelo.Producto;
import co.edu.uptc.modelo.Vendedor;
import co.edu.uptc.modelo.Venta;

import java.util.ArrayList;

public class ReporteVentasDTO{
	private String               ID;
	private String               nombreVendedor;
	private double               totalComisiones;
	private long                 numeroCuentaBanco;
	private String               tipoCuentaBanco;
	private int                  celularesVendidos;
	private ArrayList <Producto> listaProductos;

	public ReporteVentasDTO (ArrayList <Producto> paramListaProductos){
		listaProductos = paramListaProductos;
	}

	/// Metodo encargado de generar la cabecera de la tabla de ventas por vendedor
	///
	/// @return String: Cabecera de la tabla de ventas por vendedor
	public String generarCabecerasTablaVentasPorVendedor (){
		StringBuilder tablaVentasPorVendedor = new StringBuilder();
		String        columna0               = "Tipo ID y #";
		String        columna1               = "Nombre";
		String        columna2               = "Total comision";
		String        columna3               = "# cuenta";
		String        columna4               = "Tipo cuenta";
		String        columna5               = "# ventas";
		tablaVentasPorVendedor.append(String.format("###|%-15s|%-15s|%-15s|%-16s|%-12s|%-4s%n", columna0, columna1, columna2, columna3, columna4, columna5));
		return tablaVentasPorVendedor.toString();
	}

	/// Metodo encargado de obtener la tabla de ventas por vendedor
	///
	/// @param vendedores: Lista de vendedores
	///
	/// @return String: Tabla de ventas por vendedor
	public String obtenerTablaVentas (ArrayList <Vendedor> vendedores){
		StringBuilder tablaVentasPorVendedor = new StringBuilder(generarCabecerasTablaVentasPorVendedor());
		int           i                      = 1;
		for (Vendedor locVendedor : vendedores){
			ID                = locVendedor.getTipoID() + " " + locVendedor.getNumeroID();
			nombreVendedor    = locVendedor.getNombre();
			totalComisiones   = calcularComisiones(locVendedor);
			numeroCuentaBanco = locVendedor.getNumeroCuenta();
			tipoCuentaBanco   = locVendedor.getTipoCuenta();
			celularesVendidos = calcularCelularesVendidos(locVendedor);
			tablaVentasPorVendedor.append(String.format("%3d|%-15s|%-15s|$%,14.1f|%16d|%-12s|%,4d%n", i, ID, nombreVendedor, totalComisiones, numeroCuentaBanco, tipoCuentaBanco, celularesVendidos));
			i++;
		}
		return tablaVentasPorVendedor.toString();
	}

	/// Metodo encargado de calcular el número de celulares vendidos por vendedor
	///
	/// @param paramVendedor: Vendedor
	///
	/// @return int: Número de celulares vendidos por vendedor
	private int calcularCelularesVendidos (Vendedor paramVendedor){
		celularesVendidos = 0;
		for (Venta locVenta : paramVendedor.getVentasVendedor()){
			celularesVendidos += locVenta.getCantidad();
		}
		return celularesVendidos;
	}

	/// Metodo encargado de calcular las comisiones por vendedor
	///
	/// @param paramVendedor: Vendedor
	///
	/// @return double: Comisiones por vendedor
	private double calcularComisiones (Vendedor paramVendedor){
		totalComisiones = 0;
		for (Venta locVenta : paramVendedor.getVentasVendedor()){
			Producto locProducto = obtenerProducto(locVenta.getCodigoProducto());
			if (locProducto == null) continue;
			totalComisiones += locProducto.getPrecio() * locVenta.getCantidad() * 0.05;
		}
		return totalComisiones;
	}

	/// Metodo encargado de obtener el producto de la lista de productos a partir del codigo
	///
	/// @param paramCodigoProducto: codigo del producto
	///
	/// @return Producto: Producto con el código indicado En caso de que no exista el producto, devuelve null
	private Producto obtenerProducto (String paramCodigoProducto){
		for (Producto locProducto : listaProductos){
			if (locProducto.getCodigo().equals(paramCodigoProducto)){
				return locProducto;
			}
		}
		return null;
	}
}