package co.edu.uptc.dto;

import co.edu.uptc.modelo.Producto;
import co.edu.uptc.modelo.Venta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReporteMasVendidosDTO{
	private HashMap <String, Double>  marcasMasVendidasPorValor;
	private HashMap <String, Double>  lineasMasVendidasPorValor;
	private HashMap <String, Integer> marcasMasVendidasPorCantidad;
	private HashMap <String, Integer> lineasMasVendidasPorCantidad;
	private ArrayList <Venta>         listaVentas;
	private ArrayList <Producto>      listaProductos;

	public ReporteMasVendidosDTO (ArrayList <Venta> paramListaVentas, ArrayList <Producto> paramProductos){
		listaVentas                  = paramListaVentas;
		listaProductos               = paramProductos;
		marcasMasVendidasPorValor    = new HashMap <>();
		lineasMasVendidasPorValor    = new HashMap <>();
		marcasMasVendidasPorCantidad = new HashMap <>();
		lineasMasVendidasPorCantidad = new HashMap <>();
	}

	/// Metodo encargado de obtener el producto de la lista de productos a partir del codigo
	///
	/// @param paramCodigoProducto: Código del producto
	///
	/// @return Producto: Producto con el código indicado En caso de que no exista el producto, devuelve null
	private Producto obtenerProducto (String paramCodigoProducto){
		Producto producto = null;
		for (Producto locProducto : listaProductos){
			if (locProducto.getCodigo().equals(paramCodigoProducto)){
				producto = locProducto;
			}
		}
		return producto;
	}

	/// Metodo encargado de almacenar las marcas de los productos vendidos por valor
	private void almacenarMarcasMasVendidasPorValor (){
		for (Venta locVenta : listaVentas){
			String   locCodigoProducto = locVenta.getCodigoProducto();
			Producto locProducto       = obtenerProducto(locCodigoProducto);
			if (locProducto == null) continue;
			String locMarca = locProducto.getMarca();
			double valor;
			if (marcasMasVendidasPorValor.containsKey(locMarca)){
				valor = marcasMasVendidasPorValor.get(locMarca);
				valor += locVenta.getCantidad() * obtenerPrecioVenta(locProducto.getPrecio());
				marcasMasVendidasPorValor.put(locMarca, valor);
				continue;
			}
			valor = locVenta.getCantidad() * obtenerPrecioVenta(locProducto.getPrecio());
			marcasMasVendidasPorValor.put(locMarca, valor);
		}
	}

	/// Metodo encargado de almacenar las líneas de los productos vendidos por valor
	private void almacenarLineasMasVendidasPorValor (){
		for (Venta locVenta : listaVentas){
			String   locCodigoProducto = locVenta.getCodigoProducto();
			Producto locProducto       = obtenerProducto(locCodigoProducto);
			if (locProducto == null) continue;
			String locLinea = locProducto.getLinea();
			double valor;
			if (lineasMasVendidasPorValor.containsKey(locLinea)){
				valor = lineasMasVendidasPorValor.get(locLinea);
				valor += locVenta.getCantidad() * obtenerPrecioVenta(locProducto.getPrecio());
				lineasMasVendidasPorValor.put(locLinea, valor);
				continue;
			}
			valor = locVenta.getCantidad() * obtenerPrecioVenta(locProducto.getPrecio());
			lineasMasVendidasPorValor.put(locLinea, valor);
		}
	}

	/// Metodo encargado de almacenar las marcas de los productos vendidos por cantidad
	private void almacenarMarcasMasVendidasPorCantidad (){
		for (Venta locVenta : listaVentas){
			String   locCodigoProducto = locVenta.getCodigoProducto();
			Producto locProducto       = obtenerProducto(locCodigoProducto);
			if (locProducto == null) continue;
			String locMarca = locProducto.getMarca();
			int    cantidad;
			if (marcasMasVendidasPorCantidad.containsKey(locMarca)){
				cantidad = marcasMasVendidasPorCantidad.get(locMarca);
				cantidad += locVenta.getCantidad();
				marcasMasVendidasPorCantidad.put(locMarca, cantidad);
				continue;
			}
			cantidad = locVenta.getCantidad();
			marcasMasVendidasPorCantidad.put(locMarca, cantidad);
		}
	}

	/// Metodo encargado de almacenar las líneas de los productos vendidos por cantidad
	private void almacenarLineasMasVendidasPorCantidad (){
		for (Venta locVenta : listaVentas){
			String   locCodigoProducto = locVenta.getCodigoProducto();
			Producto locProducto       = obtenerProducto(locCodigoProducto);
			if (locProducto == null) continue;
			String locLinea = locProducto.getLinea();
			int    cantidad;
			if (lineasMasVendidasPorCantidad.containsKey(locLinea)){
				cantidad = lineasMasVendidasPorCantidad.get(locLinea);
				cantidad += locVenta.getCantidad();
				lineasMasVendidasPorCantidad.put(locLinea, cantidad);
				continue;
			}
			cantidad = locVenta.getCantidad();
			lineasMasVendidasPorCantidad.put(locLinea, cantidad);
		}
	}

	/// Metodo encargado de obtener el precio de la venta
	///
	/// @param precioBase: Precio base
	///
	/// @return double: Precio de la venta ((Precio Base + %Ganancia) + %Impuesto)
	private double obtenerPrecioVenta (double precioBase){
		double precioConGanancia = precioBase * 1.25;
		if (precioConGanancia > 600000){
			return precioConGanancia * 1.19;
		} else if (precioConGanancia > 0){
			return precioConGanancia * 1.05;
		} else{
			return 0;
		}
	}

	/// Metodo encargado de obtener los datos de las marcas de los productos vendidos por valor
	///
	/// @return String[]: Datos de las marcas de los productos vendidos por valor (Marca, Valor Marca).
	///
	/// Solo retorna los datos de las marcas de los productos vendidos por valor que tengan un valor mayor a 0.
	///
	/// Solo retorna el nombre de la marca en la posición 0 y el valor en la posición 1
	private String[] obtenerMarcaMasVendidaPorValor (){
		almacenarMarcasMasVendidasPorValor();
		String[] marcaMasVendidaValor = {"Marca", "$Valor Marca"};
		double   valorMaximo          = 0;
		for (Map.Entry <String, Double> keyValue : marcasMasVendidasPorValor.entrySet()){
			if (keyValue.getValue() > valorMaximo){
				valorMaximo = keyValue.getValue();

				marcaMasVendidaValor[0] = keyValue.getKey();
				marcaMasVendidaValor[1] = keyValue.getValue().toString();
			}
		}
		return marcaMasVendidaValor;
	}

	/// Metodo encargado de obtener los datos de las líneas de los productos vendidos por valor
	///
	/// @return String[]: Datos de las líneas de los productos vendidos por valor (Línea, Valor Línea).
	///
	/// Solo retorna los datos de las líneas de los productos vendidos por valor que tengan un valor mayor a 0.
	///
	/// Solo retorna el nombre de la línea en la posición 0 y el valor en la posición 1
	private String[] obtenerLineaMasVendidaPorValor (){
		almacenarLineasMasVendidasPorValor();
		String[] lineaMasVendidaValor = {"Linea", "$Valor Linea"};
		double   valorMaximo          = 0;
		for (Map.Entry <String, Double> keyValue : lineasMasVendidasPorValor.entrySet()){
			if (keyValue.getValue() > valorMaximo){
				valorMaximo = keyValue.getValue();

				lineaMasVendidaValor[0] = keyValue.getKey();
				lineaMasVendidaValor[1] = keyValue.getValue().toString();
			}
		}
		return lineaMasVendidaValor;
	}

	/// Metodo encargado de obtener los datos de las marcas de los productos vendidos por cantidad
	///
	/// @return String[]: Datos de las marcas de los productos vendidos por cantidad (Marca, Cantidad Marca).
	///
	/// Solo retorna los datos de las marcas de los productos vendidos por cantidad que tengan un valor mayor a 0.
	///
	/// Solo retorna el nombre de la marca en la posición 0 y el valor en la posición 1
	private String[] obtenerMarcaMasVendidaPorCantidad (){
		almacenarMarcasMasVendidasPorCantidad();
		String[] marcaMasVendidaCantidad = {"Marca", "$Cantidad Marca"};
		double   cantidadMaxima          = 0;
		for (Map.Entry <String, Integer> keyValue : marcasMasVendidasPorCantidad.entrySet()){
			if (keyValue.getValue() > cantidadMaxima){
				cantidadMaxima = keyValue.getValue();

				marcaMasVendidaCantidad[0] = keyValue.getKey();
				marcaMasVendidaCantidad[1] = keyValue.getValue().toString();
			}
		}
		return marcaMasVendidaCantidad;
	}

	/// Metodo encargado de obtener los datos de las líneas de los productos vendidos por cantidad
	///
	/// @return String[]: Datos de las líneas de los productos vendidos por cantidad (Línea, Cantidad Línea).
	///
	/// Solo retorna los datos de las líneas de los productos vendidos por cantidad que tengan un valor mayor a 0.
	///
	/// Solo retorna el nombre de la línea en la posición 0 y el valor en la posición 1
	private String[] obtenerLineaMasVendidaPorCantidad (){
		almacenarLineasMasVendidasPorCantidad();
		String[] lineaMasVendidaCantidad = {"Linea", "$Cantidad Linea"};
		double   cantidadMaxima          = 0;
		for (Map.Entry <String, Integer> keyValue : lineasMasVendidasPorCantidad.entrySet()){
			if (keyValue.getValue() > cantidadMaxima){
				cantidadMaxima = keyValue.getValue();

				lineaMasVendidaCantidad[0] = keyValue.getKey();
				lineaMasVendidaCantidad[1] = keyValue.getValue().toString();
			}
		}
		return lineaMasVendidaCantidad;
	}

	/// Metodo encargado de obtener la tabla de los productos vendidos por valor
	///
	/// @return String: Tabla de los productos vendidos por valor
	public String obtenerTablaMasVendidosValor (){
		String        columna1 = "Concepto";
		String        columna2 = "Valor";
		StringBuilder tabla    = new StringBuilder(String.format("%-16s | %-16s%n", columna1, columna2));
		String        fila1    = "Marca";
		String        fila2    = "Ventas Marca";
		String        fila3    = "Linea";
		String        fila4    = "Ventas Linea";

		String[] marca = obtenerMarcaMasVendidaPorValor();
		String[] linea = obtenerLineaMasVendidaPorValor();

		double valorMarca = 0;
		double valorLinea = 0;

		try{
			valorMarca = Double.parseDouble(marca[1]);
			valorLinea = Double.parseDouble(linea[1]);
		} catch (NumberFormatException e){
			System.err.println("Ocurrió un error al procesar: " + e.getMessage());
		}

		tabla.append(String.format("%-16s | %16s%n", fila1, marca[0]));
		tabla.append(String.format("%-16s | %,16.1f%n", fila2, valorMarca));
		tabla.append(String.format("%-16s | %16s%n", fila3, linea[0]));
		tabla.append(String.format("%-16s | %,16.1f%n", fila4, valorLinea));

		return tabla.toString();
	}

	/// Metodo encargado de obtener la tabla de los productos vendidos por cantidad
	///
	/// @return String: Tabla de los productos vendidos por cantidad
	public String obtenerTablaMasVendidosCantidad (){
		String        columna1 = "Concepto";
		String        columna2 = "Cantidad";
		StringBuilder tabla    = new StringBuilder(String.format("%-16s | %-16s%n", columna1, columna2));
		String        fila1    = "Marca";
		String        fila2    = "Ventas Marca";
		String        fila3    = "Linea";
		String        fila4    = "Ventas Linea";

		String[] marca = obtenerMarcaMasVendidaPorCantidad();
		String[] linea = obtenerLineaMasVendidaPorCantidad();

		int cantidadMarca = 0;
		int cantidadLinea = 0;

		try{
			cantidadMarca = Integer.parseInt(marca[1]);
			cantidadLinea = Integer.parseInt(linea[1]);
		} catch (NumberFormatException e){
			System.err.println("Ocurrió un error al procesar: " + e.getMessage());
		}

		tabla.append(String.format("%-16s | %16s%n", fila1, marca[0]));
		tabla.append(String.format("%-16s | %,16d%n", fila2, cantidadMarca));
		tabla.append(String.format("%-16s | %16s%n", fila3, linea[0]));
		tabla.append(String.format("%-16s | %,16d%n", fila4, cantidadLinea));

		return tabla.toString();
	}
}