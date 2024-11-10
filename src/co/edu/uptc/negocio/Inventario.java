package co.edu.uptc.negocio;

import co.edu.uptc.modelo.Producto;

import java.util.ArrayList;

public class Inventario{
	private static       int                  totalCantidad;
	private static       double               totalPrecioBase;
	private static       double               totalPrecioVenta;
	private static       double               totalImpuesto;
	private static       double               totalComision;
	private static       double               totalGanancia;
	private static final ArrayList <Producto> productos = new ArrayList <>();

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
		productos.add(producto);
	}

	public static void descontarProducto (String paramCodigoProducto, int paramCantidad){
		for (Producto producto : productos){
			if (producto.getCodigo().equals(paramCodigoProducto)){
				producto.setCantidad(producto.getCantidad() - paramCantidad);
			}
		}
	}

	public static void calcularTotalCantidad (){
		for (Producto producto : productos){
			totalCantidad += producto.getCantidad();
		}
	}

	public static double calcularTotalPrecioBase (){
		for (Producto producto : productos){
			if (producto.getPrecio() > 0){
				totalPrecioBase += producto.getPrecio() * producto.getCantidad();
			}
		}
		return totalPrecioBase;
	}
}