package co.edu.uptc.modelo;

// Aunque se importe desde la GUI, no implica estar usando interfaz gráfica, es un log de registro

import co.edu.uptc.gui.Log;

import java.util.ArrayList;

public class Tienda{
	private ArrayList <Producto> productos;
	private ArrayList <Vendedor> vendedores;
	private ArrayList <Venta>    ventas;

	public Tienda (){
		productos  = new ArrayList <>();
		vendedores = new ArrayList <>();
		ventas     = new ArrayList <>();
	}

	/// Metodo encargado de agregar los productos a la lista de la tienda
	///
	/// @param paramDatos: Strin[] de Datos de los productos separados por \n
	public void agregarProductos (String[] paramDatos){
		for (String locLinea : paramDatos){
			String[] locDato = locLinea.strip().split("\\|");
			try{
				locDato[0] = locDato[0].strip().replaceAll("\\s+", "");
				locDato[1] = locDato[1].strip().replaceAll("\\s+", "");
				locDato[2] = locDato[2].strip().replaceAll("\\s+", "");
				locDato[3] = locDato[3].strip().replaceAll("\\s+", "");
				locDato[4] = locDato[4].strip().replaceAll("\\s+", "");

				Producto producto;
				try{
					producto = new Producto(locDato[0], locDato[1], locDato[2], Double.parseDouble(locDato[3]), Integer.parseInt(locDato[4]));
				} catch (NumberFormatException e){
					Log.registrar("Error al agregar producto: " + locLinea);
					continue;
				}
				if (! validarCamposProducto(producto)){
					continue;
				}

				Producto productoDuplicado = obtenerProducto(producto.getCodigo());
				if (productoDuplicado != null){
					productoDuplicado.setCantidad(productoDuplicado.getCantidad() + producto.getCantidad());
					continue;
				}
				productos.add(producto);
			} catch (ArrayIndexOutOfBoundsException e){
				Log.registrar("Error al agregar producto: " + locLinea);
			}
		}
	}

	/// Metodo encargado de descontar un producto de la lista de productos cuando se realiza una venta
	///
	/// @param paramCodigoProducto: String con el Código del producto
	/// @param paramCantidad: int con la cantidad de producto a descontar
	private void descontarProducto (String paramCodigoProducto, int paramCantidad){
		Producto producto = obtenerProducto(paramCodigoProducto);
		if (producto == null) return;
		producto.setCantidad(producto.getCantidad() - paramCantidad);
	}

	/// Metodo encargado de validar los datos de un producto
	///
	/// @param paramProducto: Objeto de tipo Producto
	///
	/// @return boolean: true si los datos del producto son válidos, false en caso contrario
	private boolean validarCamposProducto (Producto paramProducto){
		if (paramProducto == null) return false;
		if (paramProducto.getMarca().isBlank()){
			return false;
		}
		if (paramProducto.getLinea().isBlank()){
			return false;
		}
		if (paramProducto.getCodigo().isBlank()){
			return false;
		}
		if (paramProducto.getPrecio() < 0){
			return false;
		}
		if (paramProducto.getCantidad() < 1){
			return false;
		}
		return true;
	}

	/// Metodo encargado de obtener el producto de la lista de productos a partir del codigo
	///
	/// @param paramCodigoProducto: String con el código del producto
	///
	/// @return Producto: Objeto de tipo Producto. En caso de que no exista el producto, devuelve null
	private Producto obtenerProducto (String paramCodigoProducto){
		for (Producto locProducto : productos){
			if (paramCodigoProducto.equalsIgnoreCase(locProducto.getCodigo())){
				return locProducto;
			}
		}
		return null;
	}

	/// Metodo encargado de obtener la lista de productos
	///
	/// @return ArrayList <Producto>: ArrayList de tipo Producto con todos los productos
	public ArrayList <Producto> getProductos (){
		return productos;
	}

	/// Metodo encargado de obtener el vendedor de la lista de vendedores a partir del codigo
	///
	/// @param paramCodigoVendedor: String con el código del vendedor
	///
	/// @return Vendedor: Objeto de tipo Vendedor. En caso de que no exista el vendedor, devuelve null
	private Vendedor obtenerVendedor (String paramCodigoVendedor){
		for (Vendedor locVendedor : vendedores){
			if (paramCodigoVendedor.equalsIgnoreCase(locVendedor.getCodigoVendedor())){
				return locVendedor;
			}
		}
		return null;
	}

