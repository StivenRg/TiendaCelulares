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
		productos   = arrayToHashMap(paramProductos);
	}

	private HashMap <String, Producto> arrayToHashMap (ArrayList <Producto> listaProductos){
		HashMap <String, Producto> productos = new HashMap <>();
		for (Producto locProducto : listaProductos){
			productos.put(locProducto.getCodigo(), locProducto);
		}
		return productos;
	}

	private void calcularVentasPorMarca (){
		marcaMasVendida = new HashMap <>();
		for (Venta locVenta : listaVentas){
			if (locVenta.getCantidad() <= 0) continue;

			Producto locProducto = obtenerProducto(locVenta.getCodigoProducto());
			if (locProducto == null) continue;

			if (marcaMasVendida.containsKey(locProducto.getMarca())){
				int cantidad = marcaMasVendida.get(locProducto.getMarca());
				cantidad += locVenta.getCantidad();
				marcaMasVendida.put(locProducto.getMarca(), cantidad);
				continue;
			}
			marcaMasVendida.put(locProducto.getMarca(), locVenta.getCantidad());
		}
	}

	private void calcularVentasPorLinea (){
		lineaMasVendida = new HashMap <>();
		for (Venta locVenta : listaVentas){
			if (locVenta.getCantidad() <= 0) continue;

			Producto locProducto = obtenerProducto(locVenta.getCodigoProducto());
			if (locProducto == null) continue;

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
		for (HashMap.Entry <String, Integer> locMarca : marcaMasVendida.entrySet()){
			if (locMarca.getValue() <= 0) continue;

			if (listaMarcaMasVendida.isEmpty()){
				listaMarcaMasVendida.add(obtenerProducto(locMarca.getKey()));
			} else if (locMarca.getValue() > listaMarcaMasVendida.getFirst().getCantidad()){
				listaMarcaMasVendida.clear();
				listaMarcaMasVendida.add(obtenerProducto(locMarca.getKey()));
			} else if (locMarca.getValue() == listaMarcaMasVendida.getFirst().getCantidad()){
				listaMarcaMasVendida.add(obtenerProducto(locMarca.getKey()));
			}
		}
		return listaMarcaMasVendida;
	}

	private ArrayList <Producto> obtenerLineaMasVendida (){
		calcularVentasPorLinea();
		ArrayList <Producto> listaLineaMasVendida = new ArrayList <>();
		for (HashMap.Entry <String, Integer> locLinea : lineaMasVendida.entrySet()){
			if (locLinea.getValue() <= 0) continue;

			if (listaLineaMasVendida.isEmpty()){
				listaLineaMasVendida.add(obtenerProducto(locLinea.getKey()));
			} else if (locLinea.getValue() > listaLineaMasVendida.getFirst().getCantidad()){
				listaLineaMasVendida.clear();
				listaLineaMasVendida.add(obtenerProducto(locLinea.getKey()));
			} else if (locLinea.getValue() == listaLineaMasVendida.getFirst().getCantidad()){
				listaLineaMasVendida.add(obtenerProducto(locLinea.getKey()));
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