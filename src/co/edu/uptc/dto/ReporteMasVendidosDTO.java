package co.edu.uptc.dto;

import co.edu.uptc.modelo.Producto;
import co.edu.uptc.modelo.Venta;

import java.util.ArrayList;
import java.util.HashMap;

public class ReporteMasVendidosDTO{
	private HashMap <String, Integer>  marcaMasVendida;
	private HashMap <String, Integer>  lineaMasVendida;
	private ArrayList <Venta>          listaVentas;
	private HashMap <String, Producto> productos;

	public ReporteMasVendidosDTO (ArrayList <Venta> paramListaVentas, ArrayList <Producto> paramProductos){
		listaVentas = paramListaVentas;
		productos   = arrayToHashMapProductos(paramProductos);
	}

	private HashMap <String, Producto> arrayToHashMapProductos (ArrayList <Producto> listaProductos){
		HashMap <String, Producto> productos = new HashMap <>();
		for (Producto locProducto : listaProductos){
			productos.put(locProducto.getCodigo(), locProducto);
		}
		return productos;
	}

	private void calcularVentasPorMarca (){
		marcaMasVendida = new HashMap <>();
		for (Venta locVenta : listaVentas){

			Producto locProducto = obtenerProducto(locVenta.getCodigoProducto());
			if (locProducto == null || locVenta.getCantidad() < 1) continue;

			if (marcaMasVendida.containsKey(locProducto.getMarca())){
				int cantidadVentas = marcaMasVendida.get(locProducto.getMarca());
				cantidadVentas += locVenta.getCantidad();
				marcaMasVendida.put(locProducto.getMarca(), cantidadVentas);
				continue;
			}
			marcaMasVendida.put(locProducto.getMarca(), locVenta.getCantidad());
		}
	}

	private void calcularVentasPorLinea (){
		lineaMasVendida = new HashMap <>();
		for (Venta locVenta : listaVentas){

			Producto locProducto = obtenerProducto(locVenta.getCodigoProducto());
			if (locProducto == null || locVenta.getCantidad() < 1) continue;

			if (lineaMasVendida.containsKey(locProducto.getLinea())){
				int cantidad = lineaMasVendida.get(locProducto.getLinea());
				cantidad += locVenta.getCantidad();
				lineaMasVendida.put(locProducto.getLinea(), cantidad);
				continue;
			}
			lineaMasVendida.put(locProducto.getLinea(), locVenta.getCantidad());
		}
	}

	private Producto obtenerProducto (String paramCodigoProducto){
		return productos.get(paramCodigoProducto);
	}

	private ArrayList <Producto> obtenerMarcaMasVendida (){
		calcularVentasPorMarca();
		ArrayList <Producto> listaMarcaMasVendida = new ArrayList <>();
		for (String locMarca : marcaMasVendida.keySet()){

			Producto locProducto = obtenerProducto(locMarca);
			if (locProducto == null) continue;

			if (listaMarcaMasVendida.isEmpty()){
				listaMarcaMasVendida.add(locProducto);
			} else if (marcaMasVendida.get(locMarca) > listaMarcaMasVendida.getFirst().getCantidad()){
				listaMarcaMasVendida.clear();
				listaMarcaMasVendida.add(locProducto);
			} else if (marcaMasVendida.get(locMarca) == listaMarcaMasVendida.getFirst().getCantidad()){
				listaMarcaMasVendida.add(locProducto);
			} else{
				System.err.println("Se produjo un error operar la marca más vendida");
			}
		}
		return listaMarcaMasVendida;
	}

	private ArrayList <Producto> obtenerLineaMasVendida (){
		calcularVentasPorLinea();
		ArrayList <Producto> listaLineaMasVendida = new ArrayList <>();
		for (String locLinea : lineaMasVendida.keySet()){

			Producto locProducto = obtenerProducto(locLinea);
			if (locProducto == null) continue;

			if (listaLineaMasVendida.isEmpty()){
				listaLineaMasVendida.add(locProducto);
			} else if (lineaMasVendida.get(locLinea) > listaLineaMasVendida.getFirst().getCantidad()){
				listaLineaMasVendida.clear();
				listaLineaMasVendida.add(locProducto);
			} else if (lineaMasVendida.get(locLinea) == listaLineaMasVendida.getFirst().getCantidad()){
				listaLineaMasVendida.add(locProducto);
			}
		}
		return listaLineaMasVendida;
	}

	public String obtenerTablaMasVendidos (){
		ArrayList <Producto> listaMarcaMasVendida = obtenerMarcaMasVendida();
		ArrayList <Producto> listaLineaMasVendida = obtenerLineaMasVendida();

		StringBuilder tablaMasVendidos = new StringBuilder();
		String        columna0         = "Concepto";
		String        columna1         = "Valor";
		tablaMasVendidos.append(String.format("%-30s | %-15s%n", columna0, columna1));

		String fila1 = "Marca +Vendida";
		String fila2 = "Cantidad ventas Marca +Vendida";

		StringBuilder marcaMasVendida = new StringBuilder();

		if (listaMarcaMasVendida.size() == 1){
			marcaMasVendida = new StringBuilder(listaMarcaMasVendida.getFirst().getMarca());
		} else{
			for (Producto locProducto : listaMarcaMasVendida){
				marcaMasVendida.append(locProducto.getMarca());
				marcaMasVendida.append("\n");
			}
		}

		tablaMasVendidos.append(String.format("%-30s | %-15s%n", fila1, marcaMasVendida));
		tablaMasVendidos.append(String.format("%-30s | %-15s%n", fila2, listaMarcaMasVendida.getFirst().getCantidad()));

		String fila3 = "Linea +Vendida";
		String fila4 = "Cantidad ventas Línea +Vendida";

		StringBuilder lineaMasVendida = new StringBuilder();

		if (listaLineaMasVendida.size() == 1){
			lineaMasVendida = new StringBuilder(listaLineaMasVendida.getFirst().getLinea());
		} else{
			for (Producto locProducto : listaLineaMasVendida){
				lineaMasVendida.append(locProducto.getLinea());
				lineaMasVendida.append("\n");
			}
		}

		tablaMasVendidos.append(String.format("%-30s | %-15s%n", fila3, lineaMasVendida));
		tablaMasVendidos.append(String.format("%-30s | %-15s%n", fila4, listaLineaMasVendida.getFirst().getCantidad()));

		return tablaMasVendidos.toString();
	}
}