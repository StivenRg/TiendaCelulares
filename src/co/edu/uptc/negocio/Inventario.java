package co.edu.uptc.negocio;

import co.edu.uptc.modelo.Producto;

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

	private static void calcularTotalCantidad (){
		totalCantidad = 0;
		for (Producto producto : productos){
			totalCantidad += producto.getCantidad();
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
				totalComision += producto.getPrecio() * producto.getCantidad() * 0.05;
			}
		}
	}

	private static void calcularTotalGanancia (){
		totalGanancia = totalPrecioBase - totalComision;
	}
}