package co.edu.uptc.negocio;

import co.edu.uptc.modelo.Producto;
import co.edu.uptc.modelo.Venta;

import java.util.ArrayList;

public class MasVendidos{
	private static String[] obtenerMarcaMasVendida (){
		String nombreMarcaMasVendida   = "No hay ventas";
		int    cantidadMarcaMasVendida = 0;

		ArrayList <Venta> listaVentas = VentasPorVendedor.getListaVentas();
		for (Venta locVenta : listaVentas){
			Producto locProducto = locVenta.getProducto();
			if (locProducto.getCantidad() <= 0) continue;
			if (locProducto.getMarca().equals(nombreMarcaMasVendida)){
				cantidadMarcaMasVendida += locProducto.getCantidad();
				continue;
			}
			if (locProducto.getCantidad() > cantidadMarcaMasVendida){
				nombreMarcaMasVendida   = locProducto.getMarca();
				cantidadMarcaMasVendida = locProducto.getCantidad();
			}
		}
		String[] marcaMasVendida = {nombreMarcaMasVendida, String.valueOf(cantidadMarcaMasVendida)};
		return marcaMasVendida;
	}
}