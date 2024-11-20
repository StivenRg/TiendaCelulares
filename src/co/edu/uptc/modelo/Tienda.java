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

	public void agregarVenta (Venta venta){
		ventas.add(venta);
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
				vendedores.add(vendedor);
			} catch (NumberFormatException e){
				System.err.println("Error en dato numerico: " + e.getMessage());
			}
		}
	}

	public ArrayList <Vendedor> getVendedores (){
		return vendedores;
	}
}