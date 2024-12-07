package co.edu.uptc.dto;

import co.edu.uptc.modelo.Producto;
import co.edu.uptc.modelo.Venta;

import java.util.ArrayList;

public class ReporteImpuestosDTO{
	double baseGrabableImpuesto5  = 0;
	double baseGrabableImpuesto19 = 0;
	double impuesto5              = 0;
	double impuesto19             = 0;
	double totalBaseGrabable      = 0;
	double totalImpuesto          = 0;
	private ArrayList <Venta>    listaVentas;
	private ArrayList <Producto> listaProductos;
	private ArrayList <Producto> listaProductosVendidos;

	public ReporteImpuestosDTO (ArrayList <Venta> paramListaVentas, ArrayList <Producto> paramListaProductos){
		listaVentas    = paramListaVentas;
		listaProductos = paramListaProductos;
	}

	/// Metodo encargado de agregar los productos vendidos provenientes de las ventas
	private void agregarProductos (){
		listaProductosVendidos = new ArrayList <>();
		for (Venta locVenta : listaVentas){
			String   locCodigoProducto = locVenta.getCodigoProducto();
			Producto locProducto       = obtenerProducto(locCodigoProducto);

			if (locProducto == null){
				Producto producto = obtenerDatosProducto(locCodigoProducto);
				if (producto == null) continue;
				producto.setCantidad(locVenta.getCantidad());
				listaProductosVendidos.add(producto);
			} else{
				locProducto.setCantidad(locProducto.getCantidad() + locVenta.getCantidad());
			}
		}
	}

	/// Metodo encargado de obtener el producto de la lista de productos Vendidos a partir del codigo
	///
	/// @param paramCodigoProducto: codigo del producto
	///
	/// @return Producto: Producto con el codigo indicado En caso de que no exista el producto, devuelve null
	private Producto obtenerProducto (String paramCodigoProducto){
		for (Producto locProducto : listaProductosVendidos){
			if (locProducto.getCodigo().equals(paramCodigoProducto)){
				return locProducto;
			}
		}
		return null;
	}

	/// Metodo encargado de obtener los datos del producto a partir del codigo
	///
	/// @param paramCodigoProducto: codigo del producto
	///
	/// @return Producto: Producto con el codigo indicado En caso de que no exista el producto, devuelve null
	private Producto obtenerDatosProducto (String paramCodigoProducto){
		for (Producto locProducto : listaProductos){
			if (locProducto.getCodigo().equals(paramCodigoProducto)){
				return locProducto;
			}
		}
		return null;
	}

	/// Metodo encargado de generar la cabecera de la tabla de impuestos
	///
	/// @return String: Cabecera de la tabla de impuestos
	public String[] obtenerCabecerasTablaImpuestos (){
		StringBuilder tablaImpuestos = new StringBuilder();
		String        columna0       = "Impuesto";
		String        columna1       = "Total bases grabables";
		String        columna2       = "Total impuesto";
		tablaImpuestos.append(String.format("%s|%s|%s%n", columna0, columna1, columna2));
		return tablaImpuestos.toString().split("\\|");
	}

	/// Metodo encargado de calcular los impuestos
	private void calcularImpuestos (){
		agregarProductos();
		final double precioBaseConGanancia = 1.35; //Modificacion de porcentaje de ganancia
		baseGrabableImpuesto5  = 0;
		baseGrabableImpuesto19 = 0;
		for (Producto locProducto : listaProductosVendidos){
			double precioConGanancia = locProducto.getPrecio() * precioBaseConGanancia;
			if (precioConGanancia > 600000){
				baseGrabableImpuesto19 += precioConGanancia * locProducto.getCantidad();
			} else{
				baseGrabableImpuesto5 += precioConGanancia * locProducto.getCantidad();
			}
		}
		impuesto5         = baseGrabableImpuesto5 * 0.05;
		impuesto19        = baseGrabableImpuesto19 * 0.19;
		totalBaseGrabable = baseGrabableImpuesto5 + baseGrabableImpuesto19;
		totalImpuesto     = impuesto5 + impuesto19;
	}

	/// Metodo encargado de obtener la tabla de impuestos
	///
	/// @return String: Tabla de impuestos
	public String obtenerTablaImpuestos (){
		StringBuilder tablaImpuestos = new StringBuilder();
		calcularImpuestos();

		String fila1 = "Impuesto 5%";
		String fila2 = "Impuesto 19%";
		tablaImpuestos.append(String.format("%s|$%,.1f|$%,.1f%n", fila1, baseGrabableImpuesto5, impuesto5));
		tablaImpuestos.append(String.format("%s|$%,.1f|$%,.1f%n", fila2, baseGrabableImpuesto19, impuesto19));

		String fila3 = "Sumatoria de Totales";
		tablaImpuestos.append(String.format("%s|$%,.1f|$%,.1f", fila3, totalBaseGrabable, totalImpuesto));

		return tablaImpuestos.toString();
	}
}