	/// Metodo encargado de agregar los vendedores a la lista de la tienda
	///
	/// @param paramDatos: String[] de Datos de los vendedores separados por \n
	public void agregarVendedor (String[] paramDatos){
		final String separadorDato = "\\|";
		for (String locLinea : paramDatos){
			String[] locDato = locLinea.strip().split(separadorDato);
			String   nombre, sNumeroTelefono, tipoID, sNumeroID, tipoCuenta, sNumeroCuenta;
			try{
				nombre          = locDato[0].strip();
				sNumeroTelefono = locDato[1].strip().replaceAll("\\s+", "");
				sNumeroID       = locDato[2].strip().replaceAll("\\s+", "");
				tipoID          = locDato[3].strip().replaceAll("\\s+", "");
				sNumeroCuenta   = locDato[4].strip().replaceAll("\\s+", "");
				tipoCuenta      = locDato[5].strip().replaceAll("\\s+", "");
			} catch (ArrayIndexOutOfBoundsException e){
				Log.registrar("Persona no válida: " + locLinea);
				continue;
			}

			try{
				long     numeroTelefono = Long.parseLong(sNumeroTelefono);
				long     numeroID       = Long.parseLong(sNumeroID);
				long     numeroCuenta   = Long.parseLong(sNumeroCuenta);
				Vendedor vendedor       = new Vendedor(nombre, numeroTelefono, numeroID, tipoID, numeroCuenta, tipoCuenta);
				if (validarVendedor(vendedor)){
					vendedores.add(vendedor);
				}
			} catch (NumberFormatException e){
				Log.registrar("Error en datos Vendedor: " + locLinea);
			}
		}
	}

	/// Metodo encargado de validar los datos de un vendedor
	///
	/// @param paramVendedor: Vendedor
	///
	/// @return boolean: true si los datos del vendedor son válidos, false en caso contrario
	private boolean validarVendedor (Vendedor paramVendedor){
		if (paramVendedor.getNumeroTelefono() < 3000000000L){
			return false;
		}
		if (paramVendedor.getNumeroCuenta() < 10000000000L){
			return false;
		}
		if (paramVendedor.getNumeroID() < 1000000){
			return false;
		}
		String tipoID = paramVendedor.getTipoID();
		if (! tipoID.equals("CC") && ! tipoID.equals("CE") && ! tipoID.equals("PA") && ! tipoID.equals("CD")){
			return false;
		}
		String tipoCuenta = paramVendedor.getTipoCuenta();
		if (! tipoCuenta.equalsIgnoreCase("Ahorros") && ! tipoCuenta.equalsIgnoreCase("Corriente")){
			return false;
		}
		return true;
	}

	/// Metodo encargado de obtener la lista de vendedores
	///
	/// @return ArrayList <Vendedor>: ArrayList de tipo Vendedor con todos los vendedores
	public ArrayList <Vendedor> getVendedores (){
		return vendedores;
	}

	/// Metodo encargado de agregar las ventas a la lista de la tienda
	///
	/// @param paramDatos: String[] de Datos de las ventas separados por \n
	public void agregarVenta (String[] paramDatos){
		final String separadorDato = "\\|";
		for (String locLinea : paramDatos){
			String[] locDato = locLinea.strip().split(separadorDato);
			String   codigoVendedor, codigoProducto, sCantidad;
			try{
				codigoVendedor = locDato[0].strip().replaceAll("\\s+", "");
				codigoProducto = locDato[1].strip().replaceAll("\\s+", "");
				sCantidad      = locDato[2].strip().replaceAll("\\s+", "");
			} catch (ArrayIndexOutOfBoundsException e){
				Log.registrar("Error al agregar venta: " + locLinea);
				continue;
			}

			try{
				int cantidad = Integer.parseInt(sCantidad);
				if (validarVenta(codigoVendedor, codigoProducto, cantidad)){
					descontarProducto(codigoProducto, cantidad);
					Venta venta = new Venta(codigoVendedor, codigoProducto, cantidad);
					agregarVentaVendedor(codigoVendedor, venta);
					ventas.add(venta);
				}
			} catch (NumberFormatException e){
				Log.registrar("Error al agregar venta: " + locLinea);
			}
		}
	}

	/// Metodo encargado de agregar una venta a un vendedor
	///
	/// @param paramCodigoVendedor: String con el Código del vendedor
	/// @param paramVenta: Objeto de tipo Venta con la venta
	private void agregarVentaVendedor (String paramCodigoVendedor, Venta paramVenta){
		Vendedor vendedor = obtenerVendedor(paramCodigoVendedor);
		if (vendedor == null){
			System.err.println("Vendedor no existe: " + paramCodigoVendedor);
			return;
		}
		String codigoProducto = paramVenta.getCodigoProducto();
		int    cantidad       = paramVenta.getCantidad();
		vendedor.agregarVenta(codigoProducto, cantidad);
	}

	/// Metodo encargado de validar las ventas
	///
	/// @param codigoVendedor: String con el Código del vendedor
	/// @param codigoProducto: String con el Código del producto
	/// @param cantidad: int con la cantidad de producto
	///
	/// @return boolean: true si las ventas son válidas, false en caso contrario
	private boolean validarVenta (String codigoVendedor, String codigoProducto, int cantidad){
		if (cantidad < 1){
			return false;
		}

		Producto producto = obtenerProducto(codigoProducto);
		Vendedor vendedor = obtenerVendedor(codigoVendedor);

		if (producto == null || vendedor == null) return false;

		if (producto.getCantidad() < cantidad){
			System.err.printf("Producto con stock insuficiente: %s : %d%n", codigoProducto, cantidad);
			return false;
		}
		return true;
	}

	/// Metodo encargado de obtener la lista de ventas
	///
	/// @return ArrayList <Venta>: ArrayList de tipo Venta con todas las ventas
	public ArrayList <Venta> getVentas (){
		return ventas;
	}
}