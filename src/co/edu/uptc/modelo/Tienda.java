package co.edu.uptc.modelo;

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

	public void agregarProductos (String[] paramDatos){
		for (String locLinea : paramDatos){
			String[] locDato = locLinea.strip().split("\\|");
			try{
				locDato[0] = locDato[0].strip();
				locDato[1] = locDato[1].strip();
				locDato[2] = locDato[2].strip();
				locDato[3] = locDato[3].strip();
				locDato[4] = locDato[4].strip();
				Producto producto = new Producto(locDato[0], locDato[1], locDato[2], Double.parseDouble(locDato[3]), Integer.parseInt(locDato[4]));
				if (! validarCamposProducto(producto)){
					continue;
				}

				Producto productoDuplicado = validarProductoExistente(producto.getCodigo());
				if (productoDuplicado != null){
					productoDuplicado.setCantidad(productoDuplicado.getCantidad() + producto.getCantidad());
					continue;
				}
				productos.add(producto);
			} catch (ArrayIndexOutOfBoundsException e){
				System.err.println("Error en el formato de inventario: " + locLinea);
			}
		}
	}

	private void descontarProducto (String paramCodigoProducto, int paramCantidad){
		Producto producto = validarProductoExistente(paramCodigoProducto);
		if (producto == null){
			System.err.println("Producto no existe: " + paramCodigoProducto);
			return;
		}
		if (producto.getCantidad() < paramCantidad){
			System.err.println("Producto con stock insuficiente: " + paramCodigoProducto);
			return;
		}
		producto.setCantidad(producto.getCantidad() - paramCantidad);
	}

	private boolean validarCamposProducto (Producto paramProducto){
		if (paramProducto.getMarca().isBlank()){
			return false;
		}
		if (paramProducto.getLinea().isBlank()){
			return false;
		}
		if (paramProducto.getCodigo().isBlank()){
			return false;
		}
		if (paramProducto.getPrecio() < 200000){
			return false;
		}
		if (paramProducto.getCantidad() < 1){
			return false;
		}
		return true;
	}

	private Producto validarProductoExistente (String paramCodigoProducto){
		for (Producto locProducto : productos){
			if (paramCodigoProducto.equalsIgnoreCase(locProducto.getCodigo())){
				return locProducto;
			}
		}
		return null;
	}

	public ArrayList <Producto> getProductos (){
		return productos;
	}

	public void agregarVenta (String[] paramDatos){
		final String separadorDato = "\\|";
		for (String locLinea : paramDatos){
			String[] locDato = locLinea.strip().split(separadorDato);
			String   codigoVendedor, codigoProducto, sCantidad;
			try{
				codigoVendedor = locDato[0].strip();
				codigoProducto = locDato[1].strip();
				sCantidad      = locDato[2].strip();
			} catch (ArrayIndexOutOfBoundsException e){
				System.err.println("Venta no válida: " + locLinea);
				continue;
			}

			try{
				int   cantidad = Integer.parseInt(sCantidad);
				Venta venta    = new Venta(codigoVendedor, codigoProducto, cantidad);
				if (validarVenta(cantidad, codigoProducto)){
					descontarProducto(codigoProducto, cantidad);
					agregarVentaVendedor(codigoVendedor, venta);
					ventas.add(venta);
				}
			} catch (NumberFormatException e){
				System.err.println("Error en venta: " + locLinea);
			}
		}
	}

	private void agregarVentaVendedor (String paramCodigoVendedor, Venta paramVenta){
		Vendedor vendedor = validarVendedorExistente(paramCodigoVendedor);
		if (vendedor == null){
			System.err.println("Vendedor no existe: " + paramCodigoVendedor);
			return;
		}
		String codigoProducto = paramVenta.getCodigoProducto();
		int    cantidad       = paramVenta.getCantidad();
		vendedor.agregarVenta(codigoProducto, cantidad);
	}

	private Vendedor validarVendedorExistente (String paramCodigoVendedor){
		for (Vendedor locVendedor : vendedores){
			if (paramCodigoVendedor.equalsIgnoreCase(locVendedor.getCodigoVendedor())){
				return locVendedor;
			}
		}
		return null;
	}

	private boolean validarVenta (int cantidad, String codigoProducto){
		if (cantidad < 1){
			return false;
		}
		Producto producto = validarProductoExistente(codigoProducto);
		if (producto == null){
			System.err.println("Producto no existe: " + codigoProducto);
			return false;
		}
		if (producto.getCantidad() < cantidad){
			System.err.println("Producto con stock insuficiente: " + codigoProducto);
			return false;
		}
		return true;
	}

	public ArrayList <Venta> getVentas (){
		return ventas;
	}

	public void agregarVendedor (String[] paramDatos){
		final String separadorDato = "\\|";
		for (String locLinea : paramDatos){
			String[] locDato = locLinea.strip().split(separadorDato);
			String   nombre, sNumeroTelefono, tipoID, sNumeroID, tipoCuenta, sNumeroCuenta;
			try{
				nombre          = locDato[0].strip();
				sNumeroTelefono = locDato[1].strip();
				sNumeroID       = locDato[2].strip();
				tipoID          = locDato[3].strip();
				sNumeroCuenta   = locDato[4].strip();
				tipoCuenta      = locDato[5].strip();
			} catch (ArrayIndexOutOfBoundsException e){
				System.err.println("Persona no válida: " + locLinea);
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
				System.err.println("Error en datos Vendedor: " + locLinea);
			}
		}
	}

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

	public ArrayList <Vendedor> getVendedores (){
		return vendedores;
	}
}